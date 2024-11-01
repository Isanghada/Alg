package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/18223
// - 다익스트라 : 다익스트라를 통해 민준이, 건우 위치에서 다른 지점으로 가는 최소 거리 계산!
//                (민준이 -> 마산 / 민준이 -> 건우 -> 마산)의 거리를 비교하여 동일하다면 가능!
public class _01_Solution_1 {
    // 최대값
    public static final int MAX = 100_000_000;
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int target; // 목표 노드
        int cost;   // 비용
        public Edge(int target, int cost){
            this.target = target;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int V = Integer.parseInt(st.nextToken());   // 정점의 수
        int E = Integer.parseInt(st.nextToken());   // 간선의 수
        int P = Integer.parseInt(st.nextToken());   // 건우의 위치

        // 인접 리스트
        List<Edge>[] adjList = new List[V+1];
        for(int v = 1; v <= V; v++) adjList[v] = new ArrayList<>();

        // 간선 정보 입력
        while(E-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            adjList[A].add(new Edge(B, C));
            adjList[B].add(new Edge(A, C));
        }

        // 민준이 위치에서의 다익스트라
        int[] mjDijkstra = dijkstra(1, V, adjList);
        // 건우 위치에서의 다익스트라
        int[] gwDijkstra = dijkstra(P, V, adjList);

        // 건우를 경유할 수 있는 경우
        if(mjDijkstra[V] == mjDijkstra[P] + gwDijkstra[V]) sb.append("SAVE HIM");
        // 불가능한 경우
        else sb.append("GOOD BYE");

        // 정답 입력
        System.out.println(sb);
    }

    private static int[] dijkstra(int start, int v, List<Edge>[] adjList) {
        int[] dijkstra = new int[v+1];
        Arrays.fill(dijkstra, MAX);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[v+1];

        pq.add(new Edge(start, 0));

        int count = v;
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            // 방문한 정점인 경우 패스
            if(visited[cur.target]) continue;

            dijkstra[cur.target] = cur.cost;
            visited[cur.target] = true;
            count--;
            // 모든 정점의 값을 계산한 경우 종료
            if(count == 0) break;

            for(Edge edge : adjList[cur.target]){
                if(visited[edge.target]) continue;
                Edge next = new Edge(edge.target, cur.cost+edge.cost);
                pq.offer(next);
            }
        }

        return dijkstra;
    }
}
