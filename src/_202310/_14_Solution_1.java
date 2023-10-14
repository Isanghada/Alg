package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1489
// - Top Down 방식의 DP!! => 이게 참 어렵네..
// - A, B팀의 오름차순 정렬 후 A팀의 최대 능력치부터 점수 계산
// - 이기는 경우, 지는 경우, 비기는 경우의 분기에 따라 다음 값 탐색
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        // 인원수 입력
        int N = Integer.parseInt(in.readLine());
        
        // 팀 A 입력
        int[] A = inputTeam(N, in);
        // 팀 B 입력
        int[] B = inputTeam(N, in);
        // dp 초기화 : 최대값으로 초기화
        int[][] dp = new int[N][N];
        for(int[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);
        // 정답 반환
        sb.append(getMaxScore(dp, A, B, N-1, N-1));
        System.out.println(sb);
    }

    // 최대 점수 계산 함수 : Top Down 방식 DP
    // - A팀의 최대 능력치부터 계산
    // - 이길 수 있는 경우 : 2점 증가
    // - 비기는 경우 : max(이기는 경우로 이동, 1점 증가)
    // - 지는 경우 : 이기는 경우로 이동
    // - dp : 최대 점수 배열
    // - a : 팀 A 배열
    // - b : 팀 B 배열
    // - r : A팀 인덱스
    // - c : B팀 인덱스
    private static int getMaxScore(int[][] dp, int[] a, int[] b, int r, int c) {
        // 범위를 벗어나는 경우 0 => 무조건 지는 경우
        if(r < 0 || c < 0) return 0;
        
        // 점수가 계산되지 않은 경우만 로직 수행
        if(dp[r][c] == Integer.MAX_VALUE){
            // 지는 경우 : 이기는 경우로 이동(b팀 인덱스 감소)
            if(a[r] < b[c]) dp[r][c] = getMaxScore(dp, a, b, r, c-1);
            // 이기는 경우 : 다음 경기 확인(a, b팀 인덱스 감소 / 2점 증가).
            else if(a[r] > b[c]) dp[r][c] = getMaxScore(dp, a, b, r-1, c-1) + 2;
            // 비기는 경우 : 이기는 경우로 이동(b팀 인덱스 감소), 다음 경기 확인(a, b팀 인덱스 감소/ 1점 증가)
            else dp[r][c] = Math.max(getMaxScore(dp, a, b, r, c-1), getMaxScore(dp, a, b, r-1, c-1) + 1);
        }

        // dp[r][c] 반환
        return dp[r][c];
    }

    // 팀 입력 함수 : 팀 입력 후 오름차순 정렬하여 반환
    // - n : 인원수
    // - in : 입력
    public static int[] inputTeam(int n, BufferedReader in) throws IOException {
        // 팀 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] team = new int[n];
        for(int idx = 0; idx < n; idx++) team[idx] = Integer.parseInt(st.nextToken());
        // 정렬
        Arrays.sort(team);

        return team;
    }
}
