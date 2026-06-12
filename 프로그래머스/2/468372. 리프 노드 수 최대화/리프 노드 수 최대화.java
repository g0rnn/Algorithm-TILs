class Solution {
    private long answer;

    public int solution(int distLimit, int splitLimit) {
        answer = 1;

        // root 1개가 현재 깊이에 존재하고,
        // 아직 분배 노드로 사용한 개수는 0개
        dfs(1, 0, 1, 0, distLimit, splitLimit);

        return (int) answer;
    }

    /**
     * @param currentNodes 현재 깊이에 존재하는 전체 노드 수
     * @param usedDistNodes 이전 깊이까지 사용한 분배 노드 수
     * @param splitProduct 현재 깊이까지 도달하는 데 사용된 분배도 곱
     * @param fixedLeaves 이전 깊이까지 리프로 확정된 노드 수
     */
    private void dfs(long currentNodes,
                     long usedDistNodes,
                     long splitProduct,
                     long fixedLeaves,
                     int distLimit,
                     int splitLimit) {

        // 현재 깊이에서 더 분배하지 않고 멈추면,
        // 현재 깊이의 모든 노드는 리프가 된다.
        answer = Math.max(answer, fixedLeaves + currentNodes);

        long remainingDistNodes = distLimit - usedDistNodes;

        // 더 이상 분배 노드로 사용할 수 없다면 종료
        if (remainingDistNodes <= 0) {
            return;
        }

        // 현재 깊이의 노드 중 실제로 분배 노드로 사용할 수 있는 개수
        long currentDistNodes = Math.min(currentNodes, remainingDistNodes);

        // 현재 깊이에 있지만 분배 노드로 쓰지 못한 노드는 리프로 확정
        long nextFixedLeaves = fixedLeaves + (currentNodes - currentDistNodes);

        for (int childCount = 2; childCount <= 3; childCount++) {
            long nextSplitProduct = splitProduct * childCount;

            if (nextSplitProduct > splitLimit) {
                continue;
            }

            // 현재 깊이에서 분배된 노드들이 만든 다음 깊이의 전체 노드 수
            long nextLevelNodes = currentDistNodes * childCount;

            dfs(
                nextLevelNodes,
                usedDistNodes + currentDistNodes,
                nextSplitProduct,
                nextFixedLeaves,
                distLimit,
                splitLimit
            );
        }
    }
}