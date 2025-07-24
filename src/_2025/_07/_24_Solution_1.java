package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14370
// - 구현! : 알파벳별로 유일하게 숫자를 만들 수 있는 경우를 순서로 탐색!
public class _24_Solution_1 {
    static final int ALP = 'Z' - 'A' + 1;   // 알파벳의 수
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());    // 테스트 케이스
        // 숫자 -> 알파벳 변환
        final String[] numberToAlp = new String[]{
                "ZERO", "ONE", "TWO", "THREE",
                "FOUR", "FIVE", "SIX", "SEVEN",
                "EIGHT", "NINE"
        };
        // 알파벳 순서
        final char[] alpOrders = new char[]{'Z','G', 'X' ,'H', 'R', 'F', 'T', 'O', 'I', 'S'};
        // 숫자 순서
        final int[] numberOrders = new int[]{0, 8, 6, 3, 4, 5, 2, 1, 9, 7};
        for(int t = 1; t <= T; t++){
            // 알파벳 개수
            int[] alpCount = new int[ALP];
            for(char alp : in.readLine().toCharArray()) alpCount[alp-'A']++;

//            System.out.println(Arrays.toString(count));
            // 정답 초기화
            int[] answer = new int[10];
            // 정해진 순서에 따라 알파벳 -> 숫자 변환
            for(int i = 0; i < 10; i++){
                int count = alpCount[alpOrders[i]-'A'];
                int number =numberOrders[i];
                answer[number] += count;

                for(char alp : numberToAlp[number].toCharArray()) alpCount[alp-'A'] -= count;
            }
//            System.out.println(Arrays.toString(count));
//            System.out.println("---------");

            // 숫자 개수에 따라 번호 출력
            sb.append("Case #").append(t).append(": ").append(getPhoneNumber(answer)).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static String getPhoneNumber(int[] target) {
        StringBuilder phone = new StringBuilder();
        for(int num = 0; num < 10; num++){
            while(target[num]-- > 0) phone.append(num);
        }
        return phone.toString();
    }
}
