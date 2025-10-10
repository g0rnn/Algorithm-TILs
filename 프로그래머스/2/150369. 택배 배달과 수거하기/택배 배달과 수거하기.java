import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Deque<Integer> del = new ArrayDeque<>(n);
        Deque<Integer> pic = new ArrayDeque<>(n);
        
        for (int i = 0; i < deliveries.length; i++) {
            del.push(deliveries[i]);
            pic.push(pickups[i]);
        }
        
        long moved = 0;
        while (!(del.isEmpty() && pic.isEmpty())) {
            trimZeros(del);
            trimZeros(pic);
            
            moved += Math.max(del.size(), pic.size());
            move(del, cap);
            move(pic, cap);
        }
        return moved * 2;
    }
    
    private void trimZeros(Deque<Integer> stack) {
        while (!stack.isEmpty() && stack.peek() == 0) {
            stack.pop();
        }
    }
    
    private void move(Deque<Integer> stack, int cap) {
        int remain = cap;
        
        while (!stack.isEmpty() && remain > 0) {
            int cnt = stack.pop();
            if (cnt <= remain) {
                remain -= cnt;
            } else {
                int leftover = cnt - remain;
                stack.push(leftover);
                remain = 0;
            }
        }
    }
}