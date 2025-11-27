```java
import java.util.*;

/*
 단어 변환 
 
 단어 begin, 단어 target
 begin에서 target으로 변환하는 "가장 짧은 변환 과정"
 1. 한 번에 한 개의 알파벳만 바꿀 수 있다. 
 2. words에 있는 단어로만 변환할 수 있다. 
 "hit" -> "hot" -> "dot" -> "dog" -> "cog"

 [Input]
 begin
 target
 words
 
 [Output]
 return: 최소 단계
 
 [Solution]
 hit -> cog
 hot, dot, dog, lot, log, cog ...
 
 1. BFS로 탐색한다. 
    1-1. Queue에서 노드(방문 정보를 관리하는 배열)를 꺼낸다. 
    1-2. 아직 방문하지 않은 노드들을 Queue에 담는다. 

 [주의]
 변환할 수 없는 경우에는 0을 return한다. 
*/

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        ArrayDeque<String> queue = new ArrayDeque<>(); 
        
        for (int index = 0; index < words.length; index++) {
            if (validateMove(begin, words[index])) {
                queue.add(begin);
            }
        }
        
        while (! queue.isEmpty()) {
            String node = queue.removeFirst();
            
            String lastWord = node.split(" ")[node.split(" ").length - 1];
            
            if (lastWord.equals(target)) {
                return node.split(" ").length - 1;
            }
            
            for (int index = 0; index < words.length; index++) {
                if ((! node.contains(words[index])) && validateMove(lastWord, words[index])) {
                    queue.addLast(node + " " + words[index]);
                }
            }
        }
        
        return answer;
    }
    
    public boolean validateMove(String before, String now) {
        int count = 0;
        for (int index = 0; index < before.length(); index++) {
            if (before.charAt(index) == now.charAt(index)) count += 1;
        }
        return count == before.length() - 1;
    }
}
```