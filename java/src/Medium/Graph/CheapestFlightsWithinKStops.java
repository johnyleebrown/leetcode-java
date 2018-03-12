package Medium.Graph;

import static Helpers.Helper.replaceBracets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 787
 * There are n cities connected by m flights. Each fight
 * starts from city u and arrives at v with a price w.
 * Now given all the cities and fights, together with
 * starting city src and the destination dst, your task
 * is to find the cheapest price from src to dst with up
 * to k stops. If there is no such route, output -1.
 */
public class CheapestFlightsWithinKStops {
    /**
     * DP
     * While the recurrence comes naturally - min(dp[i][to], dp[i - 1][from] + from_to_price)
     * the main problem was to deal with cells whose prev flights weren't analyzed yet.
     * To solve that, we will loop K + 1 times (number of edges between src and dst) to cover the worst case,
     * when the flights are in the wrong order
     *
     * Time complexity: O(K*M), M - dp.length
     * Space complexity: O(n^2)
     */
    public static class solution1 {
        public static int dp(int n, int[][] flights, int src, int dst, int K) {
            int INF = Integer.MAX_VALUE / 2;
            int[][] dp = new int[K + 2][n];
            for (int i = 0; i < dp.length; i++)
                Arrays.fill(dp[i], INF);
            dp[0][src] = 0;

            // loop K + 1 times to fill the path from src to dst
            for (int i = 1; i < dp.length; i++) {
                dp[i][src] = 0;
                for (int[] flight : flights)
                    dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
            }

            return dp[K + 1][dst] >= INF / 2 ? -1 : dp[K + 1][dst];
        }

        // space O(n) optimized
        public static int dpOptimized(int n, int[][] flights, int src, int dst, int K) {
            int INF = Integer.MAX_VALUE / 2;
            int[][] dp = new int[2][n];
            Arrays.fill(dp[0], INF);
            Arrays.fill(dp[1], INF);
            dp[0][src] = 0;
            dp[1][src] = 0;

            int x = 0;
            for (int i = 0; i <= K; i++) {
                for (int[] f : flights)
                    dp[1 - x][f[1]] = Math.min(dp[1 - x][f[1]], dp[x][f[0]] + f[2]);
                x = 1 - x;
            }

            return dp[(K + 1) % 2][dst] >= INF / 2 ? -1 : dp[(K + 1) % 2][dst];
        }
    }

    /**
     * Dijkstras
     * Find Shortest Path
     * Non-negative edge weight
     * Relaxation - update the cost of getting to the point if found a shorter path
     * distTo[w] <= distTo[v] + e.weight()
     */
    public static class solution2 {
        public static int solution2(int n, int[][] flights, int src, int dst, int K) {
            /*
              from : { to : cost }
              to :
            */
            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int[] weightedEdge : flights) {
                graph.put(weightedEdge[0], graph.getOrDefault(weightedEdge[0], new HashMap<>()));
                graph.put(weightedEdge[1], graph.getOrDefault(weightedEdge[1], new HashMap<>()));
                graph.get(weightedEdge[0]).put(weightedEdge[1], weightedEdge[2]);
            }

            /*
              pq : cost, vertex, stops (k + 1 - max)
            */
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
            pq.add(new int[]{0, src, K + 1});

            while (!pq.isEmpty()) {
                int[] minCostEntry = pq.remove();
                int cost = minCostEntry[0], from = minCostEntry[1], k = minCostEntry[2];

                if (from == dst)
                    return cost;

                if (k > 0) {
                    Map<Integer, Integer> adj = graph.get(from);
                    for (int to : adj.keySet())
                        pq.add(new int[]{cost + adj.get(to), to, k - 1});
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        String s1 = "[[4,1,1],[1,2,3],[0,3,2],[0,4,10],[3,1,1],[1,4,3]]";
        System.out.println(solution1.dp(5, replaceBracets(s1), 2, 1,1));
        System.out.println("=========");
        String s2 = "[[0,1,2],[1,2,1],[2,0,10]]";
        System.out.println(solution1.dp(3, replaceBracets(s2), 1, 2,1));
        System.out.println("=========");
        String s3 = "[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]";
        System.out.println(solution1.dpOptimized(4, replaceBracets(s3), 0, 3,1));
    }
}
