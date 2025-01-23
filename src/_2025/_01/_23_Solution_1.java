package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/22865
// - 다익스트라 : 친구의 위치 3개에서 다익스트라를 통해 다른 지점으로 이동하는 최소값 계산
//                모든 위치를 탐색하여 가장 먼 곳의 땅 번호 출력
public class _23_Solution_1 {
    // 간선 클래스
    static class Edge implements Comparable<Edge>{
        int node;   // 도착 위치
        int cost;   // 비용
        public Edge(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
        // 비용 기준 오름차순, 위치 기준 오름차순
        @Override
        public int compareTo(Edge e){
            if(this.cost == e.cost) return this.node - e.node;
            return this.cost - e.cost;
        }
    }
    static final int MAX = 2_000_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] friends = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Edge>[] edgeList = new List[N+1];
        for(int n = 1; n <= N; n++) edgeList[n] = new ArrayList<>();

        StringTokenizer st = null;
        int M = Integer.parseInt(in.readLine());
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            edgeList[D].add(new Edge(E, L));
            edgeList[E].add(new Edge(D, L));
        }

        int[] dijkstraA = dijkstra(friends[0], edgeList, N);
        int[] dijkstraB = dijkstra(friends[1], edgeList, N);
        int[] dijkstraC = dijkstra(friends[2], edgeList, N);

        int answer = 0;
        int minDistance = 0;
        for(int n = 1; n <= N; n++){
            int min = min(dijkstraA[n], dijkstraB[n], dijkstraC[n]);
            if(minDistance < min){
                answer = n;
                minDistance = min;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private static int[] dijkstra(int friend, List<Edge>[] edgeList, int n) {
        int[] dijkstra = new int[n+1];
        Arrays.fill(dijkstra, MAX);
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        dijkstra[friend] = 0;
        pq.offer(new Edge(friend, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            if(dijkstra[cur.node] < cur.cost) continue;

            for(Edge edge : edgeList[cur.node]){
                Edge next = new Edge(edge.node, edge.cost + cur.cost);
                if(dijkstra[next.node] > next.cost){
                    dijkstra[next.node] = next.cost;
                    pq.offer(next);
                }
            }
        }

        return dijkstra;
    }
}
