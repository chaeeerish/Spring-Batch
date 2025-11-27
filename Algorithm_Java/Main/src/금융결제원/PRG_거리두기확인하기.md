```java
// ‼️ 대각선? (pRow1, pCol2), (pRow2, pCol1) 만 검사하면 되잖아... 
/*
    // 2. 대각선 위치에 있는지
    else { 
        if (places[index][pRow.get(pIndex1)].charAt(pCol.get(pIndex2)) != 'X' 
            || places[index][pRow.get(pIndex2)].charAt(pCol.get(pIndex1)) != 'X') {
            flag = false;
            break;
        }
    }

 */

import java.util.*;

/*
 거리두기 확인하기 
 
 P: 응시자
 0: 빈테이블
 X: 파티션 
 
 5개의 대기실 
 각 대기실은 5X5 크기 
 ‼️ 맨해튼 거리 2이하로 앉지 말기

 [Input]
 places: 대기실 구조
 
 [Output]
 answer: 각 대기실의 규칙 준수 여부
 
 [Solution]

 [주의]

*/

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
            
        for (int index = 0; index < 5; index++) {
            // 초기화
            boolean flag = true;
            ArrayList<Integer> pRow = new ArrayList<>(); 
            ArrayList<Integer> pCol = new ArrayList<>(); 
            
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (places[index][row].charAt(col) == 'P') {
                        pRow.add(row); 
                        pCol.add(col);
                    }
                }
            }
            
            // 탐색 시작
            for (int pIndex1 = 0; pIndex1 < pRow.size(); pIndex1++) {
                for (int pIndex2 = pIndex1 + 1; pIndex2 < pRow.size(); pIndex2++) {
                    int dRow = pRow.get(pIndex2) - pRow.get(pIndex1); 
                    int dCol = pCol.get(pIndex2) - pCol.get(pIndex1); 
                    
                    // 0. 맨해튼 거리 밖에 있는지
                    if (Math.abs(dRow) + Math.abs(dCol) > 2) continue;
                    
                    // 1. 기울기 0인 위치에 있는지 
                    if (dRow == 0 || dCol == 0) {
                        int middleRow = (pRow.get(pIndex1) + pRow.get(pIndex2)) / 2; 
                        int middleCol = (pCol.get(pIndex1) + pCol.get(pIndex2)) / 2; 
                        
                        if (places[index][middleRow].charAt(middleCol) != 'X') {
                            flag = false;
                            break;
                        }
                    } 
                    
                    // 2. 대각선 위치에 있는지
                    else { 
                        if (dRow > 0) {
                            if (dCol > 0) {
                                if (places[index][pRow.get(pIndex1) + 1].charAt(pCol.get(pIndex1)) == 'X' 
                                   && places[index][pRow.get(pIndex1)].charAt(pCol.get(pIndex1) + 1) == 'X') {
                                    continue;
                                } else {
                                    flag = false;
                                    break;
                                }
                            } else {
                                if (places[index][pRow.get(pIndex1) + 1].charAt(pCol.get(pIndex1)) == 'X' 
                                   && places[index][pRow.get(pIndex1)].charAt(pCol.get(pIndex1) - 1) == 'X') {
                                    continue;
                                } else {
                                    flag = false;
                                    break;
                                }
                            }
                        } else { // dRow < 0
                            if (dCol > 0) {
                                if (places[index][pRow.get(pIndex1) - 1].charAt(pCol.get(pIndex1)) == 'X' 
                                   && places[index][pRow.get(pIndex1)].charAt(pCol.get(pIndex1) + 1) == 'X') {
                                    continue;
                                } else {
                                    flag = false;
                                    break;
                                }
                            } else {
                                if (places[index][pRow.get(pIndex1) - 1].charAt(pCol.get(pIndex1)) == 'X' 
                                   && places[index][pRow.get(pIndex1)].charAt(pCol.get(pIndex1) - 1) == 'X') {
                                    continue;
                                } else {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                
                if (flag == false) break;
            }
            
            if (flag == false) answer[index] = 0; 
            else answer[index] = 1;
        }
        
        return answer;
    }
}
```