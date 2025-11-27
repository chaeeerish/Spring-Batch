```java
import java.util.*;

class Solution {
    public int[] solution(String s) {
        int 이진변환횟수 = 0; 
        int 제거된0 = 0;
        
        String x = s; 
        
        while (true) {
            int beforeSize = x.length(); 
            x = x.replaceAll("0", "");
            int afterSize = x.length();
            
            제거된0 += beforeSize - afterSize; 
            
            x = get2진법(x.length());
            이진변환횟수 += 1;

           	if (x.equals("1")) break; 
        }
        
        return new int[] {이진변환횟수, 제거된0};
    }
    
    public String get2진법(int n) {
        StringBuilder sb = new StringBuilder();
        
        while (n / 2 != 0) {
            sb.append(n % 2); 
            n = (int) n / 2; 
        }
        
        sb.append(n);
        sb.reverse();
        return sb.toString();
    }
}
```