package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Huffman coding is a lossless data compression algorithm.
 * The idea is to assign variable-length codes to input characters, lengths of the assigned codes
 * are based on the frequencies of corresponding characters. The most frequent character gets the
 * smallest code and the least frequent character gets the largest code.
 *
 * The variable-length codes assigned to input characters are Prefix Codes,
 * means the codes (bit sequences) are assigned in such a way that the code assigned to one
 * character is not the prefix of code assigned to any other character. This is how Huffman Coding
 * makes sure that there is no ambiguity when decoding the generated bitstream.
 *
 * Note: We can use same algorithm for the Optimal merge Pattern - Optimal way to merge two files.
 * If we have two files of sizes m and n, the total computation time will be m+n.
 * Here, we use the greedy strategy by merging the two smallest size files among all the files present.
 *
 * Time Complexity: O(nlogn)
 * O(n) time the unique character and O(logn) time to extract min.
 */
public class HuffmanCodes {
    private static class Node {
        int frequency;
        Character letter;
        Node left;
        Node right;
        Node(int frequency, Character letter) {
            this.frequency = frequency;
            this.letter = letter;
        }
        public String toString() {
            String s = letter +"=" + frequency;
            if(left != null) {
                s = ",[left]=" + left.toString();
            }
            if(right != null) {
                s = ",[right]=" + left.toString();
            }
            return s;
        }
    }

    private static Map<Character,String> charToCodeMap = new HashMap<>();
    private static Map<String, Character> codeToCharMap = new HashMap<>();
    public static void main(String[] args) {
        String originalStr = "abababcabdabababcabdabababcabdabababcabdabababcabdababababababababababababababababababababaaaaaaaaaa";
        Queue<Node> priorityQueue = new PriorityQueue<>(10, (node1,node2) -> Integer.valueOf(node1.frequency).compareTo(node2.frequency));
        Node node = new Node(50, 'a');
        priorityQueue.offer(node);
        node = new Node(40, 'b');
        priorityQueue.offer(node);
        node = new Node(5, 'c');
        priorityQueue.offer(node);
        node = new Node(5, 'd');
        priorityQueue.offer(node); // O(n) for creating min heap.

        while (priorityQueue.size() > 1) { // O(n) for iterating over all the unique characters.
            Node minNode1 = priorityQueue.poll(); // O(logn)
            Node minNode2 = priorityQueue.poll(); // O(logn)
            Node newNode = new Node(minNode1.frequency + minNode2.frequency, ' ');
            newNode.right = minNode1.frequency > minNode2.frequency ? minNode1 : minNode2;
            newNode.left = minNode1.frequency > minNode2.frequency ? minNode2 : minNode1;
            priorityQueue.offer(newNode);
        }
        Node lastNode = priorityQueue.poll();
        findCharacterCodes(lastNode, "");
        System.out.println("Coding Value= " + charToCodeMap);
        System.out.println("Original String = " + originalStr);
        StringBuilder zippedStr = zipOriginalStr(originalStr);
        System.out.println("Zipped String="+ zippedStr.toString());

        StringBuilder unzippedStr = getUnzippedStr(zippedStr);
        System.out.println("UnZipped String=" + unzippedStr.toString());
        System.out.println("Is original and Unzipped String same " + originalStr.equals(unzippedStr.toString()));
    }

    private static StringBuilder getUnzippedStr(StringBuilder zippedStr) {
        String s = "";
        StringBuilder unzippedStr = new StringBuilder();
        for (int i = 0; i < zippedStr.length(); i++) {
            s += zippedStr.charAt(i);
            if (codeToCharMap.get(s) != null) {
                unzippedStr.append(codeToCharMap.get(s));
                s = "";
            }
        }
        return unzippedStr;
    }

    private static StringBuilder zipOriginalStr(String originalStr) {
        StringBuilder zippedStr = new StringBuilder();
        for(int i = 0; i < originalStr.length(); i++) {
            zippedStr.append(charToCodeMap.get(originalStr.charAt(i)));
        }
        return zippedStr;
    }

    private static void findCharacterCodes(Node node, String codes) {
        if(node.left == null && node.right == null) {
            charToCodeMap.put(node.letter, codes);
            codeToCharMap.put(codes, node.letter);
            return;
        }
        if(node.left != null) {
            findCharacterCodes(node.left, codes + "0");
        }
        if(node.right != null) {
            findCharacterCodes(node.right, codes + "1");
        }
    }
}
