package 백준;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 컴퓨터와 컴퓨터를 모두 연결하고 싶다.
 * 컴퓨터를 연결하는 비용을 최소로 사용하고 싶다.
 *
 * [Input]
 * 컴퓨터수 N
 * 선의수 M
 *
 * [Output]
 * M개의 줄에 컴퓨터를 연결하는데 드는 비용이 주어진다.
 * a b c
 *
 * [Solution]
 * 프림 알고리즘
 *
 * 1. 간선의 정보를 입력받는다.
 * 2. 최소신장트리에 0을 넣는다.
 * 3. 이후, 0을 꺼내어 최소신장트리로 만든다.
 * 4. 0과 연결된 노드들과 연결되 노드들 중 최소 가중치를 가지는 노드를 포함한다.
 * 5. 해당 노드를 기점으로 새롭게 minDistance를 업데이트 한다.
 * 6. 4-5를 반복한다.
 */

public class BOJ_1922_네트워크연결_프림_양채린 {
    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        long weight;

        public Edge(int a, int b, long weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    static int V;
    static int E;
    static ArrayList<Edge>[] lists;
    static boolean[] visited;

    public static void main(String[] args) {
        // 1. 간선의 정보를 입력받는다.
        Scanner scanner = new Scanner(System.in);

        V = scanner.nextInt();
        E = scanner.nextInt();

        lists = new ArrayList[V];
        visited = new boolean[V];

        for (int index = 0; index < V; index++) {
            lists[index] = new ArrayList<>();
        }

        for (int index = 0; index < E; index++) {
            int a = scanner.nextInt() - 1;
            int b = scanner.nextInt() - 1;
            long weight = scanner.nextLong();

            lists[a].add(new Edge(a, b, weight));
            lists[b].add(new Edge(b, a, weight));
        }

        // 2. 최소신장트리에 0을 넣는다.
        // * 3. 이후, 0을 꺼내어 최소신장트리로 만든다.
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(0, 0, 0));

        long result = 0L;

        // 6. 4-5를 반복한다.
        while (! priorityQueue.isEmpty()) {
            // * 4. 0과 연결된 노드들과 연결되 노드들 중 최소 가중치를 가지는 노드를 포함한다.
            Edge edge = priorityQueue.poll();
            if (visited[edge.b]) {
                continue;
            }

            visited[edge.b] = true;
            result += edge.weight;

            // * 5. 해당 노드를 기점으로 새롭게 minDistance를 업데이트 한다.
            for (Edge connectEdge : lists[edge.b]) {
                if (! visited[connectEdge.b]) {
                    priorityQueue.add(connectEdge);
                }
            }
        }

        System.out.println(result);
    }
}
