
import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static int M; // 홀수
    static int[] arr;

    public static void main(String[] args) throws IOException {
        input();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            M = Integer.parseInt(br.readLine());

            // 중앙값 개수 출력
            System.out.println((M + 1) / 2);

            PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> right = new PriorityQueue<>();

            int printed = 0; // 현재 줄에 출력된 개수
            int inputCount = 0;

            while (inputCount < M) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    int num = Integer.parseInt(st.nextToken());
                    inputCount++;

                    if (left.size() == right.size()) {
                        left.offer(num);
                    } else {
                        right.offer(num);
                    }

                    // 정렬 유지
                    if(!right.isEmpty() && left.peek() > right.peek()) {
                        Integer l = left.poll();
                        Integer r = right.poll();
                        left.offer(r);
                        right.offer(l);
                    }

                    // 홀수 번째 입력마다 중앙값 출력
                    if (inputCount % 2 == 1) {
                        System.out.print(left.peek() + " ");
                        printed++;
                        if (printed % 10 == 0) System.out.println(); // 줄 바꿈
                    }
                }
            }
            if (printed % 10 != 0) System.out.println();
        }
        br.close();
    }
}
