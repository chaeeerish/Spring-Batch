package 강의.DFS_BFS;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2589 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[] dy = {0, 0, -1, 1};
    public static int[] dx = {-1, 1, 0, 0};
    public static int H;
    public static int W;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < W; j++) {
                map[i][j] = s.charAt(j);
            }
        }

//        for (int i = 0; i < H; i++) {
//            for (int j = 0; j < W; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        int answer = 0;
        int answerY = 0;
        int answerX = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'L') {
                    boolean[][] isVisited = new boolean[H][W];
                    int[][] dist = new int[H][W];

                    Queue<Node> queue = new LinkedList<>();
                    isVisited[i][j] = true;
                    queue.add(new Node(i, j));

                    while (!queue.isEmpty()) {
                        Node now = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int newY = now.y + dy[k];
                            int newX = now.x + dx[k];
                            if (newY >= 0 && newY < H && newX >=0 && newX < W && map[newY][newX] == 'L' && !isVisited[newY][newX]) {
                                isVisited[newY][newX] = true;
                                dist[newY][newX] = dist[now.y][now.x] + 1;
                                queue.add(new Node(newY, newX));
                            }
                        }
                    }

                    answer = Math.max(answer, getMaxDist(dist));
//                    System.out.println(i + ", " + j);
//                    printDist(dist);
                }
            }
        }

        System.out.println(answer);
    }

    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static int getMaxDist(int[][] dist) {
        int max = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                max = Math.max(max, dist[i][j]);
            }
        }
        return max;
    }

    public static void printDist(int[][] dist) {
        System.out.println("PrintDist");
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
