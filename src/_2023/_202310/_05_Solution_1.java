package _2023._202310;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/81304
// - 비트마스킹을 활용한 다익스트라로 해결.
// - 트랩 활성화 여부를 비트마스킹으로 표현하여 출발 정점에서 차례로 이동하며 최소 이동 거리 탐색
public class _05_Solution_1 {
    // 노드 클래스
    class Node implements Comparable<Node>{
        int vertex; // 정점
        int time;   // 이동 시간
        int state;  // 비트마스킹

        public Node(int vertex, int time, int state){
            this.vertex =vertex;
            this.time = time;
            this.state = state;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    // 입, 출력값 선언
    public static int answer;
    public static int N;
    public static int[][] graph;
    public static Map<Integer, Integer> trapMap;
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        // 정답 초기화
        answer = Integer.MAX_VALUE;
        // 정점의 수
        N = n;
        // 트랩 정보
        // - key : 정점
        // - value : 비트마스킹에서의 인덱스
        trapMap = new HashMap<>();
        for(int idx = 0; idx < traps.length; idx++) trapMap.put(traps[idx], idx);

        // 인접 행렬 : roads로 인접 행렬 정리
        graph = new int[n+1][n+1];
        for(int[] g : graph) Arrays.fill(g, Integer.MAX_VALUE);
        for(int i = 0; i <= n; i++) graph[i][i] = 0;
        for(int[] road : roads) graph[road[0]][road[1]] = Math.min(graph[road[0]][road[1]], road[2]);

        // 최소 시간 계산
        getMinTime(start, end);

        // 정답 반환
        return answer;
    }

    // 최소 시간 계산 함수 : 다익스트라 기법 활용
    // - start : 시작 정점
    // - end : 도착 정점
    private void getMinTime(int start, int end) {
        // 우선순위큐를 통해 시간이 가장 작은 노드부터 탐색
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        // 방문 행렬
        // - [v][bitmask] : bitmask 상태에서 v 정점 방문 여부
        boolean[][] visited = new boolean[N+1][1 << trapMap.size()];

        // 초기값 설정
        queue.offer(new Node(start, 0, 0));

        // 우선순위큐가 빌 때가지 반복
        while(!queue.isEmpty()){
            // 최소값 반환
            Node cur = queue.poll();
            // 이미 방문한 경우 패스
            if(visited[cur.vertex][cur.state]) continue;
            // 방문 표시
            visited[cur.vertex][cur.state] = true;
            // 도착지일 경우 정답 반환
            if(cur.vertex == end){
                answer = cur.time;
                return;
            }

            // 현재 정점 함정 여부
            // - true : 함정 활성화
            // - false : 함정 비활성화 || 함정이 아닌 정점
            boolean curTrap = false;
            // 현재 정점에 함정이 있는 경우 트랩 활성화 여부체크
            if(trapMap.containsKey(cur.vertex)) curTrap = (cur.state & 1 << trapMap.get(cur.vertex)) > 0;
            
            // 모든 정점 탐색
            for(int next = 1; next <= N; next++){
                // 현재 정점일 경우 패스
                if(next == cur.vertex) continue;
                
                // 다음 정점 함정 여부
                boolean nextTrap = false;
                // 다음 정점에서의 비트마스킹 체크
                int nextState = cur.state;
                // 다음 정점이 함정일 경우 트랩 활성화 여부, 비트 마스킹 계산
                if(trapMap.containsKey(next)) {
                    int bit = 1 << trapMap.get(next);

                    nextTrap = (cur.state & bit) > 0;

                    if(nextTrap) nextState = cur.state & (~bit);
                    else nextState = cur.state | bit;
                }

                // 현재, 다음 함정 활성봐 여부가 같은 경우
                if(curTrap == nextTrap){
                    // 정방향으로 이동 가능한 경우 새로운 노드 추가
                    if(graph[cur.vertex][next] != Integer.MAX_VALUE) queue.offer(new Node(next, cur.time+graph[cur.vertex][next], nextState));
                // 현재, 다음 함정 활성봐 여부가 다른 경우
                }else{
                    // 역방향으로 이동 가능한 경우 새로운 노드 추가
                    if(graph[next][cur.vertex] != Integer.MAX_VALUE) queue.offer(new Node(next, cur.time+graph[next][cur.vertex], nextState));
                }
            }
        }
    }
}
