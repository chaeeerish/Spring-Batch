```java
import java.util.*;

/*
교점에 별 만들기

Ax + By + C = 0 으로 표현할 수 있는, n개의 직선이 주어질 때, 
이 직선의 교점 중 정수 좌표에 별을 그린다. 

[Input]
A, B, C 리스트 (요소는 1000개 이하)
=> 1000개 이하면, 어떤 알고리즘이거나 모두 가능하다. 

[Output]
별이 그려지는 최소 사각형

[Solution]
0. Input 데이터를 정리한다. 
1. 교점이 존재할 경우, 모든 교점을 구한다. (조건: 정수)
2. 사각형 배열을 만든다. 
    예를 들어, (4, 1), (4, -4), (-4, -4), (-4, 1), (0, 4) 라면, 
    2-1. 가장 큰 maxX, 가장 작은 minX, 가장 큰 maxY, 가장 작은 minY를 구한다. 
    
    2-2. 별을 찍을 index를 구한다. 
        첫번째 index = maxY - y
        두번째 index = minY - x
*/

class Solution {
    class Point {
        public int x = 0;
        public int y = 0;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public String[] solution(int[][] line) {        
        // 0. Input 데이터를 정리한다. 
        int[] AArray = new int[line.length];
        int[] BArray = new int[line.length];
        int[] CArray = new int[line.length];
        
        for (int lineIndex = 0; lineIndex < line.length; lineIndex++) {
            AArray[lineIndex] = line[lineIndex][0];
            BArray[lineIndex] = line[lineIndex][1];
            CArray[lineIndex] = line[lineIndex][2];
        }
        
        // 1. 교점이 존재할 경우, 모든 교점을 구한다. (조건: 정수)
        ArrayList<Point> 교점리스트 = new ArrayList<Point>();
        
        // ‼️ index1 + 1부터 시작하면, 시간 단축 가능하다. 
        // ‼️ Set 자료형 사용하면, 중복 제거 가능하다. 
        for (int index1 = 0; index1 < line.length; index1++) {
            for (int index2 = 0; index2 < line.length; index2++) {
                long A = AArray[index1];
                long B = BArray[index1];
                long E = CArray[index1];
                long C = AArray[index2];
                long D = BArray[index2];
                long F = CArray[index2];
                
                if ((A*D - B*C) == 0) continue;
                if (((B*F - E*D) % (A*D - B*C) != 0) || ((E*C - A*F) % (A*D - B*C) != 0)) continue;
                
                long x = (B*F - E*D) / (A*D - B*C);
                long y = (E*C - A*F) / (A*D - B*C);
                
                교점리스트.add(new Point((int) x, (int) y));
            }
        }
        
        // 2. 사각형 배열을 만든다. 
        // 2-1. 가장 큰 maxX, 가장 작은 minX, 가장 큰 maxY, 가장 작은 minY를 구한다.
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        
        for (Point point : 교점리스트) {
            if (point.x < minX) minX = point.x;
            if (point.x > maxX) maxX = point.x;
            if (point.y < minY) minY = point.y;
            if (point.y > maxY) maxY = point.y;
        }
        
//         System.out.println("minX = " + minX);
//         System.out.println("maxX = " + maxX);
//         System.out.println("minY = " + minY);
//         System.out.println("maxY = " + maxY);
        
//         System.out.println("maxX - minX + 1 = " + (maxX - minX + 1));
//         System.out.println("maxY - minY + 1 = " + (maxY - minY + 1));

        // 2-2. 별을 찍을 index를 구한다. 
        //  첫번째 index = maxY - y
        //  두번째 index = minX - x
        
        // ‼️ char[][] 쉽게 초기화할 수 있다. 
        /*
                for (char[] row : charMap) {
                    Arrays.fill(row, ',); // row 배열의 모든 원소를 '.' 으로 채우라는 의미이다. 
                }
         */
        
        char[][] charMap = new char[maxY - minY + 1][maxX - minX + 1];
        for (int row = 0; row < maxY - minY + 1; row++) {
            for (int col = 0; col < maxX - minX + 1; col++) {
                charMap[row][col] = '.';
            }
        }
        
        for (Point point : 교점리스트) {
//             System.out.println("point.x = " + (point.x));
//             System.out.println("point.y = " + (point.y));

//             System.out.println("첫번째 index = " + (maxY - point.y));
//             System.out.println("두번째 index = " + (point.x - minX));
            
            charMap[maxY - point.y][point.x - minX] = '*';
        }
        
        // 3. 결과를 Output 형식으로 변환한다.
        String[] answer = new String[maxY - minY + 1];
        for (int row = 0; row < maxY - minY + 1; row++) {
            answer[row] = String.valueOf(charMap[row]);
        }
        
        return answer;
    }
}
```