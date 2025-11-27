```java
// ﹖ “벽이 있어서 돌아가야 할 수도 있고, 어떤 칸을 먼저 방문했더라도 나중에 더 짧은 경로가 발견될 수 있는 거 아닌가?”
// 👉 BFS에서는 절대로 그런 일이 일어나지 않는다.
// 👉 따라서 어떤 칸을 ‘처음 방문한 순간’이 바로 그 칸까지의 최단거리이다.
// ‼️ 즉, BFS는 어떤 칸을 처음 방문하는 순간, 그 칸으로 들어오는 경로 중에서 가장 짧은 경로가 이미 사용된 상태이다.

// ✅ 벽은 단순히 일부 간선을 제거할 뿐 BFS 성질을 깨지 않음

import java.util.*;

/*
 게임 맵 최단거리 
 
 당신의 캐릭터 (0, 0)
 상대팀 진영 (4, 4)

 [Input]
 maps: n X m 
 (0은 벽, 1은 벽 X)
 
 [Output]
 answer: 상대팀 진영에 도착하기 위해 지나야 하는 칸의 개수 
 
 [Solution]
 BFS
 
 0. (0, 0)부터 BFS 탐색을 한다. (Queue가 빌때까지)
    0-1. 4가지 방향으로 탐색이 가능하다면, Queue에 담는다. 
        [1 or 해당 좌표의 값이 지금 값 + 1보다 클 때]
 1. [n-1, m-1]의 값을 출력한다. 
 
 [주의]
 상대팀 진영에 도착할 수 없다면 -1 리턴
 
*/

class Solution {
    static int[] dRow = {0, 1, 0, -1}; 
    static int[] dCol = {1, 0, -1, 0};
    
    public int solution(int[][] maps) {        
        int answer = 0;
        
        int n = maps.length; 
        int m = maps[0].length; 
                
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addFirst(new int[] {0, 0});
        
        while (! queue.isEmpty()) {
            int[] current = queue.removeLast();

            for (int d = 0; d < 4; d++) {
                int newRow = current[0] + dRow[d]; 
                int newCol = current[1] + dCol[d]; 

                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m) continue;
                if (maps[newRow][newCol] != 1) continue;
                
                queue.addFirst(new int[] {newRow, newCol}); 
                maps[newRow][newCol] = maps[current[0]][current[1]] + 1;
            }
        }
        
        return maps[n - 1][m - 1] == 1 ? -1 : maps[n - 1][m - 1];
    }
}
```