package ru.mirea.lab30p1.task1;


import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    public static TreeNode reverseTree(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            TreeNode temp = root.left;
            root.left = reverseTree(root.right);
            root.right = reverseTree(temp);
            return root;
        }
    }

    public static boolean searchNode(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target) {
            return true;
        }
        return searchNode(root.left, target) || searchNode(root.right, target);
    }

    public static int getWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelWidth = queue.size();

            for (int i = 0; i < levelWidth; i++) {
                TreeNode current = queue.poll();

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            maxWidth = Math.max(maxWidth, levelWidth);
        }

        return maxWidth;
    }

    public static int getNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getNodeCount(root.left) + getNodeCount(root.right);
    }

    public static boolean compareTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 != null && root2 != null) {
            return (root1.val == root2.val) &&
                    compareTrees(root1.left, root2.left) &&
                    compareTrees(root1.right, root2.right);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int height = getHeight(root);
        TreeNode reversedTree = reverseTree(root);
        boolean isNodeFound = searchNode(root, 3);
        int width = getWidth(root);
        int nodeCount = getNodeCount(root);
        boolean areTreesEqual = compareTrees(root, reversedTree);

        System.out.println("Tree height: " + height);
        System.out.println("Tree width: " + width);
        System.out.println("Node count: " + nodeCount);
        System.out.println("Reversed tree: " + reversedTree.toString());
        System.out.println("Is 3rd node found: " + isNodeFound);
        System.out.println("Are trees equal: " + areTreesEqual);
    }
}

