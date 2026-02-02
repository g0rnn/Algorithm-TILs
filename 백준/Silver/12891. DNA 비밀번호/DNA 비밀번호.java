import java.util.*;
import java.io.*;

public class Main {
	
	static int[] alpha = new int[26];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		char[] dna = br.readLine().toCharArray();
		st = new StringTokenizer(br.readLine());
		
		alpha['A' - 'A'] = Integer.parseInt(st.nextToken());
		alpha['C' - 'A'] = Integer.parseInt(st.nextToken());
		alpha['G' - 'A'] = Integer.parseInt(st.nextToken());
		alpha['T' - 'A'] = Integer.parseInt(st.nextToken());
		
		int[] cnt = new int[26];
		int sum = 0;
		for (int i = 0; i < p; i++) cnt[dna[i] - 'A'] += 1;
		
		if (check(cnt)) sum++;
		
		for (int i = p; i < s; i++) {
			cnt[dna[i] - 'A']++;
			cnt[dna[i-p] - 'A']--;
			if (check(cnt)) sum++;
		}
		
		System.out.println(sum);
		br.close();
	}
	
	private static boolean check(int[] cnt) {
		if (cnt['A' - 'A'] < alpha['A' - 'A']) return false;
		if (cnt['C' - 'A'] < alpha['C' - 'A']) return false;
		if (cnt['G' - 'A'] < alpha['G' - 'A']) return false;
		if (cnt['T' - 'A'] < alpha['T' - 'A']) return false;
		return true;
	}
}
