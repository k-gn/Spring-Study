package graph;

import java.util.*;

public class Graph01 {

    static class TreeNode {
        public int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int bfs(TreeNode root) {
        if(root == null) return 0;
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i<size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            count++;
        }
        return count;
    }

    public static int dfs(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        stack.push(root);
        countStack.push(1);

        int max = 0;
        while(!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            int count = countStack.pop();
            max = Math.max(max, count);

            if (treeNode.left != null) {
                stack.push(treeNode.left);
                countStack.push(count + 1);
            }
            if (treeNode.right != null) {
                stack.push(treeNode.right);
                countStack.push(count + 1);
            }
        }
        return max;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(8);
        root.left.left.left = new TreeNode(5);
        root.left.left.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(6);
        root.right = new TreeNode(11);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(13);


        System.out.println(dfs(root));
        System.out.println(bfs(root));
    }
}
