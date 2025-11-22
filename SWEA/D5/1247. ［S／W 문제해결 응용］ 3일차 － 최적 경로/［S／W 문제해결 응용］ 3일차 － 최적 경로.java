import java.util.*;
import java.util.stream.*;
import java.io.*;

class Solution {
    static int dx, dy;
    static int n;
    static int[][] customer;
    static int result = Integer.MAX_VALUE;
    
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		int T;
		T=Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
            result = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            dx = Integer.parseInt(st.nextToken());
            dy = Integer.parseInt(st.nextToken());
            
            customer = new int[n][2];
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                customer[i][0] = Integer.parseInt(st.nextToken());
            	customer[i][1] = Integer.parseInt(st.nextToken());
            }
            
            backtrack(sx, sy, 0, 0, visited);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
        System.out.println(sb.toString());
	}
    
    private static void backtrack(int x, int y, int cnt, int dist, boolean[] visited) {
        if (dist >= result) return; //여기까지 왔는데 여태 구한 최소거리보다 크면 제껴
        if (cnt == n) {
            int d = dist + Math.abs(x - dx) + Math.abs(y - dy);
            result = Math.min(d, result);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int d = Math.abs(x - customer[i][0]) + Math.abs(y - customer[i][1]);
                backtrack(customer[i][0], customer[i][1], cnt + 1, dist + d, visited);
                visited[i] = false;
            }
        }
    }
}