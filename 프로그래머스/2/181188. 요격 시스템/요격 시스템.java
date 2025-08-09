import java.util.*;

class Solution {
    public int solution(int[][] targets) {     
        Arrays.sort(targets, (a, b) -> a[0] - b[0]);
        int preStart = targets[0][0];
        int preEnd = targets[0][1];
        int answer = 1; // 멘 처음 target을 요격하는 미사일
        
        
        // 굵은 선을 그린다고 하고, 이 선을 최대한 굵게 유지시키면 됨
        // 선이 굵게 유지되어야 더 많은 미사일을 포함시킬 수 있으니까
        for (int i = 1; i < targets.length; i++) {
            int curStart = targets[i][0];
            int curEnd = targets[i][1];
            
            if (preStart <= curStart && curStart < preEnd) {
                preStart = Math.max(preStart, curStart);
                preEnd = Math.min(preEnd, curEnd);
            } else {
                answer++;
                preStart = curStart;
                preEnd = curEnd;
            }
        }
        
        return answer;
    }
}
