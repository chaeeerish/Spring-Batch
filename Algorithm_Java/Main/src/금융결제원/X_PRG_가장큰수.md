```java
import java.util.*;

/*
 가장 큰 수

 [Input]

 [Output]

 [Solution]
 1. 맨 앞자리 숫자로 정렬한다. 
 2. 같다면, 더 긴 수의 공통부분 바로 뒷 자리와 더 짧은 수의 가장 앞 자리를 비교한다. 

 [주의]

*/

class Solution {
    public String solution(int[] numbers) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
            String stringA = Integer.toString(a);
            String stringB = Integer.toString(b);

            if (stringA.length() == stringB.length()) return b - a;
            if (stringA.charAt(0) != stringB.charAt(0)) return stringB.charAt(0) - stringA.charAt(0);

            String AB = stringA.concat(stringB);
            String BA = stringB.concat(stringA);

            return Integer.valueOf(BA) - Integer.valueOf(AB);
        });

        for (int number : numbers) {
            queue.add(number);
        }

        StringBuilder answer = new StringBuilder();

        while (!queue.isEmpty()) {
            answer.append(Integer.toString(queue.poll()));
        }

        return Long.valueOf(answer.toString()).toString();
    }
}
```