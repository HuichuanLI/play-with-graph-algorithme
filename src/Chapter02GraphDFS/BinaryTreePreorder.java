package Chapter02GraphDFS;

// leetcode 144
// Classic Non-Recursive algorithm for preorder traversal
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(h), h is the height of the tree


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreePreorder {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.empty()) {

            // 遍历栈顶元素
            TreeNode curNode = stack.pop();
            // 在这个例子中，遍历的方式是将该节点的值放入一个线性表res中
            res.add(curNode.val);

            // 之后，把当前节点的左右孩子压入栈中，等待后续的遍历
            if (curNode.right != null)
                stack.push(curNode.right);
            if (curNode.left != null)
                stack.push(curNode.left);
        }
        return res;
    }

}