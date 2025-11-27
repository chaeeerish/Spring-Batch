public class 문자열 {
    public static void main(String[] args) {
        String string = "abc";

        // 문자열 내 문자를 가져오는 두가지 방법
        string.charAt(0);
        string.toCharArray();

        // '9'를 9로 바꾸기
        char digit = '9';
        int digitToInt1 = digit - '0';
        int digitToInt2 = Character.getNumericValue(digit);

        // 소문자를 대문자로 변환하기
        char lower = 'e';
        char upper1 = (char) (lower + ('a' - 'A'));
        char upper2 = Character.toUpperCase(lower);

        // 대문자를 소문자로 변환하기
        char upper = 'G';
        char lower1 = (char) (upper - ('a' - 'A'));
        char lower2 = Character.toLowerCase(upper);

        // StringBuilder 사용하기
        StringBuilder azBuilder = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            azBuilder.append(c);
        }

        String azString = azBuilder.toString();

    }
}
