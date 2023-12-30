package _2023._202308;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72416?language=java
// - bottom-up 방식의 Tree DP!
// - 매콤한 맛이네요.
public class _22_Solution_1 {
    // dp
    public static int[][] dp;
    // sales static 변환용
    public static int[] staticSales;
    // 인접 리스트
    public static List<Integer>[] adjList;
    public int solution(int[] sales, int[][] links) {
        // sales 길이
        int lenSales = sales.length+1;

        // 설정값 초기화
        dp = new int[lenSales][2];
        staticSales = sales;
        adjList = new ArrayList[lenSales];
        
        // 인접 리스트 초기화
        for(int i = 0; i < lenSales; i++){
            adjList[i] = new ArrayList<>();
        }

        // 인접 리스트 입력 : 단방향이므로 한 방향으로만 추가.
        for(int[] link : links){
            adjList[link[0]].add(link[1]);
        }

        // 최소값 탐색 : 1번(루트)노드 부터 탐색
        // - dp[1][0] : 1번을 포함하지 않는 경우
        // - dp[1][1] : 1번을 포함하는 경우
        getMinCost(1);

        // 1번(루트)를 포함하는 경우 포함하지 않는 경우 중 최소값 반환.
        return Math.min(dp[1][0], dp[1][1]);
    }

    // 최소값 계산 함수 : Bottom-Up 방식의 DP => 모든 정점을 돌며 계산!
    // index : 루트 인덱스.
    private void getMinCost(int index) {
        // 0 : 선택하지 않으므로 0으로 초기화
        // 1 : 선택하는 경우 매출액으로 초기화
        dp[index][0] = 0;
        dp[index][1] = staticSales[index - 1];

        // 리프 노드일 경우 종료
        if(adjList[index].size() == 0) return;

        // 아무도 선택하지 않을 경우를 위한 최소값
        int minCost = Integer.MAX_VALUE;

        // 모든 자식 노드 탐색
        for(int child: adjList[index]){
            // 자식 노드에서의 최소값 계산
            getMinCost(child);

            // 자식을 포함하지 않는 경우
            if(dp[child][0] < dp[child][1]){
                // 값 업데이트.
                dp[index][0] += dp[child][0];
                dp[index][1] += dp[child][0];

                // dp[index][0]인 경우 : 팀장과 팀원 모두 선택하지 않는 경우가 생김
                // 따라서, 그 중 최소값인 팀원 선택해야함.
                // - dp[child][1] - dp[child][0]을 하는 이유는 기본적으로 dp[child][0]을 더했기 때문.
                minCost = Math.min(minCost, dp[child][1] - dp[child][0]);
            // 자식을 포함하는 경우
            }else{
                dp[index][0] += dp[child][1];
                dp[index][1] += dp[child][1];
                
                // 이미 팀원이 최소 1명 포함되었기에 0으로 변경
                minCost = 0;
            }
        }
        // 팀원과 자식 모두를 선택하지 않는 경우 minCost만큼 증가!
        dp[index][0] += minCost;
    }
}
