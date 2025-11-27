```java
class Solution {
    public int solution(int n) {        
        String 반전3진법 = get반전3진법(n);
        return Integer.parseInt(반전3진법, 3);
    }
    
    public String get반전3진법(int n) {
        StringBuilder sb = new StringBuilder(); 
        
        while (n / 3 != 0) {
            sb.append(n % 3); 
            n = (int) n / 3; 
        }
        
        sb.append(n); 
        
        return sb.toString();
    }
}
```