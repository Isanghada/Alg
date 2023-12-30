package _2023._202308;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/92343
//
public class _21_Solution_1 {
    // 좌표 정보
    public class Point{
        int sheepCount; // 양의 수
        int wolfCount;  // 늑대의 수
        Set<Integer> visited;   // 이동한 좌표 정보

        public Point(int sheepCount, int wolfCount, Set<Integer> visited){
            this.sheepCount = sheepCount;
            this.wolfCount = wolfCount;
            this.visited = visited;
        }
    }

    public static int answer;
    public static List<Integer>[] adjList;
    public int solution(int[] info, int[][] edges) {
        // 정답 초기화 : 루트는 무조건 양이므로 1로 초기화
        answer = 1;
        
        // 인접 리스트 생성 
        adjList = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++) adjList[i] = new ArrayList<>();

        // 인접 리스트 입력
        for(int[] edge: edges){
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }

        // 최대 양의 수 계산
        getMaxCount(info);

        return answer;
    }

    // 최대 양의 수 계산 함수 : BFS를 통해 계산 진행.
    // info : 정점 별 동물 정보
    private void getMaxCount(int[] info) {
        // 덱 생성
        Deque<Point> deque = new LinkedList<>();

        // 초기 이동 좌표 생성
        Set<Integer> set = new HashSet<>();
        set.add(0);

        // 덱 초기값 설정
        deque.offer(new Point(1, 0, set));

        // 덱이 빌때까지 반복
        while(!deque.isEmpty()){
            // 현재 값 반환
            Point cur = deque.poll();

            // 정답을 최대값으로 변경
            answer = Math.max(answer, cur.sheepCount);

            // 모든 정점의 인접 정점으로 이동 진행
            for(int v : cur.visited){
                // 인접 리스트를 통해 모든 정점의 인접 확인
                for(int child : adjList[v]){
                    // 이미 방문했다면 패스.
                    if(cur.visited.contains(child)) continue;

                    // 이동 정보 새로 생성
                    Set<Integer> nextSet = new HashSet<>();
                    nextSet.addAll(cur.visited);
                    nextSet.add(child);

                    // 새로운 곳의 동물이 양일 경우
                    if(info[child] == 0){
                        // 새로운 좌표 생성
                        deque.add(new Point(cur.sheepCount + 1, cur.wolfCount, nextSet));
                    // 새로운 곳의 동물이 늑대일 경우
                    }else{
                        // 양의 수와 비교해 가능한지 확인 : 불가능하면 패스.
                        if(cur.sheepCount - cur.wolfCount <= 1) continue;
                        deque.add(new Point(cur.sheepCount, cur.wolfCount + 1, nextSet));
                    }
                }
            }
        }
    }
}
