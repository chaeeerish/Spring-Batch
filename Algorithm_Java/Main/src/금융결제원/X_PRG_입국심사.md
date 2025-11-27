```java
import java.util.*;

/*
 입국심사
 
 모든 심사대는 비어있다. 
 한 심사대는 동시에 한명만 심사를 받을 수 있고, 
 꼭 빈 심사대가 생기면 바로 들어가는건 아니다. 

 [Input]
 n : 입국심사를 기다리는 사람 수 (1,000,000,000명 이하)
 times : 각 심사관이 한 명을 심사하는데 걸리는 시간 (1,000,000,000분 이하 / 100,000명 이하)
 
 [Output]
 모든 사람이 심사를 받는데 걸리는 최소 시간!
 
 [Solution]
 숫자가 너무 크다... => 이분탐색 의심
 
 

 [주의]

*/

class Solution {
    public long solution(int n, int[] times) {        
        long start = 1; 
        // 1000000000명이 최대 1000000000분 걸리니까, 
        // 최대 가능한 시간은 1000000000000000000분이다. 
        long end = 1000000000000000001L;
        
        Arrays.sort(times);
        
        while (start < end) {
            long middle = (start + end) / 2;
            
            if (isValid(n, middle, times)) { // 이 시간 안에 전부 검사가 가능하면
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        
        return start;
    }
    
    public boolean isValid(int n, long t, int[] times) {
        long count = 0;
        
        for (int time : times) {
            count += (t / time);
        }
        
        return count >= n;
    }
}
```