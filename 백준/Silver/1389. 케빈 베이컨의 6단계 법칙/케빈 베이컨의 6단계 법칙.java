import java.util.*;
import java.io.*;

public class Main {

    private static int n, m, answer, minKebin = 1_000_000_000;
    private static Set<Integer>[] relationship;
    private static int[] kebin;

    public static void main(String[] args) throws IOException {
        input();
        kebin = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                sum += trace(i, j, 0);
            }
            kebin[i] = sum;
        }

        for (int i = 1; i <= n; i++) {
            if (minKebin > kebin[i]) {
                minKebin = kebin[i];
                answer = i;
            }
        }
        System.out.println(answer);
    }

    private static int trace(int start, int dest, int dist) {
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start, 0});
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.peek()[0];
            int curDist = q.poll()[1];

            if (cur == dest) return curDist;

            for (Integer friend : relationship[cur]) {
                if (visited[friend]) continue;

                q.offer(new int[]{friend, curDist + 1});
                visited[friend] = true;
            }
        }
        return 0;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        relationship = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            relationship[i] = new HashSet<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relationship[a].add(b);
            relationship[b].add(a);
        }
        br.close();
    }
}
