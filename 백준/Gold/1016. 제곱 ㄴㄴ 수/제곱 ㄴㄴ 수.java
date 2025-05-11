
import java.io.*;
import java.util.*;

public class Main {

    static long min, max;

    public static void main(String[] args) throws IOException {
        input();
        long ans = max - min + 1; // 살펴볼 숫자 개수
        boolean[] visited = new boolean[(int)(max - min + 2)];
        long i = 2;

        while (i * i <= max) {
            long pow = i * i;
            long start = min / pow;
            if (min % pow != 0) {
                start += 1;
            }

            for (long j = start; j * pow <= max; j++) {
                int idx = (int)(j * pow - min);
                if (!visited[idx]) {
                    visited[idx] = true;
                    ans--;
                }
            }
            i++;
        }
        System.out.println(ans);
    }


    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        br.close();
    }
}
