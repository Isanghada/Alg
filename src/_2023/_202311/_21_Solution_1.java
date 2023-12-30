package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/16400
// - 소수를 구하고 DP를 통해 경우의 수 계산
public class _21_Solution_1 {
    public static final int MOD = 123456789;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        //  물건의 값
        int N = Integer.parseInt(in.readLine());

        // 소수 계산 : 에라토스테네스의 체
        // - true : 소수 X
        // - false : 소수 O
        boolean[] prime = new boolean[N+1];
        prime[0] = true;
        prime[1] = true;
        // 소수를 담을 리스트
        List<Integer> primeList = new ArrayList<>();
        // 0, 1은 소수가 아니므로 2부터 시작!
        for(int i = 2; i <= N; i++){
            // false인 경우 : 소수인 경우
            if(!prime[i]){
                // 리스트에 추가
                primeList.add(i);
                // i의 배수는 소수가 될 수 없으므로 true로 변환
                int nonPrime = i + i;
                while(nonPrime < prime.length){
                    prime[nonPrime] = true;
                    nonPrime += i;
                }
            }
        }

        // dp 초기화
        int[] dp = new int[N+1];
        // - 0원인 경우! 1가지 방법이 있으므로 1로 변환
        dp[0] = 1;
        // 모든 소수에 대해 경우의 수 계산
        for(int num : primeList){
            // num 이상의 수를 만들 수 있는 경우의 수 계산
            for(int j = num; j <= N; j++){
                dp[j] = (dp[j] + dp[j-num]) % MOD;
            }
        }

        // 정답 출력
        sb.append(dp[N]);
        System.out.println(sb);
    }
}