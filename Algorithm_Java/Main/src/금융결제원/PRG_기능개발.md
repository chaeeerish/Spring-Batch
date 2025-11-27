```java
import java.util.*;

/*
 기능개발
 
 각 기능의 개발속도는 모두 다르다. 
 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있다. 

 [Input]
 progresses: (먼저 배포되어야 하는 순서대로) 작업의 진도
 speeds: 각 작업의 속도
 
 [Output]
 return: 각 배포마다 몇 개의 기능이 배포되는가
 
 [Solution]
 1. 얼마나 걸리는가... 를 계산 
    [7, 3, 9]
 2. 맨 앞 작업이 빠져 나가면서 그것보다 작거나 같은 작업 모두 제거 
 3. Queue가 빌 때까지 반복

 [주의]

*/

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {        
        ArrayList<Integer> answer = new ArrayList<>(); 
        
        ArrayDeque<Integer> queue = new ArrayDeque<>(); 
        for (int index = 0; index < progresses.length; index++) {
            int day = (int) (100 - progresses[index]) / speeds[index]; 
            if (speeds[index] * day < (100 - progresses[index])) day += 1; 
            
            queue.addLast(day);
        }
        
        int count = 0; 
        int deploy = 0; 
        while (! queue.isEmpty()) {
            int now = queue.removeFirst();   
            
            if (deploy == 0) deploy = now; 
            
            count += 1; 
            
            if ((! queue.isEmpty()) && queue.peekFirst() > deploy) {
                answer.add(count);
                deploy = 0;
                count = 0; 
            }
        }
        
        if (count != 0) answer.add(count);
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
```