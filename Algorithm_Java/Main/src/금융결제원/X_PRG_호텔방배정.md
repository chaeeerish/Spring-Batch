```java
import java.util.*;

/*
 호텔 방 배정
 
 호텔에는 방이 총 k개가 있다. 
 각각의 방은 1번부터 k번까지 번호가 있다. 
 처음에 모든 방은 비어있다. 
 
 1. 한 번에 한 명씩 신청한 순서대로 배정한다. 
 2. 고객은 투숙하기 원하는 방 번호를 제출한다. 
    2-1. 방이 있다면, 즉시 배정
    2-2. 없다면, 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방

 [Input]
 k: 전체 방 개수 
 room_number: 고객들이 원하는 방 번호 
 
 [Output]
 return: 고객에게 배정되는 방 번호
 
 [Solution]
 k는 10^2 이하? 
 이분탐색... 

 [주의]

*/

class Solution {
    HashMap<Long, Long> map = new HashMap<>(); 
    
    public long findRoom(long n) {
        if (! map.containsKey(n)) {
            map.put(n, n + 1); 
            return n; 
        }
        
        long nextRoom = findRoom(map.get(n));
        map.put(n, nextRoom); 
        return nextRoom; 
    }
    
    public long[] solution(long k, long[] room_number) {    
        long[] answer = new long[room_number.length];
        
        for (int index = 0; index < room_number.length; index++) {
            answer[index] = findRoom(room_number[index]);
        }
        
        return answer;
    }
}
```