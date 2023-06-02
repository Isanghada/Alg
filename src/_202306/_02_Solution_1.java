package _202306;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _02_Solution_1 {
        public static void main(String[] args) throws Exception {
            //System.setIn(new FileInputStream("input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

            int N = Integer.parseInt(br.readLine());
            int[] dp = new int[1001];
            dp[1] = 1;
            dp[2] = 2;
            for(int i = 3; i <= N; i++){
                dp[i] = (dp[i-1] + dp[i-2]) % 10007;
            }

            sb.append(dp[N]);
            System.out.println(sb);
        }
}
