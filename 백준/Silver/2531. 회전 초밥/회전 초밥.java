import java.io.*;
import java.util.*;

// 접시 위엔 여러 종류의 초밥
// k 개 연속으로 먹으면 할인
// i번의 쿠폰이 주어짐
// 먹을 수 있는 초밥의 최대 가짓수

// 접시 수 n, 가짓수 d, 연속 k, 쿠폰번호 c

// n + k  ->> 33000  array

public class Main {
    static int n, d, k, c;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n+k];
        for (int i=0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
        for (int i=0; i < k; i++) arr[n+i] = arr[i];

        // 시작지점부터 살펴가면서 현재가지 살펴온 종류 + 남은 최대 가짓수 했을 때 안되면 가지치기
        Set<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (int start = 0; start < n; start++) {
            for (int i = 0; i < k; i++) set.add(arr[start+i]);

            int cnt = set.size();
            if (!set.contains(c)) cnt++;

            max = Math.max(cnt, max);
            set.clear();
        }

        System.out.println(max);
    }
}
