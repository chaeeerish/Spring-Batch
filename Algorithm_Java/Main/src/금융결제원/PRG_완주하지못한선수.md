```java
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String part : participant) {
            if (hashMap.containsKey(part)) {
                int value = hashMap.get(part) + 1;
                hashMap.put(part, value);
            }
            else hashMap.put(part, 1);
        }
        
        for (String part : completion) {
            int value = hashMap.get(part) - 1;
            hashMap.put(part, value);
        }
        
        for (String part : participant) {            
            if (hashMap.get(part) != 0) return part;
        }
        
        return "";
    }
}
```