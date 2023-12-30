package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20951
// - DP : 경로의 경우의 수를 차례로 계산
public class _22_Solution_1 {
    // 경우의 수를 나눌 변수
    public static final int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정점 정보 설정
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점의 수
        int M = Integer.parseInt(st.nextToken());   // 간선의 수

        // DP 초기화
        int[][] dp = new int[8][N+1];
        // 인접 정점을 담을 Map 초기화
        Map<Integer, Set<Integer>> adjSet = new HashMap<>();
        for(int node = 1; node <= N; node++) {
            dp[0][node] = 1;    // 모든 정점은 시작 위치가 될 수 있으므로 1 입력
            adjSet.put(node, new HashSet<>());  // 모든 정점에 Set 초기화ㄴ
        }

        // 간선 정보 입력
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());   // 정점
            int v = Integer.parseInt(st.nextToken());   // 정점

            // 양방향이므로 2가지 경우 모두 추가
            adjSet.get(u).add(v);
            adjSet.get(v).add(u);
        }

        // 모든 경우 탐색!
        // - route : 경로 길이
        // - node : 출발 정점
        // - next : 도착 정점
        for(int route = 0; route < 7; route++){
            for(int node : adjSet.keySet()){
                for(int next : adjSet.get(node)){
                    // 다음 값과 현재 값의 합에 MOD로 나눈 나머지!
                    dp[route+1][next] = (dp[route+1][next] + dp[route][node]) % MOD;
                }
            }
        }

        // 정답 초기화
        int answer = 0;
        // 길이가 7인 경로의 모든 경우 합하여 MOD로 나눈 나머지!
        for(int count : dp[7]) answer = (answer + count) % MOD;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
