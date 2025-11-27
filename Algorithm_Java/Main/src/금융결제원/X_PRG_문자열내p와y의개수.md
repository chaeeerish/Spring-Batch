```java
/*
문자열 내 p와 y의 개수

[Input]

[Output]

[Solution]

[주의]

*/

class Solution {
boolean solution(String s) {
boolean answer = true;

        s = s.toLowerCase();
        
        int pCount = s.length() - s.replace("p", "").length();
        int yCount = s.length() - s.replace("y", "").length();

        return pCount == yCount;
    }
}
```