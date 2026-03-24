import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] parent;
	static int[] size;
	static List<Edge> edges = new ArrayList<>();
	
	static class Edge {
		int from, to;
		int weight;
		int fixed;
		Edge(int from, int to, int weight, int fixed) {this.fixed=fixed;this.from=from;this.to=to;this.weight=weight;}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		size = new int[n+1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		
		int total = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, w, d));
			total += w;
		}
		edges.sort((a, b)-> {
			if (a.fixed == b.fixed) return Integer.compare(b.weight, a.weight);
			return Integer.compare(b.fixed, a.fixed);
		});
		int cnt = 1;
		int MSTWeight = 0;
		for (Edge edge : edges) {
			if (!union(edge.from, edge.to) ) continue;
			MSTWeight += edge.weight;
			if (++cnt == n) break;
		}
		
		System.out.println(total - MSTWeight);
	}
	
	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) return false;
		
		if (size[pa] > size[pb]) {
			parent[pa] = pb;
			size[pb] += size[pa];
		} else {
			parent[pb] = pa;
			size[pa] = size[pb];
		}
		return true;
	}
	
	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
}
