import sys
from collections import deque
input = sys.stdin.readline

def bfs(weight):
    visited = [False for _ in range(n+1)]
    q = deque()
    q.append(s)
    visited[s] = True
    while q:
        now = q.popleft()
        if now == e:
            return True
        
        for next, edge in graph[now]:
            if not visited[next] and edge >= weight:
                visited[next] = True
                q.append(next)
    return False

n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    a, b, w = map(int, input().split())
    graph[a].append((b, w))
    graph[b].append((a, w))
s, e = map(int, input().split())

left = 1
right = 1_000_000_000
answer = 0
while left <= right:
    mid = (left + right) // 2
    if bfs(mid):
        answer = mid
        left = mid + 1
    else:
        right = mid - 1
print(answer)