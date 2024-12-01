package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13905
// - 최소 스패닝 트리 : 출발 지점에서 가장 무게제한이 높은 간선을 선택하며 진행!
//                      이때, 무게 제한은 (이전까지의 무게 제한, 새로운 무게 제한) 중 최소값 선택 
public class _01_Solution_1 {
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int node;   // 연결 노드
        int weight; // 무게 제한
        public Edge(int node, int weight){
            this.node = node;
            this.weight = weight;
        }
        // 무게 제한 기준 내림차순 정렬
        @Override
        public int compareTo(Edge e){
            return e.weight - this.weight;
        }
    }
    // 무게 제한 최대값
    public static final int MAX = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 집의 수
        int M = Integer.parseInt(st.nextToken());   // 다리의 수

        st = new StringTokenizer(in.readLine());
        int S = Integer.parseInt(st.nextToken());   // 출발 노드
        int E = Integer.parseInt(st.nextToken());   // 도착 노드

        // 인접 리스트 : 다리 정보 입력
        List<Edge>[] adjList = new List[N+1];
        for(int i = 0; i <= N; i++) adjList[i] = new ArrayList<>();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[node1].add(new Edge(node2, weight));
            adjList[node2].add(new Edge(node1, weight));
        }

        // 정답 입력
        // - 빼뺴로 최대 개수 반환
        sb.append(getMaxCount(adjList, S, E));
        System.out.println(sb);
    }

    private static int getMaxCount(List<Edge>[] adjList, int s, int e) {
        // 빼빼로 최대 개수 초기화
        int result = 0;

        // 우선순위 큐 초기화
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 방문 배열 초기화
        boolean[] visited = new boolean[adjList.length];

        // 출발 노드 설정
        pq.offer(new Edge(s,  MAX));
        while(!pq.isEmpty()){
            // 현재 간선 반환
            Edge cur = pq.poll();
            // 도착지인 경우 무게 제한 갱신 후 종료
            if(cur.node == e) {
                result = cur.weight;
                break;
            }
            // 현재 노드 방문 체크
            visited[cur.node] = true;
            // 연결된 다리 확인
            for(Edge adjEdge : adjList[cur.node]){
                // 이미 방문한 경우 패스
                if(visited[adjEdge.node]) continue;
                // 새로운 간선 생성
                // - (현재까지의 무게 제한, 새로운 무게 제한) 중 최소값 선택
                Edge next = new Edge(adjEdge.node, Math.min(adjEdge.weight, cur.weight));
                // 우선순위 큐에 추가
                pq.offer(next);
            }
        }

        return result;
    }
}
