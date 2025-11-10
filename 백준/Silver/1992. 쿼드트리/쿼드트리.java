
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        readInput();
        System.out.println(compress(0, n - 1, 0, n - 1));
    }

    private static String compress(int startx, int endx, int starty, int endy) {
        // 1×1 기저 사례
        if (startx == endx && starty == endy) {
            return String.valueOf(map[starty][startx]);
        }

        // 현재 구간이 모두 같은 값인지 검사
        if (isUniform(startx, endx, starty, endy)) {
            return String.valueOf(map[starty][startx]);
        }

        // 다르면 4분할
        int midx = (startx + endx) / 2;
        int midy = (starty + endy) / 2;

        String s1 = compress(startx,     midx, starty,     midy);     // 왼쪽 위
        String s2 = compress(midx + 1,   endx, starty,     midy);     // 오른쪽 위
        String s3 = compress(startx,     midx, midy + 1,   endy);     // 왼쪽 아래
        String s4 = compress(midx + 1,   endx, midy + 1,   endy);     // 오른쪽 아래

        return "(" + s1 + s2 + s3 + s4 + ")";
    }

    private static boolean isUniform(int sx, int ex, int sy, int ey) {
        int first = map[sy][sx];
        for (int i = sy; i <= ey; i++) {
            for (int j = sx; j <= ex; j++) {
                if (map[i][j] != first) return false;
            }
        }
        return true;
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        br.close();
    }
}
