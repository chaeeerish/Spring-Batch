package SWExpertAcademy;

import java.util.ArrayList;
import java.util.Scanner;

/*
    N X N 개의 로 구성되어 있다.
    1개의 셀에는 1개의 전선 혹은 코어가 올 수 있다.

    코어와 연결하는 전선은 직선만 가능하다.
    전선의 교차는 안된다. + X
    가장자리에 위치한 코어는 이미 연결된 것으로 간주한다.

    최대한 많은 코어에 전원을 연결하였을 경우, 전선 길이의 합을 구하자.
    여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값을 구하라.

    [Input]
    테스트케이스개수
    N
    N X N
    (0은 빈 셀, 1은 코어)

    [Output]
    #x 전선길이의합(최대한많은코어전원연결하기위한)(여러방법이있을경우최소!)

    [Solution]
    1. cell row, col을 저장한다.
    2. 첫번째 cell 부터 4방향으로 연결해본다.
    3. DFS, 재귀로 각 방향에 연결했을 때 => 다음 cell로 넘어간다.
 */

public class SWEA_1767_프로세서연결하기_양채린 {
    public static int N;
    public static int[][] process;
    public static ArrayList<Integer> coreRows;
    public static ArrayList<Integer> coreCols;
    public static boolean[] isValidCore;

    public static int maxConnectCount;
    public static int minConnectDistance;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.parseInt(scanner.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            N = scanner.nextInt();
            process = new int[N][N];

            coreRows = new ArrayList<>();
            coreCols = new ArrayList<>();

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    process[row][col] = scanner.nextInt();
                }
            }

            int connectCount = 0;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (process[row][col] == 1) {
                        if (row == 0 || row == N - 1 || col == 0 || col == N - 1) {
                            connectCount += 1;
                            continue;
                        }

                        if (isAvailableConnect(row, col)) {
                            coreRows.add(row);
                            coreCols.add(col);
                        }
                    }
                }
            }

            maxConnectCount = -1;
            minConnectDistance = Integer.MAX_VALUE;
            dfs(0, connectCount);

            answer.append("#").append((tc + 1)).append(" ").append(minConnectDistance).append("\n");
        }

        System.out.println(answer);
    }

    public static void dfs(int currentIndex, int connectCount) {
        if (currentIndex == coreRows.size()) {
            if (connectCount >= maxConnectCount) {
                if (connectCount == maxConnectCount) minConnectDistance = Math.min(minConnectDistance, getConnectDistance());
                else minConnectDistance = getConnectDistance();

                maxConnectCount = connectCount;

//                System.out.println("connectCount = " + connectCount);
//                System.out.println("getConnectDistance() = " + getConnectDistance());
//                printProcess();
            }
            return;
        }

        int currentCoreRow = coreRows.get(currentIndex);
        int currentCoreCol = coreCols.get(currentIndex);

        // 위쪽
        if (isValidConnect(0, 0, currentCoreRow, currentCoreCol, currentCoreCol)) {

            connectCore(0, 0, currentCoreRow, currentCoreCol, currentCoreCol);
            dfs(currentIndex + 1, connectCount + 1);
            disconnectCore(0, 0, currentCoreRow, currentCoreCol, currentCoreCol);
        }

        // 아래쪽
        if (isValidConnect(0, currentCoreRow + 1, N, currentCoreCol, currentCoreCol)) {

            connectCore(0, currentCoreRow + 1, N, currentCoreCol, currentCoreCol);
            dfs(currentIndex + 1, connectCount + 1);
            disconnectCore(0, currentCoreRow + 1, N, currentCoreCol, currentCoreCol);
        }

        // 왼쪽
        if (isValidConnect(1, currentCoreRow, currentCoreRow, 0, currentCoreCol)) {

            connectCore(1, currentCoreRow, currentCoreRow, 0, currentCoreCol);
            dfs(currentIndex + 1, connectCount + 1);
            disconnectCore(1, currentCoreRow, currentCoreRow, 0, currentCoreCol);
        }

        // 오른쪽
        if (isValidConnect(1, currentCoreRow, currentCoreRow, currentCoreCol + 1, N)) {

            connectCore(1, currentCoreRow, currentCoreRow, currentCoreCol + 1, N);
            dfs(currentIndex + 1, connectCount + 1);
            disconnectCore(1, currentCoreRow, currentCoreRow, currentCoreCol + 1, N);
        }

        // 안함
        dfs(currentIndex + 1, connectCount);
    }

    // flag == 0 : 위 아래
    // flag == 1 : 왼쪽 오른쪽
    public static boolean isValidConnect(int flag, int row1, int row2, int col1, int col2) {
        if (flag == 0) { // row 검사
            for (int row = row1; row < row2; row++) {
                if (process[row][col1] != 0) {
                    return false;
                }
            }
        }

        if (flag == 1) { // col 검사
            for (int col = col1; col < col2; col++) {
                if (process[row1][col] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isAvailableConnect(int coreRow, int coreCol) {
        if (isValidConnect(0, 0, coreRow, coreCol, coreCol)
            || isValidConnect(0, coreRow + 1, N, coreCol, coreCol)
            || isValidConnect(1, coreRow, coreRow, 0, coreCol)
            || isValidConnect(1, coreRow, coreRow, coreCol + 1, N)) {
            return true;
        }

        return false;
    }

    public static void connectCore(int flag, int row1, int row2, int col1, int col2) {
        if (flag == 0) {
            for (int row = row1; row < row2; row++) {
                process[row][col1] = 2;
            }
        }

        if (flag == 1) {
            for (int col = col1; col < col2; col++) {
                process[row1][col] = 2;
            }
        }
    }

    public static void disconnectCore(int flag, int row1, int row2, int col1, int col2) {
        if (flag == 0) {
            for (int row = row1; row < row2; row++) {
                process[row][col1] = 0;
            }
        }

        if (flag == 1) {
            for (int col = col1; col < col2; col++) {
                process[row1][col] = 0;
            }
        }
    }

    public static int getConnectDistance() {
        int distance = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (process[row][col] == 2) {
                    distance += 1;
                }
            }
        }
        return distance;
    }

    public static void printProcess() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(process[row][col] + " ");
            }
            System.out.println();
        }
    }
}
