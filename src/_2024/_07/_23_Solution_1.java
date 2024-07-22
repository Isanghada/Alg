package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

// https://www.acmicpc.net/problem/2294
// - DP : 가능한 동전으로 차례로 계산
//        같은 동전이 여러 번 주어질 수 있으므로, Set으로 중복 제거!
public class _23_Solution_1 {
    public static final int MAX = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 동전의 종류
        int K = Integer.parseInt(st.nextToken());   // 타겟 가치

        // 코인 정보 입력
        Set<Integer> coins = new TreeSet<>();
        while(N-- > 0) coins.add(Integer.parseInt(in.readLine()));

        // DP 초기화
        // - dp[target] : target원이 될 때 필요한 최소 동전의 수!
        int[] dp = new int[K+1];
        for(int i = 1; i <= K; i++) dp[i] = MAX;

        // 가능한 동전으로 차례로 탐색
        for(int coin : coins){
            for(int target = coin; target <= K; target++) dp[target] = Math.min(dp[target], dp[target-coin]+1);
        }

        // 정답 출력
        sb.append((dp[K] == MAX) ? -1 : dp[K]);
        System.out.println(sb);
    }
}
