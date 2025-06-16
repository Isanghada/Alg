package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23741
// - BFS : BFS를 통해 가능한 위치 탐색!
public class _16_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        List<Integer>[] adjList = new List[N+1];
        for(int n = 0; n <= N; n++) adjList[n] = new ArrayList<>();

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            adjList[v1].add(v2);
            adjList[v2].add(v1);
        }

        Set<Integer> answerSet = getCandidate(adjList, X, Y);
        if(answerSet.size() == 0) sb.append(-1);
        else for(int v : answerSet) sb.append(v).append(" ");

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static Set<Integer> getCandidate(List<Integer>[] adjList, int start, int step) {
        Set<Integer> set = new TreeSet<>();
        boolean[][] dp = new boolean[adjList.length][2];
        set.add(start);

        int type = step % 2;

        for(int d = 1; d <= step; d++){
            Set<Integer> nextSet = new TreeSet<>();
            int flag = (d % 2);
            for(int v : set){
                for(int next : adjList[v]) {
                    if(dp[next][flag]) continue;

                    dp[next][flag] = true;
                    nextSet.add(next);
                }
            }
            set = nextSet;
        }

        set.clear();
        for(int v = 1; v < adjList.length; v++) if(dp[v][type]) set.add(v);

        return set;
    }
}
