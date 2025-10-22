package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6506
// -
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if(N == 0 && K == 0) break;

            st = new StringTokenizer(in.readLine());
            int[] a = new int[N+1];
            a[0] = -20000;
            for(int n = 1; n <= N; n++) a[n] = Integer.parseInt(st.nextToken());

            long[][] dp = new long[K+1][N+1];
            dp[0][0] = 1L;
            for(int k = 1; k <= K; k++){
                for(int n = 1; n <= N; n++){
                    for(int check = 0; check < n; check++){
                        if(a[check] < a[n]) dp[k][n] += dp[k-1][check];
                    }
                }
            }

            long answer = 0;
            for(long count : dp[K]) answer += count;
            sb.append(answer).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
