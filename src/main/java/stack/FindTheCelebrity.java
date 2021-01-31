package stack;

import java.util.Stack;

/**
 * 277
 *
 * ======
 *
 * Task.
 *
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among
 * them, there may exist one celebrity. The definition of a celebrity is that
 * all the other n - 1 people know him/her but he/she does not know any of
 * them.
 *
 * Now you want to find out who the celebrity is or verify that there is not
 * one. The only thing you are allowed to do is to ask questions like: "Hi, A.
 * Do you know B?" to get information of whether A knows B. You need to find out
 * the celebrity (or verify there is not one) by asking as few questions as
 * possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) which tells you whether A
 * knows B. Implement a function int findCelebrity(n). There will be exactly one
 * celebrity if he/she is in the party. Return the celebrity's label if there is
 * a celebrity in the party. If there is no celebrity, return -1.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindTheCelebrity {
    /**
     * Check every two, add candidates to stack.
     */
    public static class Solution {
        public int findCelebrity(int n) {
            if (n <= 0)
                return -1;
            if (n == 1)
                return 0;

            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++)
                stack.push(i);

            while (stack.size() > 1) {
                int a = stack.pop();
                int b = stack.pop();

                if (knows(a, b)) stack.push(b);
                else stack.push(a);
            }

            int c = stack.pop();
            for (int i = 0; i < n; i++)
                if (i != c && (knows(c, i) || !knows(i, c)))
                    return -1;

            return c;
        }

        private boolean knows(int a, int b) {
            return false;
        }
    }
}
