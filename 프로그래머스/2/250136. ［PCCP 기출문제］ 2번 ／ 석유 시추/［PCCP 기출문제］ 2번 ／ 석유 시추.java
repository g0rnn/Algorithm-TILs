import java.util.*;

class Solution {
    
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 동 남 서 북
    static int[] cols;
    static boolean[][] visited;
    
    public int solution(int[][] land) {
        int n = land.length; // row
        int m = land[0].length; // col
        cols = new int[m];
        visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 석유를 뽑고 cols에 저장함.
                extract(j, i, land); // x, y
            }
        }
        
        int answer = 0;
        for (int i = 0; i < m; i++) {
            if (answer < cols[i]) answer = cols[i];
        }
        return answer;
    }
    
    private static void extract(int x, int y, int[][] land) {
        if (visited[y][x]) return;
        if (land[y][x] == 0) return;
        
        Set<Integer> visitedCols = new HashSet<>();
        Queue<int[]> q = new ArrayDeque<>();
        int n = land.length;
        int m = land[0].length;
        int count = 1;
        
        visited[y][x] = true;
        q.add(new int[]{x, y});
        visitedCols.add(x);
        
        while (!q.isEmpty()) {
            int cx = q.peek()[0];
            int cy = q.poll()[1];
            
            for (int[] o : offset) {
                int nx = cx + o[0];
                int ny = cy + o[1];
                
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[ny][nx]) continue;
                if (land[ny][nx] == 0) continue;
                
                q.offer(new int[]{nx, ny});
                visited[ny][nx] = true;
                visitedCols.add(nx);
                count += 1;
            }
        }
        for (Integer i : visitedCols) {
            cols[i] += count;
        }
    }
}