package SWExpertAcademy;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 수제버거장인
 *
 * 신메뉴를 개발하려고 한다.
 * 1번부터 N번까지 번호가 매겨져서 총 N가지의 재료가 있다.
 *
 * i번 재료와 j번 재료가 서로 궁합이 맞지 않는다면, 동시에 포함된 버거를 만들 수 없다.
 * 궁합이 맞지 않는 재료들로 M개의 쌍에 대한 정보가 주어졌을 때 ... 만들 수 있는 버거의 종류 ...
 *
 * [Input]
 * 테스트케이스 개수
 * N, M
 * M개의 줄에 동시에 들어가면 안되는 재료 번호 a, b
 *
 * [Output]
 * 제약조건을 만족시키며 만들 수 있는 버거의 가짓수
 *
 * [Hint]
 * {}, {1}, {1, 3}, {2}, {3}
 *
 * [Solution]
 * 부분집합 만들 수 있는 만큼 만들고,,,
 * 만약 그 안되는 조합에 포함된다면 PASS인데,,,
 *
 * 2^20 * 400 = 4억 ... ?
 */

public class SWEA_3421_수제버거장인_양채린 {
    public static int N;
    public static int M;

    public static int[][] pairs;

    public static int burgerCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = Integer.parseInt(scanner.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            String[] temps = scanner.nextLine().split(" ");
            N = Integer.parseInt(temps[0]);
            M = Integer.parseInt(temps[1]);

            pairs = new int[M][2];
            burgerCount = 0;

            for (int index = 0; index < M; index++) {
                temps = scanner.nextLine().split(" ");
                pairs[index][0] = Integer.parseInt(temps[0]) - 1;
                pairs[index][1] = Integer.parseInt(temps[1]) - 1;
            }

            powerset(0, 0);

            answer.append("#").append((tc + 1) + " ").append(burgerCount).append("\n");
        }

        System.out.println(answer);
    }

    public static void powerset(int cnt, int bitmask) {
        if (cnt == N) {
//            ArrayList<Integer> burger = new ArrayList<>();
//            for (int index = 0; index < N; index++) {
//                if (isSelected[index]) burger.add(index);
//            }
//
//            boolean flag = true;
//            for (int index = 0; index < M; index++) {
//                int a = pairs[index][0];
//                int b = pairs[index][1];
//
//                if ((burger.contains(a) && burger.contains(b))) {
//                    flag = false;
//                }

            // isSelected 대신에 비트 마스크를?
            for (int index = 0; index < M; index++) {
                int a = pairs[index][0];
                int b = pairs[index][1];

                // a, b 둘 다 있으면 탈락!
                // a가 있는지 확인하는 방법
                // bitmask & (1 << a)
                if ((bitmask & (1 << a)) != 0 && (bitmask & (1 << b)) != 0) {
                    return;
                }
            }
            burgerCount++;
            return;
        }

        powerset(cnt + 1, bitmask | (1 << cnt));
        powerset(cnt + 1, bitmask);
    }
}
