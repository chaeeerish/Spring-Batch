import java.util.*;

/*
 문자열 압축
 
 aabbaccc = aabbaccc
 ababcdcdababcdcd = 2ababcdcd
 abcabcdede = 2abcdede
 abcabcabcabcdededededede = 2abcabc2dedede
 
 [Input]
 s
 
 [Output]
 가장 짧은 길이
 
 [Solution]
 abcabcdede
 > 3abcdede
 
 1. 1부터 length-1까지 잘라본다. 
    1-1. token = substring을 넣는다. 
    1-2. s 전체를 검사한다. 
        1-2-1. token이 비었다면, 채운다. 
    
        // startIndex부터 startIndex + token.length() - 1까지 일치하는지 검사한다. 
        1-2-2. 일치한다면
            1-2-1-1. count + 1을 한다. 
        1-2-3. 일치하지 않는다면
            1-2-2-1. 그 count 개수만큼 candidate에 담는다. 
            1-2-2-2. token은 "" 비운다. 
            
        1-2-4. startIndex를 다음으로 옮긴다. 

 [주의]
 
*/

class Solution {
    public int solution(String s) {
        if (s.length() == 1) return 1;

        ArrayList<Integer> candidates = new ArrayList<>();

        for (int tokenLength = 1; tokenLength <= s.length() / 2; tokenLength++) {
            StringBuilder answer = new StringBuilder();
            ArrayList<String> splitList = new ArrayList<>();

            for (int startIndex = 0; startIndex <= s.length() - 1; startIndex += tokenLength) {
                int endIndex = (startIndex + tokenLength - 1 >= s.length() - 1) ? s.length() - 1 : startIndex + tokenLength - 1;

                splitList.add(s.substring(startIndex, endIndex + 1));
            }

            int sameCount = 1;
            for (int index = 1; index < splitList.size(); index++) {
                if (splitList.get(index - 1).equals(splitList.get(index))) {
                    sameCount += 1;
                } else {
                    if (sameCount == 1) {
                        answer.append(splitList.get(index - 1));
                    } else {
                        answer.append(String.valueOf(sameCount));
                        answer.append(splitList.get(index - 1));
                    }

                    sameCount = 1;
                }
            }

            if (sameCount == 1) {
                answer.append(splitList.get(splitList.size() - 1));
            } else {
                answer.append(sameCount);
                answer.append(splitList.get(splitList.size() - 1));
            }

            candidates.add(answer.toString().length());

            // System.out.println(answer.toString());
        }

        Collections.sort(candidates);
        return candidates.get(0);
    }
}
