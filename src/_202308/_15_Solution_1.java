package _202308;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/132266
// 플로이드 와샬 알고리즘 : 시간 초과
// BFS를 통해 각 부대원별 최단 거리 계산 : 시간 초과
// 다익스트라 : 도착지를 기준으로 다익스트라 진행. (단순하게만 생각하지 말자)
public class _15_Solution_1 {
    // 인접 리스트
    public static Map<Integer, List<Integer>> adjList;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 정답 초기화
        int[] answer = new int[sources.length];
    
        // 인접 리스트 생성
        adjList = new HashMap<>();
        for(int i = 1; i <= n; i++) adjList.put(i, new ArrayList<>());

        // 도로 연결
        // - 무방향이므로 양방향 모두 추가
        for(int[] r : roads){
            adjList.get(r[0]).add(r[1]);
            adjList.get(r[1]).add(r[0]);
        }
        
        // 다익스트라 초기화
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);

        // 도착지를 기준으로 다익스트라 진행.
        dijkstra(dist, destination);

        // 각 부대원의 이동 거리 입력.
        for(int i = 0; i < sources.length; i++){
            answer[i] = dist[sources[i]];
        }

        return answer;
    }
    
    // 다익스트라 함수
    private void dijkstra(int[] dist, int destination) {
        // deque 초기화
        Deque<Integer> deque = new LinkedList<>();
        
        // 초기값 설정
        deque.offer(destination);
        dist[destination] = 0;
        
        // deque이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 지역 반환
            int cur = deque.poll();
            
            // 인접 리스트를 통해 연결된 지역 확인
            for(int next : adjList.get(cur)){
                // 아직 연결되지 않았거나, 새로운 값이 작을 경우
                // - 값 변경
                // - deque에 새로운 지역 추가
                if(dist[next] == -1 || dist[next] > dist[cur] + 1){
                    dist[next] = dist[cur] + 1;
                    deque.offer(next);
                }
            }
        }
    }
}
