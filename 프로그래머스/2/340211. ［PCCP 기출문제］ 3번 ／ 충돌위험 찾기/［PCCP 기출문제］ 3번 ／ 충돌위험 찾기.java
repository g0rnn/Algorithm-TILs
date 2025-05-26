import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        Map<String, Integer>[] reports = new HashMap[20001];
        for (int i = 0; i < reports.length; i++) {
            reports[i] = new HashMap<>();
        }
        
        for (int[] route : routes) {
            int sx = points[route[0] - 1][0]; // 1-based
            int sy = points[route[0] - 1][1];
            int time = 0;
            String srt = String.valueOf(sx) + "," + String.valueOf(sy);
            if (reports[time].containsKey(srt)) {
                Integer cnt = reports[time].get(srt) + 1;
                reports[time].put(srt, cnt);
            } else {
                reports[time].put(srt, 1);
            }
            
            for (int i = 1; i < route.length; i++) {
                int dx = points[route[i] - 1][0];
                int dy = points[route[i] - 1][1];
                
                
                while (!(sx == dx && sy == dy)) {
                    time++;
                    if (sx < dx) sx++;
                    else if(sx > dx) sx--;
                    else if(sy < dy) sy++;
                    else if(sy > dy) sy--;
                    
                    String pos = String.valueOf(sx) + "," + String.valueOf(sy);
                    if (reports[time].containsKey(pos)) {
                        Integer cnt = reports[time].get(pos) + 1;
                        reports[time].put(pos, cnt);
                    } else {
                        reports[time].put(pos, 1);
                    }
                }
            }
        }
        
        for (Map<String, Integer> report : reports) {
            for (Integer cnt : report.values()) {
                if (cnt > 1) answer++;
            }
        }
        
        return answer;
    }
}