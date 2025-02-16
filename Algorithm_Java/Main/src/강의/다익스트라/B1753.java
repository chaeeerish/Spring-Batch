package 강의.다익스트라;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

public class B1753 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 시작 정점

        List<Node>[] list = new List[V + 1];
        boolean[] visitied = new boolean[V + 1];
        int[] result = new int[V + 1];

        for (int i = 0; i < V + 1; i++) {
            list[i] = new ArrayList<>();
            result[i] = INF;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u =  Integer.parseInt(st.nextToken());
            int v =  Integer.parseInt(st.nextToken());
            int w =  Integer.parseInt(st.nextToken());

            list[u].add(new Node(v, w));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        result[K] = 0;
        queue.add(new Node(K, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (!visitied[now.end]) {
                visitied[now.end] = true;
            } else {
                continue;
            }

            for (int i = 0; i < list[now.end].size(); i++) {
                Node next = list[now.end].get(i);
                if (visitied[next.end] == false && now.weight + next.weight < result[next.end]) {
                    result[next.end] = now.weight + next.weight;
                    queue.add(new Node(next.end, result[next.end]));
                }
            }
        }

        for (int i = 1; i < V + 1; i++) {
            if (result[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(result[i]);
            }
        }
    }
}
