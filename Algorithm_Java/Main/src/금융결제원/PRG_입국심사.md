```java
import java.util.*;

/*
 입국심사 
 
 모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶다. 

 [Input]
 n: 입국심사를 기다리는 사람 수
 times: 각 심사관이 한 명을 심사하는데 걸리는 시간 
 
 [Output]
 return: 모든 사람이 심사를 받는데 걸리는 시간
 
 [Solution]

 [주의]

*/

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);

        long start = 1; 
        // ‼️ long으로 모든걸 변환해서 넣어주기.. 단위때문에 틀리지 말자..
        long end = (long) times[times.length - 1] * n;
        long middle = (start + end) / 2; 
        
        
        while (start < end) {
            middle = (start + end) / 2; 
            
            long 성공한사람수 = 0; 
            for (int index = 0; index < times.length; index++) {
                성공한사람수 += middle / times[index]; 
                
                if (성공한사람수 >= n) break;
            }
            
            if (성공한사람수 >= n) {
                answer = middle;
                end = middle;
            } else { // 성공한사람수 < n
                start = middle + 1; 
            }
        }
        
        return answer;
    }
}
```