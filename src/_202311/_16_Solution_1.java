package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30460
// - DP 활용 : 이전까지의 최대값을 구하여 활용
// - dp[i] : i초에 얻을 수 있는 최대값
public class _16_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 점수를 얻는 횟수
        int N = Integer.parseInt(in.readLine());

        // 점수 배열
        int[] A = new int[N+3];
        st = new StringTokenizer(in.readLine(), " ");
        // - 점수 입력
        for(int i = 1; i <= N; i++) A[i] = Integer.parseInt(st.nextToken());

        // 최대값 초기화
        int answer = Integer.MIN_VALUE;
        // dp 초기화
        int[] dp = new int[N+3];
        // 모든 시간대에 대해서 계산
        for(int i = 1; i < N+3; i++){
            // 버튼을 누르지 않는 경우 비교
            dp[i] = dp[i-1] + A[i];
            // 3초 이상일 경우 버튼을 누르는 경우도 추가로 비교
            if(i >= 3) dp[i] = Math.max(dp[i], dp[i-3] + (A[i-2] + A[i-1] + A[i]) * 2);
        }

//        System.out.println(Arrays.toString(A));
//        System.out.println(Arrays.toString(dp[0]));
//        System.out.println(Arrays.toString(dp[1]));

        // 정답 출력 : 최대값 반환
        sb.append(dp[N+2]);
        System.out.println(sb);
    }
}
