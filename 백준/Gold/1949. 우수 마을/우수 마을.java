import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] points = new int[n+1];
        List<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            points[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int[][] dp = new int[2][n+1];
        dfs(1, 0, dp, points, graph);
        System.out.println(Math.max(dp[0][1], dp[1][1]));
    }

    static void dfs(int cur, int parent, int[][] dp, int[] points, List<Integer>[] graph) {
        dp[0][cur] = points[cur];
        dp[1][cur] = 0;

        for (int child : graph[cur]) {
            if (child == parent) continue;

            dfs(child, cur, dp, points, graph);

            dp[0][cur] += dp[1][child];
            dp[1][cur] += Math.max(dp[0][child], dp[1][child]);
        }
    }
}
