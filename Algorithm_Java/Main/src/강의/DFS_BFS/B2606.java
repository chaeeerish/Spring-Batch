package 강의.DFS_BFS;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B2606 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        recursion(1);

//        for (int i = 0; i < N + 1; i++) {
//            System.out.print(visited[i] + " ");
//        }
        int answer = 0;
        for (int i = 0; i < N + 1; i++) {
            if (visited[i]) answer++;
        }

        System.out.println(answer - 1);
    }

    public static void recursion(int a) {
        visited[a] = true;

        for (Integer b : graph[a]) {
            if (visited[b] == false) {
                recursion(b);
            }
        }
    }
}
