
import java.io.*;
import java.util.*;

public class Main {

    static int n, l, r;
    static int[][] city;
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        city = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        boolean[][] visited;
        while (true) {
            // 모든 도시를 순회하며
                // 연결가능하면 연결하고
                // 인구 이동
            visited = new boolean[n][n];
            boolean isMoved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) continue;
                    //System.out.println("bfs!");
                    List<int[]> part = bfs(j, i, visited, city);
                    //System.out.println(part.size());
                    isMoved = isMoved | flatten(city, part);
                }
            }
            if (!isMoved) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
        br.close();
    }

    private static List<int[]> bfs(int x, int y, boolean[][] visited, int[][] city) {
        List<int[]> result = new ArrayList<>();
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        result.add(new int[]{x, y});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int cx = q.peek()[0];
            int cy = q.poll()[1];

            for (int[] o : offset) {
                int nx = cx + o[0];
                int ny = cy + o[1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[ny][nx]) continue;
                int diff = Math.abs(city[cy][cx] - city[ny][nx]);
                //System.out.println(city[cy][cx] + ", " + city[ny][nx]);
                if (!(l <= diff && diff <= r)) continue;
                q.add(new int[]{nx, ny});
                result.add(new int[]{nx, ny});
                visited[ny][nx] = true;
            }
        }
        return result;
    }

    private static boolean flatten(int[][] city, List<int[]> part) {
        boolean moved = false;
        int size = part.size();
        if (size == 1) return moved;

        int sum = 0;
        for (int i = 0; i < size; i++) {
            int x = part.get(i)[0];
            int y = part.get(i)[1];
            sum += city[y][x];
        }
        final int avg = sum / size;
        for (int[] p : part) {
            if (city[p[1]][p[0]] != avg) moved = true;
            city[p[1]][p[0]] = avg;
        }
        return moved;
    }
}
