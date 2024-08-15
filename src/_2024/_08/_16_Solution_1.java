package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20168
// - 다익스트라 : A정점에서 다른 정점으로 가는 모든 경우 계산!
public class _16_Solution_1 {
    // 노드 클래스 : 연결된 도로 정보
    public static class Node{
        int v;      // 도착 정점
        int cost;   // 비용
        public Node(int v, int cost){
            this.v = v;
            this.cost =cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점의 수
        int M = Integer.parseInt(st.nextToken());   // 도로의 수
        int A = Integer.parseInt(st.nextToken());   // 시작 정점
        int B = Integer.parseInt(st.nextToken());   // 도착 정점
        int C = Integer.parseInt(st.nextToken());   // 잔액!

        // 인접 리스트
        List<Node>[] adjList = new List[N+1];
        for(int node = 1; node <= N; node++) adjList[node] = new ArrayList<>();

        // 도로 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());   // 정점
            int b = Integer.parseInt(st.nextToken());   // 정점
            int c = Integer.parseInt(st.nextToken());   // 비용

            // 인접 리스트에 정보 추가
            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        // 다익스트라 계산 : A에서 각 정점으로 가는 최소 비용이 아닌 수치심의 최소값 탐색!
        // - dijkstra[i] : i 정점으로 가는데 필요한 최소 수치심!
        int[] dijkstra = getDijkstra(adjList, A, C, N);

        // 정답 출력
        // - B에 도달할 수 없다면 -1 반환, 도달할 수 있다면 값 반환
        sb.append(dijkstra[B] == 1000000 ? -1 : dijkstra[B]);
        System.out.println(sb.toString());
    }

    private static int[] getDijkstra(List<Node>[] adjList, int a, int c, int n) {
        // 방문 배열
        boolean[][] visited = new boolean[n+1][n+1];
        // 다익스트라 초기화!
        int[]dijkstra = new int[n+1];
        Arrays.fill(dijkstra, 1000000);
        // 우선순위큐 초기화
        // - int[3] : max_cost, total_cost, end
        // - 오름차순 정렬 : max_cost -> total_cost -> end
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int diff1 = o1[0] - o2[0];
                int diff2 = o1[1] - o2[1];
                int diff3 = o1[2] - o2[2];

                if(diff1 != 0) return diff1;
                else if(diff2 != 0) return diff2;
                else return diff3;
            }
        });

        // 초기 정보 입력
        dijkstra[a] = 0;
        pq.add(new int[]{0, 0, a});

        while(!pq.isEmpty()){
            // 현재 정점 반환
            int[] cur = pq.poll();

            // 연결된 정보 입력
            for(Node next : adjList[cur[2]]){
                // 다음 비용 계산
                int nextCost = cur[1] + next.cost;
                // 가진 금액을 넘어서거나 이미 방문한 경우 패스!
                if(nextCost > c || visited[cur[2]][next.v]) continue;

                visited[cur[2]][next.v] = true;
                // 다익스트라 갱신! : 수치심이 최소가 되도록 갱신!
                dijkstra[next.v] = Math.min(dijkstra[next.v], Math.max(cur[0], next.cost));
                pq.offer(new int[]{dijkstra[next.v], nextCost, next.v});
            }
        }


        return dijkstra;
    }
}
