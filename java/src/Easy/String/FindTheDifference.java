package Easy.String;

/**
 * 389
 Given two strings s and t which consist of only lowercase letters.
 String t is generated by random shuffling string s and then add one more letter at a random position.
 Find the letter that was added in t.
 */
public class FindTheDifference {
    class Solution {
        public char findTheDifference(String s, String t) {
            int x = 0;
            char[] a1 = t.toCharArray();
            char[] a2 = s.toCharArray();
            for (int i = 0; i < a2.length; i++) {
                x += a1[i]; x -= a2[i];
            }
            return (char) (x + a1[t.length() - 1]);
        }
    }

    class Solution2 {
        public char findTheDifference(String s, String t) {
            char xor = 0;
            for (char c : s.toCharArray()) xor ^= c;
            for (char c : t.toCharArray()) xor ^= c;
            return xor;
        }
    }
}
