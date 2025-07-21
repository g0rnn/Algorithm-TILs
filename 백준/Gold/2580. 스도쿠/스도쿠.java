import java.io.*;
import java.util.*;

public class Main {

    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    private static boolean backtracking(int x, int y) {
        if (x == 9) {  // 한 줄 끝나면 다음 줄로
            x = 0;
            y++;
            if (y == 9) return true;  // 모든 칸을 다 채운 경우
        }

        if (board[y][x] != 0) {
            return backtracking(x + 1, y);  // 이미 채워진 칸이면 다음 칸으로
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(y, x, num)) {
                board[y][x] = num;
                if (backtracking(x + 1, y)) return true;
                board[y][x] = 0;  
            }
        }

        return false;
    }

    // 현재 위치에 num을 넣을 수 있는지 검사
    private static boolean isValid(int y, int x, int num) {
        // 같은 행
        for (int i = 0; i < 9; i++) {
            if (board[y][i] == num) return false;
        }

        // 같은 열
        for (int i = 0; i < 9; i++) {
            if (board[i][x] == num) return false;
        }

        // 같은 3x3 박스
        int startY = (y / 3) * 3;
        int startX = (x / 3) * 3;
        for (int i = startY; i < startY + 3; i++) {
            for (int j = startX; j < startX + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }

        return true;
    }
}