package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2602
// - DP : 각 위치별 경우의 수를 차례로 계산!
//        - dp[type][targetSize][brigeSize] : 다리별로 targetSize 크기가 brigeSize범위에서 만들어지는 경우의 수!
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] target = in.readLine().toCharArray();    // 마법
        char[] devil = in.readLine().toCharArray();     // 악마의 다리
        char[] angel = in.readLine().toCharArray();     // 천사의 다리

        int targetSize = target.length;
        int bridgeSize = devil.length;

        // dp[type][t][b] 초기화
        // - type : 다리 종류(0-악마, 1-천사)
        // - t : 만들어진 마법 길이!
        // - b : 사용할 다리 범위!
        int[][][] dp = new int[2][targetSize][bridgeSize];
        if(target[0] == devil[0]) dp[0][0][0] = 1;
        if(target[0] == angel[0]) dp[1][0][0] = 1;

        for(int x = 1; x < bridgeSize; x++){
            dp[0][0][x] = dp[0][0][x-1];
            dp[1][0][x] = dp[1][0][x-1];

            if(target[0] == devil[x]) dp[0][0][x]++;
            if(target[0] == angel[x]) dp[1][0][x]++;

            for(int t = 1; t < targetSize; t++){
                dp[0][t][x] = dp[0][t][x-1];
                dp[1][t][x] = dp[1][t][x-1];

                if(target[t] == devil[x]) dp[0][t][x] += dp[1][t-1][x-1];
                if(target[t] == angel[x]) dp[1][t][x] += dp[0][t-1][x-1];
            }
        }

        // 정답 출력
        sb.append(dp[0][targetSize-1][bridgeSize-1] + dp[1][targetSize-1][bridgeSize-1]);
        System.out.println(sb.toString());
    }
}
