```java
import java.util.*;

class Solution {
    static PriorityQueue<Integer> maxQueue;
    static PriorityQueue<Integer> minQueue;
    static int size;
    
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        maxQueue = new PriorityQueue<>((a, b) -> (b - a)); 
        minQueue = new PriorityQueue<>((a, b) -> (a - b)); 
        size = 0;
        
        for (String operation : operations) {
            String operator = operation.split(" ")[0];
            int operand = Integer.parseInt(operation.split(" ")[1]);
            
            switch(operator) {
                case "I":
                    I(operand);
                    break;
                case "D":
                    D(operand);
                    break;
            }
        }
        
        return new int[] {peek(1), peek(-1)};
    }
    
    public void I(int number) {
        maxQueue.add(number);
        minQueue.add(number);
        size++;
    }
    
    public int D(int number) {
        int answer = 0;

        if (size <= 0) return 0;
        
        if (number == -1) {
            answer = minQueue.poll();
            maxQueue.remove(answer);
            size--;
        }
        
        if (number == 1) {
            answer = maxQueue.poll();
            minQueue.remove(answer);
            size--;
        }
        
        if (size <= 0) {
            minQueue.clear();
            maxQueue.clear();
        }
                                        
        return answer;
    }
    
    public int peek(int number) {
        int answer = 0;

        if (size <= 0) return 0;
        
        if (number == -1) {
            answer = minQueue.peek();
        }
        
        if (number == 1) {
            answer = maxQueue.peek();
        }
        
        return answer;
    }
}
```