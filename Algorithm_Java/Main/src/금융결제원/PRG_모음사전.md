```java
import java.util.*;

/*
 모음사전
 
 A
 AA
 AAA
 ...
 UUUUU 
 
 이 단어가 사전에서 몇 번째 단어인지...

 [Input]
 word: 단어
 
 [Output]
 result: 몇 번째 단어인가
 
 [Solution]
 1. DFS 
    1-1. A E I O U 로 depth가 5가 될 때까지 진입한다. 
    1-2. 찾으면 return 한다. 

 [주의]

*/

class Solution {
    String[] aeiou = {"A", "E", "I", "O", "U"}; 
    static String staticWord = ""; 
    static int answer = 0; 
    
    
    public int solution(String word) {        
        staticWord = word;
        dfs("", 0);
        // -1을 해야하는 이유
        // ""도 1로 세기 때문이다. 
        return answer - 1;
    }
    
    public boolean dfs(String str, int depth) {
        if (depth == 6) {
            return false;
        } 
        
        answer += 1;
        
        if (str.equals(staticWord)) {
            return true;
        }
        
        for (String s : aeiou) {
            boolean flag = dfs(str + s, depth + 1); 
            if (flag) return true;
        }
        
        return false;
    }
}
```