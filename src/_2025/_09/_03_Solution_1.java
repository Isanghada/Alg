package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/31929
// - DP : 각 경우의 최대값을 구하며 진행
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 승리 정보
        int W = Integer.parseInt(in.readLine());
        int[] wins = Arrays.stream(("0 "+ in.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        // 패배 정보
        int L = Integer.parseInt(in.readLine());
        int[] loses = Arrays.stream(("0 "+ in.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        // 보호 점수
        int K = Integer.parseInt(in.readLine());

        // DP 초기화
        int[][] dp = new int[W+1][L+1];
        // 연속해서 이기는 경우
        for(int w = 1; w <= W; w++) dp[w][0] += dp[w-1][0] + wins[w];
        // 연속해서 지는 경우
        for(int l = 1; l <= L; l++){
            if(Math.floorMod(dp[0][l-1], K) == 0) dp[0][l] = dp[0][l-1] - loses[l];
            else dp[0][l] = dp[0][l-1] - Math.min(Math.floorMod(dp[0][l-1], K), loses[l]);
        }
        
        // DP 계산
        for(int w = 1; w <= W; w++){
            for(int l = 1; l <= L; l++){
                // w+l 번째에 이기는 경우
                dp[w][l] = dp[w-1][l] + wins[w];
                // w+l 번째에 지는 경우
                if(Math.floorMod(dp[w][l-1], K) == 0) dp[w][l] = Math.max(dp[w][l], dp[w][l-1] - loses[l]);
                else dp[w][l] = Math.max(dp[w][l], dp[w][l-1] - Math.min(Math.floorMod(dp[w][l-1], K), loses[l]));
            }
        }

        // 정답 반환
        sb.append(dp[W][L]);
        System.out.println(sb);
    }
}
