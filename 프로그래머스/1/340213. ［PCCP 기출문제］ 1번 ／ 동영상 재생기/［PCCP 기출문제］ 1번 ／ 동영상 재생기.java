import java.util.*;
import java.io.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {        
        int vLen = toSeconds(video_len);
        int ops = toSeconds(op_start);
        int ope = toSeconds(op_end);
        int cur = toSeconds(pos);
        
        for (String cmd : commands) {
            if (ops <= cur && cur <= ope) {
                // go to OpenEnd
                cur = ope;
            }
            
            if (cmd.equals("next")) {
                // jump to 10s
                cur += 10;
                // more than len
                if (cur > vLen) cur = vLen;
            } else {
                // jump to -10s
                cur -= 10;
                // less than 0s
                if (cur < 0) cur = 0;
            }
            
            if (ops <= cur && cur <= ope) {
                // go to OpenEnd
                cur = ope;
            }
        }
        
        return toTimeFormat(cur);
    }
    
    private int toSeconds(String time) {
        int mm = Integer.parseInt(time.substring(0, 2));
        int ss = Integer.parseInt(time.substring(3, 5));
        return mm * 60 + ss;
    }
    
    private String toTimeFormat(int seconds) {
        StringBuilder sb = new StringBuilder();
        
        int mm = seconds / 60;
        int ss = seconds % 60;
        
        if (mm < 10) {
            sb.append("0");
        }
        sb.append(mm);
        sb.append(":");
        
        if (ss < 10) {
            sb.append("0");
        }
        sb.append(ss);
        return sb.toString();
    }
}