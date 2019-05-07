import java.util.*;

public class Day3 {
    
    // Day 3:
    // Given the root to a binary tree, implement serialize(root), which 
    // serializes the tree into a string, and deserialize(s), which deserializes
    // the string back into the tree.
    //
    // For example, given the following Node class
    //
    // class Node:
    //     def __init__(self, val, left=None, right=None):
    //         self.val = val
    //         self.left = left
    //         self.right = right
    //
    // The following test should pass:
    //       
    // node = Node('root', Node('left', Node('left.left')), Node('right'))
    // assert deserialize(serialize(node)).left.left.val == 'left.left'
    
    public static String serialize(Node root) {
        if (root == null) {
            return "#";
        }
        return root.val + " " + serialize(root.left) + " " + serialize(root.right);
    }

    public static Node deserialize(String data) {
        Scanner vals = new Scanner(data);

        return deserialize_helper(vals);
    }

    private static Node deserialize_helper(Scanner vals) {
        String val = vals.next();

        if (val.equals("#")) {
            return null;
        }

        Node root = new Node(Integer.parseInt(val));
        root.left = deserialize_helper(vals);
        root.right = deserialize_helper(vals);

        return root;
    }

    private static class Node {

        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}