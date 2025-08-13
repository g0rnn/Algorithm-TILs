import java.util.*;
import java.io.*;

public class Main {
    static int T, n, m;
    static int[] a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        List<Integer> partialA = allPartialSum(a, n);
        List<Integer> partialB = allPartialSum(b, m);

        partialA.sort(Integer::compareTo);
        partialB.sort(Integer::compareTo);

        long answer = 0;
        int left = 0, right = partialB.size() - 1;
        while (left < partialA.size() && 0 <= right) {
            int sum = partialA.get(left) + partialB.get(right);
            if (sum == T) {
                long cntA = 1, cntB = 1;
                while (left < partialA.size() - 1 && partialA.get(left).equals(partialA.get(left + 1))) {
                    cntA++;
                    left++;
                }
                while (0 < right && partialB.get(right).equals(partialB.get(right - 1))) {
                    cntB++;
                    right--;
                }
                answer += cntA * cntB;
                left++;
                right--;
            } else if(sum < T) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(answer);
    }

    private static List<Integer> allPartialSum(int[] arr, int size) {
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int sum = 0;
            for (int j = i; j < size; j++) {
                sum += arr[j];
                tmp.add(sum);
            }
        }
        return tmp;
    }
}
