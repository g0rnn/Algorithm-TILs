class Solution {
    StringBuilder answer;
    char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                      'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                      'W', 'X', 'Y', 'Z'};
    int m, n;
    char[][] map;
    
    public String solution(int m, int n, String[] board) {
        answer = new StringBuilder();
        this.m=m; this.n=n;
        map = new char[m][n];
        for (int i = 0; i < board.length; i++) map[i] = board[i].toCharArray();
        boolean[] visited = new boolean[26];
        
        dfs(0, visited);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ('A' <= map[i][j] && map[i][j] <= 'Z') return "IMPOSSIBLE";
            }
        }
        
        return answer.toString();
    }
    
    private void dfs(int depth, boolean[] visited) {
        if (depth == 26) return;
        
        for (int i = 0; i < 26; i++) {
            if (visited[i]) continue;
            if (canBreak(alpha[i])) {
                answer.append(alpha[i]);
                visited[i] = true;
                dfs(depth + 1, visited);
                return;
            }
        }
    }
    
    private boolean canBreak(char target) {
        boolean flag = false;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == target) {
                    if (flag) {
                        map[i][j] = '.';
                        return true;
                    }
                    if (moveRight(j, i, target) || moveDown(j, i, target) || moveLeft(j, i, target)) { // 부술 수 있으면 부숴야함.
                        map[i][j] = '.';
                        flag = true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean moveRight(int x, int y, char target) {        
        for (int nx = x + 1; nx < n; nx++) {
            if (!inRange(nx, y)) return false;
            if (map[y][nx] == '*') break;
            if (map[y][nx] == target) return true;
            if (map[y][nx] != '.') break; // 다른 알파벳이 있어서 못감
            
            for (int ny = y + 1; ny < m; ny++) {
                if (!inRange(nx, ny)) return false;
                if (map[ny][nx] == '*') break;
                if (map[ny][nx] == target) return true;
                if (map[ny][nx] != '.') break;
            }
        }
        return false;
    }
    
    private boolean moveDown(int x, int y, char target) {        
        for (int ny = y + 1; ny < m; ny++) {            
            if (!inRange(x, ny)) return false;
            if (map[ny][x] == '*') break;
            if (map[ny][x] == target) return true;
            if (map[ny][x] != '.') break;
            
            for (int nx = x + 1; nx < n; nx++) {                
                if (!inRange(nx, ny)) return false;
                if (map[ny][nx] == '*') break;
                if (map[ny][nx] == target) return true;
                if (map[ny][nx] != '.') break;
            }
            
            for (int nx = x - 1; nx >= 0; nx--) {                
                if (!inRange(nx, ny)) return false;
                if (map[ny][nx] == '*') break;
                if (map[ny][nx] == target) return true;
                if (map[ny][nx] != '.') break;
            }
        }
        return false;
    }
    
    private boolean moveLeft(int x, int y, char target) {
        for (int nx = x - 1; nx >= 0; nx--) {
            if (!inRange(nx, y)) return false;
            if (map[y][nx] == '*') break;
            if (map[y][nx] == target) return true;
            if (map[y][nx] != '.') break;
            
            for (int ny = y + 1; ny < m; ny++) {
                if (!inRange(nx, ny)) return false;
                if (map[ny][nx] == '*') break;
                if (map[ny][nx] == target) return true;
                if (map[ny][nx] != '.') break;
            }
        }
        return false;
    }
    
    private boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}