package SWExpertAcademy;

import java.util.Scanner;

/*
    8개의 숫자를 입력받고
    첫 번째 숫자는 1 감소하고 맨 뒤로..
    두 번째 숫자는 2 감소하고 맨 뒤로..

    ....

    5 감소까지
    => 1사이클!

    숫자가 감소할 때 0보다 작아지면 0으로 하고 + 프로그램 종료

    이때 숫자가 암호!

    [Solution]
    0  1 2  3 4 5 6 7
    10 6 12 8 9 4 1 3

    1사이클 단위로 본다면,,
    start = 0
    0  1 2  3 4 5 6 7
    9  4 9  4 4 4 1 3
    start = 5 ((기존 start + 5) % 8)
 */

public class SWEA_1225_암호생성기_양채린 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = 10;
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            int Number = scanner.nextInt();

            int[] array = new int[8];
            for (int index = 0; index < 8; index++) {
                array[index] = scanner.nextInt();
            }

            int start = 0;
            boolean flag = false;
            while (true) {
                // 한 사이클
                for (int count = 0; count < 5; count++) {
                    array[start] -= (count + 1);

                    if (array[start] <= 0) {
                        array[start] = 0;
                        flag = true;
                        break;
                    }

                    start = (start + 1) % 8;
                }

                if (flag) {
                    break;
                }
            }
            start++;

            answer.append("#").append((tc + 1) + " ");

            int index = start;
            for (int count = 0; count < 8; count++) {
                answer.append(array[index] + " ");
                index = (index + 1) % 8;
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}
