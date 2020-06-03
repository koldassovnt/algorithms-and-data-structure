package data_structure;

public class ExpressionTree {
    class Node {
        private Node right;
        private Node left;
        private Character data;

        public Node(Node right, Node left, Character data) {
            this.right = right;
            this.left = left;
            this.data = data;
        }

        public Node() {
            right = null;
            left = null;
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

        public Character getData() {
            return data;
        }

        public void setData(Character data) {
            this.data = data;
        }
    }

    private Node root;

    public ExpressionTree() {
        this.root = new Node(null, null, null);
    }

    private int setRoot(String eq) {
        int id = -1;
        for (int i = eq.length() - 1; i >= 0; i--) {
            if (eq.charAt(i) == '+' || eq.charAt(i) == '-') {
                id = i;
                break;
            }
        }
        if (id < 0)
            for (int i = 0; i < eq.length(); i++) {
                if (eq.charAt(i) == '*' || eq.charAt(i) == '/') {
                    id = i;
                    break;
                }
            }

        return id;
    }

    private Node construct(String equation, Node node) {
        if (equation.length() == 1) {
            node.setData(equation.charAt(0));
            return node;
        }
        int idOfRoot = setRoot(equation);
        node.setData(equation.charAt(idOfRoot));

        String left = equation.substring(0, idOfRoot);
        String right = equation.substring((idOfRoot + 1));

        node.left = new Node(null, null, null);
        construct(left, node.left);

        node.right = new Node(null, null, null);
        construct(right, node.right);

        return node;
    }

    public Double solve(String equation) {
        return evaluate(construct(equation, root));
    }

    private Double evaluate(Node cur) {
        if (cur == null)
            return 0.0;

        if (cur.right == null && cur.left == null)
            return Double.parseDouble(String.valueOf(cur.data));

        Double left_val = evaluate(cur.left);
        Double right_val = evaluate(cur.right);

        if (cur.data == '+')
            return left_val + right_val;
        if (cur.data == '-')
            return left_val - right_val;
        if (cur.data == '*')
            return left_val * right_val;

        return left_val / right_val;
    }
}
