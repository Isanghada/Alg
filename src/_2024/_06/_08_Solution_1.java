package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15989
// -
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            int N = Integer.parseInt(in.readLine());

            int[] dp = new int[N+1];
            dp[0] = 1;
            for(int num = 1; num < 4; num++){
                for(int n = num; n <= N; n++) dp[n] += dp[n-num];
            }

            sb.append(dp[N]).append("\n");
//            System.out.println(Arrays.toString(dp));
        }

        // 정답 출력
        System.out.println(sb);
    }
}
