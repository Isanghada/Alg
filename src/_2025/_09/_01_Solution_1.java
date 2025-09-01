package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2600
// - DP :
public class _01_Solution_1 {
    static final int MAX = 500, T = 5;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] b = new int[3];
        for(int i = 0; i < 3; i++) b[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(b);


        int[][] dp = new int[MAX+1][MAX+1];

        for(int i = 0; i <= MAX; i++){
            for(int j = 0; j <= MAX; j++){
                if(i < b[0] && j < b[0]){
                    dp[i][j] = 1;
                    continue;
                }

                for(int count : b){
                    if(i - count < 0) break;
                    if(dp[i-count][j] == 1){
                        dp[i][j] = 2;
                        break;
                    }
                }
                if(dp[i][j] == 0){
                    for(int count : b){
                        if(j - count < 0) break;
                        if(dp[i][j-count] == 1){
                            dp[i][j] = 2;
                            break;
                        }
                    }
                }

                if(dp[i][j] == 0) dp[i][j] = 1;
            }
        }

        for(int i = 0; i < T; i++){
            st = new StringTokenizer(in.readLine());
            int k1 = Integer.parseInt(st.nextToken());
            int k2 = Integer.parseInt(st.nextToken());

            sb.append(dp[k1][k2] == 2 ? "A" : "B").append("\n");
        }

        // 정답 입력
        System.out.println(sb);
    }
}
