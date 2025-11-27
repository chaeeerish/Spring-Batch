```java
import java.util.*;

/*
 스킬트리

 [Input]
 skill: 선행 스킬 순서
 sill_trees: 유저들의 스킬트리
 
 [Output]
 skill_trees에서 가능한 skill tree 개수
 
 [Solution]
 1. skill 각 알파벳 문자열에 숫자를 +1씩 매핑한다. 
 2. skill_tree 각 skill tree들에 대하여, (반복문)
    2-1. 하나씩 돌면서 저장해둔 skill이 있다면, 마지막 skill보다 큰 값인지 검증한다. 

 [주의]

*/


class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = -1;
        
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        int index = 1;
        for (String s : skill.split("")) {
            hashMap.put(s, index++);
        }
        
        int count = 0;
        boolean flag = true;
        for (String sill_tree : skill_trees) {
            int last_number = 0;
            flag = true;
            boolean first = true;
            
            for (String s : sill_tree.split("")) {
                int current_number = hashMap.getOrDefault(s, 0);
                
                if (current_number == 0) continue;
                
                if (current_number != last_number + 1) {
                    flag = false;
                    break;
                }
                
                last_number = current_number;
                if (first == false) first = true;
            }
            
            if (flag == true) count += 1;
        }
        
        return count;
    }
}
```

✅ 좀 더 빨리 푸는 방법
```java
import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {                
        int answer = 0;
        
        for (String skill_tree : skill_trees) {
            StringBuilder sb = new StringBuilder();
            
            for (String alphabet : skill_tree.split("")) {
                if (skill.contains(alphabet)) {
                    sb.append(alphabet);        
                }
            }
            
            if (skill.startsWith(sb.toString())) answer += 1;
        }
        
        return answer;
    }
}
```