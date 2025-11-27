```java
import java.util.*;

/*
 체육복
 
 일부 학생이 체육복을 도난
 여벌 체육복이 있는 학생이 빌려줄꺼다. 
 학생들 번호는 체격순.. 
 따라서, 바로 앞이나 뒤만 가능
 
 적절히 체육복을 빌려서 최대한 많은 학생이 수업을 들을 수 있도록.. 
 
 O V O V O
 1 2 3 4 5
 
   X O X
 1 2 3 4 5

 [Input]
 n: 전체 학생수 
 lost: 도난당한 학생들의 번호
 reserve: 여벌의 체육복을 가져온 학생들의 번호
 
 [Output]
 return: 수업을 들을 수 있는 최대한 많은 학생
 
 [Solution]
 1. boolean 배열을 만든다. 
    1-1. 체육복을 가져오지 않은 학생만 false를 한다. 
 2. 여분의 체육복이 있는 학생을 기준으로, 
    2-1. 자기가 안가져왔다면 자기에게 준다. 
    2-2. 앞번호가 안가져왔다면 빌려준다. 
    2-3. 뒷번호가 안가져왔다면 빌려준다. 
 3. true의 개수를 센다.  

 [주의]
 여벌 체육복 가져온 학생이 도난당했다면, 자기가 입는다. 
*/

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {   
        // ‼️ 정렬을 안해서 틀렸다. 
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        boolean[] haveClothes = new boolean[n + 1]; 
        for (int index = 0; index < n + 1; index++) {
            haveClothes[index] = true;
        }
        
        for (int lostStudent : lost) {
            haveClothes[lostStudent] = false;
        }
        
        ArrayList<Integer> newReserve = new ArrayList<>();
        
        for (int reserveStudent : reserve) {
            if (haveClothes[reserveStudent] == false) {
                haveClothes[reserveStudent] = true;
            } else {
                newReserve.add(reserveStudent);
            }
        }
        
        for (int reserveStudent : newReserve) {
            if (reserveStudent - 1 >= 1 && haveClothes[reserveStudent - 1] == false) {
                haveClothes[reserveStudent - 1] = true;
            } else if (reserveStudent + 1 <= n && haveClothes[reserveStudent + 1] == false) {
                haveClothes[reserveStudent + 1] = true;
            }
        }
        
        int answer = 0; 
        for (int student = 1; student < n + 1; student++) {
            if (haveClothes[student]) answer += 1;
        }
        return answer;
    }
}
```