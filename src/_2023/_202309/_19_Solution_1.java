package _2023._202309;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/1837
// - 해설 참고 : https://tech.kakao.com/2017/09/13/code-festival-round-2/
// - DP 활용 : 각 시간마다의 위치를 다른 지역으로 바꾸며 최소 경로 탐색
public class _19_Solution_1 {
    // 인접 리스트
    public static Map<Integer, List<Integer>> adjList;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        // 인접 리스트 초기화
        adjList = new HashMap<>();
        for(int node = 1; node <= n; node++) {
            adjList.put(node, new ArrayList<>());
            // 자기 자신으로 돌아가는 경로 추가
            adjList.get(node).add(node);
        }

        // 경로 추가 : 양방향이므로 모든 방향으로 추가
        for(int[] edge : edge_list){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        // dp 초기화
        // - dp[i][j] : i인덱스의 경로를 j로 바꿀 때 최소 변경 횟수
        int[][] dp = new int[k][n+1];
        // - 최대값으로 초기화
        for(int i = 0; i < gps_log.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 시작 지점 설정
        dp[0][gps_log[0]] = 0;
        // - t : 경로를 변경할 시간
        for(int t = 1; t < k; t++){
            // - j : 시작 위치
            for(int j = 1; j <= n; j++){
                // - t-1 시간에 j 위치로 가는 경우가 되는 경우가 있을 경우
                if(dp[t-1][j] != Integer.MAX_VALUE){
                    // [t-1][j]에서 j에서 인접한 [t][v]로 이동하는 최소 변경 횟수 계산
                    for(int v : adjList.get(j)){
                        // - 기존 위치에서 변경되는 경우만 변경 횟수 추가
                        dp[t][v] = Math.min(dp[t][v], dp[t-1][j] + (gps_log[t] == v ? 0 : 1));
                    }
                }
            }
        }
        // 마지막 시간 좌표의 값 반환
        // - 최대값일 경우 : 불가능한 경우 -1
        int answer = dp[k-1][gps_log[k-1]];
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
