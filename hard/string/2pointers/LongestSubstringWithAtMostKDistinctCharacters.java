// 340
class Solution 
{
    public int lengthOfLongestSubstringKDistinct(String s, int k) 
	{
        int start = 0, end = 0;
		int maxLength = Integer.MIN_VALUE;
		int twoDistinctCharCounter = 0;
		int[] map = new int[256];

		while (end < s.length())
		{
			if (map[s.charAt(end++)]++ == 0) 
				twoDistinctCharCounter++;

			while (twoDistinctCharCounter > k)
				if (map[s.charAt(start++)]-- == 1)
					twoDistinctCharCounter--;

			maxLength = Math.max(maxLength, end - start);
		}

		return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }
}

