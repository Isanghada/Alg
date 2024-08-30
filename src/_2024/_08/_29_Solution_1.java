package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1504
// - 다익스트라 : 시작 정점, v1, v2에서의 다익스트라를 구하여
//                가능한 경우 중 최소 비용 선택!
// - 간선의 수가 많아
public class _29_Solution_1 {
    // 노드 클래스
    public static class Node implements Comparable<Node>{
        int v;      // 연결된 정점
        int cost;   // 이동 비용
        public Node(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int hashCode() {
            return Objects.hash(v, cost);
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node){
                Node node = (Node) obj;
                if(this.v == node.v && this.cost == node.cost) return true;
                else return false;
            }else return super.equals(obj);
        }
        @Override
        public int compareTo(Node obj){
            return this.cost - obj.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st= new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점의 수
        int E = Integer.parseInt(st.nextToken());   // 간선의 수

        // 인접 리스트!
        // - 중복된 경우가 있을 수 있어, Set으로 중복 제거
        Set<Node>[] adjSet = new Set[N+1];
        for(int v = 1; v <= N; v++) adjSet[v] = new HashSet<>();

        while(E-- > 0){
            // 간선 정보
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());   // 정점 a
            int b = Integer.parseInt(st.nextToken());   // 정점 b
            int c = Integer.parseInt(st.nextToken());   // 비용

            // 양방향 연결
            adjSet[a].add(new Node(b, c));
            adjSet[b].add(new Node(a, c));
        }

        // 경유하는 정점!
        st = new StringTokenizer(in.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 시작 지점의 다익스트라
        int[] startDijkstra = dijkstra(1, adjSet);
        // 경유 정점1의 다익스트라!
        int[] v1Dijkstra = dijkstra(v1, adjSet);
        // 경유 정점2의 다익스트라
        int[] v2Dijkstra = dijkstra(v2, adjSet);

        // 2가지 경우 중 최소값 선택!
        int answer = Math.min(
                startDijkstra[v1]+v1Dijkstra[v2]+v2Dijkstra[N],
                startDijkstra[v2]+v1Dijkstra[N]+v2Dijkstra[v1]
        );

        // 정답 반환
        // - 도달할 수 없는 경우 -1 반환, 도달할 수 있는 경우 정답 반환
        sb.append(answer >= 300_000_000 ? -1 : answer);
        System.out.println(sb);
    }

    private static int[] dijkstra(int node, Set<Node>[] adjSet) {
        int[] dijkstra = new int[adjSet.length];
        Arrays.fill(dijkstra, 300_000_000);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[adjSet.length];

        pq.offer(new Node(node, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.v]) continue;

            dijkstra[cur.v] = cur.cost;
            visited[cur.v] = true;

            for(Node adjNode : adjSet[cur.v]){
                if(visited[adjNode.v] || cur.cost+adjNode.cost >= 300000000) continue;

                Node next = new Node(adjNode.v, cur.cost+adjNode.cost);
                pq.offer(next);
            }
        }

        return dijkstra;
    }
}