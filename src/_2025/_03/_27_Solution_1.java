package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12786
// - DP : 첫 나무부터 이동할 수 있는 구멍에서 차례로 탐색
//        1. 다음 나무의 아무위치로 이동(T 기능)
//        2. O, A, B, C 기능을 활용해 이동
//        3. 각 계산시에는 T기능 활용 최소값 갱신!
public class _27_Solution_1 {
    static final int TREE = 20;     // 나무 최대 높이
    static final int MAX = 100_000; // 최대 T 활용 횟수
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());    // 나무 개수
        int K = Integer.parseInt(in.readLine());    // T 기능 제한

        // dp 초기화
        int[][] dp = new int[N+1][TREE+1];
        for(int i =0 ;i <= N; i++) Arrays.fill(dp[i], MAX);
        // - 높이 1로 시작
        dp[0][1] = 0;

        // 나무 정보 입력
        List<Integer>[] treeList= new List[N+1];
        for(int i = 0; i<= N; i++) treeList[i] = new ArrayList<>();
        treeList[0].add(1);

        StringTokenizer st = null;
        for(int i = 1; i<= N; i++){
            st = new StringTokenizer(in.readLine());
            int M = Integer.parseInt(st.nextToken());

            while(M-- > 0) treeList[i].add(Integer.parseInt(st.nextToken()));
        }

        // 모든 위치에서 탐색
        for(int n = 0; n < N; n++){
            // n번 나무의 모든 구멍에서 탐색
            for(int h : treeList[n]){
                if(h > TREE) continue;
                // commandT : 어디로든 이동하는 T 기능 활용
                // - 제한 횟수 미만인 경우!
                if(dp[n][h] < K){
                    for(int next = 1; next <= TREE; next++){
                        dp[n+1][next] = Math.min(dp[n+1][next], dp[n][h]+1);
                    }
                }
                // 이동 기능을 활용해 이동가능한 위치 계산
                commandO(dp, n, h);
                commandA(dp, n, h);
                commandB(dp, n, h);
                commandC(dp, n, h);
            }
             System.out.println(Arrays.toString(dp[n]));
        }
         System.out.println(Arrays.toString(dp[N]));
        
        // 정답 초기화
        int answer = MAX;
        // 마지막 나무의 구멍 탐색
        for(int h : treeList[N]) answer = Math.min(answer, dp[N][h]);

        // 정답 반환
        sb.append(answer != MAX ? answer : -1);
        System.out.println(sb);
    }

    private static void commandC(int[][] dp, int n, int h) {
        if(h > 1){
            dp[n+1][h-1] = Math.min(dp[n+1][h-1], dp[n][h]);
        }
    }

    private static void commandB(int[][] dp, int n, int h) {
        int nextH = Math.min(h*2, TREE);
        dp[n+1][nextH] = Math.min(dp[n+1][nextH], dp[n][h]);
    }

    private static void commandA(int[][] dp, int n, int h) {
        if(h < TREE){
            dp[n+1][h+1] = Math.min(dp[n+1][h+1], dp[n][h]);
        }
    }

    private static void commandO(int[][] dp, int n, int h) {
        dp[n+1][h] = Math.min(dp[n+1][h], dp[n][h]);
    }
}
