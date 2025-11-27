package 백준;

/*
    성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다.
    게임판은 N X M이다.
    각 칸에 포함된 적의 수는 최대 하나이다.

    격자 판의 N번 행 바로 아래인 N+1번 행에는 모든 칸의 성이 있다.

    성을 적에게서 지키기 위해 궁수 3명을 배치한다.
    궁수는 성이 있는 칸에 배치할 수 있다.

    각 턴마다,
    궁수는 하나의 적을 공격할 수 있고
    모든 궁수는 동시에 공격한다.

    궁수가 공격하는 적은 거리가 D 이하인 적 중에서 가장 가까운 적이다.
    공격받은 적은 사라진다.

    공격이 끝나면, 적이 이동한다.
    적은 한 칸 아래로 이동하며, 성이 있는 곳으로 이동했다면, 게임에서 제외된다.

    모든 적이 제외되었을 때, 게임이 끝난다.

    => 궁수를 배치한 이후의 게임 진행은 정해져있다.
    궁수의 위치에 따라서, 제거할 수 있는 적의 최대 수를 계산한다.

    [제한사항]
    궁수가 공격할 수 있는 적이 여럿일 경우, 가장 왼쪽에 있는 적을 공격한다.
    같은 적이 여러 궁수에게 공격당할 수 있다.

    두 위치의 거리 |r1 - r2| + |c1 - c2| 이다.

    [Input]
    N M D
    N개의 줄에 격자판의 상태
    (0은 빈칸, 1은 적이 있는 칸이다.)

    [Output]
    "궁수의 공격으로 제거할 수 있는 적의 최대 수"
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17135_캐슬디펜스_양채린 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, M, D;
    static int[][] gameBoard;
    static int[][] newGameBoard;

    static boolean[] isSelected;
    static int combinationN, combinationR;

    static int initEnemyCount;
    static int removeEnemyMax;
    static int castleRow;

    public static void inputTest() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        gameBoard = new int[N + 1][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < M; col++) {
                gameBoard[row][col] = Integer.parseInt(st.nextToken());

                if (gameBoard[row][col] == 1) initEnemyCount += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 0. 초기화를 한다.
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 1. 입력을 받는다.
        inputTest();

        isSelected = new boolean[M];
        combinationN = M;
        combinationR = 3;
        combination(0, 0);

        System.out.println(removeEnemyMax);
    }


    // 2. N + 1 행에 궁수를 3명 배치한다.
    public static void combination(int n, int r) {
        if (r == combinationR) {
            newGameBoard = Arrays.stream(gameBoard)
                    .map(int[]::clone)
                    .toArray(int[][]::new);
            gameTurns();
            return;
        }

        if (r > combinationR || n == combinationN) {
            return;
        }

        isSelected[n] = true;
        combination(n + 1, r + 1);

        isSelected[n] = false;
        combination(n + 1, r);
    }

    public static void gameTurns() {
        // totalEnemyCount = removeEnemyCount + disappearEnemyCount;
        int removeEnemyCount = 0;
        int disappearEnemyCount = 0;

        castleRow = N;

        // turn 시작
        while (removeEnemyCount + disappearEnemyCount != initEnemyCount) {
            // 궁수는 적 하나를 공격한다.
            // 거리가 D 이하인 적 중 가장 가까운 적을 공격한다.
            // 그러한 적이 여럿일 경우, 가장 왼쪽에 있는 적을 공격한다.
            ArrayList<int[]> willRemoveEnemy = new ArrayList<>();

            for (int col = 0; col < M; col++) {
                // (castleRow, col)
                if (isSelected[col]) {
                    int[] findEnemy = findEnemy(col);
                    if (findEnemy[0] == -1 || findEnemy[1] == -1) continue;

                    willRemoveEnemy.add(findEnemy);
                }
            }

            // 공격 받은 적은 게임에서 제외된다.
            // count + 1
            for (int[] coordinate : willRemoveEnemy) {
                if (newGameBoard[coordinate[0]][coordinate[1]] == 1) {
                    newGameBoard[coordinate[0]][coordinate[1]] = 0;
                    removeEnemyCount += 1;
                }
            }

            // 적은 아래로 한 칸 이동한다.
            // 성이 있는 경우에는 게임에서 제외된다.
            disappearEnemyCount -= moveEnemies();
            if (castleRow == 0) {
                break;
            }
        }

        removeEnemyMax = Math.max(removeEnemyMax, removeEnemyCount);
    }

    static class Node {
        int row;
        int col;
        int distance;

        public Node(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

//    public static int[] findEnemy(int startRow, int startCol) {
//        // BFS 방식으로
//        // 왼쪽에서 오른쪽으로 갈꺼다
//        int[] dRow = {0, -1, 0, -1, -1};
//        int[] dCol = {-1, 0, 1, -1, 1};
//        int[] dDistance = {1, 1, 1, 2, 2};
//
//        Queue<Node> queue = new ArrayDeque<>();
//        boolean[][] isVisited = new boolean[N + 1][M];
//
//        for (int col = 0; col < M; col++) {
//            isVisited[startRow][col] = true;
//        }
//
//        queue.add(new Node(startRow, startCol, 0));
//        isVisited[startRow][startCol] = true;
//
//        while (! queue.isEmpty()) {
//            Node currentNode = queue.poll();
//            if (currentNode.distance >= D) break;
//
////            System.out.println("queue 꺼낸거 = " + currentNode.row + " , " + currentNode.col + " = " + currentNode.distance);
//
//            for (int index = 0; index < dRow.length; index++) {
//                int newRow = currentNode.row + dRow[index];
//                int newCol = currentNode.col + dCol[index];
//
////                System.out.println("newRow = " + newRow);
////                System.out.println("newCol = " + newCol);
//
//                if (newRow >= 0 && newRow < N
//                    && newCol >= 0 && newCol < M
//                    && !isVisited[newRow][newCol]) {
//                    if (newGameBoard[newRow][newCol] == 1 && currentNode.distance + dDistance[index] <= D) {
//                        return new int[] {newRow, newCol};
//                    } else {
//                        isVisited[newRow][newCol] = true;
//                        queue.add(new Node(newRow, newCol, currentNode.distance + dDistance[index]));
//                    }
//                }
//            }
//        }
//
//        return new int[] {-1, -1};
//    }

    public static int[] findEnemy(int archerCol) {
        int[] result = {-1, -1};
        int minDist = Integer.MAX_VALUE;

        // 궁수의 위치 (N)부터 D까지 거리 범위에서 적을 찾기
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (newGameBoard[row][col] == 1) {
                    int dist = Math.abs(castleRow - row) + Math.abs(archerCol - col);
                    if (dist <= D) {
                        if (dist < minDist || (dist == minDist && col < result[1])) {
                            minDist = dist;
                            result[0] = row;
                            result[1] = col;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static int moveEnemies() {
        castleRow -= 1;

        int disappearEnemyCount = 0;
        for (int col = 0; col < M; col++) {
            if (newGameBoard[castleRow][col] == 1) {
                newGameBoard[castleRow][col] = 0;
                disappearEnemyCount += 1;
            }
        }
        return disappearEnemyCount;
    }

    public static void printNewGameBoard() {
//        System.out.println("printNewGameBoard()");
//        System.out.println("castleRow = " + castleRow);
        for (int row = 0; row < N + 1; row++) {
            for (int col = 0; col < M; col++) {
                System.out.print(newGameBoard[row][col] + "\t");
            }
            System.out.println();
        }
    }
}
