```java
import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        
        Arrays.sort(strings, (s1, s2) -> {
            if (s1.charAt(n) - s2.charAt(n) == 0) {
                return s1.compareTo(s2);
            }
            
            return s1.charAt(n) - s2.charAt(n);
            });
        
        return strings;
    }
}
```

> ArrayList 였다면 아래처럼 바뀐다. 

```java
import java.util.*;

class Solution {
    public ArrayList<String> solution(ArrayList<String> strings, int n) {
        // Collections.sort()를 사용해야 함
        Collections.sort(strings, (s1, s2) -> {
            if (s1.charAt(n) == s2.charAt(n)) {
                return s1.compareTo(s2);
            }
            return s1.charAt(n) - s2.charAt(n);
        });
        
        return strings;
    }
}
```