import java.util.*;
import java.io.*;

public class Main {
    static int n, e;
    static int[] times;
    static List<Integer>[] graph;
    static List<Integer>[] rGraph;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            times = new int[n+1];
            graph = new ArrayList[n+1];
            rGraph = new ArrayList[n+1];
            dp = new int[n+1];
            Arrays.fill(dp, -1);

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
                rGraph[i] = new ArrayList<>();
                times[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                rGraph[b].add(a);
            }

            int target = Integer.parseInt(br.readLine());
            System.out.println(dfs(target));
        }
    }

    private static int dfs(int cur) {
        if (rGraph[cur].isEmpty()) return times[cur];
        if (dp[cur] != -1) return dp[cur];

        int max = 0;
        for (int prev : rGraph[cur]) {
            max = Math.max(dfs(prev), max);
        }
        return dp[cur] = max + times[cur];
    }
}
