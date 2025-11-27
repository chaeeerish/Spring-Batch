```java
import java.util.*;

class Solution {
    static int start = 0;
    static int end = 0;
    static String[] gems;
    
    HashMap<String, Integer> basket = new HashMap<>(); 
    int answerStart = 0; 
    int answerEnd = 0; 
    int answerSize = Integer.MAX_VALUE; 
    
    public int[] solution(String[] gems) {
        int[] answer = {};
        this.gems = gems;
        
        HashSet<String> set = new HashSet<>(); 
        for (String gem : gems) set.add(gem);
        int N = set.size(); 
        
        put(gems[0]);
        
        while (true) { 
            if (allContain(N)) {
                if ((end - start + 1) < answerSize) {
                    answerStart = start; 
                    answerEnd = end; 
                    answerSize = (end - start + 1);
                }
                
                remove(gems[start]);
                start++;
            } else {
                end++;
                if (end == gems.length) break;
                // ‼️ 그러니까, 결국 종료 조건은
                // end가 끝까지 닿았는데 allContain이 false일 때다. 
                // 왜냐하면, 아무리 start를 움직여도 가망이 없기 때문이다. 
                
                put(gems[end]);
            }
        }
        
        return new int[] {answerStart + 1, answerEnd + 1};
    }
    
    public void put(String key) {
        if (basket.containsKey(key)) {
            basket.put(key, basket.get(key) + 1);
        } else {
            basket.put(key, 1);
        }
    }
    
    public void remove(String key) {
        if (basket.containsKey(key)) {
            if (basket.get(key) > 1) basket.put(key, basket.get(key) - 1);
            else basket.remove(key);
        } 
    }
    
    public boolean allContain(int N) {        
        return basket.keySet().size() == N;
    }
}
```