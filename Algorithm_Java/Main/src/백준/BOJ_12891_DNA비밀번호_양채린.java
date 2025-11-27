package 백준;

import java.util.*;

/*
* DNA 비밀번호
*
* DNA는 'A', 'C', 'G', 'T' 인 문자열이다.
* DNA 문자열의 부분 문자열을 비밀번호로 사용할거다.
* 부분문자열을 뽑았을 때, 등장하는 문자의 개수가 특정 개수 이상이어야 비밀번호로 사용할 수 있다.
* 예를 들어, ‘A’ 는 1개 이상, ‘C’는 1개 이상, ‘G’는 1개 이상, ‘T’는 0개 이상이 등장해야 비밀번호로 사용할 수 있다
*
* [Input]
* DNA 문자열의 길이
* 비밀번호로 사용할 부분문자열의 길이
* 임의로 만든 DNA 문자열
* 각 원소의 최소 개수가 공백으로 구분되어 제공
*
* [Output]
* 민호가 만들 수 있는 비밀번호의 종류의 수
*
* [Solution]
* 일단 dna의 index 0에서부터, 부분문자열의 길이만큼 잘라서 살펴본다.
* 각 부분문자열에서 A 개수, C 개수, G 개수, T개수를 계산한다.
* 가능하면 비밀번호 종류의수 ++!
*
* 근데,,
* for문 돌리면서
* 슬라이딩 윈도우처럼
* A면 A 개수 ++
* C면 C 개수 ++
* ..
*
* 이렇게 할 수 있지 않을까~?
*
* CCTGGATTG
* CCTG
*  CTGG
*   TGGA
*    GGAT
* ...
*
* */

public class BOJ_12891_DNA비밀번호_양채린 {
    public static void main(String[] args) {
        // Input을 입력받고 초기화를 한다
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        int dnaLength = Integer.parseInt(string.split(" ")[0]);
        int partLength = Integer.parseInt(string.split(" ")[1]);

        String dna = scanner.nextLine();

        string = scanner.nextLine();
        int limitA = Integer.parseInt(string.split(" ")[0]);
        int limitC = Integer.parseInt(string.split(" ")[1]);
        int limitG = Integer.parseInt(string.split(" ")[2]);
        int limitT = Integer.parseInt(string.split(" ")[3]);

        int passwordCount = 0;

        // index가 0일 때, 초기상태 세팅
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 0);
        map.put('G', 0);
        map.put('T', 0);

        for (int index = 0; index < partLength; index++) {
            map.put(dna.charAt(index), map.get(dna.charAt(index)) + 1);
        }

        if (map.get('A') >= limitA
                && map.get('C') >= limitC
                && map.get('G') >= limitG
                && map.get('T') >= limitT) {
            passwordCount++;
        }

//        printMap(map);

        for (int index = 1; index < dnaLength - partLength + 1; index++) {
            map.put(dna.charAt(index - 1), map.get(dna.charAt(index - 1)) - 1);
            map.put(dna.charAt(index + partLength - 1), map.get(dna.charAt(index + partLength - 1)) + 1);

            if (map.get('A') >= limitA
                    && map.get('C') >= limitC
                    && map.get('G') >= limitG
                    && map.get('T') >= limitT) {
                passwordCount++;
            }
        }

        System.out.println(passwordCount);
    }

    public static void printMap(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }
}
