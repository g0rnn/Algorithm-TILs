import java.util.*;

class Solution {
    
    private int size;
    private List<List<int[]>> empty = new ArrayList<>();
    private List<List<int[]>> puzzles = new ArrayList<>();
    private int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int solution(int[][] game_board, int[][] table) {
        size = table.length;
        boolean[][] eVisited = new boolean[size][size];
        boolean[][] pVisited = new boolean[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!eVisited[i][j] && game_board[i][j] == 0) {
                    empty.add(extractShape(i, j, game_board, eVisited, 0));
                }
                if (!pVisited[i][j] && table[i][j] == 1) {
                    puzzles.add(extractShape(i, j, table, pVisited, 1));
                }
            }
        }
        
        
        return match();
    }
    
    private int match() {
        int maxSize = puzzles.size();
        boolean[] used = new boolean[maxSize];
        int cnt = 0;
        
        for (List<int[]> shape : empty) {
            for (int i = 0; i < maxSize; i++) {
                if (used[i]) continue;
                
                List<int[]> puzzle = puzzles.get(i);
                if (matchRotatedPuzzles(shape, puzzle)) {
                    cnt += puzzle.size();
                    used[i] = true;
                    break;
                }
            }
        }
        
        return cnt;
    }
    
    private boolean matchRotatedPuzzles(List<int[]> shape, List<int[]> puzzle) {
        for (int i = 0; i < 4; i++) {
            if (compare(shape, puzzle)) return true;
            puzzle = rotate(puzzle);
        }
        
        return false;
    }
    
    // 왼쪽으로 90도만 회전시킴
    private List<int[]> rotate(List<int[]> puzzle) {
        List<int[]> rotated = new ArrayList<>();
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        
        for (int[] coor : puzzle) {
            minx = Math.min(minx, coor[0]);
            miny = Math.min(miny, coor[1]);
        }
        
        for (int[] coor : puzzle) {
            rotated.add(new int[]{coor[1] - minx, -1 * coor[0] - miny});
        }
        
        normalize(rotated);
        return rotated;
    }
    
    private boolean compare(List<int[]> shape, List<int[]> puzzle) {
        if (shape.size() != puzzle.size()) return false;
        for (int i = 0; i < shape.size(); i++) {
            if (shape.get(i)[0] != puzzle.get(i)[0]) return false;
            if (shape.get(i)[1] != puzzle.get(i)[1]) return false;
        }
        return true;
    }
    
    private List<int[]> extractShape(int i, int j, int[][] board, boolean[][] visited, int SHAPE) {
        List<int[]> shape = new ArrayList<>();
        Deque<int[]> dq = new ArrayDeque<>();
        visited[i][j] = true;
        dq.add(new int[]{j, i});
        shape.add(new int[]{j, i});
                
        while (!dq.isEmpty()) {
            int x = dq.peek()[0];
            int y = dq.poll()[1];
            
            for (int[] o : offset) {
                int nx = x + o[0];
                int ny = y + o[1];
                
                if (nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
                if (visited[ny][nx]) continue;
                if (board[ny][nx] == SHAPE) {
                    dq.add(new int[]{nx, ny});
                    visited[ny][nx] = true;
                    shape.add(new int[]{nx, ny});
                }
            }
        }
        normalize(shape);
        return shape;
    }
    
    private void normalize(List<int[]> shape) {
        shape.sort((a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        
        for (int[] coor : shape) {
            minx = Math.min(coor[0], minx);
            miny = Math.min(coor[1], miny);
        }
        
        for (int[] coor : shape) {
            coor[0] -= minx;
            coor[1] -= miny;
        }
    }
}