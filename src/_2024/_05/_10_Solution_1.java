package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11058
// - DP : 1번 동작으로 A를 출력하고 가능한 복사 동작과 비교하여 갱신
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 버튼 횟수
        int N = Integer.parseInt(in.readLine());

        // DP 초기화
        long[] dp = new long[N+1];
        // 차례로 계산
        for(int n = 1; n <= N; n++) {
            // [n-1] 동작에 1번 동작!
            dp[n] = dp[n-1] + 1;

            // 복사가 가능한 경우부터 진행!
            if(n > 6){
                // copy 인덱스 이전의 상태를 복사!
                for(int copy = 3; copy < 6; copy++){
                    dp[n] = Math.max(dp[n], dp[n-copy] * (copy-1));
                }
            }
        }

        // 정답 반환
        sb.append(dp[N]);
        System.out.println(sb);
    }
}
