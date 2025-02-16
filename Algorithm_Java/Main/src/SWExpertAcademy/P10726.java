package SWExpertAcademy;

import java.util.Scanner;

/*
* 마지막 N개의 비트가 모두 켜져 있다면, ON
* 아니면 OFF
*
* N = 4일 때,
* 4 => 100 => OFF
*
* N = 4일 때,
* 30 => 11110 => OFF
*
* n = 4일 때,
* 47 => 101111 => ON
*
* n = 5일 때,
* 31 => 11111 => ON
*
* n = 5일 때,
* 62 => 111110 => ON
 */

public class P10726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        String[] answer = new String[testCase];

        for (int tc = 0; tc < testCase; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            String binaryString = Integer.toBinaryString(M);
//            System.out.println(binaryString);

            if (N > binaryString.length()) {
                answer[tc] = "OFF";
                continue;
            }

            boolean flag = true;
            for (int index = binaryString.length() - 1; index >= binaryString.length() - N; index--) {
//                System.out.println(index);

                if (index < 0) continue;

                if (binaryString.charAt(index) == '0') {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer[tc] = "ON";
            } else {
                answer[tc] = "OFF";
            }
        }

        for (int tc = 0; tc < testCase; tc++) {
            System.out.println("#" + (tc + 1) + " " + answer[tc]);
        }
    }
}
