package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27114
// -
public class _21_Solution_1 {
    static final int TYPE = 3, MAX = 10_000_000, DIR = 4;
    static final int[] DELTA = new int[]{0, 3, 1, 2};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] energies = new int[]{
                0,
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };

        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[DIR][K+1];
        for(int d = 0; d < DIR; d++) Arrays.fill(dp[d], MAX);
        dp[0][0] = 0;

        for(int type = 1; type <= TYPE; type++){
            int cur = energies[type];
            for(int energy = Math.max(1, energies[type]); energy <= K; energy++){
                for(int d = 0; d < DIR; d++){
                    int pastD = (DIR - DELTA[type] + d) % 4;
                    dp[d][energy] = Math.min(dp[d][energy], dp[pastD][energy-cur] + 1);
                }
            }
        }

        // 정답 출력
        sb.append(dp[0][K] == MAX ? -1 : dp[0][K]);
        System.out.println(sb);
    }
}