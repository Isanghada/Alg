package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/16888
// - 모든 결과를 계산해두고 숫자에 따라 결과 반환
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 테스트케이스의 수 입력
        int N = Integer.parseInt(in.readLine());

        // dp 초기화
        boolean[] dp = new boolean[1000001];
        // 이전 결과를 토대로 이번 결과 계산
        for(int i = 1; i <= 1000000; i++){
            for(int j = 1; j * j <= i; j++){
                // (현재 목표인 수) - (완전 제곱수 j^2)의 결과가 false이면 koosaga가 이길 수 있는 경우
                // - 따라서, i의 결과를 true로 변환!
                if(!dp[i-j*j]){
                    dp[i] = true;
                    break;
                }
            }
        }

        // 테스트케이스 별로 결과 반환!
        while(N-- > 0) sb.append(dp[Integer.parseInt(in.readLine())] ? "koosaga\n" : "cubelover\n");

        System.out.println(sb);
    }
}
