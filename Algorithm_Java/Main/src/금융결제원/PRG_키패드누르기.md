```java
import java.util.*;

/*
 키패드 누르기
 
 왼손과 오른손만을 이용해서, 숫자를 입력한다. 
 왼손 엄지손가락 - * 
 오른손 엄지손가락 - # 
 에서 시작한다. 
 
 - 상하좌우로만 이동
 - 키패드 이동 한 칸은 거리 1이다. 
 - 3, 6, 9는 왼손 엄지손가락으로
 - 1, 4, 7는 오른손 엄지손가락으로
 - 2, 5, 8, 0은 더 가까운 손가락으로!
    - (두 엄지손가락 길이가 같다면, 오른손잡이는 오른손으로, 왼손잡이는 왼손으로)
    
    0 1 2
 
 0  1 2 3
 1  4 5 6
 2  7 8 9
 3  * 0 #

 [Input]
 numbers: 순서대로 누를 배열
 hand: left / right
 
 [Output]
 return: 각 번호를 누른 엄지손가락이 왼손인지 오른손인지
 
 [Solution]
 1. 왼손과 오른손의 시작 좌표를 정한다. 
    - 왼손: (3, 0), 오른손: (3, 2)
 2. 움직인다. 
    2-1. 1, 4, 7이면 무조건 왼손을 옮긴다. 
    2-2. 3, 6, 9면 무조건 오른손을 옮긴다. 
    2-3. 2, 5, 8, 0이면 거리를 계산해서 가까운 쪽을 옮긴다. 

 [주의]

*/

class Solution {
    static HashMap<Integer, Integer> locationRow = new HashMap<>(); 
    static HashMap<Integer, Integer> locationCol = new HashMap<>(); 

    static int leftRow = 3;
    static int leftCol = 0;
    static int rightRow = 3;
    static int rightCol = 2;
    static String handStatic = "";
    
    static StringBuilder sb = new StringBuilder();
    
    public String solution(int[] numbers, String hand) {        
        handStatic = hand;
        
        locationRow.put(1, 0);
        locationRow.put(2, 0);
        locationRow.put(3, 0);
        
        locationRow.put(4, 1);
        locationRow.put(5, 1);
        locationRow.put(6, 1);
        
        locationRow.put(7, 2);
        locationRow.put(8, 2);
        locationRow.put(9, 2);
        
        locationRow.put(0, 3);
        
        locationCol.put(1, 0);
        locationCol.put(2, 1);
        locationCol.put(3, 2);
        
        locationCol.put(4, 0);
        locationCol.put(5, 1);
        locationCol.put(6, 2);
        
        locationCol.put(7, 0);
        locationCol.put(8, 1);
        locationCol.put(9, 2);
        
        locationCol.put(0, 1);

        
        for (int number : numbers) {
            switch (number) {
                case 1, 4, 7:
                    moveLeftHand(number);
                    break;
                case 3, 6, 9:
                    moveRightHand(number);
                    break;
                case 2, 5, 8, 0:
                    moveMiddle(number);
                    break;
            }
        }
        
        return sb.toString();
    }
    
    public void moveMiddle(int number) {
        int leftDistance = getDistance(number, leftRow, leftCol);
        int rightDistance = getDistance(number, rightRow, rightCol);
        
        if (leftDistance < rightDistance) {
            moveLeftHand(number);
        } else if (leftDistance > rightDistance) {
            moveRightHand(number);
        } else {
            if (handStatic.equals("left")) {
                moveLeftHand(number);
            } else {
                moveRightHand(number);
            }
        }
    }
    
    public int getDistance(int number, int row, int col) {
        int targetRow = locationRow.get(number);
        int targetCol = locationCol.get(number);
        
        return Math.abs(row - targetRow) + Math.abs(col - targetCol);
    }
    
    public void moveLeftHand(int number) {
        leftRow = locationRow.get(number);
        leftCol = locationCol.get(number);
        sb.append("L");
    }
    
    public void moveRightHand(int number) {
        rightRow = locationRow.get(number);
        rightCol = locationCol.get(number);
        sb.append("R");
    }
}
```

✅ leftRow, leftCol, rightRow, rightCol 을 관리할 필요가 없이, 그냥 숫자로 관리하면 언젠가 다 알 수 있음... 
```java
import java.util.*;

/*


 [Input]
 
 [Output]
 
 [Solution]

 [주의]

*/

class Solution {
    static StringBuilder sb = new StringBuilder(); 
    
    static int[][] location = {
        {3, 1}, 
        {0, 0}, {0, 1}, {0, 2}, 
        {1, 0}, {1, 1}, {1, 2}, 
        {2, 0}, {2, 1}, {2, 2}, 
        
        {3, 0}, {3, 2}
    };
    
    static int leftNumber = 10; 
    static int rightNumber = 11; 
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        for (int number : numbers) {
            switch(number) {
                case 1, 4, 7:
                    leftNumber = number;
                    sb.append("L");
                    break;
                case 3, 6, 9:
                    rightNumber = number;
                    sb.append("R");
                    break;
                default:
                    if (getDistance(leftNumber, number) < getDistance(rightNumber, number)) {
                        leftNumber = number;
                        sb.append("L");
                        break;
                    } else if (getDistance(leftNumber, number) > getDistance(rightNumber, number)) {
                        rightNumber = number;
                        sb.append("R");
                        break;
                    } else {
                        if (hand.equals("left")) {
                            leftNumber = number;
                            sb.append("L");
                            break;
                        } else {
                            rightNumber = number;
                            sb.append("R");
                            break;
                        }
                    }
            }
        }
        
        return sb.toString();
    }
    
    public int getDistance(int current, int target) {
        return Math.abs(location[current][0] - location[target][0]) + Math.abs(location[current][1] - location[target][1]);
    }
}
```