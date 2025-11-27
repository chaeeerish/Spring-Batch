package SWExpertAcademy;

/*
    이진 트리에서 두 정점의 가장 가까운 조상을 찾고,,
    그 정점을 루트로 하는 서브 트리의 크기를 알아내라.

    정점번호는 1부터 V까지
    루트 정점은 항상 1번이다.

    [Input]
    테스트케이스개수
    정점의개수 간선의개수 정점번호1 정점번호2
    E개의 간선
    부모 자식 부모 자식 ...

    [Output]
    가장가까운공통조상번호 서브트리의크기

    [Solution]
    먼저, 트리를 그리고 Class Node 이용해서!
    아니다 배열로 넣어야겠다.
    [[-1, -1], [자식1, 자식2], [자식1, 자식2], [], []]

    아닌데,, 부모를 찾아가야하니까
    class Node
    Node parent
    Node child1
    Node child2

    부모로 거슬러 올라가면서 자기 루트 노드 다 Set에 저장

    두번째 정점번호도 마찬가지

    아 아니다.

    정점번호1에 대해서 루트노드가 나올때마다
    내려가서 정점번호2 있는지 찾기
 */

import java.lang.reflect.Array;
import java.util.*;

public class SWEA_1248_공통조상 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            int[] parentsInfo = new int[V + 1];
            int[][] childsInfo = new int[V + 1][2];

            for (int v = 0; v < V + 1; v++) {
                parentsInfo[v] = -1;
                childsInfo[v][0] = -1;
                childsInfo[v][1] = -1;
            }

            int p, c;
            for (int e = 0; e < E; e++) {
                p = sc.nextInt();
                c = sc.nextInt();

                parentsInfo[c] = p;

                if (childsInfo[p][0] == -1) {
                    childsInfo[p][0] = c;
                } else {
                    childsInfo[p][1] = c;
                }
            }

            // v1에 대해서 루트노드를 탐색하기
            int parentOfV1 = parentsInfo[v1];
//            ArrayList<Integer> parentsOfV1 = new ArrayList<>();
            while (true) {
                if (parentOfV1 == -1) break;

                // v1의 루트노드들의 자식에 v2가 있는지
                Queue<Integer> queue = new LinkedList<>();
                queue.add(parentOfV1);

                boolean flag = false;
                int now = -1;
                while (! queue.isEmpty()) {
                    now = queue.poll();

                    if (now == v2) {
                        flag = true;
                        break;
                    }

                    if (childsInfo[now][0] != -1) {
                        queue.add(childsInfo[now][0]);
                    }
                    if (childsInfo[now][1] != -1) {
                        queue.add(childsInfo[now][1]);
                    }
                }

                int subTreeSize = 0;
                if (flag) {
                    // parentOfV1에 대해서 subTreeSize 구하기
                    int size = 0;

                    queue = new LinkedList<>();
                    queue.add(parentOfV1);

                    while (! queue.isEmpty()) {
                        now = queue.poll();
                        size += 1;

                        if (childsInfo[now][0] != -1) {
                            queue.add(childsInfo[now][0]);
                        }
                        if (childsInfo[now][1] != -1) {
                            queue.add(childsInfo[now][1]);
                        }
                    }

                    answer.append("#").append(tc + 1).append(" ").append(parentOfV1).append(" ").append(size).append("\n");
                    break;
                }

                parentOfV1 = parentsInfo[parentOfV1];
            }
        }

        System.out.println(answer);
    }
}
