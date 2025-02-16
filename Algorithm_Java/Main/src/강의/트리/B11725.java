package 강의.트리;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B11725 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static ArrayList<Integer>[] tree;
    public static int[] parent;
    public static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        parent = new int[N + 1];
        isVisited = new boolean[N + 1];

//        for (int i = 0; i < tree.length; i++) {
//            System.out.println(i + "의 자식은");
//            for (int j = 0; j < tree[i].size(); j++) {
//                System.out.print(tree[i].get(j) + " ");
//            }
//            System.out.println();
//        }

        recursion(1, 0);

        for (int i = 2; i < N + 1; i++) {
            System.out.print(parent[i] + " ");
        }
    }

    public static void recursion(int now, int prev) {
        parent[now] = prev;
        isVisited[now] = true;

        for (int i = 0; i < tree[now].size(); i++) {
            if (isVisited[tree[now].get(i)] == false) {
                recursion(tree[now].get(i), now);
            }
        }
    }
}
