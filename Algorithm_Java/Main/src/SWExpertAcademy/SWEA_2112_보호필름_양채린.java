package SWExpertAcademy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 얇은 투명한 막을 D장 쌓아서 제작한다.
 * 그 막은 동일한 크기의 셀들이 가로 방향으로 W개 붙어서 만들어진다.
 * => 이렇ㄱㅔ 제작된 필름은 두께 D, 가로 W의 보호필름이라 한다.
 *
 * 각 셀들은 A 또는 B 특성을 가지고 있다.
 * 보호 필름의 성능은 셀들의 특성이 어떻게 배치됨에 따라 결정된다.
 *
 * 합격기준 K를 넘으면, 보호필름 성능이 향상된다.
 * 충격은 세로 방향으로 가해지므로, 세로 방향 셀 특성이 중요하다.
 *
 * 단면의 모든 세로 방향에 대해서 동일 특성 셀이 K개 연속 있어야 통과이다.
 *
 * --
 *
 * 성능 검사 통과하기 위해서 약품을 사용할 수 있다.
 * 막 별로 투입할 수 있다.
 * >> 아아 막은 가로였음
 * A를 투입하면, 모두 A로 // B를 투입하면, 모두 B로 변경된다.
 *
 * 다만. 투입 횟수의 최소값이 있다.
 *
 * [Input]
 * 테스트케이스 개수
 * 보호필름의두께 가로크기 합격기준
 * 보호 필름 단면의 정보 * D줄
 *
 * [Output]
 * 약품 투입 횟수를 최소로 하여 성능검사를 통과할 수 있는 방법을 찾아라.
 *
 * [Solution]
 * 최소 .. 투입 횟수니까
 * 0번부터 D번까지 모두 투입해보아야 한다.
 *
 * 그런 의미에서 부분집합문제다!!
 */

public class SWEA_2112_보호필름_양채린 {
    public static int[][] film;
    public static boolean[] isSelected;
    public static int D;
    public static int W;
    public static int K;

    public static int[][] newFilm;
    public static int minInsert;

    public static int[] all1;
    public static int[] all0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = Integer.parseInt(scanner.nextLine());
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            // 0. 초기화 하기
            String[] temp = scanner.nextLine().split(" ");
            D = Integer.parseInt(temp[0]); // 보호필름 두께
            W = Integer.parseInt(temp[1]); // 보호필름 가로
            K = Integer.parseInt(temp[2]); // 합격 기준

            film = new int[D][W];
            for (int mak = 0; mak < D; mak++) {
                temp = scanner.nextLine().split(" ");
                for (int cell = 0; cell < W; cell++) {
                    film[mak][cell] = Integer.parseInt(temp[cell]);
                }
            }

            all0 = new int[W];
            all1 = new int[W];
            for (int cell = 0; cell < W; cell++) {
                all0[cell] = 0;
                all1[cell] = 1;
            }

            // 1. 부분집합 구하기
            isSelected = new boolean[D];
            minInsert = 14;
            insert(0, 0);

            // 2. 정답을 기록하기
            answer.append("#").append(tc + 1).append(" ").append(minInsert).append("\n");
        }
        System.out.println(answer);
    }

    public static void powerSet(int elementIndex) {
        if (elementIndex == D) {
//			System.out.println(Arrays.toString(isSelected));
            return;
        }

        isSelected[elementIndex] = false;
        powerSet(elementIndex + 1);

        isSelected[elementIndex] = true;
        powerSet(elementIndex + 1);
    }

    public static void insert(int elementIndex, int selectCount) {
        if (minInsert <= selectCount) return;

        if (elementIndex == D) {
            if (validateK()) {
//                System.out.println("validate하다. ");
//                System.out.println(Arrays.toString(isSelected));
//                System.out.println("getInsertCount() = " + getInsertCount());
//                printFilm();
                minInsert = Math.min(minInsert, selectCount);
            }
            return;
        }

        int[] swap = film[elementIndex];
        isSelected[elementIndex] = false;
        insert(elementIndex + 1, selectCount);

        film[elementIndex] = all0;
        isSelected[elementIndex] = true;
        insert(elementIndex + 1, selectCount + 1);

        film[elementIndex] = all1;
        isSelected[elementIndex] = true;
        insert(elementIndex + 1, selectCount + 1);

        film[elementIndex] = swap;
        isSelected[elementIndex] = false;
    }

    // 0 0 1 1 1 0 0
    // 배열에서 같은게 K개 이상 있는지 점검해야 한다.
    public static boolean validateK() {
        int cellCount = 0;

        for (int cell = 0; cell < W; cell++) {
            int[] sequenceCount = new int[D];
            sequenceCount[0] = 1;

            for (int mak = 1; mak < D; mak++) {
                if (film[mak - 1][cell] == film[mak][cell]) {
                    sequenceCount[mak] = sequenceCount[mak - 1] + 1;
                } else {
                    sequenceCount[mak] = 1;
                }

                if (sequenceCount[mak] >= K) {
                    cellCount += 1;
                    break;
                }
            }
        }

        if (cellCount == W) return true;
        return false;
    }

    public static int getInsertCount() {
//        System.out.println("getInsertCount()");
//        System.out.println(Arrays.toString(isSelected));

        int count = 0;
        for (int index = 0; index < D; index++) {
            if (isSelected[index]) count += 1;
        }
        return count;
    }

    public static void printFilm() {
        for (int mak = 0; mak < D; mak++) {
            for (int cell = 0; cell < W; cell++) {
                System.out.print(film[mak][cell] + " ");
            }
            System.out.println();
        }
    }
}
