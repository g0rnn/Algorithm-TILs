import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[] corridor = new int[201];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                int s = (Math.min(a, b) + 1) / 2;
                int e = (Math.max(a, b) + 1) / 2;

                for (int c = s; c <= e; c++) corridor[c]++;
            }

            int ans = 0;
            for (int c = 1; c <= 200; c++) ans = Math.max(ans, corridor[c]);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
