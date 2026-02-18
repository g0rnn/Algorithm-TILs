
import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[][] offset = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[] parent, rank; // rank는 트리의 높이를 저장

    static class Edge {
        int a, b, w;
        Edge(int a, int b, int w) { this.a = a; this.b = b; this.w = w; }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a); b = find(b);

        if (a == b) return false;
        if (rank[a] < rank[b]) parent[a] = b;
        else if (rank[a] > rank[b]) parent[b] = a;
        else {parent[b] = a; rank[a]++;}
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int K = labelIslands();               // 섬 라벨: 1..K
        List<Edge> edges = buildEdges(K);     // 가능한 다리 간선 수집

        edges.sort(Comparator.comparingInt(e -> e.w));

        parent = new int[K + 1];
        rank = new int[K + 1];
        for (int i = 1; i <= K; i++) parent[i] = i;
        
        // 크루스칼
        int used = 0;
        int ans = 0;
        for (Edge e : edges) { // 모든 (오름차순으로 정렬된) 엣지를 순회하며
            if (union(e.a, e.b)) { // 사이클이 없는 최소 간선을 선택하며 == union
                ans += e.w;
                used++;
                if (used == K - 1) break; // 노드 n개에 대해 n-1개의 엣지를 찾았다면 종료
            }
        }

        if (K == 1) System.out.println(0);
        else System.out.println(used == K - 1 ? ans : -1);
    }
    
    static int labelIslands() {
        int id = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    id++;
                    bfsLabel(j, i, id);
                }
            }
        }
        return id;
    }

    static void bfsLabel(int sx, int sy, int id) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        visited[sy][sx] = true;
        map[sy][sx] = id;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + offset[d][0];
                int ny = y + offset[d][1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (visited[ny][nx] || map[ny][nx] != 1) continue;
                visited[ny][nx] = true;
                map[ny][nx] = id;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    static List<Edge> buildEdges(int K) {
        int INF = 1_000_000;
        int[][] best = new int[K+1][K+1];
        for (int i = 1; i <= K; i++) Arrays.fill(best[i], INF);

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (map[y][x] == 0) continue;
                int from = map[y][x];

                for (int d = 0; d < 4; d++) {
                    int nx = x + offset[d][0];
                    int ny = y + offset[d][1];
                    int len = 0;

                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                    if (map[ny][nx] != 0) continue;

                    while (0 <= nx && nx < m && 0 <= ny && ny < n) {
                        if (map[ny][nx] == 0) {
                            len++;
                            nx += offset[d][0];
                            ny += offset[d][1];
                        } else {
                            int to = map[ny][nx];
                            if (to != from && len >= 2) {
                                if (len < best[from][to]) {
                                    best[from][to] = len;
                                    best[to][from] = len;
                                }
                            }
                            break; // 바다가 아닌 지점에 왔으니 종료
                        }
                    }
                }
            }
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= K; i++)
            for (int j = i + 1; j <= K; j++)
                if (best[i][j] < INF) edges.add(new Edge(i, j, best[i][j]));
        return edges;
    }
}
