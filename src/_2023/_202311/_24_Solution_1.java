package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2421
// - DP를 활용해 해결
// - 2차원 배열로 각 저금통에 저축된 돈이 되는 경우 계산
public class _24_Solution_1 {
    public static int[][] dp;
    public static boolean[] isPrime;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202311/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 목표 금액
        int N = Integer.parseInt(in.readLine());

        // 소수 판별 : 에라토스테네스의 체
        isPrime = new boolean[1000000];
        // 최대 999999까지의 소수 판별!
        for(int num = 2; num < isPrime.length; num++){
            if(!isPrime[num]){
                int next = num + num;
                while(next < isPrime.length){
                    isPrime[next] = true;
                    next += num;
                }
            }
        }

        // DP 초기화
        dp = new int[N+1][N+1];
        for(int i = 0; i <= N; i++) Arrays.fill(dp[i], -1);
        dp[1][1] = 0;

        // 정답 반환
        sb.append(calculateMin(N, N));
        System.out.println(sb);
    }

    // DP 계산 함수 : DP[one][two] 반환
    // - 재귀를 통해 계산
    private static int calculateMin(int one, int two) {
        // 범위를 벗어날 경우 0 반환
        if(one == 0 || two == 0) return 0;
        // 이미 계산된 경우 값 반환
        if(dp[one][two] != -1) return dp[one][two];

        // one, two를 합친 값 계산
        int cur = Integer.parseInt(String.valueOf(one)+String.valueOf(two));
        // 2가지 경우 중 경우의 수가 많은 경우 선택
        // + cur이 소수인 경우 1 증가
        return dp[one][two] = Math.max(calculateMin(one-1, two),
                            calculateMin(one, two-1))
                            + (isPrime[cur] ? 0 : 1);
    }
}
