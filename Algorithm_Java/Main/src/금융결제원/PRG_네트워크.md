```java
import java.util.*;

/*
 네트워크
 AB, BC가 연결되어 있으면, AC도 연결되어 있다.
 
 i, j가 연결되어 있으면, computers[i][j]가 1이다. 

 [Input]
 n: 컴퓨터 개수
 computers: 연결 정보
 
 [Output]
 return: 네트워크의 개수
 
 [Solution]
 1 1 0
 1 1 0
 0 0 1
 
 1 1 0
 1 1 1
 0 1 1
 
 1 0 0 1
 0 1 1 0
 0 1 1 0
 1 1 0 1
 
 1. 모든 점을 시작점으로 한다. 
    1-1. 이미 방문했다면, 패스한다. 
    1-2. 방문하지 않았다면, 해당 정점을 기준으로 DFS 탐색을 한다. 
        1-2-1. 네트워크 개수를 +1 한다. 
    1-3. 탐색에 닿은 정점들은 모두 방문처리를 한다. 

 [주의]

*/

class Solution {
    static boolean[] visited;
    static int nStatic; 
    static int[][] computersStatic;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        nStatic = n; 
        computersStatic = computers;
        
        for(int index = 0; index < nStatic; index++) {
            if (visited[index] == false) {
                visited[index] = true;
                answer += 1;
                dfs(index);
            }
        }
        
        return answer;
    }
    
    public void dfs(int index) {
        for (int index2 = 0; index2 < nStatic; index2++) {
            if (index == index2) continue;
            if (visited[index2]) continue;
            
            if (computersStatic[index][index2] == 1 || computersStatic[index2][index] == 1) {
                visited[index2] = true;
                dfs(index2);
            }
        }
    }
}
```