package SWExpertAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
    N개 이하의 자연수로 이루어진 수열
    M번의 편집을 거쳐서 완성할 것이다.

    ouput => 완성된 수열에서 인덱스 L의 데이터를 출력해라.

    I 2 7 // 2번 인덱스 앞에 7을 추가하고, 한 칸 씩 뒤로 이동한다.
    D 4 // 4번 인덱스 자리를 지우고, 한 칸 씩 아프로 이동한다.
    C 3 8 // 3번 인덱스 자리를 8로 바꾼다.
 */

public class P5122 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/chaeeerish/Documents/GitHub/Algorithm_Java/Main/src/SWExpertAcademy/P5122_input.txt"));
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());
        for (int tc = 0; tc < testCase; tc++) {
            String[] stringSplit = sc.nextLine().split(" ");
            int N = Integer.parseInt(stringSplit[0]);
            int M = Integer.parseInt(stringSplit[1]);
            int L = Integer.parseInt(stringSplit[2]);

            LinkedList<Integer> array = new LinkedList<>();
            stringSplit = sc.nextLine().split(" ");
            for (String element : stringSplit) {
                System.out.println(element);
                array.add(Integer.valueOf(element));
            }

            for (int index = 0; index < M; index++) {
                stringSplit = sc.nextLine().split(" ");

                if (stringSplit[0].equals("I")) {
                    array.add(Integer.parseInt(stringSplit[1]), Integer.valueOf(stringSplit[2]));
                } else if (stringSplit[0].equals("D")) {
                    array.remove(Integer.parseInt(stringSplit[1]));
                } else if (stringSplit[0].equals("C")) {
                    array.remove(Integer.parseInt(stringSplit[1]));
                    array.add(Integer.parseInt(stringSplit[1]), Integer.valueOf(stringSplit[2]));
                }
            }

            sb.append("#" + (tc + 1) + " ");
            if (array.size() <= L) {
                sb.append(-1).append("\n");
            } else {
                sb.append(array.get(L)).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void printArray(List<Integer> array) {
        for (Integer element : array) {
            System.out.print(element.intValue() + " ");
        }
        System.out.println("**********");
    }
}
