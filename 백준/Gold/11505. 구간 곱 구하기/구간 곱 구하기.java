import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[] data;
    static long[] trees; // 곱셈 결과가 int 범위를 넘어가므로 long 사용
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        data = new int[n + 1];
        trees = new long[4 * n];

        for (int i = 1; i <= n; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        makeTree(1, n, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (type == 1) {
                update(1, n, b, c, 1);
            } else {
                sb.append(query(1, n, b, c, 1)).append('\n');
            }
        }
        System.out.print(sb);
    }

    // 구간 곱 트리 생성
    private static long makeTree(int s, int e, int cur) {
        if (s == e) {
            return trees[cur] = data[s];
        }
        int mid = (s + e) / 2;
        // 자식 노드의 곱을 구할 때 long 연산 후 MOD
        return trees[cur] = (makeTree(s, mid, cur * 2) * makeTree(mid + 1, e, cur * 2 + 1)) % MOD;
    }

    // 구간 곱 쿼리
    private static long query(int s, int e, int q_s, int q_e, int cur) {
        // 범위를 벗어난 경우 곱셈의 항등원인 1 반환
        if (q_e < s || e < q_s) {
            return 1;
        }
        // 범위에 완전히 포함되는 경우
        if (q_s <= s && e <= q_e) {
            return trees[cur];
        }
        int mid = (s + e) / 2;
        long left = query(s, mid, q_s, q_e, cur * 2);
        long right = query(mid + 1, e, q_s, q_e, cur * 2 + 1);
        return (left * right) % MOD;
    }

    // 값 업데이트
    private static long update(int s, int e, int targetIdx, int val, int cur) {
        // 타겟 인덱스가 범위 밖이면 현재 노드 값 그대로 반환
        if (targetIdx < s || targetIdx > e) {
            return trees[cur];
        }
        // 리프 노드에 도달했을 때 값 변경
        if (s == e) {
            return trees[cur] = val;
        }
        int mid = (s + e) / 2;
        long left = update(s, mid, targetIdx, val, cur * 2);
        long right = update(mid + 1, e, targetIdx, val, cur * 2 + 1);
        // 변경된 자식 노드 값을 바탕으로 부모 노드 갱신
        return trees[cur] = (left * right) % MOD;
    }
}