import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<Edge>[] edges = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
        for (int[] r : road) {
            edges[r[0]].add(new Edge(r[0], r[1], r[2]));
            edges[r[1]].add(new Edge(r[1], r[0], r[2]));
        }
        
        int[] dist = dijkstra(N, 0, edges);
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }

        return answer;
    }
    
    private int[] dijkstra(int N, int start, List<Edge>[] edges) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.offer(new Node(1, 0));
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (Edge adj : edges[cur.num]) {
                int nextDist = cur.dist + adj.w;
                if (nextDist < dist[adj.e]) {
                    dist[adj.e] = nextDist;
                    pq.offer(new Node(adj.e, nextDist));
                }
            }
        }
        return dist;
    }
    
    static class Edge{
        int s, e, w;
        Edge(int s, int e, int w) {
            this.s=s;this.e=e;this.w=w;
        }
    }
    
    static class Node{
        int num, dist;
        Node(int n, int d) {
            this.num=n;this.dist=d;
        }
    }
}