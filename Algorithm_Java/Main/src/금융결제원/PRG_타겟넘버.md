```java
import java.util.*;

/*
 타겟 넘버
 
 n개의 음이 아닌 정수로, 
 순서를 바꾸지 않고, 적절히 더하거나 빼서, 타겟넘버를 만든다. 
 +1             -1 
 +1     -1      +1      -1
 +1 -1  +1 -1   +1 -1   +1 -1
 ...

 [Input]
 numbers: 적절히 더하고 뺄 주어진 숫자
 target: 만들 숫자
 
 [Output]
 return: 가능한 경우의 수
 
 [Solution]
 1. 첫번째 numbers index 부터 + 혹은 -로 dfs 탐색을 시작한다. 
    1-1. 이미 target number를 넘어섰다면, return한다. 
    1-2. dfs 끝까지 도달했다면, target 번호 인지 아닌지 검사한다. 

 [주의]

*/

class Solution {   
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, 0, target);    
        return answer;
    }
    
    public void dfs(int[] numbers, int index, int result, int target) {
        if (index == numbers.length) {
            if (target == result) answer += 1;
            return;
        }
        
        dfs(numbers, index + 1, result + numbers[index], target);
        dfs(numbers, index + 1, result - numbers[index], target);
    }
}
```