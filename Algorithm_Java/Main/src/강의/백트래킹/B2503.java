package 강의.백트래킹;

import java.io.*;
import java.util.StringTokenizer;

public class B2503 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int N = 0;
    public static int answer = 0;
    public static int[][] hint;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        hint = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            hint[i][0] = Integer.parseInt(st.nextToken());
            hint[i][1] = Integer.parseInt(st.nextToken());
            hint[i][2] = Integer.parseInt(st.nextToken());
        }

//        recursion(0, 100);

        for (int number1 = 100; number1 <= 999; number1++) {
            if (!is_valid(number1)) {
                continue;
            }

            int hint_idx = 0;
            for (int j = 0; j < N; j++) {
                if (get_strike(number1, hint[j][0]) == hint[j][1] && get_ball(number1, hint[j][0]) == hint[j][2]) {
                    hint_idx++;
                } else {
                    break;
                }
            }
            if (hint_idx == N) {
//                System.out.println("number1 = " + number1);
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static boolean is_valid(int number1) {
        int firstDigit = number1 / 100;  // 백의 자리
        int secondDigit = (number1 / 10) % 10;  // 십의 자리
        int thirdDigit = number1 % 10;  // 일의 자리

        if (firstDigit == secondDigit || secondDigit == thirdDigit || thirdDigit == firstDigit) {
            return false;
        }

        if (firstDigit == 0 || secondDigit == 0 || thirdDigit == 0) {
            return false;
        }

        return true;
    }

    public static boolean recursion(int hint_idx, int number1) {
        if (hint_idx == N) {
            answer += 1;
            System.out.println("number1 = " + number1);
            return true;
        }

        if (number1 >= 1000) {
            return true;
        }

        if (get_strike(number1, hint[hint_idx][0]) == hint[hint_idx][1] && get_ball(number1, hint[hint_idx][0]) == hint[hint_idx][2]) {
            if (recursion(hint_idx + 1, number1)) {
                recursion(0, number1 + 1);
            } else {

            }
        } else {
            recursion(0, number1 + 1);
        }
        return false;
    }

    public static int get_strike(int number1, int number2) {
        String st_number1 = Integer.toString(number1);
        String st_number2 = Integer.toString(number2);

        int strike = 0;
        for (int i = 0; i < 3; i++) {
            if (st_number1.charAt(i) == st_number2.charAt(i)) {
                strike++;
            }
        }
        return strike;
    }

    public static int get_ball(int number1, int number2) { // number1, hint[hint_idx][0]
        String st_number1 = Integer.toString(number1);
        String st_number2 = Integer.toString(number2);

        int ball = 0;
        for (int i = 0; i < 3; i++) {
            if (st_number1.charAt(i) != st_number2.charAt(i) && st_number1.contains(Character.toString(st_number2.charAt(i)))) {
                ball++;
            }
        }
        return ball;
    }
}
