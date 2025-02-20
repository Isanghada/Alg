package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.acmicpc.net/problem/12852
// - DP : 정수 N에 대해 3가지 연산을 모두 실행하여 각 결과의 최소 연산인 경우 선택!
public class _20_Solution_1 {
    // 최대값
    static final int MAX = 3_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 시작값
        int N = Integer.parseInt(in.readLine());

        // DP 초기화
        int[] dp = new int[N+1];
        Arrays.fill(dp, MAX);
        dp[N] = 0;

        // 연산 순서 초기화
        int[] order = new int[N+1];
        order[N] = N;

        for(int n = N; n > 1; n--){
            orderDiv(dp, order, n, 3);      // 3으로 나누기
            orderDiv(dp, order, n, 2);      // 2로 나누기
            orderMinus(dp, order, n, 1);    // 1로 빼기
        }

        // 순서 리스트!
        List<Integer> answerList = new ArrayList<>();

        // 1부터 역순으로 연산 순서 탐색
        int num = 1;
        if(dp[num] != MAX){
            while(true){
                answerList.add(num);
                if(num == order[num]) break;
                num = order[num];
            }
        }

        sb.append(dp[1]).append("\n");
        for(int i = answerList.size()-1; i >= 0; i--) sb.append(answerList.get(i)).append(" ");

        // 정답 출력
        System.out.println(sb);
    }

    private static void orderMinus(int[] dp, int[] order, int n, int d) {
        if(n - d > 0){
            int next = n - d;
            if(dp[next] > (dp[n] + 1)) {
                dp[next] = dp[n] + 1;
                order[next] = n;
            }
        }
    }

    private static void orderDiv(int[] dp, int[] order, int n, int d) {
        if(n % d == 0){
            int next = n / d;
            if(dp[next] > (dp[n] + 1)) {
                dp[next] = dp[n] + 1;
                order[next] = n;
            }
        }
    }
}
