package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14863
// - DP: 각 경로에 도달할 수 있는 모든 경우 계산!
public class _27_Solution_1 {
    public static class Node{
        int time;
        int cost;
        public Node(int time, int cost){
            this.time = time;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1];
        for(int i = 0; i <= N; i++) Arrays.fill(dp[i], -1);
        dp[0][0] = 0;

        for(int n = 1; n <= N; n++){

            st = new StringTokenizer(in.readLine());
            Node[] nodes = new Node[2];
            for(int type = 0; type < 2; type++){
                nodes[type] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            int prev = n-1;
            for(int time = 0; time < K; time++){
                if(dp[prev][time] != -1){
                    for(int type = 0; type < 2; type++){
                        Node next = new Node(time+nodes[type].time, dp[prev][time]+nodes[type].cost);
                        if(next.time > K) continue;
                        dp[n][next.time] = Math.max(dp[n][next.time], next.cost);
                    }
                }
            }
        }

        int answer = 0;
        for(int cost : dp[N]){
            if(cost != -1) answer = Math.max(answer, cost);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
