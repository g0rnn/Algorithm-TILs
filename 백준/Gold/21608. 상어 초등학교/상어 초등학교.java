
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] board;
    static int[][] likes;
    static int[][] offset = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        likes = new int[n*n+1][4];

        for (int i = 0; i < n*n; i++)  {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) likes[student][j] = Integer.parseInt(st.nextToken());

            int[] best = new int[] {0, 0, n+1, n+1}; // {point, empty,  x, y}

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (board[y][x] != 0) continue;

                    int like = 0;
                    int empty = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + offset[d][0];
                        int ny = y + offset[d][1];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                        if (board[ny][nx]==0) empty++;
                        else if (isLiked(student, board[ny][nx])) like++;
                    }

                    int[] cur = new int[] {like, empty, x, y};
                    if (better(cur, best)) best = cur;
                }
            }
            board[best[3]][best[2]] = student;
        }

        int ans = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int student = board[r][c];
                int cnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = r + offset[d][1];
                    int nc = c + offset[d][0];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (isLiked(student, board[nr][nc])) cnt++;
                }

                if (cnt == 1) ans += 1;
                else if (cnt == 2) ans += 10;
                else if (cnt == 3) ans += 100;
                else if (cnt == 4) ans += 1000;
            }
        }

        System.out.println(ans);
    }

    private static boolean isLiked(int stu, int other) {
        for (int i = 0; i < 4; i++)
            if (likes[stu][i]==other) return true;
        return false;
    }

    private static boolean better(int[] cur, int[] best) {
        if (cur[0] != best[0]) return cur[0] > best[0];
        if (cur[1] != best[1]) return cur[1] > best[1];
        if (cur[3] != best[3]) return cur[3] < best[3]; // y가 먼저
        return cur[2] < best[2];
    }
}
