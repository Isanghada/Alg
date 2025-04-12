package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13549
// - BFS : 각 좌표에서 이동 가능한 좌표를 탐색하며 체크 
public class _12_Solution_1 {
    // 최대 좌표
    static final int MAX = 200_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken());   // 시작 좌표
        int end = Integer.parseInt(st.nextToken());     // 끝 좌표 

        // DP 초기화
        int[] dp = new int[MAX+1];
        Arrays.fill(dp, MAX);

        // 덱 초기화
        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(start);
        dp[start] = 0;

        while(!deque.isEmpty()){
            // 현재 좌표
            int point = deque.pollFirst();

            if(point > 0) {
                // 순간 이동
                for (int next = point + point; next <= MAX; next += next) {
                    if (dp[next] != MAX) continue;
                    deque.offerLast(next);
                    dp[next] = dp[point];
                }

                // -1 이동
                if (dp[point-1] == MAX) {
                    deque.offerLast(point-1);
                    dp[point-1] = dp[point]+1;
                }
            }

            // +1 이동
            if (point < MAX && dp[point+1] == MAX) {
                deque.offerLast(point+1);
                dp[point+1] = dp[point]+1;
            }
        }

        // 정답 출력
        sb.append(dp[end]);
        System.out.println(sb.toString().trim());
    }
}
