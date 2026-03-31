import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Long> nums = new ArrayList<>();
		boolean[] chosen = new boolean[10];
		subset(nums, chosen, 0);
		nums.sort(Comparator.naturalOrder());
		if (n >= nums.size()) System.out.println(-1);
		else System.out.println(nums.get(n));
	}
	
	private static void subset(List<Long> nums, boolean[] chosen, int idx) {
		if (idx == 10) {
			StringBuilder sb=new StringBuilder();
			for (int i = 9; i >= 0; i--) {
				if (chosen[i]) sb.append(i);
			}
			if (sb.length() > 0) nums.add(Long.parseLong(sb.toString()));
			return;
		}
		
		chosen[idx] = true;
		subset(nums, chosen, idx+1);
		
		chosen[idx] = false;
		subset(nums, chosen, idx+1);
	}
}
