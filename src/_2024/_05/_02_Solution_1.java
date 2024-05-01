package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15817
// - DP : 각 파이프를 사용하여 만들 수 있는 경우를 차례로 계산!
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 파이프의 종류
        int X = Integer.parseInt(st.nextToken());   // 목표 길이

        // DP 초기화
        // - dp[x] : x 길이를 만들 수 있는 경우의 수
        int[] dp = new int[X+1];
        // 길이 0은 1개의 경우 존재!
        dp[0] = 1;

        // 각 파이프 차례로 진행
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int L = Integer.parseInt(st.nextToken());   // 파이프 길이
            int C = Integer.parseInt(st.nextToken());   // 파이프 개수

            // 기준 길이
            for(int x = X; x >= 0; x--){
                // 파이프 사용 횟수
                for(int k = 1; k <= C; k++){
                    // x를 만들기 위해 필요한 이전 길이
                    int past = x - k * L;
                    // 0 이상인 경우 x를 만들 수 있는 개수 증가
                    if(past >= 0) dp[x] += dp[past];
                    // 0 미만인 경우 종료!
                    else break;
                }
            }
        }

        // 정답 반환
        sb.append(dp[X]);
        System.out.println(sb);
    }
}
