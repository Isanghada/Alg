package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/25289
// - DP : 공차를 기준으로 모든 등차수열 길이 탐색!
//          수열의 값이 (1, 100)이므로 (-99, 99) 모든 공차 탐색!
public class _03_Solution_1 {
    static final int MAX = 100;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int answer = 0;
        int[] dp = null;
        for(int d = -99; d <= 99; d++){
            dp = new int[MAX+1];
            for(int i = 0; i < N; i++){
                if(A[i] - d < 1 || A[i] - d > MAX) dp[A[i]] = 1;
                else dp[A[i]] = dp[A[i]-d] + 1;
                answer = Math.max(answer, dp[A[i]]);
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
