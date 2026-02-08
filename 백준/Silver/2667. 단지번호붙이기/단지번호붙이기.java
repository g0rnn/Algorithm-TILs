import java.io.*;
import java.util.*;

class Main {
    static int n;
    static char[][] map;
    static boolean[][] visited;
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][];
        visited = new boolean[n][n];
        
        for (int i = 0; i < n; i++) map[i] = br.readLine().toCharArray();
        
        List<Integer> house = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '0') continue;
                if (visited[i][j]) continue;
                house.add(bfs(j, i));
            }
        }
        Collections.sort(house);
        System.out.println(house.size());
        for (int h : house) System.out.println(h);
    }
    
    private static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[y][x] = true;
        
        int result = 1;
        while (!q.isEmpty()) {
            int cx = q.peek()[0];
            int cy = q.poll()[1];
            
            for (int d = 0; d < 4; d++) {
                int nx = cx + offset[d][0];
                int ny = cy + offset[d][1];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx] == '0') continue;
                
                visited[ny][nx] = true;
                q.offer(new int[]{nx, ny});
                result++;
            }
        }
        return result;
    }
}