```java
import java.util.*;

class Solution {
    public int solution(int[][] dots) {        
        HashSet<Double> slopeList = new HashSet<>();
        HashSet<Integer> indexList = new HashSet<>(List.of(0, 1, 2, 3));
        
        for (int index1 = 0; index1 < dots.length; index1++) {
            for (int index2 = index1 + 1; index2 < dots.length; index2++) {
                HashSet<Integer> firstSet = new HashSet<>(List.of(index1, index2));
                HashSet<Integer> secondSet = new HashSet<>(List.of(0, 1, 2, 3));
                secondSet.removeAll(firstSet);
                
                System.out.println(firstSet);
                System.out.println(secondSet);
                
                ArrayList<Integer> firstList = new ArrayList<>(firstSet);
                ArrayList<Integer> secondList = new ArrayList<>(secondSet);
                
                Double firstSlope = (double) (dots[firstList.get(0)][1] - dots[firstList.get(1)][1]) / (double) (dots[firstList.get(0)][0] - dots[firstList.get(1)][0]);
                Double secondSlope = (double) (dots[secondList.get(0)][1] - dots[secondList.get(1)][1]) / (double) (dots[secondList.get(0)][0] - dots[secondList.get(1)][0]);
                
                System.out.println("firstSlope = " + firstSlope);
                System.out.println("secondSlope = " + secondSlope);
                
                // ✅ == 은 객체 주소 비교라서 X
                // ✅ equals는 객체 값 비교라서 O
                if (firstSlope.equals(secondSlope)) return 1;
            }
        }
        
        return 0;
    }
}
```