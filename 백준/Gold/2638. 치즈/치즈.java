
import java.util.*;
import java.io.*;

public class Main {

    static int[][] board;
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // {x, y}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) cnt++;
            }
        }
        br.close();

        int time = 0;

        while (cnt != 0) {
            Map<String, Integer> result = bfs(n, m);

            for (Map.Entry<String, Integer> e : result.entrySet()) {
                String k = e.getKey();
                Integer v = e.getValue();

                if (v >= 2) {
                    // k -> 좌표로 분리
                    StringTokenizer s = new StringTokenizer(k, ",");
                    int x = Integer.parseInt(s.nextToken());
                    int y = Integer.parseInt(s.nextToken());
                    board[y][x] = 0;
                    cnt--;
                }
            }
            time++;
        }

        System.out.println(time);
    }

    private static Map<String, Integer> bfs(int n, int m) {
        Map<String, Integer> result = new HashMap<>();
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + offset[d][0];
                int ny = y + offset[d][1];

                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1) continue;
                if (visited[ny][nx]) continue;

                if (board[ny][nx] == 1) {
                    result.put(nx + "," + ny, result.getOrDefault(nx + "," + ny, 0) + 1);
                } else {
                    q.add(new int[]{nx, ny});
                    visited[ny][nx] = true;
                }
            }
        }

        return result;
    }
}
