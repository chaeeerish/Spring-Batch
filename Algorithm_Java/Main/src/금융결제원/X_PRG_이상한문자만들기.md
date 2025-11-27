```java
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        int index = 0;
        int 홀짝 = 0;
        while (index < s.length()) {
            char c = s.charAt(index++);

            if (c == ' ') {
                sb.append(" ");
                홀짝 = 0;
                continue;
            }

            if (홀짝 % 2 == 0 && 'a' <= c && 'z' >= c) {
                sb.append((char) (c - 32));
            } else if (홀짝 % 2 != 0 && 'A' <= c && 'Z' >= c) {
                sb.append((char) (c + 32));
            } else {
                sb.append(c);
            }

            홀짝 += 1;
        }

        return sb.toString();
    }
}
```