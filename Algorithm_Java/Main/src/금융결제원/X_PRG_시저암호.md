```java
import java.util.*;

/*
 시저 암호
 
 AB를 1만큼 밀면 BC가 된다. 
 AB를 3만큼 밀면 DE가 된다. 
 z를 1만큼 밀면 a가 된다. 
 
 [Input]
 문자열 s, 거리 n
 
 [Output]
 s를 n만큼 민, 암호문
 
 [Solution]
 0. s를 toCharArray로 배열로 만든다. 
 1. 입력한 모든 문자열에 대해서 반복한다. 
    1-1. 알파벳이 아닌 경우 그냥 붙인다. 
    1-2. 알파벳인 경우,
        1-2-1. c를 숫자로 바꾼다. 
        1-2-2. n만큼 민다. 
        1-2-3. 'z', 'Z'를 넘었을 수 있으니, % 연산자로 순환시킨다. 
        1-2-4. 'a' 혹은 'A'를 기준으로, 다시 알파벳으로 만든다. 
 
 [주의]
 공백은 밀어도 공백이다.
*/

class Solution {
   public char push(char c, int n) {
      if (!Character.isAlphabetic(c)) return c;

      int offset = Character.isUpperCase(c) ? 'A' : 'a';
      int position = c - offset;
      position = (position + n) % ('Z' - 'A' + 1);

      return (char) (offset + position);
   }

   public String solution(String s, int n) {
      String answer = "";

      char[] sArray = s.toCharArray();
      for (int index = 0; index < sArray.length; index++) {
         sArray[index] = push(sArray[index], n);
      }

      return String.valueOf(sArray);
   }
}

```