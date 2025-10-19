package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1636
// -
public class _19_Solution_1 {
    static final int MAX = 200;
    public static class Node{
        int s;
        int e;
        public Node(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;

        int[][] dp = new int[N+1][MAX+1];
        int[][] trace = new int[N+1][MAX+1];
        for(int n = 1; n <= N; n++) Arrays.fill(dp[n], 50_000_000);

        List<Node> list = new ArrayList<>();
        list.add(null);
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list.add(new Node(s, e));
        }

        for(int stress = list.get(1).s; stress <= list.get(1).e; stress++) dp[1][stress] = 0;
        for(int n = 2; n <= N; n++){
            Node cur = list.get(n);
            Node past = list.get(n-1);

            for(int pastStress = past.s; pastStress <= past.e; pastStress++){
                for(int curStress = cur.s; curStress <= cur.e; curStress++){
                    int diff = Math.abs(pastStress - curStress);
                    int sum = dp[n-1][pastStress] + diff;

                    if(sum < dp[n][curStress]){
                        dp[n][curStress] = sum;
                        trace[n][curStress] = pastStress;
                    }
                }
            }
           //  System.out.println(Arrays.toString(dp[n]));
        }

        int answerStress = list.get(N).s;
        for(int stress = list.get(N).s; stress <= list.get(N).e; stress++){
            if(dp[N][answerStress] > dp[N][stress]) answerStress = stress;
        }

        sb.append(dp[N][answerStress]).append("\n");

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(answerStress);

        for(int n = N; n > 1; n--){
            answerStress = trace[n][answerStress];
            deque.offerLast(answerStress);
        }
        while(!deque.isEmpty()) sb.append(deque.pollLast()).append("\n");

        // 정답 출력
        System.out.println(sb.toString());
    }
}
