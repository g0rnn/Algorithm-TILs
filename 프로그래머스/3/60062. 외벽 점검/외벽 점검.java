import java.util.*;

class Solution {
    
    public int solution(int n, int[] weak, int[] dist) {
        int weakLen = weak.length;
        int[] resizedWeak = new int[weakLen * 2];
        
        // weak를 늘여서 원형으로 검사할 수 있도록
        for (int i = 0; i < weakLen; i++) {
            resizedWeak[i] = weak[i];
            resizedWeak[i + weakLen] = weak[i] + n;
        }
        
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] visited = new boolean[dist.length];
        List<Integer> cur = new ArrayList<>();
        permute(dist, 0, visited, cur, permutations);
        
        int answer = 9;
        for (List<Integer> permuteDist : permutations) {
            // 앞에서부터 잘나가며 사용 -> 중복 감내해야함..ㅜㅜ
            for (int i = 1; i <= permuteDist.size(); i++) {
                List<Integer> subList = permuteDist.subList(0, i);
                if (check(subList, resizedWeak, weakLen)) {
                    answer = Math.min(answer, subList.size());
                }
            }
        }
        
        if (answer == 9) {
            return -1;
        }
        return answer;
    }
    
    private void permute(int[] dist, int depth, boolean[] visited, List<Integer> cur, List<List<Integer>> result) {
        if (depth == dist.length) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            cur.add(dist[i]);
            permute(dist, depth + 1, visited, cur, result);
            cur.remove(cur.size() - 1);
            visited[i] = false;
        }
    }
    
    private boolean check(List<Integer> friends, int[] weak, int weakLen) {   
        // 모든 경우의 수를 검사
        // 약점마다 친구를 두면서 가능한지 확인
        for (int i = 0; i < weakLen; i++) {
            int friendIdx = 0; // 투입할 친구의 index
            int pos = weak[i] + friends.get(friendIdx);
            
            // 이제 점검 시작
            int j = i; // 현재 점검중인 weak의 index
            while (j < i + weakLen) {
                if (weak[j] > pos) {
                    friendIdx++;
                    if (friendIdx == friends.size()) break;
                    pos = weak[j] + friends.get(friendIdx);
                }
                
                j++; // 다음 취약점
            }
            if (j >= i + weakLen) return true; // j가 한바퀴를 돌면(i + weakLen보다 크면) 모든 weak를 커버한거임
            
        }
        return false;
    }
}