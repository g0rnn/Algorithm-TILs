
import java.util.*;
import java.io.*;

public class Main {

    static String L, R;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    private static int solution() {
        if (L.length() < R.length()) return 0; // 자릿수 차이가 나면 무조건 0
        
        int ans = 0;
        for (int i = 0; i < L.length(); i++) {
            if (L.charAt(i) != R.charAt(i)) break;
            if (L.charAt(i) == '8') ans++;
        }
        return ans;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        L = input[0];
        R = input[1];
        br.close();
    }
}
