package SWExpertAcademy;

import java.util.Scanner;

/*
    제곱근 놀이

    2이상의 정수 N
    - N을 N + 1로 바꿀 수 있다.
    - root N이 정수일 때, N을 root N으로 바꿀 수 있다.

    게임의 목표 => N을 2로 만들자.

    N을 2로 만들기 위해 조작해야하는 횟수의 최솟값은?

    [Input]
    testcase
    N = 2
    3
    4
    ...

    [Output]
    N을 2로 만들어야 하는 횟수의 최소!

    [Solution]
    N 은 두 가지 경우 중에 하나로 간다.
    1. N + 1
    2. root N
 */

public class SWEA_6782_현주가좋아하는제곱근놀이_양채린 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            long N = scanner.nextLong();
            long count = 0;

            while (N > 2) {
                long sqrt = (long) Math.sqrt(N);

                if (sqrt * sqrt == N) {
                    count += 1;
                    N = (long) Math.sqrt(N);
                } else {
                    count += ((sqrt + 1) * (sqrt + 1) - N);
                    N = (sqrt + 1) * (sqrt + 1);
                }
            }

            answer.append("#").append(tc + 1).append(" ").append(count).append("\n");
        }

        System.out.println(answer);
    }
}
