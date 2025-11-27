package SWExpertAcademy;

import java.util.Scanner;

/*
 * 상하좌우로 밀수있다.
 * 방향을 정하면, 격자 위에 있는 모든 타일이 그 방향으로 밀린다.
 *
 * 밀리는 방향에 다른 타일이 있고, 두 타일에 적힌 숫자가 같다면 !!,, 두 타일은 합쳐져 새로운 하나의 타일이 된다.
 * 이렇게 합쳐져서 만든 새로운 타일은 숫자가 같은 다른 타일이 와도 합쳐지지 않는다.
 *
 * 2 2 4 2 2 2
 * 왼쪽으로 민다.
 * 4 4 4 2 0 0
 *
 * [Input]
 * 테스트 케이스 개수
 * 정수 문자열
 * 타일 map
 *
 * 0이면 칸에 타일이 없다는 뜻이다.
 *
 * [Output]
 * 움직인 이후 칸들의 모습
 *
 * [Solution]
 * 음....
 *
 * left, right, up, down 중에 하나 이고....
 *
 * left냐 right냐
 * up이나 down이냐
 * => 방향만 다름
 *
 * left/right냐 up/down이냐
 * => 열이냐 행이냐만 다름
 *
 * left 인 경우)
 * 2 2 4 2 2 2
 * 4 4 4 2 0 0
 *
 * 2랑 2랑 같으니까 새로운 타일 4
 * 4 그대로 두고 4랑 2랑 다르니까 퍃스
 * 4 그대로 두고
 * 2랑 2랑 같으니까 새로운 타일 4
 * 2 그대로 두고
 * 나머지 0으로 채우기
 *
 * up 인 경우)
 * 4 (0, 0)
 * 4 (1, 0)
 * 8 (2, 0)
 * 2 (3, 0)
 * 0 (4, 0)
 *
 * 8 (0, 0)
 * 8 (1, 0)
 * 2 (2, 0)
 * 0 (3, 0)
 * 0 (4, 0)
 */

public class SWEA_6109_추억의2048게임_양채린 {
    public static int N;
    public static int[][] map;
    public static int[][] newMap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.parseInt(scanner.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            // 0. 초기화를 한다.
            String[] temps = scanner.nextLine().split(" ");
            N = Integer.parseInt(temps[0]);
            String S = temps[1];

            map = new int[N][N];
            newMap = new int[N][N];

            for (int row = 0; row < N; row++) {
                temps = scanner.nextLine().split(" ");
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(temps[col]);
                }
            }

            // 1. 어떤 S냐에 따라 분기한다.
            switch(S) {
                case "left":
                    for (int row = 0; row < N; row++) {
                        newMap[row] = push(map[row]);
                    }
                    break;
                case "right":
                    for (int row = 0; row < N; row++) {
                        reverseArray(map[row]);
                        newMap[row] = push(map[row]);
                        reverseArray(newMap[row]);
                    }

                    break;
                case "up":
                    for (int col = 0; col < N; col++) {
                        int[] columnArray = new int[N];
                        for (int row = 0; row < N; row++) {
                            columnArray[row] = map[row][col];
                        }

                        int[] newArray = push(columnArray);

                        for (int row = 0; row < N; row++) {
                            newMap[row][col] = newArray[row];
                        }
                    }
                    break;
                case "down":
                    for (int col = 0; col < N; col++) {
                        int[] columnArray = new int[N];
                        for (int row = 0; row < N; row++) {
                            columnArray[row] = map[row][col];
                        }

                        reverseArray(columnArray);
                        int[] newArray = push(columnArray);
                        reverseArray(newArray);

                        for (int row = 0; row < N; row++) {
                            newMap[row][col] = newArray[row];
                        }
                    }

                    break;
            }

            answer.append("#").append(tc + 1).append("\n");
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    answer.append(newMap[row][col]).append(" ");
                }
                answer.append("\n");
            }
        }

        System.out.println(answer);
    }

    // left
    public static int[] push(int[] originArray) {
        int[] newArray = new int[N];

        // * 2 2 4 2 2 2
        // * 4 4 4 2 0 0

        int curIndex = 0;
        int fixedIndex = 0;

        // 0인 경우, 미리 당겨두기
        shiftZeros(originArray);

        while (true) {
            if (originArray[curIndex] == originArray[curIndex + 1]) {
                newArray[fixedIndex] = originArray[curIndex] * 2;
                curIndex += 2;
                fixedIndex += 1;
            } else {
                newArray[fixedIndex] = originArray[curIndex];
                curIndex += 1;
                fixedIndex += 1;
            }

            if (curIndex == N - 1) {
                newArray[fixedIndex] = originArray[curIndex];
                curIndex += 1;
                fixedIndex += 1;
                break;
            }

            if (curIndex + 1 >= N) {
                break;
            }
        }

        return newArray;
    }

    // 0이면 한 칸씩 앞으로 당기기
    public static void shiftZeros(int[] arr) {
        int index = 0;

        for (int num : arr) {
            if (num != 0) {
                arr[index++] = num;
            }
        }

        while (index < arr.length) {
            arr[index++] = 0;
        }
    }

    // 배열 뒤집기
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}
