```java
import java.util.*;

/*
 징검다리
 
 distance만큼 떨어진 곳에 도착지점이 있다. 
 그 사이에는 바위들이 놓여있다. 
 바위 n개를 제거하여 만들 수 있는 거리의 최솟값 중에 가장 큰 값을 찾는다. 

 [Input]
 distance: 도착지점까지의 거리
 rocks: 바위들의 위치를 담은 배열  
 n: 제거할 바위 수
 
 [Output]
 return: 바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값 중 가장 큰 값
 
 [Solution]

 [주의]

*/

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks); 
        
        int start = 0; 
        int end = distance; 
        int middle = (start + end) / 2; 
        
        int[] newRocks = new int[rocks.length + 2]; 
        newRocks[0] = 0; newRocks[rocks.length + 1] = distance; 
        
        for (int index = 0; index < rocks.length; index++) {
            newRocks[index + 1] = rocks[index];
        }
        
        while (start <= end) {
            middle = (start + end) / 2; 
            
            // 최소 middle 거리로 건널 수 있는가 검증
            int 제거할돌개수 = 0; 
            int 도착돌인덱스 = 1; 
            int 시작돌인덱스 = 0; 
            
            while (도착돌인덱스 < newRocks.length) {
                if (newRocks[도착돌인덱스] - newRocks[시작돌인덱스] < middle) {
                    도착돌인덱스 += 1; 
                    제거할돌개수 += 1; 
                } else { // 누적 + rocks[돌인덱스] >= middle
                    시작돌인덱스 = 도착돌인덱스; 
                    도착돌인덱스 += 1; 
                }
            }
            
            if (제거할돌개수 > n) end = middle - 1; 
            else { // 제거할돌개수 < n
                // ‼️ 조건을 만족하는 가장 큰 값을 구해야 하므로
                // start를 늘린다. 
                // 대신에 answer은 계속 갱신한다. 
                // start가 마지막으로 탐색한 곳이 정답이 아닐수도 있기 때문이다. 
                // 단순히, 종료조건이 되어서 빠져나온 것 뿐이다. 
                answer = middle;
                start = middle + 1; 
            }
        }
        
        return answer;
    }
}
```