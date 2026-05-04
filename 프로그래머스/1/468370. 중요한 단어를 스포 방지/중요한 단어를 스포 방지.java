/**
* 스포 방지 단어 : 조금이라도 모자이크된 단어들
* 중요한 단어: 스포 방지 단어 + 스포방지 구간이 아닌 구간에 등장 X + 중복X
* 주의) 왼쪽부터 중요한 단어를 판단해야함.
* 
* @Return 스포 방지 단어 중 중요한 단어 수
*/

import java.util.*;

class Solution {

    
    
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        Set<String> importants = new HashSet<>();
        Set<String> openWords = new HashSet<>();
        
        // message에서 spoiler_ranges를 통해 스포 방지 단어들만추려낼 수도 있지만
        // 각 단어가 스포 방지 단어인지 확인하고 스포방지라면 중요한 단어인지 확인하는 구조 
        
        for (int i = 0; i < message.length(); i++) {
            String word = parse(message, i);
            if (!isSpoiled(word, i, spoiler_ranges)) openWords.add(word);
            i += word.length(); // 공백으로 가게 됨
        }
        
        for (int i = 0; i < message.length(); i++) {
            String word = parse(message, i);
            //System.out.println("word="+word);
            if (isSpoiled(word, i, spoiler_ranges)) {
                if (!openWords.contains(word)) importants.add(word);
                //System.out.println(importants.toString());
            }
            i += word.length();
        }
        
        //System.out.println(openWords.toString());
        return importants.size();
    }
    
    // e < range_start || range_end < s 라면 스포일러 단어 아님
    private boolean isSpoiled(String word, int s, int[][] spoiler_ranges) {
        int e = s + word.length() - 1;
        // 구간에 한번이라도 속하면 참
        for (int[] range : spoiler_ranges) {
            if (!(e < range[0] || range[1] < s)) return true;
        }
        return false;
    }
    
    private String parse(String msg, int start) {
        int end = start;
        while (end < msg.length() && msg.charAt(end) != ' ') {
            end++;
        }
        return msg.substring(start, end);
    }
}