```java
class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2.length];
        
        for (int row = 0; row < arr1.length; row++) {
            for (int col = 0; col < arr2.length; col++) {
                int result = 0; 
                for (int k = 0; k < arr2.length; k++) {
                    result += arr1[row][k] * arr2[k][col];
                }
                
                answer[row][col] = result;
            }
        }
        
        return answer;
    }
}
```