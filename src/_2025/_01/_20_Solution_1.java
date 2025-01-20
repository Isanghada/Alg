package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5721
// - DP : 각 행의 최대값을 계산한 후, 가능한 조합 중 최대값 계산!
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        do{
            st = new StringTokenizer(in.readLine());
            int M = Integer.parseInt(st.nextToken());   // 행
            int N = Integer.parseInt(st.nextToken());   // 열

            // 종료 조건
            if(M == 0 && N == 0) break;

            int[] nums;
            int[] dp = new int[M+1];

            for(int r = 1; r <= M; r++){
                st = new StringTokenizer(in.readLine());
                nums = new int[N+1];
                for(int c = 1; c <= N; c++){
                    nums[c] = Integer.parseInt(st.nextToken());
                    if(c > 1) nums[c] = Math.max(nums[c-2]+nums[c], nums[c-1]);
                }

                dp[r] = nums[N];
                if(r > 1) dp[r] = Math.max(dp[r-2] + dp[r], dp[r-1]);
            }
            sb.append(dp[M]).append("\n");
        }while(true);


        // 정답 출력
        System.out.println(sb);
    }
}
