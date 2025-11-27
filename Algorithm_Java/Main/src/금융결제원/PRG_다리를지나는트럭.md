```java
import java.util.*;

/*
 다리를 지나는 트럭

 [Input]
 bridge_length : 다리에 올라갈 수 있는 트럭 수
 weight : 다리가 견딜 수 있는 무게
 truck_weights : 트럭 별 무게
 
 [Output]
 모든 트럭이 다리를 건너려면 몇 초가 걸리는가?
 순서대로!
 
 [Solution]

 [주의]

*/

class Solution {
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    int sumQueue = 0;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        for (int index = 0; index < bridge_length; index++) {
            queue.addFirst(-1);
        }
        
        int time = 0; 
        int index = 0;
        
        while (true) {
            int removeIndex = queue.removeFirst(); 

            if (removeIndex != -1) sumQueue -= truck_weights[removeIndex];
            if (removeIndex == truck_weights.length - 1) break;
            
            if (
                index != -1
                && index <= (truck_weights.length - 1) 
                && (sumQueue + truck_weights[index] <= weight)
                && (getCount() + 1 <= bridge_length)
            ) {
                queue.addLast(index); 
                sumQueue += truck_weights[index];
                index += 1;
            } else {
                queue.addLast(-1);
            }
            
            time += 1; 
        }
        
        return time + 1;
    }
    
    public int getCount() {
        int count = 0; 
        for (int queueElement : queue) {
            if (queueElement != -1) count += 1; 
        }
        
        return count;
    }
}
```