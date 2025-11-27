package SWExpertAcademy;

import java.util.*;

/*
    경근이가 한 개의 자연수를 공책에 적는다.
    N번에 걸쳐서 자연수 2개씩을 적는다.

    이때, 홍준이는 경근이가 2개씩 추가로 적을 때마다,
    지금까지 적은 수 중엣 크기가 중간인 수를 알려준다.

    5
    1
    1 3 5
    1 2 3 5 6
    1 2 3 5 6 8 9

    N개의 중간값들을 매번 출력하면 출력 양이 너무 많기 때문에,
    그 수들의 합을 20171109로 나눈 나머지를 출력하는 프로그램

    [Input]
    테스트케이스개수 T
    N 경근이가처음에공책에쓴자연수A
    N개의 줄에 결처셔,,,
    X Y

    [Output]
    N개의 중간값들을 모두 더한

    [Solution]
    우선순위큐!?
 */

public class SWEA_3000_중간값구하기 {
    public static int N;
    public static int A;
    public static PriorityQueue<Integer> descHeap;
    public static PriorityQueue<Integer> ascHeap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = scanner.nextInt();
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            N = scanner.nextInt();
            A = scanner.nextInt();

            descHeap = new PriorityQueue<>(Collections.reverseOrder());
            ascHeap = new PriorityQueue<>();

            descHeap.add(A);

            int middleSum = 0;
            for (int index = 0; index < N; index++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                if (descHeap.peek() >= a) descHeap.add(a);
                else ascHeap.add(a);

                if (descHeap.peek() >= b) descHeap.add(b);
                else ascHeap.add(b);

                // descHeap이 ascHeap보다 하나 더 많게 만드는 작업!
                while (descHeap.size() != ascHeap.size() + 1) {
                    if (descHeap.size() == ascHeap.size() + 1) {
                        int descHeapPeek = descHeap.isEmpty() ? 0 : descHeap.peek();
                        int ascHeapPeek = ascHeap.isEmpty() ? Integer.MAX_VALUE : ascHeap.peek();

                        if (descHeapPeek <= ascHeapPeek) break;
                    }

                    if (descHeap.size() < ascHeap.size()) {
                        // ascHeap에서 descHeap으로 옮겨야 한다.
                        int swap = ascHeap.poll();
                        descHeap.add(swap);
                    } else { // descHeap.size() >= ascHeap.size()
                        // descHeap에서 ascHeap으로 옮겨야 한다.
                        int swap = descHeap.poll();
                        ascHeap.add(swap);
                    }
                }

                middleSum = (middleSum + descHeap.peek()) % 20171109;

//                System.out.println("descHeap");
//                System.out.println(Arrays.toString(descHeap.toArray()));
//                System.out.println("ascHeap");
//                System.out.println(Arrays.toString(ascHeap.toArray()));
//                System.out.println();
            }

            answer.append("#").append((tc + 1)).append(" ").append(middleSum).append("\n");
        }
        System.out.println(answer);
    }
}
