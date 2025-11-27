```java
import java.util.*;

// ‼️ 문제 이해 MISS

/*
불량 사용자

'*': 알파벳 소문자, 숫자 하나 가린다.

[Input]
user_id: 이벤트 응모자 아이디 목록
banned_id: 불량 사용자 아이디 목록

[Output]
return: 당첨에서 제외되어야 할 제재 아이디 목록은 "몇가지 경우의 수"가 가능한지

[Solution]

[주의]

*/

class Solution {
ArrayList<HashSet<String>> bannedMapping = new ArrayList<>();
HashSet<List<String>> graphSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        for (String ban : banned_id) {
            HashSet<String> tmp = new HashSet<>();
            
            for (String user : user_id) {
                if (ban.length() != user.length()) continue;
                
                boolean flag = true;
                for (int index = 0; index < ban.length(); index++) {
                    if (ban.charAt(index) == '*') continue; 
                    
                    if (ban.charAt(index) != user.charAt(index)) {
                        flag = false; 
                        break;
                    }
                }
                
                if (flag) tmp.add(user);
            }
            
            bannedMapping.add(tmp);
        }
        
        dfs(new ArrayList<String>(), 0);
        
        return graphSet.size();
    }
    
    // 선택 -> 재귀 -> 선택 
    // 전형적인 DFS/백트래킹...
    // 전의 상태를 점검할 수 있음...   
    public void dfs(ArrayList<String> graph, int index) {
        if (index == bannedMapping.size()) {
            // 중복 제거의 핵심은 정렬 + Set이다. 
            Collections.sort(graph);
            
            // 이렇게하면, 독립된 복사본이다. 
            graphSet.add(new ArrayList<>(graph)); 
                    
            return;
        }
        
        HashSet<String> nowHashSet = bannedMapping.get(index);
        
        for (String nowUser : nowHashSet) {
            if (graph.contains(nowUser)) continue; 
            else {
                graph.add(nowUser);
                dfs(graph, index + 1); 
                graph.remove(nowUser);
            }
        }
    }
}
```