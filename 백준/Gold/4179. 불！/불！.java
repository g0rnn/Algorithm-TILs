import java.util.*;
import java.io.*;

public class Main {

    static final char WALL = '#';
    static int r, c, answer=1;
    static char[][] board;
    static int[] J = new int[2];
    static List<int[]> fireStarts = new ArrayList<>();
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'J') {
                    J[0] = j; // x
                    J[1] = i;
                } else if (board[i][j] == 'F') {
                    fireStarts.add(new int[]{j, i});
                }
            }
        }
        br.close();

        if (bfs()) {
            System.out.println(answer);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }

    private static boolean bfs() {
        Queue<int[]> jihoon = new ArrayDeque<>();
        Queue<int[]> fire = new ArrayDeque<>();
        boolean[][] jVisited = new boolean[r][c];
        boolean[][] fVisited = new boolean[r][c];

        if (isEdge(J[0], J[1])) return true;

        jihoon.offer(new int[]{J[0], J[1]});
        jVisited[J[1]][J[0]] = true;

        // 불 시작위치 지정
        for (int[] f : fireStarts) {
            fire.offer(f);
            fVisited[f[1]][f[0]] = true;
        }

        while (!jihoon.isEmpty()) {
            int jSize = jihoon.size();
            int fSize = fire.size();

            while (fSize-- > 0) {
                int x = fire.peek()[0];
                int y = fire.poll()[1];

                for (int[] o : offset) {
                    int nx = x + o[0];
                    int ny = y + o[1];

                    if (nx < 0 || nx >= c || ny < 0 || ny >= r) {
                        continue;
                    }
                    if (fVisited[ny][nx] || board[ny][nx] == WALL) {
                        continue;
                    }
                    fire.offer(new int[]{nx, ny});
                    fVisited[ny][nx] = true;
                    board[ny][nx] = 'F';
                }
            }

            while (jSize-- > 0) {
                int x = jihoon.peek()[0];
                int y = jihoon.poll()[1];

                if (isEdge(x, y)) {
                    return true;
                }

                for (int[] o : offset) {
                    int nx = x + o[0];
                    int ny = y + o[1];

                    if (nx < 0 || nx >= c || ny < 0 || ny >= r) {
                        continue;
                    }
                    if (jVisited[ny][nx] || board[ny][nx] == WALL || board[ny][nx] == 'F') {
                        continue;
                    }

                    jihoon.offer(new int[]{nx, ny});
                    jVisited[ny][nx] = true;
                }
            }
            answer++;
        }
        return false;
    }

    private static boolean isEdge(int x, int y) {
        return x == 0 || y == 0 || x == (c-1) || y == (r-1);
    }
}
