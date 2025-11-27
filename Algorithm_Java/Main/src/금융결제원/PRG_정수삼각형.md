```java
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {        
        int[][] result = new int[triangle.length][triangle.length]; 
        result[0][0] = triangle[0][0];
        
        for (int height = 1; height < triangle.length; height++) {
            for (int width = 0; width < triangle[height].length; width++) {
                int left = width - 1; 
                int right = width; 
                
                if (left >= 0) {
                    result[height][width] = Math.max(result[height][width], triangle[height][width] + result[height - 1][left]);
                }
                
                result[height][width] = Math.max(result[height][width], triangle[height][width] + result[height - 1][right]);
            }
        }
        
        int answer = Integer.MIN_VALUE; 
        for (int width = 0; width < triangle.length; width++) {
            answer = Math.max(answer, result[triangle.length - 1][width]);
        }
        return answer;
    }
}
```