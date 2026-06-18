import java.util.*;

class Solution {
    int cur;
    int[] prev, next;
    boolean[] removed;
    Deque<Integer> stack = new ArrayDeque<>();
    
    public String solution(int n, int k, String[] cmds) {
        cur = k;
        prev = new int[n];
        next = new int[n];
        removed = new boolean[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i-1;
            next[i] = i+1;
        }
        prev[0] = -1;
        next[n-1] = -1;
        
        for (String cmd : cmds) {
            char c = cmd.charAt(0);
            if (c == 'U') {
                int step = Integer.parseInt(cmd.substring(2));
                move(step, prev);
            } else if(c == 'D') {
                int step = Integer.parseInt(cmd.substring(2));
                move(step, next);
            } else if (c == 'C') {
                delete();
            } else {
                restore();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < removed.length; i++) {
            sb.append(removed[i] ? 'X' : 'O');
        }
        return sb.toString();
    }
    
    void move(int step, int[] to) {
        for (int i = 0; i < step; i++) {
            cur = to[cur];
        }
    }

    void delete() {
        int prv = prev[cur];
        int nxt = next[cur];
        
        if (prv != -1) next[prv] = nxt;
        if (nxt != -1) prev[nxt] = prv;
        
        stack.push(cur);
        removed[cur] = true;
        
        if (nxt != -1) cur = nxt;
        else cur = prv;
    }
    
    void restore() {
        int zombie = stack.pop();
        removed[zombie] = false;
        
        int prv = prev[zombie];
        int nxt = next[zombie];
        if (prv != -1) next[prv] = zombie;
        if (nxt != -1) prev[nxt] = zombie;
    }
}