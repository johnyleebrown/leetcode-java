package graph.shortest_paths.bfs.tree;


import commons.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107
 */
public class BinaryTreeLevelOrderTraversalII
{

	// BFS
	public List<List<Integer>> levelOrderBottom(TreeNode root)
	{
		Queue<TreeNode> q = new LinkedList<>();
		List<List<Integer>> wrap = new LinkedList<>();
		if (root == null) return wrap;
		q.offer(root);
		while (!q.isEmpty())
		{
			int lvl = q.size();
			List<Integer> sub = new LinkedList<>();
			for (int i = 0; i < lvl; i++)
			{
				if (q.peek().left != null) q.offer(q.peek().left);
				if (q.peek().right != null) q.offer(q.peek().right);
				sub.add(q.poll().val);
			}
			wrap.add(0, sub);
		}
		return wrap;
	}

	// DFS
	public List<List<Integer>> levelOrderBottom2(TreeNode root)
	{
		List<List<Integer>> wrap = new LinkedList<>();
		levelMaker(wrap, root, 0);
		return wrap;
	}

	public void levelMaker(List<List<Integer>> list, TreeNode root, int level)
	{
		if (root == null) return;
		if (level >= list.size()) list.add(0, new LinkedList<>());
		levelMaker(list, root.left, level + 1);
		levelMaker(list, root.right, level + 1);
		list.get(list.size() - level - 1).add(root.val);
	}
}
