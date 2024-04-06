package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25343
// - DP : 최장 증가 부분 수열 2차원 형태
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 배열의 크기
        int N = Integer.parseInt(in.readLine());
        int[][] map = new int[N+1][N+1];    // 배열 초기화
        int[][] dp = new int[N+1][N+1];     // DP 초기화

        // 첫 위치는 무조건 가능!
        dp[1][1] = 1;

        StringTokenizer st = null;
        for(int r = 1; r <= N; r++){
            // 배열 정보 입력
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                // [r][c]를 기준으로 좌상단의 모든 좌표와 비교하여 수열 길이가 최대인 경우로 DP 갱신
                // - (N X N) 배열이므로 최단 경로는 좌상단에서 우하단으로 우, 하 방향으로만 이동하는 경우
                // - 따라서, [r][c]를 기준으로 좌상단 모든 좌표는 최단 경로에 포함된다!
                for(int i = 0; i <= r; i++){
                    for(int j = 0; j <= c; j++){
                        if(map[r][c] > map[i][j]) dp[r][c] = Math.max(dp[r][c], dp[i][j]+1);
                    }
                }
            }
        }

        int answer = 0;
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++) answer = Math.max(answer, dp[r][c]);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
