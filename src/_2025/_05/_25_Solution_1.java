package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25759
// - DP : 꽃의 아름다움을 기준으로 가능한 모든 경우 계산!
public class _25_Solution_1 {
    static final int MAX = 100; // 꽃의 아름다움 최대값
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());    // 꽃의 개수
        
        // dp 초기화
        int[] dp = new int[MAX+1];
        Arrays.fill(dp, -1);

        // - 초기값 설정! : 처음 꽃만으로는 꽃다발을 만들 수 없으므로 0으로 설정
        StringTokenizer st = new StringTokenizer(in.readLine());
        dp[Integer.parseInt(st.nextToken())] = 0;
        // 나머지 꽃의 경우 탐색
        for(int i = 1; i < N; i++){
            // 현재 꽃의 아름다움
            int cur = Integer.parseInt(st.nextToken());
            // 모든 조합 계산!
            for(int k = 1; k <= MAX; k++){
                if(dp[k] != -1) {
                    int diff = k - cur;
                    dp[cur] = Math.max(dp[cur], dp[k] + diff * diff);
                }
            }
        }

        int answer = 0;
        for(int k = 1; k <= MAX; k++) answer = Math.max(answer, dp[k]);

        // 결과 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
