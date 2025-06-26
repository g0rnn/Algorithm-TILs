
import java.util.*;
import java.io.*;

public class Main {

    static final int MAX = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        br.close();

        int[] time = new int[MAX + 1];
        int[] ways = new int[MAX + 1];

        Arrays.fill(time, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        time[n] = 0;
        ways[n] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                if (next < 0 || next > MAX) {
                    continue;
                }

                if (time[next] == -1) { // 처음 방문
                    time[next] = time[cur] + 1;
                    ways[next] = ways[cur];
                    q.add(next);
                } else if (time[next] == time[cur] + 1) { // 같은 시간에 다시 도달
                    ways[next] += ways[cur];
                }
            }
        }

        System.out.println(time[k]);
        System.out.println(ways[k]);
    }
}
