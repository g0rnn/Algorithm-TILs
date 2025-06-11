import java.util.*;

class Solution {
    
    List<int[]> discounts = new ArrayList<>(20_000); 
    
    public int[] solution(int[][] users, int[] emoticons) {
        List<int[]> results = new ArrayList<>(20_000);
        final int emtiLen = emoticons.length;
        
        initDiscounts(new int[emtiLen], emtiLen, 0);
        
        // discount: 이모티콘의 할인율 배열
        for (int[] discount : discounts) {
            int plusUser = 0;
            int totalPrice = 0;
            int[] userTotalPrice = new int[users.length]; 
            
            for (int i = 0; i < emtiLen; i++) {
                for (int j = 0; j < users.length; j++) {
                    // 이 이모티콘을 살 사람
                    if (users[j][0] <= discount[i]) {
                        userTotalPrice[j] += emoticons[i] - (emoticons[i] * discount[i] / 100);
                    }
                }
            }
            
            // 모든 유저가 (특정 할인율에 대해) 이모티콘 구매 완료 -> 여기서 과소비하면 이용권 구매
            for (int i = 0; i < users.length; i++) {
                if (userTotalPrice[i] >= users[i][1]) {
                    userTotalPrice[i] = 0;
                    plusUser++;
                }
                totalPrice += userTotalPrice[i];
            }
            
            results.add(new int[]{plusUser, totalPrice});
        }
        
        results.sort((e1, e2) -> {
            if (e1[0] == e2[0]) return Integer.compare(e2[1], e1[1]); // 내림차순
            return Integer.compare(e2[0], e1[0]); // 내림차순
        }); 
        return results.get(0);
    }
    
    private void initDiscounts(int[] discount, int emoticonLen, int idx) {
        if (idx == emoticonLen) {
            discounts.add(Arrays.copyOf(discount, discount.length));
            return;
        }
        
        for (int i = 10; i <= 40; i += 10) {
            discount[idx] = i;
            initDiscounts(discount, emoticonLen, idx+1);
        }
    }
}