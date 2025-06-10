import java.util.*;

class Solution {
    
    int preIdx = 0;
    int postIdx = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        
        List<Node> tree = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            tree.add(new Node(nodeinfo[i], i+1));
        }
        
        tree.sort((e1, e2) -> {
            if (e1.y == e2.y) return Integer.compare(e1.x, e2.x);
            return Integer.compare(e2.y, e1.y);
        });
        
        Node root = tree.get(0);
        for (int i = 1; i < tree.size(); i++) {
            append(root, tree.get(i));
        }
        
        int[][] answer = new int[2][nodeinfo.length];
        preorder(root, answer[0]);
        postorder(root, answer[1]);
        return answer;
    }
    
    void append(Node root, Node node) {
        if (root.x > node.x) {
            if (root.left == null) root.left = node;
            else append(root.left, node);
            
        } else {
            if (root.right == null) root.right = node;
            else append(root.right, node);
        }
    }
    
    void preorder(Node node, int[] pre) {
        if (node == null) return;
        pre[preIdx++] = node.num;
        preorder(node.left, pre);
        preorder(node.right, pre);
    }
    
    void postorder(Node node, int[] post) {
        if (node == null) return;
        postorder(node.left, post);
        postorder(node.right, post);
        post[postIdx++] = node.num;
    }
    
    static class Node {
        int x, y, num;
        Node left, right;
        
        Node(int[] node, int num) {
            this.x = node[0];
            this.y = node[1];
            this.num = num;
        }
    }
}