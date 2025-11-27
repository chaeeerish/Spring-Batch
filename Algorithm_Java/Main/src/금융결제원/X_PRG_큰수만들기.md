```java
import java.util.*;

// ‼️ 생각지도 못한 발상..

/*
 큰 수 만들기
 
 어떤 숫자에서 k개의 수를 제거했을 때, 얻을 수 있는 가장 큰 숫자 
 1924 => 1 2 제거 => 9 4
 1231234 => 1 2 1 제거 => 3234
 4177252841 => 4 1 2 2 제거 

 [Input]
 number: 숫자
 k: 삭제할 개수
 
 [Output]
 return: k개의 수를 제거하고 가장 큰 수
 
 [Solution]
 1. 
 
 [주의]

*/

class Solution {
    public String solution(String number, int k) {        
        ArrayDeque<Integer> stack = new ArrayDeque<>(); 
        
        int kCount = 0; 
        
        for (char c : number.toCharArray()) {
            int n = c - '0';
            
            while (! stack.isEmpty()) {
                if (kCount == k) break;
                
                if (stack.peekFirst() < n) {
                    stack.removeFirst();
                    kCount += 1;
                } else {
                    break;
                }
            }
            
            stack.addFirst(n); 
        }
                
        StringBuilder sb = new StringBuilder(); 
        int index = 0;
        
        while (! stack.isEmpty()) {
            sb.append(stack.removeLast()); 
            index += 1; 
            
            if (index == number.length() - k) break;
        }
        
        return sb.toString();
    }
}
```