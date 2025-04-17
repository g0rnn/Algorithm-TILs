
import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] map;
    static int[][] offset = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 상 우 하 좌
    static int[][] offset2 = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}}; // 상 우상 우 우하 하 좌하 좌 좌상
    static double[] percentage = {0.05, 0.02, 0, 0.02};
    static double[] percentage2 = {0, 0.1, 0.07, 0.01, 0, 0.01, 0.07, 0.1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        input();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(explodeSand()));
        bw.flush();
    }

    private static int explodeSand() {
        int totalOut = 0;
        int x = N / 2 + 1, y = N / 2 + 1;
        int curDir = 0;
        visited[y][x] = true;

        while (x != 1 || y != 1) {
            // 일단 좌로 이동
            int nextDir = (curDir + 3) % 4;
            int nx = x + offset[nextDir][0];
            int ny = y + offset[nextDir][1];
            if (visited[ny][nx]) {
                // 직진
                nextDir = curDir;
                nx = x + offset[nextDir][0];
                ny = y + offset[nextDir][1];
            }
            totalOut += moveTo(nx, ny, nextDir);

            // 결과 적용
            visited[ny][nx] = true;
            x = nx;
            y = ny;
            curDir = nextDir;
        }
        return totalOut;
    }

    // 상: 0, 우: 1, 하: 2, 좌: 3
    private static int moveTo(int x, int y, int dir) {
        int outSand = 0;
        int totalMoved = 0;

        // 2%, 5% 적용
        for (int d = 0; d < 4; d++) {
            int nd = (dir + d) % 4;
            int nx = x + offset[nd][0] * 2;
            int ny = y + offset[nd][1] * 2;
            int sand = (int)(map[y][x] * percentage[d]);

            if (nx < 1 || nx > N || ny < 1 || ny > N) {
                // 모래 밖인 경우
                outSand += sand;
                totalMoved += sand;
                continue;
            }
            map[ny][nx] += sand;
            totalMoved += sand;
        }

        // 10, 7, 1% 적용
        int expanded = dir * 2;
        for (int d = 0; d < 8; d++) {
            int nd = (expanded + d) % 8;
            int nx = x + offset2[nd][0];
            int ny = y + offset2[nd][1];
            int sand = (int)(map[y][x] * percentage2[d]);

            if (nx < 1 || nx > N || ny < 1 || ny > N) {
                // 모래 밖인 경우
                outSand += sand;
                totalMoved += sand;
                continue;
            }
            map[ny][nx] += sand;
            totalMoved += sand;
        }

        int ax = x + offset[dir][0];
        int ay = y + offset[dir][1];
        if (ax < 1 || ax > N || ay < 1 || ay > N) {
            // 모래 밖인 경우
            outSand += map[y][x] - totalMoved;
        } else {
            map[ay][ax] += map[y][x] - totalMoved;
        }
        map[y][x] = 0;
        return outSand;
    }

    // 왼쪽으로 불면 y가 양옆으로 이동
    // 아래로 불면 x가 양옆으로 이동

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
