package SWExpertAcademy;

import java.util.*;

/*
    힙은 최대값 혹은 최솟값을 찾아내는 연산을 빠르게 하기 위해 고안된 자료구조이다.
    완전이진트리를 기본으로 한 자료구조이다.

    힙 속성
    - A가 B의 부모노드이면, A의 키값과 B의 키값 사이에는 항상 일정한 대소 관계가 성립한다.
    - 형제노드 사이에서는 일정한 대소관계가 정해지지 않는다.

    부모노드 키 > 자식노드 키 = 최대힙
    부모노드 키 < 자식노드 키 = 최소힙

    [Input]
    테스트케이스수 T
    연산수 N
    N개의 줄에 걸쳐서, 순서대로 수행해야하는 연산에 대한 정보
    1 x => x를 힙에 추가한다.
    2 => 최대 키를 출력해라. 없으면 -1

    [Output]
    연산 2의 결과들을 출력해라.

    [Solution]
    우선순위큐?
 */

public class SWEA_2930_힙 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.parseInt(scanner.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            int N = Integer.parseInt(scanner.nextLine()); // 연산의 수

            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
            answer.append("#").append(tc + 1).append(" ");

            for (int index = 0; index < N; index++) {
                String[] temps = scanner.nextLine().split(" ");

                if (temps.length == 2) { // 힙에 추가
                    priorityQueue.add(Integer.parseInt(temps[1]));
                } else { // 힙에서 삭제
                    if (priorityQueue.size() == 0) answer.append(-1).append(" ");
                    else answer.append(priorityQueue.remove()).append(" ");
                }
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}
