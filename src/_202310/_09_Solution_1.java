package _202310;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/68937
// - BFS 활용! : 노드 간의 거리가 가장 먼 것을 찾는 것!
// - BFS를 최대 3번 사용
//   - 아무 노드에서 가장 먼 노드(v1) 탐색
//   - v1 기준 가장 먼 노드(v2) 탐색
//   - v2 기준 가장 먼 노드 탐색
public class _09_Solution_1 {
    // 가장 먼 노드들을 담을 클래스
    public class Node{
        List<Integer> list; // 가장 먼 노드들의 리스트
        int depth;  // 거리

        public Node(List<Integer> list, int depth){
            this.list = list;
            this.depth = depth;
        }
    }
    public static int N;    // 정점의 수
    public static Map<Integer, Set<Integer>> adjList;   // 인접 리스트
    public int solution(int n, int[][] edges) {
        // 입력
        N = n;
        // 인접 리스트 생성
        adjList = new HashMap<>();
        for(int v = 1; v <= N; v++) adjList.put(v, new HashSet<>());
        for(int[] edge : edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        // 기준 정점 탐색 : 1번 노드에서 가장 먼 노드
        int v1 = getMaxDepth(1).list.get(0);
        
        // v1 기준 가장 먼 노드들의 리스트 탐색
        Node result1 = getMaxDepth(v1);
        // 가장 먼 거리가 2개 이상인 경우 거리 반환
        if(result1.list.size() > 1) return result1.depth;
        // 1개인 경우 해당 정점을 기준으로 가장 먼 정점 탐색
        Node result2 = getMaxDepth(result1.list.get(0));
        // 가장 먼 거리가 2개 이상이 경우 거리 반환
        if(result2.list.size() > 1) return result2.depth;
        // 아닐 경우 거리의 -1 반환
        else return result2.depth - 1;
    }

    // BFS 함수
    // - start : 시작 정점
    private Node getMaxDepth(int start) {
        // 반환값 초기화
        Node result = null;
        List<Integer> vertexList = new ArrayList<>();
        int depth = 0;

        // 덱 초기화
        Deque<int[]> deque = new LinkedList<>();
        // 방문 행렬
        boolean[] visited = new boolean[N+1];

        // 초기값 설정 : start 정정부터 시작
        deque.offerLast(new int[] {start, 0});
        visited[start] = true;

        // 덱이 빌때까지 반복
        while(!deque.isEmpty()){
            // 현재 정점 반환
            int[] cur = deque.pollFirst();

            // 거리가 멀 경우 새로운 값으로 변경
            if(depth != cur[1]) {
                vertexList = new ArrayList<>();
                depth = cur[1];
            }
            // 정점 리스트에 추가
            vertexList.add(cur[0]);

            // 인접 리스트에서 이동 가능한 정점 탐색
            for(int next : adjList.get(cur[0])){
                // 방문한 경우 패스
                if(visited[next]) continue;

                // 새로운 값을 덱에 추가
                deque.offerLast(new int[] {next, cur[1]+1});
                visited[next] = true;
            }
        }

        result = new Node(vertexList, depth);
        return result;
    }
}
