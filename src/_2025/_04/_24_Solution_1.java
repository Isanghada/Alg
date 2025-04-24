package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2296
// -
public class _24_Solution_1 {
    static final int X = 0, Y = 1, C = 2;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        int[][] buildings = new int[N][3];
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            buildings[i] = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };

        }
        Arrays.sort(buildings, Comparator.comparingInt((int[] b) -> b[X]));

        for(int[] b : buildings) System.out.println(Arrays.toString(b));

        int answer = 0;
        int[][] dp = new int[N][2];
        for(int i = 0; i < N; i++){
            dp[i][0] = buildings[i][C];
            dp[i][1] = buildings[i][C];
            for(int j = 0; j < i; j++){
                if(buildings[j][Y] < buildings[i][Y]) dp[i][0] = Math.max(dp[i][0], dp[j][0] + buildings[i][C]);
                else if(buildings[j][Y] > buildings[i][Y]) dp[i][1] = Math.max(dp[i][1], dp[j][1] + buildings[i][C]);
            }

            for(int type = 0; type < 2; type++) answer = Math.max(answer, dp[i][type]);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
