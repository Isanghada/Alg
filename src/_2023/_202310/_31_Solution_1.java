package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2591
// - DP 활용
//   - 인덱스 i의 경우의 수
//   - dp[i-1] + dp[i-2] (단, dp[i-2]는 i-1, i를 합친 카드가 존재할 경우만 더해준다.)
// - 0이 들어가는 경우의 처리가 중요
public class _31_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202310/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 카드의 숫자 입력
        String input = in.readLine();
        // 각 카드의 숫자 분리 : 인덱스 1부터 N까지 사용.
        int[] numbers = new int[input.length()+1];
        for(int i = 1; i <= input.length(); i++) numbers[i] = input.charAt(i-1) - '0';

        // dp 초기화
        // - dp[i] : i 인덱스의 카드까지 사용했을 때의 경우의 수
        long[] dp = new long[numbers.length];
        // 첫 숫자가 0보다 클 경우 1, 0이면 0 반환
        dp[0] = dp[1] = numbers[1] > 0 ? 1 : 0;

        // 마지막 카드까지 경우의 수를 차례로 계산
        for(int i = 2; i < numbers.length; i++){
            int card = numbers[i-1] * 10 + numbers[i];
            // - dp[i-1] : 현재 숫자가 0보다 클 경우
            // - dp[i-2] : 이전 숫자와 현재 숫자를 합친 값이 10이상 34이하일 경우
            dp[i] = (numbers[i] > 0 ? dp[i-1] : 0) + (card <= 34 && card >= 10 ? dp[i-2] : 0);
        }

        // 정답 반환
        sb.append(dp[input.length()]);
        System.out.println(sb);
    }
}