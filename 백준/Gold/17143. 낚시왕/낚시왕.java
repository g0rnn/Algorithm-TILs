import java.io.*;
import java.util.*;

public class Main {
	static int R, C, m;
	static int[][] offset = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	static Shark[][] board;
	static List<Shark> sharks = new ArrayList<>();
	
	static class Shark{
		int r, c, step, dir, size;
		boolean isDead;
		Shark(int r, int c, int step, int dir, int size){
			this.r=r;this.c=c;this.step=step;this.dir=dir;this.size=size;
			isDead = false;
		}
		
		void move(Shark[][] map) {
			int nr = this.r;
			int nc = this.c;
			
			int period;
			if (dir < 2) period = step % (2 * (R - 1));
			else period = step % (2 * (C - 1));
			
			for (int i = 0; i < period; i++) {
				nr += offset[dir][1];
				nc += offset[dir][0];
				
				if (nr <= 0 || nc <= 0 || nr > R || nc > C) {
					dir = reverse(dir);
					nr += 2 * offset[dir][1];
					nc += 2 * offset[dir][0];
				}
			}
			
			
			r = nr; c = nc;
			if (map[r][c] != null) {
				Shark other = map[r][c];
				if (other.size < this.size) {
					map[r][c] = this;
					other.isDead = true;
				} else this.isDead = true;
			} else map[r][c] = this;
		}
		
		private int reverse(int d) {
			if (d == 0) return 1;
			if (d == 1) return 0;
			if (d == 2) return 3;
			return 2;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new Shark[R+1][C+1];
		Shark[][] nextBoard = new Shark[R+1][C+1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int _r = Integer.parseInt(st.nextToken());
			int _c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			Shark shark = new Shark(_r, _c, s, d, z);
			board[_r][_c] = shark;
			sharks.add(shark);
		}
		
		int total = 0;
		for (int i = 1; i <= C; i++) {
			total += hunt(i);
			
			moveAll(nextBoard);
			board = nextBoard;
			nextBoard = new Shark[R+1][C+1];
		}
		System.out.println(total);
	}
	
	private static void moveAll(Shark[][] nextBoard) {
		for (Shark s : sharks) {
			if (s.isDead) continue;
			s.move(nextBoard);
		}
	}
	
	private static int hunt(int col) {
		for (int row = 1; row <= R; row++) {
			if (board[row][col] != null && !board[row][col].isDead) {
				board[row][col].isDead = true;
				return board[row][col].size;
			}
		}
		return 0;
	}
}

