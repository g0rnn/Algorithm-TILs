import java.util.*;
import java.io.*;

public class Main {
    static long[][] trees;
    static final int MIN = 0;
    static final int MAX = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        trees = new long[4*n][2];
        long[] nums = new long[n+1];
        for (int i = 1; i <= n; i++) nums[i] = Long.parseLong(br.readLine());

        init(nums, 1, 1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(queryMin(a, b, 1, n, 1)).append(" ").append(queryMax(a, b, 1, n, 1)).append("\n");
        }
        System.out.println(sb);
    }

    private static void init(long[] nums, int node, int start, int end) {
        if (start == end) {
            trees[node][MIN] = nums[start];
            trees[node][MAX] = nums[start];
            return;
        }

        init(nums, node * 2, start, (start+end) / 2);
        init(nums, node*2+1, (start+end)/2 + 1, end);
        trees[node][MIN] = Math.min(trees[node*2][MIN], trees[node*2+1][MIN]);
        trees[node][MAX] = Math.max(trees[node*2][MAX], trees[node*2+1][MAX]);
    }

    // fStart, fEnd: 찾고자 하는 범위의 시작과 끝
    private static long queryMin(int fStart, int fEnd, int start, int end, int node) {
        if (fEnd < start || end < fStart) return Long.MAX_VALUE;
        if (fStart <= start && end <= fEnd) return trees[node][MIN];

        long left = queryMin(fStart, fEnd, start, (start+end)/2, node*2);
        long right = queryMin(fStart, fEnd, (start+end)/2+1, end, node*2+1);
        return Math.min(left, right);
    }

    private static long queryMax(int fStart, int fEnd, int start, int end, int node) {
        if (fEnd < start || end < fStart) return Long.MIN_VALUE;
        if (fStart <= start && end <= fEnd) return trees[node][MAX];

        long left = queryMax(fStart, fEnd, start, (start+end)/2, node*2);
        long right = queryMax(fStart, fEnd, (start+end)/2+1, end, node*2+1);
        return Math.max(left, right);
    }
}
