package 강의.LIS_LCS;

import java.io.*;
import java.util.StringTokenizer;

public class B9251 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String s2 = st.nextToken();

        int[][] array = new int[s1.length()][s2.length()];
        if (s1.charAt(0) == s2.charAt(0)) {
            array[0][0] = 1;
        } else {
            array[0][0] = 0;
        }

        for (int i = 1; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                array[i][0] = 1;
            } else {
                array[i][0] = array[i - 1][0];
            }
        }

        for (int j = 1; j < s2.length(); j++) {
            if (s2.charAt(j) == s1.charAt(0)) {
                array[0][j] = 1;
            } else {
                array[0][j] = array[0][j - 1];
            }
        }

//        for (int i = 0; i < s1.length(); i++) {
//            for (int j = 0; j < s2.length(); j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    array[i][j] = array[i - 1][j - 1] + 1;
                } else {
                    array[i][j] = Math.max(array[i - 1][j], array[i][j - 1]);
                }
            }
        }

        System.out.println(array[s1.length() - 1][s2.length() - 1]);
    }
}
