package 백준;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
    맥주 마시며 걸어가기

    출발: 상근이네 집 with 맥주 20개
    진행: 50미터 당 한 병을 마신다. == 50미터를 가기 위해서 맥주 한 병이 필요하다.
    편의점: 빈 맥주병을 버리고 새 맥주병을 구매할 수 있다. 하지만, 20병을 넘으면 안된다.

    편의점, 상근이네 집, 페스티벌 좌표가 주어질 때,
    행복하게 페스티벌에 도착할 수 있는가?

    [Input]
    테스트케이스개수t
    편의점개수n
    상근이네집 x y
    편의점 x y
    페스티벌 x y

    두 좌표 사이의 거리는 x1 - x2 + y1 - y2

    [Output]
    행복하게 갈 수 있으면? happy
    맥주가 바닥나면? sad

    [Solution]
    1. 입력받는다.
    2. DFS, 집에서 출발
        2-1. 페스티벌을 방문할 수 있으면, true return
        2-2. 페스티벌, 방문하지 않은 편의점 중에 거리가 1000이하인 장소
            2-1-1. isVisited = true
            2-1-2. DFS
                2-1-2-1. true가 리턴되면 바로 true return
            2-1-3. isVisited = false;

    XX

    distance가 1000이하인 간선만 남겨두었을 ㄸ ㅐ...
    house에서 BFS로 출발하여, festival에 도착하는가?

    1. 입력받는다.
    2. distance가 1000이하이면, boolean[][] map에 true로 연결한다.
    3. 해당 간선들로 N에서 N+1로 도착할 수 있는지 검증한다.
    4. 플로이드 워샬로 k를 거쳐서 갈 수 있으면 true를 한다.
    5. N에서 N+1로 최종적으로 갈 수 있으면 happy를 출력한다.
 */

public class BOJ_9205_맥주마시면서걸어가기_양채린 {
    static Scanner scanner;
    static StringBuilder output;

    static int T;
    static int N;
    static int houseX, houseY, festivalX, festivalY;
    static int[][] storeXY;

    static boolean[][] floyd;

    static void input() {
        N = scanner.nextInt();

        houseX = scanner.nextInt();
        houseY = scanner.nextInt();

        storeXY = new int[N][2];
        for (int index = 0; index < N; index++) {
            storeXY[index][0] = scanner.nextInt();
            storeXY[index][1] = scanner.nextInt();
        }

        festivalX = scanner.nextInt();
        festivalY = scanner.nextInt();
    }

    static void init() {
        floyd = new boolean[N + 2][N + 2];

        // 집 ~ 페스티벌
        if (getDistacne(houseX, houseY, festivalX, festivalY) <= 1000) {
            floyd[N][N + 1] = true;
        }

        // 집 ~ 편의점
        for (int index = 0; index < N; index++) {
            if (getDistacne(houseX, houseY, storeXY[index][0], storeXY[index][1]) <= 1000) {
                floyd[N][index] = true;
            }
        }

        // 편의점끼리
        for (int index1 = 0; index1 < N; index1++) {
            for (int index2 = index1 + 1; index2 < N; index2++) {
                if (getDistacne(storeXY[index1][0], storeXY[index1][1], storeXY[index2][0], storeXY[index2][1]) <= 1000) {
                    floyd[index1][index2] = true;
                    floyd[index2][index1] = true;
                }
            }
        }

        // 편의점 ~ 페스티벌
        for (int index = 0; index < N; index++) {
            if (getDistacne(storeXY[index][0], storeXY[index][1], festivalX, festivalY) <= 1000) {
                floyd[index][N + 1] = true;
            }
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        output = new StringBuilder();
        T = scanner.nextInt();

        for (int testCase = 0; testCase < T; testCase++) {
            input();
            init();

            for (int k = 0; k < N + 2; k++) {
                for (int a = 0; a < N + 2; a++) {
                    for (int b = 0; b < N + 2; b++) {
                        if (floyd[a][b] == false && floyd[a][k] == true && floyd[k][b] == true) {
                            floyd[a][b] = true;
                        }
                    }
                }
            }

            if (floyd[N][N + 1]) output.append("happy").append("\n");
            else output.append("sad").append("\n");
        }
        System.out.print(output);
    }

    static int getDistacne(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
