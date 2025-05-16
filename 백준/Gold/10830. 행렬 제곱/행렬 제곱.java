
import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long B;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        input();
        int[][] answer = squareMatrix();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void initSquare(int[][][] squared) {
        int idx = 0;
//        int sqCount = 1;
        squared[idx] = deepCopyOf(matrix); // idx: 0, 행렬 A의 0 제곱

        while (idx < 62) {
            squared[idx + 1] = multiplyMatrix(squared[idx], squared[idx]);
            idx += 1;
//            sqCount *= 2;
        }
    }

    private static int[][] squareMatrix() {
        // IDX 0: (matrix)^0, IDX 1: (matrix)^1, IDX n: (matrix)^n
        int[][][] squared = new int[64][N][N]; // log2(10^12) = 36
        initSquare(squared);

        int powIdx = 0;
        int[][] answer = new int[N][N];
        for (int i = 0; i < N; i++) {
            answer[i][i] = 1;
        }

        // 비트 연산을 통해 B제곱을 구함
        // 현재 비트가 1인 경우에만 answer에 곱하여 (matrix)^n을 만든다
        while (B > 0) {
            if ((B & 1) == 1) {
                answer = multiplyMatrix(answer, squared[powIdx]);
            }
            powIdx += 1;
            B >>= 1;
        }
        return answer;
    }

    // O(1) = 25
    private static int[][] multiplyMatrix(int[][] mat, int[][] mat2) {
        int[][] ret = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    ret[i][j] = (ret[i][j] + mat[i][k] * mat2[k][j]) % 1000;
                }
            }
        }

        return ret;
    }

    private static int[][] deepCopyOf(int[][] src) {
        int[][] copy = new int[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone();  // 각 행을 개별적으로 복사
        }
        return copy;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }
}
