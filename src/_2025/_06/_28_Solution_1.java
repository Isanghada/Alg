package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22968
// - DP : 각 높이별 최소 정점의 개수를 계산하여 높이 탐색!
public class _28_Solution_1 {
    static final int MAX = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        long[] dp = getDP();

        while(T-- > 0){
            int V = Integer.parseInt(in.readLine());
            sb.append(getMaxHeight(dp, V)).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static int getMaxHeight(long[] dp, int v) {
        int height = 0;
        for(int h = 1; h < 50; h++){
            if(dp[h] > v) {
                height = h-1;
                break;
            }
        }

        return height;
    }

    private static long[] getDP() {
        long[] dp = new long[50];
        dp[1] = 1;
        dp[2] = 2;

        int idx = 2;
        while(dp[idx] <= MAX){
            idx++;
            dp[idx] = dp[idx-1] + dp[idx-2] + 1;
        }

//        for(long d : dp) System.out.println(d+" -------");
//        System.out.println(idx);
        return dp;
    }
}
