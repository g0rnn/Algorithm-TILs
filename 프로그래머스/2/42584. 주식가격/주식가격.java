import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Deque<Integer> mStack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            while (!mStack.isEmpty() && prices[mStack.peek()] > prices[i]) {
                int prev = mStack.pop();
                answer[prev] = i - prev;
            }
            mStack.push(i); // idx
        }
        while (!mStack.isEmpty()) {
            int prev = mStack.pop();
            answer[prev] = n-1 - prev;
        }
        return answer;
    }
}