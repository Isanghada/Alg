package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23324
// - 그래프 : 간선의 가중치가 1인 경우를 찾고 각 정점(U, V)으로 이동하는 최소값 계산
//            (U, V)의 최소값이 0인 경우 모든 정점 쌍의 최단 거리의 합 0 반환
//            아닐 경우 U에 속하는 정점의 수와 V에 속하는 정점의 수의 곱 반환
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();

        int[] group = new int[N+1];
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjList[u].add(v);
            adjList[v].add(u);
            if(i == K){
                group[u] = 1;
                group[v] = -1;
                deque.offerLast(u);
                deque.offerLast(v);
            }
        }

        if(isZero(adjList, deque.peekFirst(), deque.peekLast())) sb.append(0);
        else{
            long groupA = 1;
            long groupB = 1;
            while(!deque.isEmpty()){
                int cur = deque.pollFirst();

                for(int next : adjList[cur]){
                    if(group[next] != 0) continue;

                    if(group[cur] == 1) groupA++;
                    else groupB++;
                    group[next] = group[cur];
                    deque.offerLast(next);
                }
            }
            sb.append(groupA * groupB);
        }


        // 정답 반환
        System.out.println(sb);
    }

    private static boolean isZero(List<Integer>[] adjList, int start, int end) {
        Deque<Integer> deque = new LinkedList<>();
        boolean[] visited = new boolean[adjList.length];

        visited[start] = true;
        for(int next : adjList[start]){
            if(next == end) continue;

            deque.offerLast(next);
            visited[next] = true;
        }

        while(!deque.isEmpty()){
            int cur = deque.pollFirst();

            for(int next : adjList[cur]){
                if(visited[next]) continue;

                deque.offerLast(next);
                visited[next] = true;
            }
        }

        return visited[end];
    }
}
