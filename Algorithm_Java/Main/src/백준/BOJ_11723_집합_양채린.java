package 백준;

import java.util.Objects;
import java.util.Scanner;

/*
    집합 (비트연산 연습)

    비어있는 공집합 S가 주어졌을 때,
    아래의 연산을 수행하는 프로그램을 작성해라.

    add x
    remove x
    check x
    toggle x
    all  // {1, 2, ..., 20} 으로 바꾸기
    empty // 공집합으로 바꾸기

    [Input]
    수행해야 하는 연산의 수
    수행해야 하는 연산
    ...

    [Output]
    check 연산이 주어질 때마다 결과 출력

    [Solution]
    비트 연산을 이용하여 ...

    add x => x번째를 1로 바꿔주면 되니까 x번째가 1인 비트배열을 곱 & 한다.
    remove x => x번째를 0으로 바꿔주면 되니까 x번째가 0이고 나머지가 다 1인 비트배열을 곱 & 한다. 그러면 x의 이외는 인덱스 번째는 1인거는 1로 나오고 0인거는 0으로 나온다. x번째 인덱스 번째는 1이어도 0이어도 0으로 나온다.
    check x => x번째만 1인 비트배열과 곱 & 했는데 0보다 크면 있는거다
    toggle x => check 연산을 통해 있는지 없는지 확인하고, 있으면 add, 없으면 remove 한다.
    all  => 11111111~111인 비트 배열과 or 연산을 한다. 111111~1111인 배열을 만든는 방법은 10000~000인 배열에서 -1을 하면 된다.
    empty => 0으로 바꾼다.
 */

public class BOJ_11723_집합_양채린 {
    public static void main(String[] args) {
        // Input을 입력받고 초기화를 한다
        Scanner scanner = new Scanner(System.in);

        int M  = Integer.parseInt(scanner.nextLine());
        StringBuilder stringBuilder = new StringBuilder();

        int N = 0;
        for (int index = 0; index < M; index++) {
            String command = scanner.nextLine();
            String operator = command.split(" ")[0];
            int x = -1;

            if (! (Objects.equals(operator, "all") || operator.equals("empty"))) {
                x = Integer.parseInt(command.split(" ")[1]);
            }

            switch (operator) {
                case "add":
                    N = N | (1 << x);
                    break;
                case "remove":
                    N = N & ~(1 << x);
                    break;
                case "check":
                    if (0 < (1 << x & N)) {
                        stringBuilder.append(1).append("\n");
                    } else {
                        stringBuilder.append(0).append("\n");
                    }
                    break;
                case "toggle":
                    if ((1 << x & N) > 0) {
                        N = N & ~(1 << x);
                    } else {
                        N = N | (1 << x);
                    }
                    break;
                case "all":
                    N = N | ((1 << 21) - 1);
                    break;
                case "empty":
                    N = 0;
                    break;
            }
        }

        System.out.println(stringBuilder);
    }
}
