package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25419
// - DP
public class _31_Solution_1 {
    static final int NAN = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] unusedArr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[N+1];
        for(int unused : unusedArr) dp[unused] = NAN;

        int maxNum = N;
        for(int n = 1; n <= N; n++){
            if(n + K > N) break;
            if(check(dp, n, K)){
                maxNum = n - 1;
                break;
            }
        }

        for(int n = maxNum; n > 0; n--){
            if(dp[n] == NAN) continue;
            int select = 1;
            int limit = Math.min(n + K, N);
            for(int next = n + 1; next <= limit; next++){
                if(dp[next] == 1) {
                    select = 0;
                    break;
                }
            }
            dp[n] = select;
        }

        boolean flag = false;
        int limit = Math.min(N, K);
        for(int n = 1; n <= limit; n++){
            if(dp[n] == 1){
                flag = true;
                break;
            }
        }

        // 정답 반환
        sb.append(flag ? 1 : 0);
        System.out.println(sb);
    }

    private static boolean check(int[] dp, int n, int k) {
        int limit = n + k;
        for(; n < limit; n++){
            if(dp[n] != NAN) return false;
        }
        return true;
    }
}