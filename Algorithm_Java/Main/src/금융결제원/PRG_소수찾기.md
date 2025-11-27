```java
import java.util.*;

/*
소수 찾기

한자리 숫자가 적힌 종이 조각...
종이조각으로 만들 수 있는 소수가 몇개?

[Input]
numbers가 String 타입으로 주어진다.

[Output]
몇 개의 수소를 만들 수 있는지 리턴한다.

[Solution]
0. numbers를 쪼개서 int 리스트로 만든다.
1. nPr에서 r을 0부터 numbers.length까지 반복한다.
   1-1. 기저조건에 닿으면, Set 자료형의 결과 집합에 넣는다.
2. 모든 결과 집합에서
   2-1. 소수를 판별한다.
   2-1-1. 소수면, count를 증가한다.

반복이 안되니까 재귀로...

[주의]

*/

class Solution {
int ELEMENT_COUNT = 0;
int SELECT_COUNT = 0;

    int[] elementArray;
    int[] selectedElementArray; 
    boolean[] isSelected;
    
    Set<Integer> result = new HashSet<>();
    
    public int solution(String numbers) {        
        // 0. numbers를 쪼개서 int 리스트로 만든다. 
        char[] numbersCharArray = numbers.toCharArray();
        
        //  1. nPr에서 r을 0부터 numbers.length까지 반복한다. 
        ELEMENT_COUNT = numbersCharArray.length; 
        
        elementArray = new int[ELEMENT_COUNT]; 
        isSelected = new boolean[ELEMENT_COUNT];
        
        for (int elementIndex = 0; elementIndex < ELEMENT_COUNT; elementIndex++) {
            elementArray[elementIndex] = (int) (numbersCharArray[elementIndex] - '0');
        }
        
        for (int selectCount = 1; selectCount <= ELEMENT_COUNT; selectCount++) {
            SELECT_COUNT = selectCount;
            selectedElementArray = new int[SELECT_COUNT]; 
            
            permutation(0);
        }

        /*
         2. 모든 결과 집합에서
            2-1. 소수를 판별한다. 
                2-1-1. 소수면, count를 증가한다. 
        */
        int answer = 0;
        for (int resultElement : result) {
            if (isPrimeNumber(resultElement)) {
                answer += 1;
            }
        }
        
        return answer;
    }
    
    public void permutation(int selectIndex) {
        if (selectIndex == SELECT_COUNT) {
            addToSet();
            return;
        }
        
        for (int elementIndex = 0; elementIndex < ELEMENT_COUNT; elementIndex++) {
            if (isSelected[elementIndex]) {
                continue; 
            }
            
            selectedElementArray[selectIndex] = elementArray[elementIndex]; 
            isSelected[elementIndex] = true;
            permutation(selectIndex + 1); 
            
            selectedElementArray[selectIndex] = -1; 
            isSelected[elementIndex] = false;
        }
    }
    
    public void addToSet() {
        StringBuilder sb = new StringBuilder();
        
        for (int index = 0; index < SELECT_COUNT; index++) {
            sb.append(selectedElementArray[index]); 
        }
        
        System.out.println(Integer.valueOf(sb.toString()));
        result.add(Integer.valueOf(sb.toString()));
    }
    
    public boolean isPrimeNumber(int number1) {
        if (number1 <= 1) return false;
        
        for (int number2 = 2; number2 <= Math.sqrt(number1); number2++) {
            if (number1 % number2 == 0) return false;
        }
        
        return true;
    }
}

// ‼️ DFS로 풀기

class Solution {
int ELEMENT_COUNT = 0;     
int[] elementArray;

    Set<Integer> result = new HashSet<>();
    
    public int solution(String numbers) {        
        boolean[] visited = new boolean[numbers.length()];
        dfs(numbers, "", visited);

        int answer = 0;
        for (int resultElement : result) {
            if (isPrimeNumber(resultElement)) {
                answer += 1;
            }
        }
        
        return answer;
    }
    
    public void dfs(String numbers, String current, boolean[] visited) {
        if (! current.isEmpty()) {
            result.add(Integer.parseInt(current));
        }
        
        for (int index = 0; index < numbers.length(); index++) {
            if (visited[index]) continue; 
            
            visited[index] = true; 
            dfs(numbers, current + numbers.charAt(index), visited);
            visited[index] = false;
        }
    }
    
    public boolean isPrimeNumber(int number1) {
        if (number1 <= 1) return false;
        
        for (int number2 = 2; number2 <= Math.sqrt(number1); number2++) {
            if (number1 % number2 == 0) return false;
        }
        
        return true;
    }
}
```