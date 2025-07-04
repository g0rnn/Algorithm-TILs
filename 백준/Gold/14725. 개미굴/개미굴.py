import sys
input = sys.stdin.readline
N = int(input())
trie = {}

def dfs(cur, prefix):
    for k in sorted(cur):
        print(prefix + k)
        dfs(cur[k], prefix + "--")

for _ in range(N):
    n = input().split()
    cur = trie
    for i in n[1:]:
        if i not in cur:
            cur[i] = {}
        cur = cur[i]

dfs(trie, "")