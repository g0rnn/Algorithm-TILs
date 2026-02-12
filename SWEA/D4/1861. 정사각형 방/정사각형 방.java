

import java.util.*;
import java.io.*;

public class Solution {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			
			List<int[]> coords = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0 ;j < n; j++) {
					int val = Integer.parseInt(st.nextToken());
					coords.add(new int[] {j, i, val}); // {x, y, val}
				}
			}
			coords.sort((a, b) -> Integer.compare(a[2], b[2]));
			
			int max = 0;
			int minStartRoom = 0; // 가장 작은 넓이의 시작 방번호
			
			int startRoom = coords.get(0)[2]; // 현재 조사중인 구간의 시작 방 번호
			int cnt = 1;
			for (int i = 0; i < coords.size()-1; i++) {
				if (isClose(coords.get(i), coords.get(i+1))) { // 연속되는 방번호라면 넓이 ++
					cnt++;
					continue;
				} 
				
				// 연속되지 않으면 넓이 갱신 및 상태 초기화
				if (max < cnt) {
					max = cnt;
					minStartRoom = startRoom;
				}
				cnt = 1;
				startRoom = coords.get(i+1)[2]; // i와 i+1이 연속되지 않기 때문에 i+1번의 방번호를 넣어줌
			}
			
			// i가 n-1일 때 최대라면
			if (max < cnt) {
				max = cnt;
				minStartRoom = startRoom;
			}
			
			sb.append("#").append(tc).append(" ").append(minStartRoom).append(" ").append(max).append("\n");
 		}
		System.out.println(sb);
	}
	
	private static boolean isClose(int[] cur, int[] next) {
		return Math.abs(cur[0] - next[0]) + Math.abs(cur[1] - next[1]) == 1;
	}
 
 }
