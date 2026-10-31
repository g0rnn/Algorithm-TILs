import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> bridge = new ArrayDeque<>(); // {bus, endedTime}
        Queue<Integer> waited = new ArrayDeque<>();
        for (int t : truck_weights) waited.offer(t);
        
        int total = 0;
        for (int t= 0 ; t <= bridge_length * truck_weights.length+1; t++) {
            
            if (!bridge.isEmpty() && bridge.peek()[1] <= t) {
                int[] out = bridge.poll();
                total -= out[0];
            }
            
            if (!waited.isEmpty() && total + waited.peek() <= weight) {
                int w = waited.poll();
                int endedAt = t + bridge_length;
                
                bridge.offer(new int[] {w, endedAt});
                total += w;
            }
            
            if (waited.isEmpty() && bridge.isEmpty()) return t+1;
        }
        return -1;
    }
}