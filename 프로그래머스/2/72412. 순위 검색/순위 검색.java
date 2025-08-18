import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Map<String, List<Integer>> map = makeWithAllKey();
        
        for (String data : info) {
            StringTokenizer st = new StringTokenizer(data);
            String lang = st.nextToken();
            String job = st.nextToken();
            String career = st.nextToken();
            String food = st.nextToken();
            Integer score = Integer.parseInt(st.nextToken());
            
            for (String l : new String[]{lang, "-"}) {
                for (String j : new String[]{job, "-"}) {
                    for (String c : new String[]{career, "-"}) {
                        for (String f : new String[]{food, "-"}) {
                            String key = l + j + c + f;
                            map.get(key).add(score);
                        }
                    }
                }
            }
        }
        
        for (List<Integer> score : map.values()) {
            score.sort(Comparator.naturalOrder());
        }
        
        for (int i = 0; i < query.length; i++) {
            String q = query[i].replaceAll(" and ", " ");
            String[] qs = q.split(" ");
            String key = qs[0] + qs[1] + qs[2] + qs[3];
            Integer target = Integer.parseInt(qs[4]);
            
            List<Integer> score = map.get(key);
            int s = 0, e = score.size();
            while (s < e) {
                int mid = (s + e) / 2;
                if (score.get(mid) < target) s = mid + 1;
                else e = mid;
            }
            answer[i] = score.size() - s;
        }
        
        return answer;
    }
    
    private Map<String, List<Integer>> makeWithAllKey() {
        String[] lang = {"cpp", "java", "python", "-"};
        String[] jobs = {"backend", "frontend", "-"};
        String[] career = {"junior", "senior", "-"};
        String[] foods = {"chicken", "pizza", "-"};
        Map<String, List<Integer>> m = new HashMap<>();
        
        for (String l : lang) {
            for (String j : jobs) {
                for (String c : career) {
                    for (String f : foods) {
                        String key = l + j + c + f;
                        m.put(key, new ArrayList<>());
                    }
                }
            }
        }
        return m;
    }
}