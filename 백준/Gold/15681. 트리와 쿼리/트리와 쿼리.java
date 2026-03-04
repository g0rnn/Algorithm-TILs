import java.util.*;
import java.io.*;

/*
 * 유니온 파인드 처럼 만드는 방법 -> parent 배열과 size배열을 사용
 * 참조를 통해 Node를 직접 만드는 방법 -> 근데 방향이 없고, 연결 정보만 있는게 누가 부모인지 어떻게 앎?
 */

public class Main {
	static int n, r, q;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		graph = new ArrayList[n+1];
		cnt = new int[n+1];
		//Arrays.fill(cnt, 1);
		
		for (int i = 1; i <=n; i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		visited[r]=true;
		dfs(r);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			int node = Integer.parseInt(br.readLine());
			sb.append(cnt[node]).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int dfs(int root) {		
		int size = 0;
		for (int adj : graph[root]) {
			if (visited[adj]) continue;
			visited[adj] = true;
			size += dfs(adj);
		}
		
		cnt[root] = size+1;
		
		return size+1;
	}
}
