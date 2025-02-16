package 백준;

/*
    병사를 배치할 때 전투력이 높은 병사가 앞쪽에 오도록 내림차순으로 배치한다.
    남아있는 병사의 수가 최대가 되도록

    특정한 위치에 있는 병사를 열외시키면서,, 남아있는 병사의 수가 최대?

    아~!
    이 순위를 유지하면서, 제외하고, 그런데 내림차순이 되는,,

    15 11 4 8 5 2 4
    15 11 4     2
    15 11   8 5   4
    15 11   8 5 2

    ‼️ 재귀로 했더니 시간초과...

    적어둬볼까?
    지금부터 적는건 i ~ length 까지 내림차순 배열 크기 최대 값
    15 11 4 8 5 2 4
                  1
                1
              2
            3
          2
       4
    5

    내 다음의 인덱스를 i + 1 ~ length 를 돌면서 나보다 작으면서 + 가장 큰 숫자를 가진 애 + 1을 한다.
*/

import java.util.Scanner;
import java.util.stream.IntStream;

public class P18353 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] array = new int[N];
        int[] dp = new int[N];
        for (int index = 0; index < N; index++) {
            array[index] = sc.nextInt();
            dp[index] = 1;
        }

        for (int index = N - 1; index >= 0; index--) {
            int max = 0;
            for (int index2 = index + 1; index2 < N; index2++) {
                if (array[index] > array[index2]) {
                    max = Math.max(max, dp[index2]);
                }
                dp[index] = max + 1;
            }
        }

        int answer = 0;
        for (int index = 0; index < N; index++) {
            answer = Math.max(answer, dp[index]);
        }

        System.out.println(N - answer);
    }
}
