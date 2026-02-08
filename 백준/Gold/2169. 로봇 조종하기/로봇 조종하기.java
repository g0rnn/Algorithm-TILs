
import java.io.*;
import java.util.*;

public class Main {

    static int[][] mars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        mars = new int[n][m];
        for (int i= 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mars[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][m];
        dp[0][0] = mars[0][0];
        for (int j = 1; j < m; j++) dp[0][j] = dp[0][j-1] + mars[0][j];

        int[] left = new int[m];
        int[] right = new int[m];
        
        for (int i = 1; i < n; i++) {
            left[0] = dp[i-1][0] + mars[i][0];
            for (int j = 1; j < m; j++) left[j] = Math.max(dp[i-1][j], left[j-1]) + mars[i][j];
            
            right[m-1] = dp[i-1][m-1] + mars[i][m-1];
            for (int j = m-2; j >= 0; j--) right[j] = Math.max(dp[i-1][j], right[j+1]) + mars[i][j];
            
            for (int j = 0; j < m; j++) dp[i][j] = Math.max(left[j], right[j]);
        }
        System.out.println(dp[n-1][m-1]);
    }
}
