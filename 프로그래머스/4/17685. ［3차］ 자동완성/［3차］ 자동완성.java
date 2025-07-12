import java.util.*;

class Solution {
    public int solution(String[] words) {
        int[] prefixLengths = new int[words.length]; // 각 단어의 최소 입력 길이
        boolean[] gameOver = new boolean[words.length]; // 고유 prefix 판단 완료 여부
        int gameOverCount = 0;

        for (int i = 0; i < 100; i++) { // 최대 단어 길이 100으로 충분
            if (gameOverCount == words.length) break;

            Map<String, Integer> countMap = new HashMap<>();

            // i번째 글자를 기준으로 등장 횟수 집계
            for (int j = 0; j < words.length; j++) {
                if (gameOver[j]) continue;
                if (words[j].length() <= i) continue; // 글자가 더 이상 없으면 skip

                String key = words[j].substring(0, i + 1);
                countMap.merge(key, 1, Integer::sum);
            }

            // 다시 단어를 순회하면서 고유 prefix인지 판단
            for (int j = 0; j < words.length; j++) {
                if (gameOver[j]) continue;
                if (words[j].length() <= i) continue;

                String key = words[j].substring(0, i + 1);
                if (countMap.get(key) == 1) {
                    prefixLengths[j] = i + 1;
                    gameOver[j] = true;
                    gameOverCount++;
                }
            }
        }

        // 단어 끝까지 봐도 고유 prefix가 안 나온 경우 전체 길이 사용
        for (int i = 0; i < words.length; i++) {
            if (prefixLengths[i] == 0) prefixLengths[i] = words[i].length();
        }

        // 결과 합산
        int answer = 0;
        for (int len : prefixLengths) answer += len;
        return answer;
    }
}