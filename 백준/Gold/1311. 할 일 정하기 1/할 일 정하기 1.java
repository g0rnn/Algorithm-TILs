import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] works;
    static int[][] dp;
    static final int INF = 16_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        works = new int[n][n];
        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) works[i][j] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }
        System.out.println(tsp(0, 0)); // 여기선 누구에게도 일을 주지 않고 시작함
    }

    private static int tsp(int who, int visited) {
        // 모든 사ㅏㄹㅁ에게 일이 배정된 경우
        if (visited == (1 << n) - 1) return 0;
        if (dp[who][visited] != -1) return dp[who][visited];

        int minCost = INF;
        for (int next = 0; next < n; next++) {
            if ((visited & (1 << next)) != 0) continue; // 이미 방문함
            int cost = tsp(who+1, visited | (1 << next)) + works[who][next];
            minCost = Math.min(cost, minCost);
        }

        return dp[who][visited] = minCost;
    }
}
