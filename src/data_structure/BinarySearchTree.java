package data_structure;

public class BinarySearchTree<T extends Comparable<T>> {
    class Node {
        private T data;
        private Node right;
        private Node left;

        public Node() {
            right = null;
            left = null;
        }

        public Node(T data, Node right, Node left) {
            this.data = data;
            this.right = right;
            this.left = left;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }
    }

    private int elemSize;
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int size() {
        return elemSize;
    }

    public void insert(T elem) {
        if (find(elem))
            System.out.println("This element is already exist");
        else {
            root = add(root, elem);
            elemSize++;
        }
    }

    private Node add(Node node, T element) {
        if (node == null)
            node = new Node(element, null, null);
        else {
            if (element.compareTo(node.data) > 0)
                node.right = add(node.right, element);
            else
                node.left = add(node.left, element);
        }
        return node;
    }

    public void delete(T elem) {
        if (find(elem)) {
            root = remove(root, elem);
            elemSize--;
            return;
        }
        System.out.println("Element doesnt exist, try input again");
    }

    private Node remove(Node node, T element) {
        if (node == null) return null;

        if (element.compareTo(node.data) < 0)
            node.left = remove(node.left, element);

        else if (element.compareTo(node.data) > 0)
            node.right = remove(node.right, element);

        else {
            if (node.left == null) {
                Node rightChild = node.right;
                node.data = null;
                node = null;

                return rightChild;
            } else if (node.right == null) {
                Node leftChild = node.left;
                node.data = null;
                node = null;

                return leftChild;
            } else {
                Node leastInRight = findLeastInRight(node.right);
                node.data = leastInRight.data;

                node.right = remove(node.right, leastInRight.data);
            }
        }
        return node;
    }

    private Node findLeastInRight(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;

        return current;
    }

//    private Node findMaxInLeft(Node node){
//        Node current = node;
//        while (current.right != null)
//            current = current.right;
//
//        return current;
//    }

    public boolean find(T element) {
        return contains(root, element);
    }

    private boolean contains(Node node, T elem) {

        if (node == null) return false;

        if (elem.compareTo(node.data) > 0) return contains(node.right, elem);

        else if (elem.compareTo(node.data) < 0) return contains(node.left, elem);

        else return true;
    }

    public T smallest() {
        Node cur = root;
        while (cur.left != null)
            cur = cur.left;

        return cur.data;

    }

    public T largest() {
        Node cur = root;
        while (cur.right != null)
            cur = cur.right;

        return cur.data;
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null)
            return;
        print(node.left);
        System.out.printf("%s ", node.data);
        print(node.right);
    }
}
