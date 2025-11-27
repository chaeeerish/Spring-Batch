```java
import java.util.*;

/*
 순위

 n명의 권투선수
 1~n 번호 
 
 [A, B] = A > B

 [Input]
 n : 선수의 수
 results : 경기 결과
 
 [Output]
 정확하게 순서를 매길 수 있는 선수의 수!
 
 [Solution]
 1. 인접리스트를 만든다. 
 2. 주어진 results를 통해 연결한다. 
 3. 3중 포문으로 k를 거쳐 갈 수 있는, i, j를 연결한다. 
 4. in + out이 n - 1인 노드의 개수를 센다. 

 [주의]
 .
*/

class Solution {
    public int solution(int n, int[][] results) {        
        int[][] graph = new int[n][n]; 
        for (int index = 0; index < results.length; index++) {
            graph[results[index][0] - 1][results[index][1] - 1] = 1; 
        }
        
        // ‼️ 플로이드 워샬에서 k가 가장 바깥 반복문이다. 
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {                
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;

                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1; 
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        
        int answer = 0;
        for (int node = 0; node < n; node++) {
            int sum = getSumOfRow(graph, node) + getSumOfCol(graph, node);
            if (sum == n - 1) answer += 1;
        }
        
        return answer;
    }
    
    public int getSumOfRow(int[][] graph, int node) {
        int sum = 0;
        for (int row = 0; row < graph.length; row++) {
            if (row == node) continue;
            sum += graph[row][node];
        }
        return sum;
    }
    
    public int getSumOfCol(int[][] graph, int node) {
        int sum = 0;
        for (int col = 0; col < graph.length; col++) {
            if (col == node) continue;
            sum += graph[node][col];
        }
        return sum;
    }
}
```