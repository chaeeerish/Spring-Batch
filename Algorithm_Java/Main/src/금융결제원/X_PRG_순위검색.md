```java
import java.util.*;

/*
 순위 검색
 
 cpp java python 중 하나
 backend frontend 중 하나
 junior senior 중 하나
 chicken pizza 중 하나
 
 각 팀은 조건에 만족하는 사람을 찾고 있다. 

 [Input]
 info : 지원자들의 조건 "개발언어 직군 경력 소울푸드 점수"
 query : 팀이 궁금해하는 문의조건 "X and X and ... 점수" 
 
 [Output]
 result : 각 팀에 만족하는 지원자는 몇 명인가
 
 [Solution]
 1. 모든 query에 대하여,
    1-1. -이 아닌, 각 조건을 가지면서 점수 이상인 사람의 개수 세기
 
 50000 * 100000 = 5000000000 = 50억? OMG
 
 // 
 
 1. 미리 가능한 경우를 만들어둔다. 
 2. 각 query에 대하여
    3. 해당 조건을 가진 사람들의 점수를 모은다. 
    4. 각 점수를 정렬한다. 
    5. query가 원하는 점수 이상의 사람을 센다. 

 [주의]

*/

class Solution {
    HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answers = new int[query.length];
        
        for (String infoElement : info) {
            dfs("", infoElement.split(" "), 0); 
        }
        
        for (String key : hashMap.keySet()) {
            System.out.println(key);
            Collections.sort(hashMap.get(key));
            // printArray(hashMap.get(key));
        }
        
        int queryIndex = 0;
        for (String queryElement : query) {            
            String[] temp = queryElement.replace(" and ", " ").split(" ");
            String[] elementArray = Arrays.copyOfRange(temp, 0, 4); // index 0 부터 3
            int score = Integer.parseInt(temp[4]);
            
            if (hashMap.get(String.join("", elementArray)) == null) {
                answers[queryIndex++] = 0;
            } else {
                ArrayList<Integer> candidates = hashMap.get(String.join("", elementArray)); 
                answers[queryIndex++] = findBiggerThanScore(candidates, score);
            }
            

        }
        
        return answers;
    }
    
    public void dfs(String result, String[] elementArray, int depth) {
        if (depth == 4) {
            int score = Integer.parseInt(elementArray[4]); 
            
            if (hashMap.containsKey(result)) {
                hashMap.get(result).add(score);
            } else {
                hashMap.put(result, new ArrayList(List.of(score)));
            }
            
            return;
        }
        
        dfs(result + "-", elementArray, depth + 1);
        dfs(result + elementArray[depth], elementArray, depth + 1);
    }
    
    public void printArray(ArrayList<Integer> arrayList) {
        for (Integer integer : arrayList) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
    
    public int findBiggerThanScore(ArrayList<Integer> arrayList, int score) {
        int start = 0; 
        int end = arrayList.size(); 
        int middle = 0;
        
        while (start < end) {
            middle = (int) ((start + end) / 2);
            
            if (arrayList.get(middle) < score) {
                start = middle + 1; 
            } else {
                end = middle;
            }
        }
        
        return arrayList.size() - start;
    }
}
```