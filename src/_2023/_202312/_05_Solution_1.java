package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/27210
// - DP를 통해 계산! : 왼쪽, 오른쪽을 바라보는 것을 따로 계산하여 최대값 탐색
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 석상의 수
        int N = Integer.parseInt(in.readLine());
        // 석상 입력
        int[] stones = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 초기화 : 최소값을 절대값을 취하므로 0
        int answer = 0;
        // dp 초기화
        // - dp[0][i] : i번 째까지 칠할 때 왼쪽 기준 경우의 수
        // - dp[1][i] : i번 째까지 칠할 때 오른쪽 기준 경우의 수
        int[][] dp = new int[2][N];
        for(int i = 0; i < N; i++){
            // 왼쪽을 바라보는 경우 1, 오른쪽을 바라보는 경우 -1
            int stone = (stones[i] == 1 ? 1 : -1);
            // 첫 인덱스인 경우 값 그대로 입력
            if(i == 0){
                dp[0][i] = stone;
                dp[1][i] = -stone;
            }else{
                // 왼쪽을 바라보는 경우 : 값 그대로 탐색
                dp[0][i] = Math.max(dp[0][i-1] + stone, stone);
                // 오른쪽을 바라보는 경우 : (-)를 곱한 값으로 탐색
                dp[1][i] = Math.max(dp[1][i-1] - stone, -stone);
            }
            // 최대값으로 변경!
            answer = Math.max(answer, dp[0][i]);
            answer = Math.max(answer, dp[1][i]);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
