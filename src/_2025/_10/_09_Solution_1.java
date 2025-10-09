package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25215
// -
public class _09_Solution_1 {
    static final int TYPE = 2;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = "0"+in.readLine();
        int len = input.length();

        int[][] dp = new int[TYPE][len];
        dp[1][0] = -1;
        for(int i = 1; i < len; i++){
            char alp = input.charAt(i);
            if('a' <= alp && alp <= 'z'){
                if(dp[0][i-1] != -1){
                    dp[0][i] = dp[0][i-1] + 1;
                    dp[1][i] = dp[0][i-1] + 2;
                }

                if(dp[1][i-1] != -1){
                    dp[0][i] = Math.min(dp[0][i], dp[1][i-1]+2);
                    dp[1][i] = Math.min(dp[1][i], dp[1][i-1]+2);
                }
            }else{
                if(dp[0][i-1] != -1){
                    dp[0][i] = dp[0][i-1] + 2;
                    dp[1][i] = dp[0][i-1] + 2;
                }

                if(dp[1][i-1] != -1){
                    dp[0][i] = Math.min(dp[0][i], dp[1][i-1]+2);
                    dp[1][i] = Math.min(dp[1][i], dp[1][i-1]+1);
                }
            }
        }
//        System.out.println(Arrays.toString(dp[0]));
//        System.out.println(Arrays.toString(dp[1]));

        // 정답 출력
        sb.append(Math.min(dp[0][len-1], dp[1][len-1]));
        System.out.println(sb);
    }
}
