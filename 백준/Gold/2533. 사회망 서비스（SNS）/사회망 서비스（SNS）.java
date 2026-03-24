import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static List<Integer>[] graph;
	static int[][] dp;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n+1][2];
		parent = new int[n+1];
		graph = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		dfsWithStack(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	private static void dfsWithStack(int root) {
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> postOrder = new Stack<>();
		boolean[] visited = new boolean[n+1];
		
		stack.push(root);
		visited[root] = true;
		
		while (!stack.isEmpty()) {
			int u = stack.pop();
			postOrder.push(u);
			
			for (int v : graph[u]) {
				if (visited[v]) continue;
				visited[v] = true;
				parent[v] = u;
				stack.push(v);
			}
		}
		
		while(!postOrder.isEmpty()) {
			int u = postOrder.pop();
			
			dp[u][0] = 0;
			dp[u][1] = 1;
			
			for (int v : graph[u]) {
				if (v == parent[v]) continue;
				dp[u][0] += dp[v][1];
				dp[u][1] += Math.min(dp[v][0], dp[v][1]);
			}
		}
	}
}
