package dp._2multidimensional;

/**
 * 1143
 */
public class LongestCommonSubsequence
{
	/**
	 * Subproblem:
	 *
	 * For each i and j - calculate ans[i][j] which is the lcs for s1[0..i] and
	 * s2[0..j].
	 *
	 * How the solution ends:
	 *
	 * 1. either chars s1[i] == s2[j], then ans[i-1][j-1] + 1
	 *
	 * 2. or they don't, then we take max of answer without s1[i], ans without
	 * s2[j], or without both.
	 */
	public static class Solution
	{
		public int longestCommonSubsequence(String s1, String s2)
		{
			int n = s1.length();
			int m = s2.length();
			int[][] ans = new int[n + 1][m + 1];

			for (int i = 1; i <= n; i++)
			{
				for (int j = 1; j <= m; j++)
				{
					if (s1.charAt(i - 1) == s2.charAt(j - 1))
						ans[i][j] = ans[i - 1][j - 1] + 1;
					else
						ans[i][j] = Math.max(ans[i - 1][j - 1], Math.max(ans[i][j - 1], ans[i - 1][j]));
				}
			}

			return ans[n][m];
		}
	}
}