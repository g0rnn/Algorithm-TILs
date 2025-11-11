
import java.io.*;
import java.util.*;

public class Main {
    private static int tc = 0;

    public static void main(String[] args) throws IOException {
        readInput();
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }

            for (int j = 0; j < w; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                edges.add(new Edge(s, e, -t));
            }

            sb.append(hasNegativeCycle(edges, n) ? "YES" : "NO").append('\n');
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static boolean hasNegativeCycle(List<Edge> edges, int n) {
        int[] dist = new int[n + 1]; // 1에서 출발!
        Arrays.fill(dist, 0);

        for (int i = 1; i < n; i++) {
            boolean updated = false;
            for (Edge e : edges) {
                if (dist[e.to] > dist[e.from] + e.time) {
                    dist[e.to] = dist[e.from] + e.time;
                    updated = true;
                }
            }
            if (!updated) break;
        }
        for (Edge e : edges) {
            if (dist[e.to] > dist[e.from] + e.time) return true;
        }
        return false;
    }

    static class Edge {
        int from;
        int to;
        int time;

        Edge(int f, int t, int time) {
            from = f;
            to = t;
            this.time = time;
        }
    }
}
