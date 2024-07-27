package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14284
// - 다익스트라 : 다익스트라 알고리즘을 통해 시작 지점인 s에서 모든 정점으로 가는 최소 비용 계산!
public class _28_Solution_1 {
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int node;   // 연결된 정점
        int cost;   // 비용
        public Edge(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Edge){
                Edge e = (Edge) obj;
                if(this.node == e.node && this.cost == e.node){
                    return true;
                }else return false;
            }
            return super.equals(obj);
        }
        @Override
        public int hashCode() {
            return Objects.hash(this.node, this.cost);
        }
        // 비용 기준 오름차순 정렬!
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점의 수
        int M = Integer.parseInt(st.nextToken());   // 간선의 수

        // 인접 리스트!
        Map<Integer, Set<Edge>> adjMap = new HashMap<>();
        for(int node = 1; node <= N; node++) adjMap.put(node, new HashSet<>());

        // 간선 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjMap.get(a).add(new Edge(b, c));
            adjMap.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(in.readLine());
        int S = Integer.parseInt(st.nextToken());   // 시작 정점
        int T = Integer.parseInt(st.nextToken());   // 끝 정점

        // 정답 출력
        // - 다익스트라를 통해 S에서 모든 정점으로 가는 최소 비용 계산!
        sb.append(dijkstra(S, T, N, adjMap));
        System.out.println(sb);
    }

    private static int dijkstra(int s, int t, int n, Map<Integer, Set<Edge>> adjMap) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(s, 0));

        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, 50000000);
        dist[s] = 0;

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(visited[cur.node]) continue;
            visited[cur.node] = true;

            for(Edge next : adjMap.get(cur.node)){
                int newCost = cur.cost + next.cost;
                if(dist[next.node] > newCost){
                    dist[next.node] = newCost;
                    pq.add(new Edge(next.node, dist[next.node]));
                }
            }
        }

        return dist[t];
    }
}
