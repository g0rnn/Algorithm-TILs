import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] dish = new int[n+k+1];
        int[] cnt = new int[d+1];
        int distinct = 0;

        for (int i = 0; i < n; i++) {
            dish[i] = Integer.parseInt(br.readLine());

            if (i < k) {
                dish[i+n] = dish[i];
                if (cnt[dish[i]] == 0) distinct++;
                cnt[dish[i]]++;
            }
        }

        int result = distinct + ((cnt[c] == 0) ? 1 : 0);
        for (int i = k; i < n+k; i++) {
            if (cnt[dish[i]] == 0) distinct++;
            cnt[dish[i]]++;

            cnt[dish[i-k]]--;
            if (cnt[dish[i-k]] == 0) distinct--;

            if (cnt[c] == 0) result = Math.max(result, distinct+1);
            else result = Math.max(result, distinct);
        }
        System.out.println(result);
    }
}
