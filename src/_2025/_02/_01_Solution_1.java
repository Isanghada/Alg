package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13902
// - DP : 1. 가능한 웍의 크기 계산 -> 중복 제거를 위해 Set 활용
//           - 동시에 2개까지 활용가능하므로 이중 for문으로 처리
//        2. 짜장면 개수에 따라 최소 요리 횟수 계산
public class _01_Solution_1 {
    // 최대값
    static final int MAX = 100_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 짜장면의 수
        int M = Integer.parseInt(st.nextToken());   // 웍의 수


        // 주어진 웍의 정보
        int[] S = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 가능한 웍의 크기 Set : S를 활용해 가능한 모든 웍의 크기 계산
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < M; i++){
            set.add(S[i]);
            for(int j = i+1; j < M; j++) set.add(S[i] + S[j]);
        }

        // DP 초기화
        // - dp[n] : n개의 짜장면을 만들기 위해 필요한 최소 요리 횟수
        int[] dp = new int[N+1];
        Arrays.fill(dp, MAX);
        // - 짜장면 0개는 0번으로 설정
        dp[0] = 0;
        
        // - 가능한 모든 웍의 크기 활용
        for(int s : set){
            // 짜장면의 수를 차례로 체크
            for(int n = 0; n < N; n++){
                // 타겟 개수(N)을 초과할 경우 종료
                if(n + s > N) break;
                // - 불가능한 개수인 경우 패스
                if(dp[n] == MAX) continue;

                // 최소 요리 횟수 갱신
                dp[s+n] = Math.min(dp[s+n], dp[n]+1);
            }
        }

        // 정답 입력
        sb.append(dp[N] == MAX ? -1 : dp[N]);
        System.out.println(sb);
    }
}
