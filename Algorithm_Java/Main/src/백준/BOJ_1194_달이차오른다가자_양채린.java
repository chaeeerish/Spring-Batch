package 백준;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
    달이차오른다 가자

    직사각형 미로, 탈출
    - 빈칸 .: 이동가능
    - 벽 #: 이동불가능
    - 열쇠 abcdef: 이동가능 + 열쇠획득
    - 문 ABCDEF: 대응하는 열쇠 있으면 이동 가능
    - D: 민식이 현재 위치
    - 1 : 출구, 도착지

    이동은 상하좌우 가능~!

    [Input]
    세로크기N 가로크기M
    N X M 미로 정보

    [Output]
    민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값!
    만약 민식이가 미로를 탈출할 수 없으면, -1

    [Solution]
    각 칸에 키 리스트를 보관해야 되는데... StringBuilder?

    1. 입력받는다.
    2. 시작 위치에서 상하좌우 이동한다.
    3.
 */

public class BOJ_1194_달이차오른다가자_양채린 {
    static class Node {
        int row;
        int col;
        int distance;
        int keys;

        public Node(int row, int col, int distance, int keys) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.keys = keys;
        }
    }

    static Scanner scanner;

    static int N;
    static int M;
    static char[][] map;
    static boolean[][][] isVisited;

    static int startRow;
    static int startCol;

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, -1, 0, 1};

    static void input() {
        String[] strings = scanner.nextLine().split(" ");
        N = Integer.parseInt(strings[0]);
        M = Integer.parseInt(strings[1]);

        map = new char[N][M];
        for (int row = 0; row < N; row++) {
            map[row] = scanner.nextLine().toCharArray();

            for (int col = 0; col < M; col++) {
                if (map[row][col] == '0') {
                    map[row][col] = '.';

                    startRow = row;
                    startCol = col;
                }
            }
        }

        isVisited = new boolean[N][M][64];
    }

    static void print() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        input();

//        System.out.println("startRow = " + startRow);
//        System.out.println("startCol = " + startCol);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startRow, startCol, 0, 0));

        boolean flag = false;
        while (! queue.isEmpty()) {
            Node current = queue.poll();

            if (map[current.row][current.col] == '1') {
                System.out.println(current.distance);
                return;
            }

//            System.out.println("row = " + current.row);
//            System.out.println("col = " + current.col);
//            System.out.println("current.distance = " + current.distance);
//            System.out.println("keys = " + current.keys);
//            System.out.println("queue.size() = " + queue.size());
//            System.out.println();

            for (int d = 0; d < 4; d++) {
                int newRow = current.row + dRow[d];
                int newCol = current.col + dCol[d];
                int keys = current.keys;

                if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= M) continue;
                if (map[newRow][newCol] == '#') continue;

                if (map[newRow][newCol] >= 'a' && map[newRow][newCol] <= 'f') {
                    keys = keys | (1 << (map[newRow][newCol] - 'a'));
                }

                if (map[newRow][newCol] >= 'A' && map[newRow][newCol] <= 'F') {
                    if ((keys & (1 << (map[newRow][newCol] - 'A'))) == 0) {
                        continue;
                    }
                }

                if (isVisited[newRow][newCol][keys] == false) {
                    isVisited[newRow][newCol][keys] = true;
                    queue.add(new Node(newRow, newCol, current.distance + 1, keys));
                }
            }
        }

        System.out.println(-1);
    }
}
