// 이중 for 풀어보기
class Solution {
    private long answer;
    private int distLimit;
    private int splitLimit;

    public int solution(int dist_limit, int split_limit) {
        long answer = 1;
        
        for (int i = 0; i <= 30; i++) {
            long pow2 = (long) Math.pow(2, i);
            
            for (int j = 0; j <= 20; j++) {
                long pow3 = (long) Math.pow(3, j);
                
                if (pow2*pow3 > split_limit) continue;
                
                long d2 = Math.min(dist_limit, pow2 - 1);
                long d3 = Math.min(dist_limit - d2, gSum(pow2, 3, j));
                long leaf = 1 + d2 + d3 * 2;
                answer = Math.max(answer, leaf);
            }
        }
        return (int) answer;
    }
    
    private long gSum(long a, long r, long n) {
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += a;
            a *= r;
        }
        return res;
    }
}

/**
기본 전제: 분배 노드의 수를 구하면 리프 노드의 수를 알 수 있다. (혹은 리프노드는 분배노드의 개수에 따라 정해진다.)

어떤 리프 노드 하나를 골라 자식 k개를 만들면, 그 노드는 더이상 리프가 아니게 되고 자식 k개가 새 리프가 된다.
```
자식 1개 → 리프 변화 0
자식 2개 → 리프 +1
자식 3개 → 리프 +2
자식 4개 → 리프 +3
```

즉, 리프 수를 최대화하려면 가능한한 많은 내부 노드가 많은 자식을 가지도록 만들어야함.

그런데 이 문제에선 내부 노드의 최대 갯수가 정해져 있음. 그래서 리프 노드를 최대화하려면 한 내부 노드의 자식을 최대한 많이 생성해야함(여기서는 3개)

수학적 고찰로 222...333 구조가 효율적이라는 것을 알아냈으면, 그대로 구하면서 완전 탐색을 진행하면 됨.

L = 1 + \sum_{v \in I}(\operatorname{children}(v)-1)

*/