import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int min = Integer.MAX_VALUE;
    static int cnt = 0;
    static int[][] grid;
    static List<int[]> virus;
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][n];
        virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] != 1) cnt++;
                if (grid[i][j] == 2) virus.add(new int[] {j, i});
            }
        }

        comb(new ArrayList<>(), 0);
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static void comb(List<int[]> v, int start) {
        if (v.size() == m) {
            min = Math.min(min, measure(new ArrayDeque<>(v)));
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            v.add(virus.get(i));
            comb(v, i+1);
            v.remove(v.size() - 1);
        }
    }

    private static int measure(Deque<int[]> q) {
        boolean[][] visited = new boolean[n][n];
        int time = 0;
        int infected = 0;
        for (int[] start : q) visited[start[1]][start[0]] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int x = q.peek()[0];
                int y = q.poll()[1];
                infected++;

                for (int d = 0; d < 4; d++) {
                    int nx = x + offset[d][0];
                    int ny = y + offset[d][1];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if (grid[ny][nx] == 1 || visited[ny][nx]) continue;

                    visited[ny][nx] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
            time++;
        }
        if (infected == cnt) return time - 1;
        return Integer.MAX_VALUE;
    }
}
