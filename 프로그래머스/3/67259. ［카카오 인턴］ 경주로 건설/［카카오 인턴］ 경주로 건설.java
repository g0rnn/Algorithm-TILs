import java.util.*;

class Solution {
    
    static final int WALL = 1;
    int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 우측 방향이 0
    
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        
        // {x, y, corner, dir, dist} - dir: 마지막으로 움직였던 방향
        // 코너 기준 내림차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int costA = a[2] * 500 + a[4] * 100;
            int costB = b[2] * 500 + b[4] * 100;
            return Integer.compare(costA, costB);
        });
        int[][][] cost = new int[4][n][n];
        for (int[][] layer : cost)
            for (int[] row : layer)
                Arrays.fill(row, Integer.MAX_VALUE);
        pq.add(new int[]{0, 0, 0, -1, 0}); // 아직 움직이지 않았으므로 dir을 -1로 설정
            
        while(!pq.isEmpty()) {
            int x = pq.peek()[0];
            int y = pq.peek()[1];
            int corner = pq.peek()[2];
            int lastDir = pq.peek()[3];
            int dist = pq.poll()[4];
            
            if (x == n-1 && y == n-1) {
                System.out.println("dist=" + dist + ", corner="+corner);
                return dist * 100 + corner * 500;
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + offset[dir][0];
                int ny = y + offset[dir][1];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (board[ny][nx] == WALL) continue;
                
                // 이동하는 방향이 이전과 다르다면 코너임
                int nextCorner = (lastDir == -1 || lastDir == dir) ? corner : corner + 1;
                int nextCost = nextCorner * 500 + (dist+1) * 100;
                
                if (cost[dir][ny][nx] > nextCost) {
                    pq.add(new int[]{nx, ny, nextCorner, dir, dist + 1});
                    cost[dir][ny][nx] = nextCost;
                }
            }
        }
        
        return answer;
    }
}