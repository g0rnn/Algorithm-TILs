import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        int gameOverCount = 0;
        boolean[] gameOver = new boolean[words.length];
        List<Map<String, Integer>> report = new ArrayList<>(1_000_000);
        
        for (int i = 0; i < 1_000_000; i++) { // i : 자릿수
            if (gameOverCount == words.length) break; // 종료해야할 시점
            
            report.add(new HashMap<>());
            
            for (int j = 0; j < words.length; j++) { // j : words의 index                
                if (gameOver[j]) continue; // 이미 작업을 완료한 word 라면 스킵
                // 자릿수를 초과하거나 unique판정이 끝난 word는 이제 없음
                String word = words[j];
                report.get(i).merge(word.substring(i, i+1), 1, Integer::sum);
                
                if (word.length() == i + 1) {
                    gameOver[j] = true; // 단어의 끝에 도달했으면 해당 단어는 game over
                    gameOverCount++;
                }
            }
            // unique 판정이 끝나면 이스케이프
            // 맵을 살펴보고 1이 나온게 있으면 해당 단어는 이스케이프
            for (Map.Entry<String, Integer> entry : report.get(i).entrySet()) {
                if (entry.getValue() == 1) {
                    // 단어 이스케이프
                    int index = findWord(words, i, entry.getKey());
                    gameOver[index] = true;
                    gameOverCount++;
                }
                answer += entry.getValue();
            }
            
        }
        return answer;
    }
    
    private int findWord(String[] words, int index, String target) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() <= index) continue;
            if (words[i].substring(index, index+1).equals(target)) return i;
        }
        return -1;
    }
}