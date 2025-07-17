package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32187
// - DP : 각 음식별로 얻을 수 있는 행복도의 합이 최대가 되도록 탐색!
public class _17_Solution_1 {
    static class Node{
        int p;
        long v;
        public Node(int p, long v){
            this.p = p;
            this.v = v;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 음식 종류
        int M = Integer.parseInt(st.nextToken());   // 학생 수

        // dp 초기화 : 음식별로 따로 계산 후 최대값 계산
        // - dp[type][n] : n번 음식 배식 여부에 따른 최대 행복도
        //      - type : 배식 여부 (0-배식X, 1-배식O)
        //      - n : 음식 번호
        long[][] dp = new long[2][N+1];
        List<Node> pastList = new ArrayList<>();
        for(int m = 1; m <= M; m++){
            st = new StringTokenizer(in.readLine());

            List<Node> curList = new ArrayList<>();

            // 최대 음식 개수
            int count = Integer.parseInt(st.nextToken());
            for(int i = 0; i < count; i++) {
                int p = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                dp[1][p] = dp[0][p] + v;
                curList.add(new Node(p, dp[1][p]));
            }
            for(Node past : pastList) dp[0][past.p] = Math.max(dp[0][past.p], past.v);
            pastList = curList;
//            System.out.println(Arrays.toString(dp[0]));
//            System.out.println(Arrays.toString(dp[1]));
//            System.out.println("----");
        }

        // 정답 계산
        long answer = 0;
        for(int n = 1; n <= N; n++) answer += Math.max(dp[0][n], dp[1][n]);

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
