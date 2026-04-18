import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    static int[][] dp;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        dp = new int[n][n];

        for (int i = 0 ;i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j= 0; j < n; j++) {
                board[i][j]  = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < n; j++) {
                 max = Math.max(dfs(j, i), max);
            }
        }
        System.out.println(max);
    }

    private static int dfs(int x, int y) {
        if (dp[y][x] != 0) return dp[y][x];

        dp[y][x] = 1;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny)) continue;
            if (board[y][x] <= board[ny][nx]) continue;

            dp[y][x] = Math.max(dfs(nx, ny) + 1, dp[y][x]);
        }

        return dp[y][x];
    }
    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
