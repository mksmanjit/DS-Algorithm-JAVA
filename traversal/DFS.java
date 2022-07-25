package traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Depth First Traversals:
 * (a) Inorder (Left, Root, Right) : 4 2 5 1 3
 * (b) Preorder (Root, Left, Right) : 1 2 4 5 3
 * (c) Postorder (Left, Right, Root) : 4 5 2 3 1
 *
 * Time Complexity - O(n)
 * Space Complexity - O(log n), equivalent to height of the tree.
 */
public class DFS {
    Node root;

    public static void main(String[] args) {
        DFS tree = new DFS();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        List<Integer> dfsArray = new ArrayList<>();
        preOrder(tree.root, dfsArray);
        System.out.println("PreOrder " + dfsArray);

        dfsArray = new ArrayList<>();
        inOrder(tree.root, dfsArray);
        System.out.println("InOrder " + dfsArray);

        dfsArray = new ArrayList<>();
        postOrder(tree.root, dfsArray);
        System.out.println("PostOrder " + dfsArray);
    }

    public static void preOrder(Node node, List<Integer> dfsArray) {
        if(node == null) return;
        dfsArray.add(node.key);
        preOrder(node.left, dfsArray);
        preOrder(node.right, dfsArray);
    }

    public static void inOrder(Node node, List<Integer> dfsArray) {
        if(node == null) return;
        inOrder(node.left, dfsArray);
        dfsArray.add(node.key);
        inOrder(node.right, dfsArray);
    }

    public static void postOrder(Node node, List<Integer> dfsArray) {
        if(node == null) return;
        postOrder(node.left, dfsArray);
        postOrder(node.right, dfsArray);
        dfsArray.add(node.key);
    }
}