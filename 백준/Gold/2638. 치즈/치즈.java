
import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] board;
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        System.out.println(melt());
    }

    private static int melt() {
        int hour = 0;
        List<int[]> pos;
        while (true) {
            pos = bfs();
            if (pos.isEmpty()) break;
            for (int[] p : pos) {
                int x = p[0], y = p[1];
                board[y][x] = 0;
            }
            hour++;
        }
        return hour;
    }

    private static List<int[]> bfs() {
        List<int[]> corner = new ArrayList<>();
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        boolean[][] cheese = new boolean[n][m];

        q.offer(new int[]{0, 0}); // 가장자리는 항상 비어있기 때문에
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];

            for (int[] o : offset) {
                int nx = x + o[0];
                int ny = y + o[1];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (visited[ny][nx]) continue;
                if (board[ny][nx] == 1) {
                    if (cheese[ny][nx]) corner.add(new int[]{nx, ny});
                    cheese[ny][nx] = true;
                    continue;
                }
                q.offer(new int[]{nx, ny});
                visited[ny][nx] = true;
            }
        }
        return corner;
    }
}
