```java
import java.util.*;

class Solution {
public String solution(String s) {
String answer = "";

        String[] sArray = s.split("");
        Arrays.sort(sArray, Collections.reverseOrder());
        
        return String.join("", sArray);
    }
}
```