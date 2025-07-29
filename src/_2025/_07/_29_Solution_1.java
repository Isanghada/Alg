package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/29756
// - DP
public class _29_Solution_1 {
    static final int MAX_HP = 100;
    static int N, K;
    static int[] SCORE_ARR, HP_ARR;
    static int[][] DP;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        SCORE_ARR = initArr(in.readLine(), N);
        HP_ARR = initArr(in.readLine(), N);

        DP = new int[N+1][MAX_HP+1];
        for(int i = 1; i <= N; i++) Arrays.fill(DP[i], -1);

        // 정답 반환
        sb.append(getMaxScore(N, MAX_HP));
        System.out.println(sb);
    }

    private static int getMaxScore(int n, int hp) {
        if(DP[n][hp] == -1){
            int newHP = Math.min(MAX_HP, hp + K);
            DP[n][hp] = getMaxScore(n-1, newHP);

            if(newHP >= HP_ARR[n]){
                DP[n][hp] = Math.max(DP[n][hp], getMaxScore(n-1, newHP - HP_ARR[n])+SCORE_ARR[n]);
            }
        }

        return DP[n][hp];
    }

    private static int[] initArr(String input, int n) {
        StringTokenizer st = new StringTokenizer(input);
        int[] result = new int[n+1];
        for(int i = 1; i <= n; i++) result[i] = Integer.parseInt(st.nextToken());
        return result;
    }
}