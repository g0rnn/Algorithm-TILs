
import java.util.*;
import java.io.*;

public class Main {

    static int[] nums = new int[10];
    static int[] baseBoard = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0};
    static int[] extraBoard1 = {10, 13, 16, 19, 25, 30, 35, 40, 0};
    static int[] extraBoard2 = {20, 22, 24, 25, 30, 35, 40, 0};
    static int[] extraBoard3 = {30, 28, 27, 26, 25, 30, 35, 40, 0};
    static Player[] hoses = new Player[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        List<List<Integer>> players = new ArrayList<>();
        backtrack(players, new ArrayList<>());

        for (List<Integer> player : players) {
            answer = Math.max(startGame(player), answer);
        }
        System.out.println(answer);
        br.close();
    }

    // 구성된 말로 윷놀이 수행 & 계산된 값 반환
    private static int startGame(List<Integer> player) {
        int count = 0;

        for (int i = 1; i <= 4; i++) {
            hoses[i] = new Player(i);
        }

        for (int i = 0; i < 10; i++) {
            int num = player.get(i);
            Player p = hoses[num];
            if (p.finished) {
                continue;
            }

            // move
            int jump = nums[i];

            count += p.move(jump);

            if (isDuplicate()) {
                return 0;
            }
        }
        return count;
    }

    private static boolean isDuplicate() {
        for (int i = 1; i <= 4; i++) {
            if (hoses[i].finished) {
                continue;
            }
            for (int j = i + 1; j <= 4; j++) {
                if (hoses[j].finished) {
                    continue;
                }

                Player p1 = hoses[i];
                Player p2 = hoses[j];

                if (p1.board == p2.board && p1.pos == p2.pos && p1.board[p1.pos] != 0) {
                    return true;
                }

                int point1 = p1.board[hoses[i].pos];
                int point2 = p2.board[hoses[j].pos];
                if (point1 == point2 && point1 != 0 && (point1 == 25 || point1 == 30 || point1 == 35 || point1 == 40)) {
                    if (point1 == 30 && p1.board != p2.board) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> current) {
        if (current.size() == 10) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= 4; i++) {
            current.add(i);
            backtrack(result, current);
            current.remove(current.size() - 1);
        }
    }

    static class Player {
        int num;
        int pos;
        int[] board;
        boolean finished;

        public Player(int num) {
            this.num = num;
            this.pos = 0;
            this.board = baseBoard;
        }

        int move(int idx) {
            pos += idx;
            if (pos >= board.length - 1) {
                finished = true;
                return 0;
            }
            if (board == baseBoard) {
                if (board[pos] == 10) {
                    board = extraBoard1;
                    pos = 0;
                } else if (board[pos] == 20) {
                    board = extraBoard2;
                    pos = 0;
                } else if (board[pos] == 30) {
                    board = extraBoard3;
                    pos = 0;
                }
            }
            if (!finished && pos < board.length) {
                return board[pos];
            }
            finished = true;
            return 0;
        }
    }
}
