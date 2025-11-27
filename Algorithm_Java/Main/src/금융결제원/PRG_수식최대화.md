```java
import java.util.*;

/*
수식 최대화

+, -, *
연산자를 재정의해서 만들 수 있는 가장 큰 숫자를 제출한다.

[Input]
expression 문자열이다.

[Output]

[Solution]
어떤 수가 있을지 모르니, 결국 모든 경우를 해보는 완전탐색이다.
우선순위를 정하는 함수가 하나 있어야 하고,
계산하는 함수가 또 하나 있어야겠다.

0. 모든 가능한 경우에 대해서 계산한다.
1. expression에서 피연산자와 연산자를 분리한다. 가변형 배열에 담는다.
   1-1. 피연산자  찾기
   1-1-1. split으로 숫자만 쪼갠다.
   1-1-2. long 형으로 변환해서 배열에 담는다.

   1-2. 연산자 찾기
   1-2-1. index = 0 부터 숫자가 아니면 배열에 답는다.
2. 1번째 연산자를 찾아서 계산한다.
3. 2번째 연산자를 찾아서 계산한다.
4. 3번째 연산자를 찾아서 계산한다.
5. 최대값을 갱신한다.

[주의]
같은 순위의 연산자는 없어야 한다.
계산된 숫자가 음수라면 절대값으로 변환하여 제출한다.
숫자를 많이 다루므로, long으로 자료형을 잡아야겠다.
같은 연산자 끼리는 앞에가 우선순위가 높다.
*/

class Solution {
String[] 연산자순위리스트 = {"*+-", "*-+", "+*-", "+-*", "-+*", "-*+"};

    ArrayList<Long> 피연산자리스트 = new ArrayList<>();
    ArrayList<Character> 연산자리스트 = new ArrayList<>();
    // ✅ LinkedList를 사용하면, 삽입-삭제 시간을 줄일 수 있다. 
    
    public long solution(String expression) {
        long answer = Long.MIN_VALUE;
        
        // 0. 모든 가능한 경우에 대해서 계산한다. 
        // 2. 1번째 연산자를 찾아서 계산한다. 
        // 3. 2번째 연산자를 찾아서 계산한다. 
        // 4. 3번째 연산자를 찾아서 계산한다. 
        // 5. 최대값을 갱신한다. 
        
        for (String 연산자순위 : 연산자순위리스트) {
            init(expression);
                        
            for (char 지금계산연산자 : 연산자순위.toCharArray()) {
                int 탐색인덱스 = 0; 
                
                while (탐색인덱스 < 연산자리스트.size()) {
                    if (Character.compare(연산자리스트.get(탐색인덱스), 지금계산연산자) == 0) {
                        long a = 피연산자리스트.get(탐색인덱스); 
                        long b = 피연산자리스트.get(탐색인덱스 + 1); 
                        long result = calculate(지금계산연산자, a, b); 
                        
                        피연산자리스트.remove(탐색인덱스);
                        피연산자리스트.remove(탐색인덱스);
                        
                        피연산자리스트.add(탐색인덱스, result);
                        
                        연산자리스트.remove(탐색인덱스);
                                                
                        // printArrayList(피연산자리스트);
                        // printArrayList(연산자리스트);
                        
                        continue;
                    }
                    
                    탐색인덱스 += 1;
                }
            }
            
            answer = Math.max(answer, Math.abs(피연산자리스트.get(0)));
        }
        
        return answer;
    }
    
    public void init(String expression) {
        피연산자리스트 = new ArrayList<>();
        String[] 분리된expression = expression.split("\\*|\\+|\\-");
        
        for (String element : 분리된expression) {
            피연산자리스트.add(Long.valueOf(element));
        }

        연산자리스트 = new ArrayList<>();
        
        for (int index = 0; index < expression.length(); index++) {
            if (! Character.isDigit(expression.charAt(index))) 연산자리스트.add(expression.charAt(index));
        }
        
        // ✅ StringTokenizer로 쪼갤 수 있다. 
        // ✅ 굳이 피연산자, 연산자 나눌 필요도 없다. 
    }
    
    public long calculate(char operator, long a, long b) {
        // switch(operator) {
        //     case '*':
        //         return a * b; 
        //     case '+':
        //         return a + b; 
        //     case '-':
        //         return a - b; 
        // }
        
        switch(operator) {
            case '*':
                return a * b; 
            case '+':
                return a + b; 
            case '-':
                return a - b; 
        }
        
        return 0;
    }
    
    public void printArrayList(ArrayList arrayList) {
        for (Object obj : arrayList) {
            System.out.print(obj + " ");
        }
        
        System.out.println();
    }
}
```