package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13910
// - DP : 2개의 웍까지 사용할 수 있으므로, 가능한 사이즈를 Set으로 중복제거하여 활용!
//        각 사이즈별로 가능한 횟수 차례로 계산!
public class _01_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] S = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
        Set<Integer> sSet = new HashSet<>();
        for(int a = 0; a < M; a++){
            sSet.add(S[a]);
            for(int b = a+1; b < M; b++) {
                sSet.add(S[a]+S[b]);
            }
        }

        int[] dp = new int[N+1];
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        for(int s : sSet){
            for(int size = s; size <= N; size++){
                dp[size] = Math.min(dp[size], dp[size-s]+1);
            }
        }

        // 정답 입력
        sb.append(dp[N] == 100000 ? -1 : dp[N]);
        System.out.println(sb);
    }
}
