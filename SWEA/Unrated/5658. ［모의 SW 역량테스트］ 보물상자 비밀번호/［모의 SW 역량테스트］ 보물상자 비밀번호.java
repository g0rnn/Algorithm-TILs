
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			String input = br.readLine();
			input += input.substring(0, n/4);
			
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < n; i++) {
				String sub = input.substring(i, i+n/4);
				set.add(Integer.parseInt(sub, 16));
			}
			Integer[] arr = set.toArray(new Integer[0]);
			Arrays.sort(arr, Collections.reverseOrder());
			sb.append("#").append(tc).append(" ").append(arr[k-1]).append("\n");
		}
		System.out.println(sb);
	}
	
}
