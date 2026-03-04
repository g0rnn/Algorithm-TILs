import java.util.*;
import java.io.*;

// 이전에 건 상태가 3개임 {위치, gas/탐험가, 시간} -- 근데 이 시간이라는 상태는 외부로 분리할 수 있긴함.
// 상태가 2개임. {위치, 벽을 깼는지}
public class Main {
	static int n, m;
	static char[][] grid;
	static int[][] offset = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		grid = new char[n][m];
		for (int i = 0; i < n; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		
		System.out.println(bfs());
	}
	
	// 벽을 부수고 더 빠른 경로를 만날 수 는 있음. 근데 그렇다고 같은 dist[][] 를 써버리면 나중에 깨야 하는 경우를 판별하지 못함.
	// 그래서 이 경우엔 3차원 배열을 사용하는 것으로 합의봄.
	private static int bfs() {
		Deque<Node> q = new ArrayDeque<>();
		int[][][] dist = new int[n][m][2];
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < m; j++)
				Arrays.fill(dist[i][j], -1);
		
		q.offer(new Node(0, 0, false));
		dist[0][0][0] = 1;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x, y = cur.y;
			int b = cur.broken ? 1 : 0;
			if (x == m-1  && y == n-1) return dist[y][x][b];
			
			for (int d = 0; d < 4; d++) {
				int nx = x+offset[d][0];
				int ny = y+offset[d][1];
				
				if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
				
				if (grid[ny][nx] == '0') {
					if (dist[ny][nx][b] != -1) continue;
					dist[ny][nx][b] = dist[y][x][b] + 1;
					q.offer(new Node(nx, ny, cur.broken));
				}
				else {
					if (cur.broken) continue;
					if (dist[ny][nx][1] != -1) continue;
					dist[ny][nx][1] = dist[y][x][0]+1;
					q.offer(new Node(nx, ny, true));
				}
			}
		}
		
		return -1;
	}
	
	static class Node {
		int x, y;
		boolean broken;
		Node(int xx, int yy, boolean bb) {x = xx; y = yy;broken = bb;}
	}
}
