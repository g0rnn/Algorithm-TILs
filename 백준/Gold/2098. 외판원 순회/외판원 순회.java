import java.util.*;
import java.io.*;

public class Main {
    static int[][] w;
    static int[][] dp;
    static int n;
    static final int INF = 16_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = new int[n][n];
        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) w[i][j] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }
        System.out.println(tsp(0, 1));
    }

    private static int tsp(int cur, int visited) {
        if (visited == (1 << n) - 1) {
            if (w[cur][0] == 0) return INF;
            return w[cur][0];
        }

        if (dp[cur][visited] != -1) return dp[cur][visited];

        int minCost = INF;
        for (int nextNode = 0; nextNode < n; nextNode++) {
            if (w[cur][nextNode] == 0) continue;
            if ((visited & (1 << nextNode)) != 0)  continue;
            int cost = tsp(nextNode, visited | (1 << nextNode)) + w[cur][nextNode];
            minCost = Math.min(cost, minCost);
        }

        return dp[cur][visited] = minCost;
    }
}
