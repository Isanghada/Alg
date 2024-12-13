package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5569
// - DP : 각 좌표마다 (방향, 교차로 사용 여부)에 따른 경우의 수 계산!
public class _13_Solution_1 {
    public static final int MOD = 100_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int W = Integer.parseInt(st.nextToken());   // 행
        int H = Integer.parseInt(st.nextToken());   // 열

        // dp 초기화
        // - dp[w][h][d][t] : (w, h) 좌표에 d 방향, 교차로 사용 여부(t)에 따른 경우의 수
        //   - w, h : 행, 열
        //   - d : 방향 -> 0(북쪽), 1(동쪽)
        //   - t : 교차로 사용 여부 -> 0(미사용), 1(사용)
        int[][][][] dp = new int[W+1][H+1][2][2];
        // (1, 1) 좌표 초기화
        dp[1][1][0][0] = 1;
        dp[1][1][1][0] = 1;

        // 모든 좌표 탐색
        for(int r = 1; r <= W; r++){
            for(int c = 1; c<= H; c++){
                // 시작 좌표인 경우 패스
                if(r == 1 && c == 1) continue;
                // 교차로를 사용하지 않은 경우 계산
                dp[r][c][0][0] = (dp[r-1][c][0][0] + dp[r-1][c][0][1]) % MOD;
                dp[r][c][1][0] = (dp[r][c-1][1][0] + dp[r][c-1][1][1]) % MOD;

                // 교차로를 사용하는 경우 계산
                // - 좌표 중 1(시작 좌표)이 있는 경우 교차로를 사용하지 않으므로 패스
                if(r == 1 || c == 1) continue;
                dp[r][c][0][1] = (dp[r-1][c][1][0]) % MOD;
                dp[r][c][1][1] = (dp[r][c-1][0][0]) % MOD;
            }
        }

        int answer= (dp[W][H][0][0] + dp[W][H][0][1] +
                dp[W][H][1][0] + dp[W][H][1][1]) % MOD;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
