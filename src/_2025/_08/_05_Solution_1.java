package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15487
// - DP : (i, j, k, l) 순서대로 최대값이 경우를 찾으며 탐색
public class _05_Solution_1 {
    static final int TYPE = 4, MIN = -3_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 배열 크기
        int N = Integer.parseInt(in.readLine());

        // 배열 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] A =  new int[N+1];
        for(int n = 1; n <= N; n++) A[n] = Integer.parseInt(st.nextToken());

        // dp 초기화
        int[][] dp = new int[TYPE+1][N+1];
        for(int type = 1, o = -1; type <= TYPE; type++, o = -o){
            Arrays.fill(dp[type], MIN);
            // 배열의 n번째 값을 선택하는 경우와 선택하지 않는 경우 중 최대값 선택
            for(int n = type; n <= N; n++){
                dp[type][n] = Math.max(dp[type][n-1], dp[type-1][n-1] + o*A[n]);
            }
        }

        // 정답 반환
        sb.append(dp[TYPE][N]);
        System.out.println(sb);
    }
}
