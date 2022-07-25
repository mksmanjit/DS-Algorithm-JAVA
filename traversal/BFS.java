package traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Level order traversal of a tree is breadth first traversal for the tree.
 *
 * Time Complexity - O(n)
 * Space Complexity - O(n)
 */
public class BFS {
    public static void main(String[] args) {
        DFS tree = new DFS();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        List<Integer> bfsArray = bfs(tree.root);
        System.out.println("bfs " + bfsArray);
    }

    public static List<Integer> bfs(Node root) {
        List<Integer> bfsArray = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            bfsArray.add(node.key);
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return bfsArray;
    }
}