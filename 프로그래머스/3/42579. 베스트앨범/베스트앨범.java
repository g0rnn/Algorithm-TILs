import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Integer>> playlist = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if (!playlist.containsKey(genres[i])) playlist.put(genres[i], new ArrayList<>());
            playlist.get(genres[i]).add(i);
        }
        
        Map<Integer, String> sumToKey = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> e : playlist.entrySet()) {
            int sum = 0;
            for (int idx : e.getValue()) sum += plays[idx];
            sumToKey.put(sum, e.getKey());
            list.add(sum);
        }
        
        List<Integer> answer = new ArrayList<>();
        list.sort(Comparator.reverseOrder()); // 기본 정렬은 매개변수로 안넘겨도 되는걸로 앎.
        for (int sum : list) {
            String key = sumToKey.get(sum);
            List<Integer> idxs = playlist.get(key);
            idxs.sort((a, b) -> {
                if (plays[a] == plays[b]) return Integer.compare(a, b);
                return Integer.compare(plays[b], plays[a]);
            });
            for (int j = 0; j < Math.min(2, idxs.size()); j++) answer.add(idxs.get(j));
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}