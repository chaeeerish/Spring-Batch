package SWExpertAcademy;

import java.sql.Array;
import java.util.*;

/*
    루트가 있는 트리에서 BFS 탐색을 하ㅕ려고 한다.
    1부터 N까지 번호가 붙은 노드들이 있다.
    1이 루트이자, 탐색 시작점이다.

    큐를 이용해서,
    노드의 자식들을 작은 번호부터 순서대로 뒤에 넣는다.

    [Input]
    테스트케이스
    N
    각 노드의 부모정점을 의미하는 N - 1개의 자연수가 공백으로 주어진다.

    [Output]
    몇 개의 간선을 지나야 탐색을 끝낼 수 있는가?

    [Solution]
    부모정보리스트 = [-1, 1, 부모, 부모, 부모 ...]
    자식정보리스트 = [-1, [자식1, 자식2 ... ], [자식1, 자식2 ... ] ... ]
 */

public class SWEA_1855_영준이의진짜BFS {
    public static int N;
    public static ArrayList<Integer>[] childList;
    public static int[][] parentInfo;

    static class Node {
        int index;
        int depth;

        public Node(int index, int depth) {
            this.index = index;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCase = scanner.nextInt();
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            N  = scanner.nextInt();

            childList = new ArrayList[N + 1];
            for (int v = 0; v < N + 1; v++) {
                childList[v] = new ArrayList<>();
            }

            parentInfo = new int[N + 1][2]; // 부모노드, depth
            for (int v = 2; v < N + 1; v++) {
                int parent = scanner.nextInt();
                parentInfo[v][0] = parent;
                childList[parent].add(v);
            }

            // BFS
            int distance = 0;

            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(1, 0));

            Node previousNode = null;
            while (! queue.isEmpty()) {
                Node nowNode = queue.poll();
                parentInfo[nowNode.index][1] = nowNode.depth;

                if (nowNode.index != 1) {
                    distance += getDistance(previousNode, nowNode);
                }

                for (Integer child : childList[nowNode.index]) {
                    queue.add(new Node(child, nowNode.depth + 1));
                }

                previousNode = nowNode;
            }

            answer.append("#").append((tc + 1)).append(" ").append(distance).append("\n");
        }

        System.out.println(answer);
    }

    public static int getDistance(Node start, Node end) {
        // start의 depth가 end depth보다 항상 작거나 같다.
        // start와 end의 공통 조상 찾기

        // end depth를 start와 맞추기
        int startParent = start.index;
        int endParent = end.index;

        while (true) {
            if (parentInfo[startParent][1] == parentInfo[endParent][1]) {
                break;
            }

            endParent = parentInfo[endParent][0];
        }

        while (true) {
            if (startParent == endParent) {
                break;
            }

            // start 먼저 움직이기
            startParent = parentInfo[startParent][0];

            // end 먼저 움직이기
            endParent = parentInfo[endParent][0];
        }

        return start.depth - parentInfo[startParent][1] + end.depth - parentInfo[endParent][1];
    }
}
