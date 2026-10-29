import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int day = ((100 - progresses[i]) + speeds[i] - 1) / speeds[i];
            q.offer(day);
        }
        while (!q.isEmpty()) {
            int deployed = q.poll();
            int cnt = 1;
            
            while (!q.isEmpty() && deployed >= q.peek()) {
                q.poll();
                cnt++;
            }
            answer.add(cnt);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}