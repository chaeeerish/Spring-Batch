package SWExpertAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;

/*
* 1년 동안 가장 적은 비용으로 수영장을 이용하고 싶다.
* 이용권 4가지 종류
* 1. 1일 이용권: 1일 이용이 가능하다.
* 2. 1달 이용권: 1달 이용이 가능하다.
* 3. 3달 이용권: 연속된 3달 동안 이용이 가능하다.  (그 해에 다 써야함)
* 4. 1년 이용권: 1년 동안 이용이 가능하다.
*
* [Input]
* 각 이용권 요금
* 각 달의 이용 계획
*
* 10
* 10 40 100 300
* 0 0 2 9 1 5 0 0 0 0 0 0
*
* [Output]
* 가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고, 그 비용 출력해라.
*
* [해결]
* 4가지 경우가 있다.
* 1.
*   17일*10원 = 170원
* 2.
*   4달*40원 = 160원
* 3.
*   1일*2*10원 + 3달*1*100원 = 120원
* 4.
*   1일*3*10원 + 1달*2*40원 = 110원
* 5.
*   1년*300원 = 300원
*/

public class P1952 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/chaeeerish/Documents/GitHub/Algorithm_Java/Main/src/SWExpertAcademy/P1952_input.txt"));
        Scanner sc = new Scanner(System.in);

        int testCase = Integer.parseInt(sc.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            int[] feeList = new int[4];
            int[] monthPlan = new int[12];
            int[] dp = new int[13];

            // 10 40 100 300
            String[] temp = sc.nextLine().split(" ");
            for (int index = 0; index < 4; index++) {
                feeList[index] = Integer.parseInt(temp[index]);
            }

            // 0 0 2 9 1 5 0 0 0 0 0 0
            temp = sc.nextLine().split(" ");
            for (int index = 0; index < 12; index++) {
                monthPlan[index] = Integer.parseInt(temp[index]);
            }

            // Dynamic Programming
            for (int i = 0; i < 12; i++) {
                // 1일 이용권
                dp[i + 1] = dp[i] + monthPlan[i] * feeList[0];

                // 1달 이용권
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + feeList[1]);

                // 3달 이용권
                if (i >= 2) {
                    dp [i + 1] = Math.min(dp[i + 1], dp[i - 2] + feeList[2]);
                }
            }

            // 1년 이용권
            int 최소비용 = Math.min(dp[12], feeList[3]);
            answer.append("#").append((tc + 1)).append(" ").append(최소비용).append("\n");
        }

        System.out.println(answer);
    }
}
