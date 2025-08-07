import java.util.*;

class Solution {
    char[][] container;
    int n, m;
    int[][] offset = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        int answer = n * m;
        
        container = padding(storage);
        
        for (String req : requests) {
            if (req.length() == 1){
                answer -= one(req.charAt(0));
            } else {
                answer -= two(req.charAt(0));
            }
        }
        return answer;
    }
    
    private char[][] padding(String[] storage) {
        char[][] ret = new char[n+2][m+2];
        Arrays.fill(ret[0], ' ');
        Arrays.fill(ret[n+1], ' ');
        for (int i = 0; i < n; i++) {
            ret[i+1][0] = ' ';
            ret[i+1][m+1] = ' ';
            for (int j = 0; j < m; j++) {
                ret[i+1][j+1] = storage[i].charAt(j);
            }
        }
        return ret;
    }
    
    private int one(char target) {
        int count = 0;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n+2][m+2];
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];
            
            for (int[] o : offset) {
                int nx = x + o[0];
                int ny = y + o[1];
                if (nx < 0 || nx >= m+2 || ny < 0 || ny >= n + 2) continue;
                if (visited[ny][nx]) continue;
                if (container[ny][nx] == target) {
                    visited[ny][nx] = true;
                    container[ny][nx] = ' ';
                    count++;
                }
                else if (container[ny][nx] == ' ') {
                    visited[ny][nx] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        //System.out.println("one: "+count);
        return count;
    }
    
    private int two(char target) {
        int count = 0;
        for (int i = 0; i < n+2; i++) {
            for (int j = 0; j < m+2; j++) {
                if (container[i][j] == target) {
                    container[i][j] = ' ';
                    count++;
                }
            }
        }
        //System.out.println("two: "+count);
        return count;
    }
}