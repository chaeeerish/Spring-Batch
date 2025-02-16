package SWExpertAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    주어진 트리를 in-order 방식으로 순회해 각 노드를 읽으면 나오는 특정 단어를 출력해라.

    [Input]
    정점번호    정점문자    왼쪽자식    오른쪽자식
    1           W           2           3
    2           F           4           5
    3           R           6           7
 */

public class P1231 {
    public static class Node {
        char data;
        int leftNumber = -1;
        int rightNumber = -1;

        public Node(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/chaeeerish/Documents/GitHub/Algorithm_Java/Main/src/SWExpertAcademy/P1231_input.txt"));
        Scanner sc = new Scanner(System.in);

        StringBuilder answer = new StringBuilder();

        int testCase = 10;
        for (int tc = 0; tc < testCase; tc++) {
            int N = Integer.parseInt(sc.nextLine());

            Node[] nodeList = new Node[N + 1];

            // 트리 List 초기화하기
            for (int index = 1; index < N + 1; index++) {
                String temp = sc.nextLine();
                String[] split = temp.split(" ");
                int nodeNumber = Integer.parseInt(split[0]);
                char nodeData = split[1].toCharArray()[0];

                nodeList[nodeNumber] = new Node(nodeData);

                if (split.length == 4) {
                    nodeList[nodeNumber].leftNumber = Integer.parseInt(split[2]);
                    nodeList[nodeNumber].rightNumber = Integer.parseInt(split[3]);

                } else if (split.length == 3) {
                    nodeList[nodeNumber].leftNumber = Integer.parseInt(split[2]);
                }
            }

//            for (int k = 1; k < N; k++) {
//                Node node = nodeList[k];
//                System.out.println(node.data + " " + node.leftNumber + " " + node.rightNumber);
//            }

            // 중위 순회 하기
            // 루트 노드 1부터 시작
            StringBuilder word = new StringBuilder();
            inOrder(nodeList, word, 1);

            // 정답에 저장
            answer.append("#").append((tc + 1) + " ").append(word).append("\n");
        }

        System.out.println(answer);
    }

    public static void inOrder(Node[] nodeList, StringBuilder sb, int curIndex) {
        // 왼쪽 먼저
        if (nodeList[curIndex].leftNumber != -1) {
            inOrder(nodeList, sb, nodeList[curIndex].leftNumber);
        }

        // 그리고 나
        sb.append(nodeList[curIndex].data);
//        System.out.println("curIndex " + curIndex);

        // 그리고 오른쪽
        if (nodeList[curIndex].rightNumber != -1) {
            inOrder(nodeList, sb, nodeList[curIndex].rightNumber);
        }
    }
}
