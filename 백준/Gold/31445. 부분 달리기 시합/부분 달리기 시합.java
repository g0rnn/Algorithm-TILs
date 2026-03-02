import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007L;
    static int[] indeg;
    static List<Integer>[] graph;
    static int n;

    public static void main(String[] args) throws Exception {
        input();

        // dp[v] = v까지 확정 가능한 "선행자 수 + 자기 자신"의 누적
        long[] dp = new long[n + 1];
        Arrays.fill(dp, 1L);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++)
            if (indeg[i] == 0) q.add(i);

        // visited stamp
        int[] visited = new int[n + 1];
        int stamp = 0;
        long ans = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            ans = (ans + dp[u]) % MOD;

            // u의 "직접 자식"은 indegree 감소 + topo 큐 투입
            ArrayDeque<Integer> bfs = new ArrayDeque<>();
            stamp++;

            for (int v : graph[u]) {
                if (visited[v] != stamp) {
                    visited[v] = stamp;
                    bfs.add(v);
                }
                if (--indeg[v] == 0) {
                    q.add(v);
                }
            }

            // u에서 도달 가능한 모든 정점 v에 dp[u]를 "딱 한 번" 전파
            while (!bfs.isEmpty()) {
                int x = bfs.poll();
                dp[x] += dp[u];
                dp[x] %= MOD;

                for (int nx : graph[x]) {
                    if (visited[nx] != stamp) {
                        visited[nx] = stamp;
                        bfs.add(nx);
                    }
                }
            }
        }

        System.out.println(ans % MOD);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        indeg = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            indeg[b]++;
        }
    }
}
