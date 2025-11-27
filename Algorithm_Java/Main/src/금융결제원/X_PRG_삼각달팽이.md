```java
class Solution {
    public int[] solution(int n) {
        int nLength = 0;
        for (int tmp = 1; tmp < n + 1; tmp++) {
            nLength += tmp;
        }
        
        int[][] pyramid = new int[n][n];
        int row = 0; 
        int col = 0;
                
        int k = n;
        int number = 1; 
        
        while (number <= nLength) {
            // 1. row + 1을 k번 
            for (int count = 0; count < k; count++) {
                pyramid[row++][col] = number++; 
            }
            
            row--;
            col++;
            
            // 2. col + 1을 k번
            for (int count = 0; count < k - 1; count++) {
                pyramid[row][col++] = number++; 
            }
            
            col--;
            col--;
            row--;
            
            // 3. row - 1, col - 1을 k - 1번 
            for (int count = 0; count < k - 2; count++) {
                pyramid[row--][col--] = number++; 
            }
            
            row++;
            row++;
            col++;
            
            k -= 3;
        }
        
        
        int[] answer = new int[nLength];
        int index = 0;
        
        for (int row2 = 0; row2 < n; row2++) {
            for (int col2 = 0; col2 <= row2; col2++) {
                answer[index++] = pyramid[row2][col2]; 
            }
        }
        
        return answer;
    }
}
```

✅ 문제 유형의 패턴화 

```java
class Solution {
    public int[] solution(int n) {
        int total = n * (n + 1) / 2;
        int[] answer = new int[total];
        
        int[][] pyramid = new int[n][n];
        
        int[] dRow = {1, 0, -1};
        int[] dCol = {0, 1, -1}; 
        
        int row = 0; 
        int col = 0; 
        int d = 0;
        
        for (int number = 1; number <= total; number++) {
            pyramid[row][col] = number; 
            
            int newRow = row + dRow[d]; 
            int newCol = col + dCol[d]; 
            
            if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n || pyramid[newRow][newCol] != 0) {
                d = (d + 1) % 3; 
                newRow = row + dRow[d]; 
                newCol = col + dCol[d]; 
            }
            
            row = newRow; 
            col = newCol;
        }
        
        int index = 0; 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[index++] = pyramid[i][j]; 
            }
        }
        
        return answer;
    }
}
```