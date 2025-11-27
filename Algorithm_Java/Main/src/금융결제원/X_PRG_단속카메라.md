```java
import java.util.*;

// ‼️ 그리디는 역시 접근법이 어렵ㄴ다....

/*
 단속카메라 
 
 고속도로를 이용하는 모든 차량이 단속용 카메라를 한 번은 만나도록 설치하고 싶다. 
 최소 몇대의 카메라? 

 [Input]
 
 [Output]
 
 [Solution]

 [주의]

*/

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (a, b) -> {
            return a[1] - b[1];
        });       
        
        ArrayDeque<Integer> camera = new ArrayDeque<>();
        camera.addFirst(routes[0][1]);
        
        for (int index = 1; index < routes.length; index++) {
            if (camera.peekFirst() < routes[index][0]) {
                camera.addFirst(routes[index][1]);
            }
        }
        
        return camera.size();
    }
}
```