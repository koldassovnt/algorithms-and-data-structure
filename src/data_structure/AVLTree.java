package data_structure;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<T extends Comparable<T>> {
    class Node {
        private T data;
        private int balanceFactor;
        private int height;
        private Node left, right;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data) {
            this.data = data;
        }
    }

    private Node root;
    private int nodeCount = 0;

    public AVLTree() {
    }

    public boolean contains(T data) {
        return find(root, data);
    }

    private boolean find(Node node, T elem) {

        if (node == null) return false;

        if (elem.compareTo(node.data) > 0) return find(node.right, elem);

        else if (elem.compareTo(node.data) < 0) return find(node.left, elem);

        else return true;
    }

    public void insert(T data) {
        if (data == null) return;

        if (!find(root, data)) {
            root = insert(root, data);
            nodeCount++;
        }
    }

    private Node insert(Node node, T data) {
        if (node == null) return new Node(data);  // check point to stop recursion

        if (data.compareTo(node.data) > 0)
            node.right = insert(node.right, data);
        else
            node.left = insert(node.left, data);

        update(node);

        return balance(node);
    }

    private void update(Node node) {
        int lh = (node.left == null) ? -1 : node.left.height;  // lh = left height
        int rh = (node.right == null) ? -1 : node.right.height; // rh = right height

        node.height = 1 + Math.max(lh, rh);  // setting height

        node.balanceFactor = rh - lh;  // setting balance factor
    }

    private Node balance(Node node) {
        if (node.balanceFactor == -2) { // check that rh - lh = -2
            if (node.left.balanceFactor <= 0)
                return leftLeftCase(node);
            else
                return leftRightCase(node);
        } else if (node.balanceFactor == 2) { // check that rh - lh = 2
            if (node.right.balanceFactor >= 0)
                return rightRightCase(node);
            else
                return rightLeftCase(node);
        }

        return node;
    }

    private Node leftLeftCase(Node node) {
        return rightRotation(node);
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightRightCase(Node node) {
        return leftRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node);
        return leftRotation(node);
    }

    private Node rightRotation(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        update(node);
        update(newRoot);

        return newRoot;
    }

    private Node leftRotation(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        update(node);
        update(newRoot);

        return newRoot;
    }

    public void remove(T data) {
        if (data == null) return;

        if (find(root, data)) {
            root = remove(root, data);
            nodeCount--;
        }
    }

    private Node remove(Node node, T data) {
        if (node == null) return null;

        if (data.compareTo(node.data) < 0)
            node.left = remove(node.left, data);

        else if (data.compareTo(node.data) > 0)
            node.right = remove(node.right, data);

        else {
            if (node.left == null)
                return node.right;

            else if (node.right == null)
                return node.left;

            else {
                Node leastInRight = findLeastInRight(node.right);
                node.data = leastInRight.data;

                node.right = remove(node.right, leastInRight.data);
            }
        }
        update(node);

        return balance(node);
    }

    private Node findLeastInRight(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;

        return current;
    }

//    private Node findMaxInLeft(Node node) {
//        Node current = node;
//        while (current.right != null)
//            current = current.right;
//
//        return current;
//    }

    // printing level order
    public void print() {
        printLevelOrder(root);
    }

    private void printLevelOrder(Node root) {
        // Base Case
        if (root == null)
            return;

        // Create an empty queue for level order tarversal
        Queue<Node> q = new LinkedList<Node>();

        // Enqueue Root and initialize height
        q.add(root);

        while (true) {
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.size();
            if (nodeCount == 0)
                break;

            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while (nodeCount > 0) {
                Node node = q.peek();
                System.out.print(node.data + " ");
                q.remove();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
                nodeCount--;
            }
            System.out.println();
        }
    }
}
