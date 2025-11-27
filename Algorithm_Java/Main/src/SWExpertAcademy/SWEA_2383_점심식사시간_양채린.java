package SWExpertAcademy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * N*N 정사각형 모양 방
 * 계단에 빨리 도착해야되는데..
 *
 * P는 사람들, S는 계단이다.
 *
 * 이동 완료 시간 = 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * = 모든사람 [계단 입구까지 가는 시간 + 계단을 내려가는 시간]
 *
 * 계단 입구까지 가는 시간 = | 사람 세로위치 - 계단 가로위치 | + | 사람 가로위치 - 계단 세로위치 |
 * 계단을 내려가는 시간 = 계단 입구에 도착하면, 1분 후 아래로 내려갈 수 있고
 *                          + 계단에는 동시 3명만 가능하고
 *                          + 3명이 내려가는 중이면, 기다려야 한다.
 *                          + 내려가는 시간은 계단 길이 K만큼이다.
 *
 * [Input]
 * 테스트케이스개수10
 * 지도크기N
 * N X N의 지도 정보 (1은 사람, 2는 계단의 입구이자 길이)
 *
 * [Output]
 * 이동이 완료되는 최소 시간
 *
 * [Solution]
 * 1. 입력을 받는다
 * 2. 완전탐색으로 각 사람이 1번 계단으로 가느냐, 2번 계단으로 가느냐..
 * 3. DFS로 powet set을 구하고, 각각의 경우에 대해서 시뮬레이션을 돌린다.
 *  3-1. 1번 계단 / 2번 계단 나누어서 2번 진행
 *      3-1-1. 사람 ~ 계단으로의 거리를 구한다.
 *      3-1-2. 시간은 계속 가는 중 ...
 *          3-1-1-1. 각 사람 별로 셋 중 하나를 수행한다.
 *              3-1-1-1-1. 이동시킨다.
 *              3-1-1-1-2. 계단앞에서 1분 대기한다.
 *              3-1-1-1-3. 계단을 내려간다. == 계단 QUEUE에 들어간다.
 *              3-1-1-1-4. 계단을 다 내려갔다. == 계단 QUEUE에서 나온다.
 *  3-2. 각 계단에서 걸린 시간 중 최다 시간이 총 소요 시간이다.
 *  3-3. 지금까지의 최소 시간과 비교해서 더 작은 것을 채택한다.
 */

public class SWEA_2383_점심식사시간_양채린 {
    static Scanner scanner;

    static int T;
    static int N;
    static int[][] map;

    static ArrayList<Integer> peopleRows;
    static ArrayList<Integer> peopleCols;

    static ArrayList<Integer> stairRows;
    static ArrayList<Integer> stairCols;
    static ArrayList<Integer> stairTime;

    static int[] whichStair; // 0번, 1번

    static int minTime;

    static void input() {
        N = scanner.nextInt();
        map = new int[N][N];

        peopleRows = new ArrayList<>();
        peopleCols = new ArrayList<>();

        stairRows = new ArrayList<>();
        stairCols = new ArrayList<>();
        stairTime = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                map[row][col] = scanner.nextInt();

                if (map[row][col] == 1) {
                    peopleRows.add(row);
                    peopleCols.add(col);
                } else if (map[row][col] >= 2) {
                    stairRows.add(row);
                    stairCols.add(col);
                    stairTime.add(map[row][col]);
                }
            }
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        T = scanner.nextInt();

        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            // 1. 입력을 받는다
            input();

            // 2. 완전탐색으로 각 사람이 1번 계단으로 가느냐, 2번 계단으로 가느냐..
            whichStair = new int[peopleRows.size()];
            minTime = Integer.MAX_VALUE;
            powerSet(0);

            // 4. 출력한다
            answer.append("#").append(tc + 1).append(" ").append(minTime).append("\n");
        }

        System.out.println(answer);
    }

    static void powerSet(int elementIndex) {
        if (elementIndex == peopleRows.size()) {
            simulate();
            return;
        }

        whichStair[elementIndex] = 0;
        powerSet(elementIndex + 1);

        whichStair[elementIndex] = 1;
        powerSet(elementIndex + 1);
    }

    static void simulate() {
        int time1 = stairTimeFlow(0);
        int time2 = stairTimeFlow(1);

        minTime = Math.min(minTime, Math.max(time1, time2));
    }

    static class Node {
        int index;
        int remainTime;

        public Node(int index, int remainTime) {
            this.index = index;
            this.remainTime = remainTime;
        }
    }

    static int stairTimeFlow(int stair) {
// *      3-1-1. 사람 ~ 계단으로의 거리를 구한다.
        ArrayList<Integer> distance = new ArrayList<>();
        for (int index = 0; index < peopleRows.size(); index++) {
            if (whichStair[index] != stair) continue;

            distance.add(Math.abs(peopleRows.get(index) - stairRows.get(whichStair[index]))
                    + Math.abs(peopleCols.get(index) - stairCols.get(whichStair[index])));
        }

// *      3-1-2. 시간은 계속 가는 중 ...
// *          3-1-1-1. 각 사람 별로 셋 중 하나를 수행한다.
//                *              3-1-1-1-1. 이동시킨다.
//                *              3-1-1-1-2. 계단앞에서 1분 대기한다.
//                *              3-1-1-1-3. 계단을 내려간다. == 계단 QUEUE에 들어간다.
//                *              3-1-1-1-4. 계단을 다 내려갔다. == 계단 QUEUE에서 나온다.

        ArrayList<Integer> waitingStair = new ArrayList<>();
        ArrayList<Node> inStair = new ArrayList<>();

        int time = 1;
        while (true) {
            if (isDone(distance, waitingStair, inStair)) {
                break;
            }

            // 3-1-1-1-1. 이동시킨다.
            for (int index = 0; index < distance.size(); index++) {
                // 아직 이동 중인 노드
                if (distance.get(index) >= 0) {
                    distance.set(index, distance.get(index) - 1);

                    // 3-1-1-1-2. 계단앞에서 1분 대기한다.
                    // 만약 도착했고 1분도 더 지났다면 == 이제 들어갈 준비가 되었다
                    if (distance.get(index) == -1) {
                        if (inStair.size() <= 2) {
                            inStair.add(new Node(index, stairTime.get(stair)));
                        } else {
                            waitingStair.add(index);
                        }
                    }
                }
            }

            ArrayList<Node> willRemove = new ArrayList<>();
            for (Node inStairPeople : inStair) {
                inStairPeople.remainTime -= 1;

                // 3-1-1-1-4. 계단을 다 내려갔다. == 계단 QUEUE에서 나온다.
                if (inStairPeople.remainTime == 0) {
                    willRemove.add(inStairPeople);
                }
            }
            inStair.removeAll(willRemove);

            // 3-1-1-1-3. 계단을 내려간다. == 계단 QUEUE에 들어간다.
            while (inStair.size() <= 2 && waitingStair.size() >= 1) {
                Integer waitingStairPeople = waitingStair.remove(0);
                inStair.add(new Node(waitingStairPeople, stairTime.get(stair)));
            }

            time += 1;
        }

        return time;
    }

    static boolean isDone(ArrayList<Integer> distance, ArrayList<Integer> waitingStair, ArrayList<Node> inStair) {
        for (int index = 0; index < distance.size(); index++) {
            if (distance.get(index) != -1) return false;
        }
        if (waitingStair.size() != 0) return false;
        if (inStair.size() != 0) return false;

        return true;
    }
}
