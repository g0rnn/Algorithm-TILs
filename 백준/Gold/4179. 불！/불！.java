import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static char[][] grid;
	static Deque<int[]> jihun, fire;
	static int[][] jVis, fVis;
	static int[][] offset = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		grid = new char[n][m];
		jVis = new int[n][m];
		fVis = new int[n][m];
		jihun = new ArrayDeque<>();
		fire = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			Arrays.fill(jVis[i], -1);
			Arrays.fill(fVis[i], -1);
		}
		
		for (int i = 0; i < n; i++) {
			grid[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 'J') {
					jihun.offer(new int[] {j, i});
					jVis[i][j] = 0;
				}
				else if (grid[i][j] == 'F') {
					fire.offer(new int[] {j, i});
					fVis[i][j] = 0;
				}
			}
		}
		
		while (!fire.isEmpty()) {
			int x = fire.peek()[0];
			int y = fire.poll()[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + offset[d][0];
				int ny = y + offset[d][1];
				
				if (!inRange(nx, ny)) continue;
				if (grid[ny][nx] == '#')continue;
				if (fVis[ny][nx] != -1) continue;
				
				fVis[ny][nx] = fVis[y][x] + 1;
				fire.offer(new int[] {nx, ny});
			}
		}
		
		int val = bfs();
		if (val == -1) System.out.println("IMPOSSIBLE");
		else System.out.println(val);
	}
	
	private static int bfs() {
		
		while (!jihun.isEmpty()) {
			int x = jihun.peek()[0];
			int y = jihun.poll()[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + offset[d][0];
				int ny = y + offset[d][1];
				
				if (!inRange(nx, ny)) return jVis[y][x] + 1;
				if (grid[ny][nx] == '#') continue;
				if (jVis[ny][nx] != -1) continue;
				if (fVis[ny][nx] != -1 && fVis[ny][nx] <= jVis[y][x] + 1) continue;
				
				jVis[ny][nx] = jVis[y][x] + 1;
				jihun.offer(new int[] {nx, ny});
			}
		}
		return -1;
	}
	
	private static boolean inRange(int x, int y) {
		return 0 <= x && x < m && 0 <= y && y < n;
	}
}
