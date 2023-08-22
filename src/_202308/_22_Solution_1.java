package _202308;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72416?language=java
// - 오..전혀 모르겠는걸?
// - 다시봐야 할 것 1순위.
// - dp를 사용하는 것 까진 이해했지만..어렵네
public class _22_Solution_1 {
    public static int[][] dp;
    public static int[] extra;
    public static int[] staticSales;
    public static List<Integer>[] adjList;
    public int solution(int[] sales, int[][] links) {
        int lenSales = sales.length+1;

        dp = new int[lenSales][2];
        extra = new int[lenSales];
        staticSales = sales;
        adjList = new ArrayList[lenSales];

        for(int i = 0; i < lenSales; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int[] link : links){
            adjList[link[0]].add(link[1]);
        }

        getMinCost(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    private void getMinCost(int index) {
        dp[index][0] = 0;
        dp[index][1] = staticSales[index - 1];

        if(adjList[index].size() == 0) return;

        int minCost = Integer.MAX_VALUE;

        for(int child: adjList[index]){
            getMinCost(child);

            if(dp[child][0] < dp[child][1]){
                dp[index][0] += dp[child][0];
                dp[index][1] += dp[child][0];

                minCost = Math.min(minCost, dp[child][1] - dp[child][0]);
            }else{
                dp[index][0] += dp[child][1];
                dp[index][1] += dp[child][1];

                minCost = 0;
            }
        }
        dp[index][0] += minCost;
    }
}
