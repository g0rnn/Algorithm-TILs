
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        br.close();

        System.out.println(z(n, r, c));
    }

    private static int z(int n, int r, int c) {
        if (n == 0) return 0;

        int div = 1 << (n-1);
        int size = div * div;

        if (r < div && c < div) {
            return z(n - 1, r, c);
        } else if (r < div && c >= div) {
            return size + z(n - 1, r, c - div);
        } else if (r >= div && c < div) {
            return size * 2 + z(n - 1, r - div, c);
        } else {
            return size * 3 + z(n - 1, r - div, c - div);
        }
    }
}
