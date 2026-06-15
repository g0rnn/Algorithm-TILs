// 경로 합은 long
import java.util.*;

class Solution {
    List<Edge>[] graph;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        boolean[] isSummit = new boolean[n+1];
        boolean[] isGate = new boolean[n+1];
        
        for (int summit : summits) isSummit[summit] = true;
        for (int gate : gates) isGate[gate] = true;
        Arrays.sort(summits);
        
        buildGraph(n, paths);
        int[] intensityOf = dijkstra(gates, isGate, isSummit, n);
        
        int min = Integer.MAX_VALUE;
        int target = 0;
        
        for (int summit : summits) {
            if (intensityOf[summit] < min) {
                target = summit;
                min = intensityOf[summit];
            }
        }
        
        return new int[]{target, min};
    }
    
    
    private int[] dijkstra(int[] gates, boolean[] isGate, boolean[] isSummit, int n) {
        int[] memo = new int[n+1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1])); // {노드번호, 가장 긴 엣지}
        for (int gate : gates) pq.offer(new int[]{gate, 0});
        
        while (!pq.isEmpty()) {
            int cur = pq.peek()[0];
            int intensity = pq.poll()[1];
            
            if (isSummit[cur]) continue;
            if (intensity > memo[cur]) continue;
            
            // 출발지 빼고, 갈 수 없는 길(intensity가 높은길) 빼고
            for (Edge adj : graph[cur]) {
                int maxIntensity = Math.max(intensity, adj.w);
                
                if (isGate[adj.e]) continue;
                if (maxIntensity < memo[adj.e]) { 
                    pq.offer(new int[]{adj.e, maxIntensity});
                    memo[adj.e] = maxIntensity;
                }
            }
        }
        return memo;
    }
    
    private void buildGraph(int n, int[][] paths) {
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] path : paths) {
            int s = path[0];
            int e = path[1];
            int w = path[2];
            
            graph[s].add(new Edge(e, w));
            graph[e].add(new Edge(s, w));
        }
    }
    
    static class Edge{
        int e, w;
        Edge(int e, int w) {this.e = e; this.w = w;}
    }
}