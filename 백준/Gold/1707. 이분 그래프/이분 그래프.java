import java.util.*;
import java.io.*;

class Main {
    static final int GREY = 1;
    static final int WHITE = 0;
    static int v, e;
    static List<List<Integer>> graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for (int j=0;j<v;j++) graph.add(new ArrayList<>());
            
            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken())-1;
                int u2 = Integer.parseInt(st.nextToken())-1;
                
                graph.get(u).add(u2);
                graph.get(u2).add(u);
            }
            
            sb.append(isCyclic() ? "NO" : "YES").append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    
private static boolean isCyclic() {
    int[] visited = new int[v]; // -1: 미방문, 0: red, 1: black
    Arrays.fill(visited, -1);

    for (int i = 0; i < v; i++) {
        if (visited[i] == -1) {
            Deque<Integer> q = new ArrayDeque<>();
            q.add(i);
            visited[i] = 0;

            while (!q.isEmpty()) {
                int cur = q.poll(); 

                for (int nei : graph.get(cur)) {
                    // 1. 아직 방문하지 않은 노드라면
                    if (visited[nei] == -1) {
                        visited[nei] = (visited[cur]+1) % 2;
                        q.add(nei);
                    } 
                    // 2. 이미 방문했는데, 같은 색 -> 순환!
                    else if (visited[nei] == visited[cur]) {
                        return true;
                    }
                }
            }
        }
    }
    return false;
}
}