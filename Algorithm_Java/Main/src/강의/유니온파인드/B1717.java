package 강의.유니온파인드;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B1717 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[] parent;
    public static int[] rank;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int calculation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (calculation == 0) { // 합집합
                union(a, b);
            } else { // a가 b에 포함되어 있는가
                if (find(a) == find(b)) {
                    answer.add("YES");
                } else {
                    answer.add("NO");
                }
            }
        }

//        for (int i = 0; i < N + 1; i++) {
//            System.out.print(parent[i] + " ");
//        }

        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }

    public static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if (A == B) {
            return;
        }

        if (rank[A] < rank[B]) {
            parent[A] = B;
            rank[A] += 1;
        } else if (rank[A] > rank[B]) {
            parent[B] = A;
            rank[B] += 1;
        } else {
            parent[A] = B;
            rank[B] += 1;
        }
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return parent[a];
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }
}
