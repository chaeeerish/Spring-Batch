```java
import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        // 가격이 떨어지지 않은 기간은 몇초인가?
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(0);
        
        for (int index = 1; index < prices.length; index++) {
            // System.out.println("prices[stack.peekFirst()] = " + prices[stack.peekFirst()]);
            // System.out.println("prices[index] = " + prices[index]);
            
            if ((! stack.isEmpty()) && (prices[stack.peekFirst()] > prices[index])) {
                while (! stack.isEmpty()) {
                    int nowIndex = stack.removeFirst(); 
                    answer[nowIndex] = index - nowIndex;
                    
                    if ((! stack.isEmpty()) && (prices[stack.peekFirst()] <= prices[index])) break;
                }
                stack.addFirst(index);
            } else {
                stack.addFirst(index);
            }
        }
        
        for (int index = 0; index < prices.length; index++) {            
            if (answer[index] == 0) {
                answer[index] = prices.length - 1 - index;
            }
        }
        
        return answer;
    }
}
```