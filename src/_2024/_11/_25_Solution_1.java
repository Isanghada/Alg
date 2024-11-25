package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1197
// - 유니온-파인드 : 유니온 파인드를 활용해 최소 스패닝 트리 구성!
public class _25_Solution_1 {
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int node1;  // 노드
        int node2;  // 노드
        int cost;   // 가중치
        public Edge(int node1, int node2, int cost){
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
        // 가중치 기준 오름차순 정렬
        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int V = Integer.parseInt(st.nextToken());   // 노드의 수
        int E = Integer.parseInt(st.nextToken());   // 간선의 수

        // 부모 노드 초기화
        int[] parents = initParents(V);

        // 우선순위 큐 초기화
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 간선 정보 입력
        while(E-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if(A < B) pq.offer(new Edge(A, B, C));
            else pq.offer(new Edge(B, A, C));
        }

        // 선택된 간선의 수
        int count = 1;
        // 정답 초기화
        int answer = 0;
        // pq가 비지않고, 모든 간선이 선택되지 않은 경우 반복
        while(!pq.isEmpty() && count < V){
            // 간선 반환
            Edge edge = pq.poll();
            // 유니온이 가능하면 answer, count 갱신
            if(union(edge.node1, edge.node2, parents)) {
                answer += edge.cost;
                count++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static boolean union(int node1, int node2, int[] parents) {
        int parent1 = find(node1, parents);
        int parent2 = find(node2, parents);

        if(parent1 == parent2) return false;
        parents[parent2] = parent1;

        return true;
    }

    private static int find(int node, int[] parents) {
        if(node == parents[node]) return node;
        return parents[node] = find(parents[node], parents);
    }

    private static int[] initParents(int v) {
        int[] parents = new int[v+1];
        for(int i = 1; i <= v; i++) parents[i] = i;
        return parents;
    }
}