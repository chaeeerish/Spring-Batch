```java
import java.util.*;

/*
 방의 개수
 
 숫자에 따라서, 이동 방향이 다르다. 
 이동하는 방향이 담긴 배열이 주어질 때, 방의 갯수를 return해라. 

 [Input]
 arrows: 이동하는 방향이 담긴 배열
 
 [Output]
 방의 개수
 
 [Solution]
 일단 방향은 필요가 없는데... 
 
 푸는 핵심 로직! 
 어떠한 정점에 마주하면, 처음 겹치면 도형이 생긴다. 
 정점은 사실 무한대로 만들 수 있다. 1/2도 있으니까

 [주의]
 방은 다른 방으로 둘러 싸여질 수 있다. 
 배열의 크기는 100,000 이하 == 한 방향으로 100,000까지 이동할 수 있음. 
 => 배열로 나타내기에는 너무 크다. 
*/

class Solution {
    static class Vertex {
        int x; 
        int y; 
        String id; // ‼️ id를 표현하는 새로운 방법이다. 
        HashSet<String> connectedVertices; 
        
        Vertex(int x, int y) {
            this.x = x; 
            this.y = y; 
            this.id = id(x, y);
            this.connectedVertices = new HashSet<>();
        }
        
        public static String id(int x, int y) {
            return String.format("(%d, %d)", x, y);
        }
    }
    
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    
    public int solution(int[] arrows) {
        int count = 0;
        
        HashMap<String, Vertex> vertices = new HashMap<>();
        
        Vertex v = new Vertex(0, 0);
        vertices.put(v.id, v);
        
        // 코드가 0.5칸을 직접 계산하는 게 아니라, 
        // “한 칸 이동을 두 번 반복”하는 방식으로
        // 좌표계를 2배 확장한 셈으로 만든다. 
        for (int d: arrows) {
            for (int i = 0; i < 2; i++) {
                int x = v.x + dx[d];
                int y = v.y + dy[d];
                String id = Vertex.id(x, y);
                
                if (!vertices.containsKey(id)) {
                    vertices.put(id, new Vertex(x, y));
                } else if (!v.connectedVertices.contains(id)) {
                    count++;
                }
                
                Vertex u = vertices.get(id);
                v.connectedVertices.add(u.id);
                u.connectedVertices.add(v.id);
                v = vertices.get(id);
            }
        }
        
        return count;
    }
}
```