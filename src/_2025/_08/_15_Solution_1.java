package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/23325
// - DP : 각 경우에서 가장 큰 값을 선택!
public class _15_Solution_1 {
    // 최저값
    static final int MIN = -3_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String input = in.readLine();   // 수식
        int len = input.length();       // 길이

        // 길이 1인 경우
        if(input.length() == 1) sb.append(input.charAt(0) == '+' ? 10 : 1);
        // 길이 2인 경우
        else if(input.length() == 2) sb.append(11);
        else {
            // dp 초기화
            int[] dp = new int[len];

            // 초기화
            dp[0] = input.charAt(0) == '+' ? 10 : 1;
            dp[1] = input.substring(0, 2).equals("+-") ? 11 : MIN;

            // 모든 경우 탐색
            for(int i = 2; i < len; i++){
                dp[i] = calculate(dp[i-2], input.charAt(i-1), input.charAt(i) == '+' ? 10 : 1);
                if(i > 2 && input.substring(i-1, i+1).equals("+-")) dp[i] = Math.max(dp[i], calculate(dp[i-3], input.charAt(i-2), 11));
            }

            sb.append(dp[len-1]);
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static int calculate(int cur, char operand, int num) {
        int result = cur;
        if(operand == '+') result += num;
        else result -= num;

        return result;
    }
}
