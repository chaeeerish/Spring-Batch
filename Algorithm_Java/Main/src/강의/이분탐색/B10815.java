package 강의.이분탐색;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10815 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] cardList = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cardList[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sanggeun = new int[M];
        for (int i = 0; i < M; i++) {
            sanggeun[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cardList);
//        for (int i = 0; i < N; i++) {
//            System.out.print(cardList[i] + " ");
//        }
//        System.out.println();

        int[] answer = new int[M];
        int start, end, mid;
        for (int i = 0; i < M; i++) {
            start = 0;
            end = N - 1;

            while (start <= end) {
                mid = (start + end) / 2;

                if (sanggeun[i] == cardList[mid]) {
                    answer[i] = 1;
                    break;
                } else if (sanggeun[i] < cardList[mid]) {
                    end = mid - 1;
                } else if (sanggeun[i] > cardList[mid]) {
                    start = mid + 1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
