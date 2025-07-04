import sys
input = sys.stdin.readline

class Trie:
    def __init__(self):
        self.children = {}

def insert(root, path):
    node = root
    for food in path:
        if food not in node.children:
            node.children[food] = Trie()
        node = node.children[food]

def printNode(node, depth):
    for key in sorted(node.children.keys()):
        print("--" * depth + key)
        printNode(node.children[key], depth + 1)

N = int(input())
root = Trie()
for _ in range(N):
    parts = input().split()
    k = int(parts[0])
    insert(root, parts[1:])

printNode(root, 0)