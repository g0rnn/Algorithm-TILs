import java.util.*;
import java.io.*;

public class Main {
	static int n = 8;
	static char[][] grid;
	static int[][] offset = {{1, 0}, {1, 1}, {1, -1}, {-1, 0}, {-1, 1}, {-1, -1}, {0, 1}, {0, -1}, {0, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		grid = new char[n][n];
		
		for (int i = 0; i < n; i++)
			grid[i] = br.readLine().toCharArray();
		
		System.out.println(bfs());
	}
	
	// 시간에 따라 맵이 바뀌기 때문에 시간이 상태임
	// 시간에 따라 갔던 곳을 이동할 수도 있음.
	// 이전엔? 벽을 부쉈는지 여부에 따라 갔던 곳을 이동할 수도 있음.
	
	// 시간이 2일 때를 확인해야할텐데 시간이 2일 때 맵이 저장되지 않고 있음.. -> 아님. 애초에 큐는 size에 맞게 돌아가기 때문에 시간이 2일 때 시간이 2인 맵에서 전부를 확인하고있음
	private static int bfs() {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[8][8][50]; // {y, x, time}
		
		int time = 0;
		q.offer(new int[] {0, 7});
		visited[7][0][time] = true;
		
		while (!q.isEmpty() && time < 50) {

			int size = q.size();
			
			while (size-- > 0) {
				int x = q.peek()[0];
				int y = q.poll()[1];
				if (grid[y][x] == '#') continue; // 죽음;
				if (x == 7 && y == 0) return 1;
				
				for (int d =0; d < 9; d++) {
					int nx = x + offset[d][0];
					int ny = y + offset[d][1];
					
					if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
					if (visited[ny][nx][time+1]) continue;
					if (grid[ny][nx] == '#') continue;
					
					q.offer(new int[] {nx, ny});
					visited[ny][nx][time+1] = true;
				}
			}
			
			// 벽 움직이기
			// 이동해야하는데 마지막 행이면 지워야함.
			for (int j = 0; j < n; j++) if (grid[7][j] =='#')grid[7][j]= '.';
			
			for (int j = 0; j < n; j++) {
				for (int i = n-1; i > 0; i--) {
					grid[i][j] = grid[i-1][j];
				}
				grid[0][j] = '.';
			}
			
			time++;
		}
		
		return 0;
	}
}
