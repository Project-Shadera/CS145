// Name: Drakin Woodell
// Date: 5/24/2024
// Class: CS145
// Assignment: Lab #7 - Binary Printing Tree

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class BinarySearchTree {
    private TreeNode root;

    public void add(int val) {
        root = addRecursive(root, val);
    }

    private TreeNode addRecursive(TreeNode current, int val) {
        if (current == null) {
            return new TreeNode(val);
        }

        if (val < current.val) {
            current.left = addRecursive(current.left, val);
        } else if (val > current.val) {
            current.right = addRecursive(current.right, val);
        } else {
            return current;
        }

        return current;
    }

    public void remove(int val) {
        root = removeRecursive(root, val);
    }

    private TreeNode removeRecursive(TreeNode current, int val) {
        if (current == null) {
            return null;
        }

        if (val == current.val) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            
            int smallestValue = findSmallestValue(current.right);
            current.val = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
            return current;
        }
        if (val < current.val) {
            current.left = removeRecursive(current.left, val);
            return current;
        }
        current.right = removeRecursive(current.right, val);
        return current;
    }

    private int findSmallestValue(TreeNode root) {
        return root.left == null ? root.val : findSmallestValue(root.left);
    }

    public boolean contains(int val) {
        return containsRecursive(root, val);
    }

    private boolean containsRecursive(TreeNode current, int val) {
        if (current == null) {
            return false;
        }
        if (val == current.val) {
            return true;
        }
        return val < current.val
                ? containsRecursive(current.left, val)
                : containsRecursive(current.right, val);
    }

    public void preOrderTraversal(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.val + " ");
            inOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal(TreeNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.val + " ");
        }
    }

    public void outputTree(int totalSpaces) {
        outputTreeRecursive(root, totalSpaces);
    }

    private void outputTreeRecursive(TreeNode node, int spaces) {
        if (node == null) {
            return;
        }
        outputTreeRecursive(node.right, spaces + 5);
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.println(node.val);
        outputTreeRecursive(node.left, spaces + 5);
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        System.out.println("Tree (vertically):");
        tree.outputTree(0);

        System.out.println("\nPre-order traversal:");
        tree.preOrderTraversal(tree.root);

        System.out.println("\nIn-order traversal:");
        tree.inOrderTraversal(tree.root);

        System.out.println("\nPost-order traversal:");
        tree.postOrderTraversal(tree.root);

        tree.remove(30);
        System.out.println("\nTree after removing 30 (vertically):");
        tree.outputTree(0);

        int searchVal = 40;
        System.out.println("\nTree contains " + searchVal + ": " + tree.contains(searchVal));
    }
}