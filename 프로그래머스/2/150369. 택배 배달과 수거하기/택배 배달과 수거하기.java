class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int d = n - 1;
        int p = n - 1;
        
        while (true) {
            while (d >= 0 && deliveries[d] == 0) d--;
            while (p >= 0 && pickups[p] == 0) p--;
            
            if (d < 0 && p < 0) break;
            
            answer += (Math.max(d, p) * 2L) + 2L;
            
            int capD = cap;
            // 현재 용량을 다 채울 때까지 반복
            while (d >= 0 && capD > 0) {
                if (deliveries[d] - capD <= 0) {
                    capD -= deliveries[d];
                    deliveries[d] = 0;
                    d--;
                } else {
                    deliveries[d] -= capD;
                    capD = 0;
                }
            }
            
            int capP = cap;
            while (p >= 0 && capP > 0) {
                if (pickups[p] - capP <= 0) {
                    capP -= pickups[p];
                    pickups[p] = 0;
                    p--;
                } else {
                    pickups[p] -= capP;
                    capP = 0;
                }
            }
        }
        
        return answer;
    }
}