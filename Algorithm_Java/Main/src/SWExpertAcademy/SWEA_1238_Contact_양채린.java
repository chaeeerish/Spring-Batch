package SWExpertAcademy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1238_Contact_양채린 {
    static int L;
    static int start;
    static int SIZE = 100 + 1;
    static int[] visited;
    static int[][] graph;
    static Queue<Integer> queue;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            L = scanner.nextInt();
            start = scanner.nextInt();

            graph = new int[SIZE][SIZE];
            queue = new LinkedList<>();
            visited = new int[SIZE];

            for (int i = 0; i < L / 2; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();

                graph[from][to] = 1;
            }

            System.out.print("#" + tc + " ");
            bfs(start);
        }
    }

    // BFS를 통한 연락망 탐색
    public static void bfs(int startNode) {
        queue.offer(startNode); // 시작점 큐에 삽입
        visited[startNode] = 1; // 방문처리

        int max = 0; // 가장 높은 번호를 가진 사람
        ArrayList<Integer> maxList = new ArrayList<>(); // 각 레벨에서의 최대값 저장

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            max = 0;

            // 같은 레벨에서 반복하여 최댓값을 구함
            for (int t = 0; t < queueSize; t++) {
                int currentNode = queue.poll();
                for (int i = 1; i < SIZE; i++) {
                    // 연락 가능하다면 큐에 삽입하고 방문처리
                    if (graph[currentNode][i] == 1 && visited[i] == 0) {
                        queue.offer(i);
                        visited[i] = 1;
                        max = Math.max(max, i); // 최대값 업데이트
                    }
                }
            }

            // 해당 레벨에서 가장 큰 값을 리스트에 추가
            maxList.add(max);
        }

        // 마지막에서 두 번째 레벨의 최댓값을 출력 (최대 레벨의 최대값)
        System.out.println(maxList.get(maxList.size() - 2));
    }
}
