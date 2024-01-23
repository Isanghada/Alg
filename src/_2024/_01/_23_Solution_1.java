package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/28422
// - DP : 각 경우를 차례로 계산!
//   - 앞의 경우를 미리 계산하고, 현재의 숫자가 마지막이며 (2장, 3장)인 경우를 체크
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 카드의 수
        int N = Integer.parseInt(in.readLine());
        // 카드
        int[] cardArr = Arrays.stream(("0 " + in.readLine()).split(" ")).mapToInt(Integer::new).toArray();
        // DP 초기화
        int[] dp = new int[N+1];

        // 최대 숫자 : 2^10 미만
        final int LIMIT = pow(2, 10);
        int[] binary = new int[LIMIT];
        for(int num = 0; num < LIMIT; num++){
            int sum = 0;
            for(char b : Integer.toBinaryString(num).toCharArray())
                sum += b - '0';
            binary[num] = sum;
        }

        // 첫 2장, 3장인 경우 계산!
        int value = cardArr[1];
        for(int idx = 2; idx <= Math.min(N, 3); idx++){
            value ^= cardArr[idx];
            dp[idx] = binary[value];
        }

        // 4번째 카드가 마지막인 경우부터 끝까지 진행
        for(int idx = 4; idx <= N; idx++) {
            // 현재 카드 숫자
            int cur = cardArr[idx];
            // 이전 카드와 xor 연산!
            // - xor 연산은 교환 법칙이 성립하므로 순서대로 계산하지 않아도 된다.

            // 현재
            dp[idx] = Math.max(dp[idx], dp[idx-1]);
            for (int prev = 1; prev <= 2; prev++) {
                int prevIdx = idx - prev;
                if(prevIdx <= 2) continue;

                cur ^= cardArr[prevIdx];
                // 현재 값, 새로운 값 중 최대값 선택
                dp[idx] = Math.max(dp[idx], dp[prevIdx - 1] + binary[cur]);
            }
        }

        // 정답 출력
        sb.append(dp[N]);
        System.out.println(sb);
    }

    private static int pow(int num, int square) {
        int result = 1;
        for(int i = 1; i <= square; i++) result *= num;
        return result;
    }
}
