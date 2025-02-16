import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class 기본_문법 {
    public static void main(String[] args) {
        // print
        System.out.println("Hello World");
        System.out.println(35);
        System.out.println(35 + 30);

        // 변수
        int x = 40;
        System.out.println(x);
        x = 50;
        final int y = 40;

        // 데이터 타입
        long l = 30L;
        int i = 30;
        short s = 30;
        byte b = 30;

        double dd = 30.0; // double이 더 크다
        float ff = 30.0f;

        boolean isMarried = true;

        char c = 'a';
        char cc = '한';

        String str = "여러 글자 작성 가능한다.";

        // 형 변환(타입 캐스팅)
        int ii = (int) 30L;
        long ll = 30;

        dd = ff; // 자동 형변환
        ff = (float) dd; // 강제 형변환

        // 문자열
        System.out.printf("저는 %s입니다. 나이는 %d살이고요, 키는 %fcm입니다.\n", "홍길동", 20, 164.0);
        String str2 = String.format("저는 %s입니다. 나이는 %d살이고요, 키는 %fcm입니다.", "홍길동", 20, 164.0);
        System.out.println(str2);

        // Math
        System.out.println(Math.max(10, 30));
        System.out.println(Math.min(10, 30));
        System.out.println(Math.abs(-1));

        // 문자열 숫자 상호 변환
        int iii = Integer.parseInt("100");
        String str3 = String.valueOf(iii);
        long lll = Long.parseLong(str3);

        // Random
        Random random = new Random();
        int rand = random.nextInt(10); // 0 ~ 9
        int rand2 = random.nextInt(4) + 5; // 5 ~ 9

        // 키보드 입력
        Scanner scanner = new Scanner(System.in);
        String str4 = scanner.next();
        int iiii = scanner.nextInt();

        // if 문
        int  iiiii = 10;
        if (i < 5) {

        } else if (i < 3) {

        } else {

        }

        // 삼항연산
        boolean isPerson = true;
        String str5 = isPerson ? "결혼 했다" : "결혼 안 했다";

        // 논리연산
        boolean b1 = true && true;
        boolean b2 = true || true;

        switch (str5) {
            case "결혼 했다":
                System.out.println("O");
                break;
            case "결혼 안 했다":
                System.out.println("X");
                break;
            default:
                System.out.println("?");
        }

        // 반복문
        for (int j = 0; j < 10; j++) {
            System.out.println(j);
        }

        while (true) {
            break;
        }

        do {
            System.out.println("hi");
            break;
        } while (true);

        // 배열
        int[] score = new int[5]; // 초기화를 하지 않으면 0으로 초기화
        int[] score2 = new int[] {10, 20, 30, 40, 50};
        int[] score3 = {10, 20, 30, 40, 50};
        System.out.println(score.length);

        score[0] = 10;
        score[1] = 10;
        score[2] = 10;
        score[3] = 10;
        score[4] = 10;

        String[] names = new String[2]; // 초기화를 하지 않으면 null로 초기화

        // ArrayList
        ArrayList<Integer> scoreList = new ArrayList<>();
        scoreList.add(10);
        scoreList.add(20);
        scoreList.add(30);
        scoreList.add(40);
        scoreList.add(50);

        System.out.println(scoreList.get(0));
        System.out.println(scoreList.size());

        scoreList.remove(2);
    }

    // 최대 공약수
    public static int gcd(int a, int b) {
        int tmp = -1;
        while (a % b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return b;
    }

    // 최소 공배수
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
