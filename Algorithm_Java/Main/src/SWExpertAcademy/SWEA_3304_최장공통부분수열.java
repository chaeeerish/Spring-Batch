package SWExpertAcademy;

import java.util.Scanner;

/*
    최대 공통 부분 수열 LCS ...
    acaykp
    capcak
    의 경우,

    최대 공통 부분 수열은 acak이다.
    (문자열이 아님을 주의해라.)

    [Input]
    테스트케이스수
    두문자열을공백을사이에두고주어진다

    [Output]
    최대공통부분수열길이

    [Solution]
    a c a y k p
    c a p c a k
 */

public class SWEA_3304_최장공통부분수열 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.parseInt(scanner.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            String[] temps = scanner.nextLine().split(" ");
            String string1 = temps[0];
            String string2 = temps[1];

            int[][] dp = new int[string1.length() + 1][string2.length() + 1];
            for (int row = 1; row < string1.length() + 1; row++) {
                for (int col = 1; col < string2.length() + 1; col++) {
                    int string1Index = row - 1;
                    int string2Index = col - 1;

                    if (string1.charAt(string1Index) == string2.charAt(string2Index)) {
                        dp[row][col] = dp[row - 1][col - 1] + 1;
                    } else {
                        dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                    }
                }
            }

            answer.append("#").append((tc + 1)).append(" ").append(dp[string1.length()][string2.length()]).append("\n");
        }
        System.out.println(answer);
    }
}
