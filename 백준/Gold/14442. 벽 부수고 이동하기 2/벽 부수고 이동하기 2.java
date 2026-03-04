import java.util.*;
import java.io.*;

public class Main {
	static int n, m, k;
	static char[][] grid;
	static int[][] offset = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		grid = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][][] visited= new boolean[n][m][k+1]; 
		
		q.offer(new int[] {0, 0, 1, 0}); // {x, y, 거리, 몇개 부쉈는지}
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			int dist = q.peek()[2];
			int cnt = q.poll()[3];
			
			if (x == m-1&&y == n-1) return dist;
			
			for (int d = 0; d < 4; d++) {
				int nx = x + offset[d][0];
				int ny = y + offset[d][1];
				
				if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;

				if (grid[ny][nx] == '1') {
					if (cnt >= k) continue;
					if (visited[ny][nx][cnt+1]) continue;
					
					q.offer(new int[] {nx, ny, dist+1, cnt+1});
					visited[ny][nx][cnt+1] = true;
				} else {
					if (visited[ny][nx][cnt]) continue;
					
					q.offer(new int[] {nx, ny, dist+1, cnt});
					visited[ny][nx][cnt] = true;
				}
			}
		}
		return -1;
	}
}
