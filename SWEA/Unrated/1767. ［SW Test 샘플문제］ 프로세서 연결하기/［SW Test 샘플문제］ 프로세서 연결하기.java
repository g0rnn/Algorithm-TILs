
import java.util.*;
import java.io.*;

public class Solution {
	static int n;
	static int[][] map;
	static List<int[]> cores = new ArrayList<>(); // 전선을 연결해야하는 코어들
	static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[] result = new int[2]; // {개수, 선길이}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// static 초기화
			n = Integer.parseInt(br.readLine());
			cores.clear();
			map = new int[n][n];
			result[0] = 1; // 최대를 구해야함 , 아무것도 안고르는 경우는 없음
			result[1] = Integer.MAX_VALUE; // 최소를 구해야함
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j =0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
					if (map[i][j] == 0) continue;
					if (i == 0 || j == 0 || i == n-1 || j == n-1) continue;
					cores.add(new int[] {j, i}); // x, y로 저장
				}
			}
			
			dfs(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(result[1]).append("\n");
		}
		System.out.println(sb);
	}
	
	
	private static void dfs(int coreIdx, int totalLen, int chosen) {
		if (coreIdx == cores.size()) {
			if (result[0] < chosen) {
				result[0] = chosen;
				result[1] = totalLen;
			}
			else if (result[0] == chosen) {
				result[1] = Math.min(result[1], totalLen);

			}
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			// 선연결
			if (!canLine(coreIdx, d)) continue; 
			int cnt = line(coreIdx, d);
			dfs(coreIdx+1, totalLen+cnt, chosen + 1);
			// 선해제
			removeLine(coreIdx, d, cnt);
		}
		dfs(coreIdx+1, totalLen, chosen);
	}
	
	private static boolean canLine(int idx, int dir) {
		int[] coord = cores.get(idx);
		int x = coord[0]; int y = coord[1];
		int nx = x, ny = y;
		int cnt = 0;
		while (true) {
			nx += offset[dir][0];
			ny += offset[dir][1];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
				if (cnt > 0) return true;
				return false;
			}
			if (map[ny][nx] > 0) return false; // 이미 놓여진 곳에 놓으려고 시도함
			cnt++;
		}
	}
	
	private static int line(int idx, int dir) {
		int[] coord = cores.get(idx);
		int x = coord[0]; int y = coord[1];
		int nx = x, ny = y;
		int cnt = 0;
		while (true) {
			nx += offset[dir][0];
			ny += offset[dir][1];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
				if (cnt > 0) return cnt;
				return -1;
			}
			if (map[ny][nx] > 0) return -1; // 이미 놓여진 곳에 놓으려고 시도함
			map[ny][nx] = 2;
			cnt++;
		}
	}
	
	private static void removeLine(int idx, int dir, int cnt) {
		int[] coord = cores.get(idx);
		int x = coord[0]; int y = coord[1];
		int nx = x, ny = y;
		while (cnt-- > 0) {
			nx += offset[dir][0];
			ny += offset[dir][1];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n)	break;
			if (map[ny][nx] == 2) map[ny][nx] = 0;
			else break;
		}
	}
}
