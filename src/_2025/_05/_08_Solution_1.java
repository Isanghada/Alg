package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5546
// - DP : (파스타 종류, 연속으로 먹은 날, 날짜)를 기준으로 가능한 경우를 모두 계산!
public class _08_Solution_1 {
    // PASTA : 파스타 종류 3개
    // COUNT : 연속으로 먹을 수 있는 날 2일
    static final int PASTA = 3, COUNT = 3, MOD = 10_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 주어진 날짜
        int K = Integer.parseInt(st.nextToken());   // 정해진 일정

        // 정해진 파스타 정보 입력
        int[] pastaArr = new int[N+1];
        for(int k = 1; k <= K; k++){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            pastaArr[A] = B;
        }

        // dp 초기화
        // - dp[pasta][count][n] : n 날에 pasta를 count만큼 연속해서 먹는 경우의 수
        //      - pasta : 파스타 종류 (1-토마토, 2-크림, 3-바질)
        //      - count : 연속으로 먹은 일수
        //      - n : 날짜
        int[][][] dp = new int[PASTA+1][COUNT][N+1];

        // 1일차 일정에 따라 초기화!
        if(pastaArr[1] != 0) dp[pastaArr[1]][1][1] = 1;
        else for(int pasta = 1; pasta <= PASTA; pasta++) dp[pasta][1][1] = 1;

        // 모든 날짜에 대해 가능한 경우 계산
        for(int day = 2; day <= N; day++){
            // 파스타가 정해진 경우
            if(pastaArr[day] != 0) setDP(dp, day, pastaArr[day]);
            // 모든 파스타의 경우 계산
            else for(int pasta = 1; pasta <= PASTA; pasta++) setDP(dp, day, pasta);
        }

//        System.out.println(Arrays.toString(pastaArr));
//        for(int[][] d : dp){
//            for(int[] a : d) System.out.println(Arrays.toString(a));
//            System.out.println("--------");
//        }

        int answer = 0;
        for(int pasta = 1; pasta <= PASTA; pasta++){
            for(int count = 1; count < COUNT; count++) answer = (answer + dp[pasta][count][N]) % MOD;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
    // dp 계산 함수
    // - dp : dp 배열
    // - day : 날짜
    // - pasta : 파스타 종류
    private static void setDP(int[][][] dp, int day, int pasta) {
        // 같은 pasta를 먹는 경우
        dp[pasta][2][day] = dp[pasta][1][day-1];
        // 이전과 다른 pasta를 먹는 경우
        for(int past = 1; past <= PASTA; past++){
            if(pasta == past) continue;
            dp[pasta][1][day] = (dp[pasta][1][day]
                    + dp[past][1][day-1]
                    + dp[past][2][day-1])
                    % MOD;
        }
    }
}
