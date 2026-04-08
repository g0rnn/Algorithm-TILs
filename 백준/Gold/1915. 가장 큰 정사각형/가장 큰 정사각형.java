import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] board= new char[n][m];
        for (int i = 0;i  < n; i++) {
            board[i]= br.readLine().toCharArray();
        }

        int[][] dp = new int[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[i][0] = board[i][0] - '0';
            if (dp[i][0] == 1) max = 1;
        }

        for (int i = 0; i < m; i++) {
            dp[0][i] = board[0][i] - '0';
            if (dp[0][i] == 1) max = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1;j < m; j++) {
                if (board[i][j] == '0') dp[i][j] = 0;
                else dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;

                max = Math.max(dp[i][j], max);
            }
        }
        System.out.println(max*max);
    }
}
