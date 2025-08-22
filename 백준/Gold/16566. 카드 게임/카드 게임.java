
import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[] cards;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        cards = new int[m];
        for (int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        // DSU: 서로 겹치지 않는 집합
        parent = new int[m + 1];
        for (int i = 0; i <= m ; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());

            int idx = upperBound(cards, x);
            int p = find(idx);
            sb.append(cards[p]).append('\n');
            use(p);
        }
        System.out.println(sb);
    }

    private static int upperBound(int[] cards, int x) {
        int l = 0, r = cards.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (cards[mid] <= x) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void use(int idx) {
        parent[idx] = find(idx + 1);
    }
}
