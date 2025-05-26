import java.util.*;

class Solution {
    
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 동 남 서 북
    public int solution(int[][] land) {
        int answer = 0;
        int[] maxOil = new int[501];
        boolean[][] visited = new boolean[land.length][land[0].length];
        
        for (int line = 0; line < land[0].length; line++) {
            for (int depth = 0; depth < land.length; depth++) {
                bfs(land, maxOil, visited, line, depth);
            }
            
            if (maxOil[line] > answer) answer = maxOil[line];
        }
        
        return answer;
    }
    
    private void bfs(int[][] land, int[] maxOil, boolean[][] visited, int x, int y) {
        if (visited[y][x] || land[y][x] == 0) return;
        
        int oil = 1;
        int xMax = land[0].length;
        int yMax = land.length;
        Queue<int[]> q = new ArrayDeque<>();
        Set<Integer> visitedLine = new HashSet<>();
        
        if (visited[y][x]) return;
        
        q.offer(new int[]{x, y});
        visited[y][x] = true;
        visitedLine.add(x);
        
        while(!q.isEmpty()) {
            int cx = q.peek()[0];
            int cy = q.poll()[1];
            
            if (land[cy][cx] == 0) continue;
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + offset[dir][0];
                int ny = cy + offset[dir][1];
                
                if (nx < 0 || nx >= xMax || ny < 0 || ny >= yMax) continue;
                if (visited[ny][nx] || land[ny][nx] == 0) continue;
                q.offer(new int[]{nx, ny});
                visitedLine.add(nx);
                visited[ny][nx] = true;
                oil++;
            }
        }
        
        for (Integer i : visitedLine) {
            maxOil[i] += oil;
        }
    }
}