package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/2302
// - DP
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());

        Deque<Integer> deque = new LinkedList<>();
        while(M-- > 0) deque.offerLast(Integer.parseInt(in.readLine()));

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= 40; i++) dp[i] = dp[i-1] + dp[i-2];

        int start = 0;
        int answer = 1;
        while(!deque.isEmpty()){
            int vip = deque.pollFirst();
            answer *= dp[vip - start - 1];
            start = vip;
        }
        answer *= dp[N - start];

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());

    }
}
