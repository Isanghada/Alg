package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2157
// - DP : dp[M][N] 2차원 배열을 통해 DP 계산! => N번 도시를 M번째에 여행한 경우의 최대값!
//        - M : 여행한 도시의 수
//        - N : 현재 도시!
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int M = Integer.parseInt(st.nextToken());   // 최대 여행의 수
        int K = Integer.parseInt(st.nextToken());   // 항로의 수

        // map을 통해 인접한 여행 정보 입력!
        // - 도시 번호가 증가하는 방향만 활용!!!
        Map<Integer, Integer>[] adjMap = new Map[N+1];
        for(int node = 1; node <= N; node++) adjMap[node] = new HashMap<>();

        // 항로 정보 입력
        while(K-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 도시 번호가 작아지는 경우 패스
            if(A > B) continue;
            // 점수(C)가 큰 값으로 갱신!
            adjMap[A].put(B, Math.max(adjMap[A].getOrDefault(B, 0), C));
        }

        // dp 초기화
        // dp[m][n] : n번 도시를 m번 째에 여행할 경우의 최대값
        // - 1번 도시의 경우 입력!
        int[][] dp = new int[M+1][N+1];
        for(int next : adjMap[1].keySet()) dp[2][next] = adjMap[1].get(next);

        // 2번째 여행지부터 차례로 탐색!
        for(int m = 2; m < M; m++){
            // 모든 도시 탐색
            for(int n = m; n < N; n++){
                // 0인 경우 현재 단계에 갈 수 없으므로 패스
                if(dp[m][n] == 0) continue;
                // 다음 여행지 갱신!
                for(int next : adjMap[n].keySet()){
                    dp[m+1][next] = Math.max(dp[m+1][next], dp[m][n] + adjMap[n].get(next));
                }
            }
        }
        // 모든 N번 도시의 값 중 최대값 선택
        int answer = 0;
        for(int m = 2; m <= M; m++) answer = Math.max(answer, dp[m][N]);


        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
