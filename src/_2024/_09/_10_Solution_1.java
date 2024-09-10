package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1967
// - 그래프 탐색 : 총 2번의 그래프 탐색을 통해 노드 간 가장 먼 거리 계산!
//                  1. 임의의 노드에서 가장 먼 노드 탐색 => 가장 먼 노드 A
//                  2. 노드 A에서 가장 먼 노드 탐색! => 이 경우가 노드 간 가장 먼 거리인 경우!
public class _10_Solution_1 {
    // 간선 클래스
    public static class Edge{
        int node;   // 도착 노드
        int weight; // 가중치
        public Edge(int node, int weight){
            this.node = node;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 노드 개수
        int N = Integer.parseInt(in.readLine());

        // 간선 리스트!
        List<Edge>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();

        // 간선 정보 입력 : 부모-자식 관계이지만, 결국 양방향 그래프!
        StringTokenizer st = null;
        for(int n = 1; n < N; n++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[a].add(new Edge(b, w));
            adjList[b].add(new Edge(a, w));
        }

        // 1번 노드에서 가장 먼 노드 계산
        int[] startNode = getMaxWeight(adjList, 1);
//        System.out.println(Arrays.toString(startNode));

        // startNode에서 가장 먼 노드 계산
        int[] endNode = getMaxWeight(adjList, startNode[0]);
//        System.out.println(Arrays.toString(endNode));

        // 정답 출력
        sb.append(endNode[1]);
        System.out.println(sb.toString());
    }
    // 가장 먼 노드 탐색 함수 : BFS를 통해 가장 먼 노드 탐색!
    private static int[] getMaxWeight(List<Edge>[] adjList, int start) {
        // 가장 먼 노드 초기화
        int[] node = new int[] {start, 0};

        // 덱, 방문 배열 초기화
        Deque<int[]> deque = new LinkedList<>();
        boolean[] visited = new boolean[adjList.length];

        // 초기값 설정
        deque.offerLast(new int[]{start, 0});
        visited[start] = true;

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 노드 반환!
            int[] cur = deque.pollFirst();
            // 거리가 멀 경우 갱신!
            if(node[1] < cur[1]) node = cur;

            // 연결된 노드 탐색!
            for(Edge edge : adjList[cur[0]]){
                // 이미 방문한 경우 패스
                if(visited[edge.node]) continue;

                // 새로운 노드 정보 추가!
                int[] next = new int[]{edge.node, cur[1]+edge.weight};
                deque.offerLast(next);
                visited[next[0]] = true;
            }
        }

        return node;
    }
}
