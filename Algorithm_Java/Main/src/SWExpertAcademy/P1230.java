package SWExpertAcademy;

/*
    암호문은 특수 제작된 처리기로만 수정이 가능하다.
    삽입 처리기 x y s : 앞에서부터 x번째 암호문 뒤에 y개의 암호문을 삽입한다. s는 덧붙일 암호문이다.
    삭제 처리기 x y: 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제한다.
    추가 처리기 y, s: 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙인다. s는 덧붙일 암호문이다.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class P1230 {
    public static void main(String[] args) throws FileNotFoundException {
        int testCase = 10;

        System.setIn(new FileInputStream("/Users/chaeeerish/Documents/GitHub/Algorithm_Java/Main/src/SWExpertAcademy/P1230_input.txt"));
        Scanner sc = new Scanner(System.in);

        StringBuilder answer = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            int cryptogramCount = Integer.valueOf(sc.nextLine());

            String cryptogramEntire = sc.nextLine();
            LinkedList<String> cryptogramList = new LinkedList<>();

            for (String cryptogram : cryptogramEntire.split(" ")) {
                cryptogramList.add(cryptogram);
            }

            int commandCount = Integer.parseInt(sc.nextLine());
            String commandEntire = sc.nextLine();

            String[] commandList = commandEntire.split(" ");

            int commandIndex = 0;
            int ccount = 0;
            while (true) {
                String command = commandList[commandIndex++];
                int x, y;

                if (command.equals("I")) {
                    x = Integer.parseInt(commandList[commandIndex++]);
                    y = Integer.parseInt(commandList[commandIndex++]);

                    for (int j = x; j < x + y; j++) {
                        System.out.println(commandList[commandIndex]);
                        cryptogramList.add(j, commandList[commandIndex++]);
                    }
                } else if (command.equals("D")) {
                    x = Integer.parseInt(commandList[commandIndex++]);
                    y = Integer.parseInt(commandList[commandIndex++]);

                    for (int j = x; j < x + y; j++) {
                        cryptogramList.remove(x);
                    }
                } else if (command.equals("A")) {
                    y = Integer.parseInt(commandList[commandIndex++]);

                    for (int j = 0; j < y; j++) {
                        cryptogramList.add(commandList[commandIndex++]);
                    }
                }

                if (commandList.length <= commandIndex) {
                    break;
                }
            }

            answer.append("#").append((tc + 1)).append(" ");
            for(int j=0; j<10; j++){
                answer.append(cryptogramList.get(j)).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}
