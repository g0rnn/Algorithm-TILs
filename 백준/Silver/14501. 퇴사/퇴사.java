
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] times = new int[n];
		int[] profits = new int[n];
		int[] dp = new int[n + 2]; // 상담은 최대 5일 동안 진행됨
		
		for (int i = 0 ; i < n; i++) {
			String[] node = br.readLine().split(" ");
			times[i] = Integer.parseInt(node[0]);
			profits[i] = Integer.parseInt(node[1]);
		}
		
		// dp[day] : 오늘까지 오면서 번 최대 이익
		// 오늘 상담하지 않는 경우 & 상담하는 경우 모두 계산해준다.
		
		
		// 오늘 일을 안 한다면, 오늘까지 번 돈(dp[day])이 그대로 내일(dp[day+1])로 이어진다. 이어질 경우 둘 중 더 큰값을 설정해야한다.
		// 오늘 상담한다면 t일 후 기존에 그날짜에 기록된 최대 수익 vs (오늘까지 번 돈 + 이번 상담수익)을 계산
		for (int day = 0; day < n; day++) {
			int endDay = day + times[day]; // 오늘 받은 상담의 종료일자
			
			dp[day+1] = Math.max(dp[day+1], dp[day]); // 오늘 상담하지 않은 경우
			if (endDay <= n) dp[endDay] = Math.max(dp[day] + profits[day], dp[endDay]); // 오늘 상담하는 경우
		}
		
		// 마지막 날에 1일 상담을 받으면 퇴사일(n)에 돈 받을 수 있음.
		System.out.println(dp[n]);
		
	}

}
