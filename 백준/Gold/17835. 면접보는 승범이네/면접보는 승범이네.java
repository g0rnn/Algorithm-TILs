import java.io.*;
import java.util.*;

public class Main {

    static int V, E, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<List<int[]>> graph = new ArrayList<>();
        List<Integer> spot = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(v).add(new int[]{u, c});
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            spot.add(Integer.parseInt(st.nextToken()));
        }
        br.close();

        long[] distance = dijkstra(graph, spot);
        long maxDist = -1;
        int maxCity = 0;
        for (int i = 1; i <= V; i++) {
            if (distance[i] > maxDist) {
                maxCity = i;
                maxDist = distance[i];
            }
        }
        System.out.println(maxCity);
        System.out.println(maxDist);
    }

    private static long[] dijkstra(List<List<int[]>> graph, List<Integer> spot) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        long[] distance = new long[V + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        for (Integer s : spot) {
            distance[s] = 0L;
            pq.offer(new Node(s, 0L));
        }
        while (!pq.isEmpty()) {
            int city = pq.peek().city;
            long dist = pq.poll().dist;

            if (dist > distance[city]) continue;

            for (int[] next : graph.get(city)) {
                long nextDist = next[1] + dist;
                if (nextDist < distance[next[0]]) {
                    pq.offer(new Node(next[0], nextDist));
                    distance[next[0]] = nextDist;
                }
            }
        }
        return distance;
    }

    static class Node {
        int city;
        long dist;
        public Node(int city, long dist) {
            this.city = city;
            this.dist = dist;
        }
    }
}
