package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5972
// - 다익스트라 : 1번 지점에서 모든 지점으로 가는 최소값을 계산하여 N까지의 최소값 확인
public class _01_Solution_1 {
    // 도로 정보를 담을 클래스
    private static class Road implements Comparable<Road>{
        int v;  // 정점
        int w;  // 비용
        public Road(int v, int w){
            this.v = v;
            this.w = w;
        }
        // 비용 기준 내림차순 정렬
        @Override
        public int compareTo(Road o) {
            return this.w - o.w;
        }
    }
    public static Map<Integer, List<Road>> adjList;
    public static final int LIMIT = 100000000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());   // 지역의 수
        int M = Integer.parseInt(st.nextToken());   // 도로의 수

        // 인접 리스트 생성
        adjList = new HashMap<>();
        for(int i = 1; i <= N; i++) adjList.put(i, new ArrayList<>());

        // 도로 정보 추가 : 양방향
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());   // 지역 a
            int b = Integer.parseInt(st.nextToken());   // 지역 b
            int c = Integer.parseInt(st.nextToken());   // 비용 c

            adjList.get(a).add(new Road(b, c));
            adjList.get(b).add(new Road(a, c));
        }

        // dp 초기화 : 모든 비용 LIMIT으로 초기화
        int[] dp = new int[N+1];
        Arrays.fill(dp, LIMIT);

        // 1번 지점에서 시작하므로 0으로 초기화
        dp[1] = 0;
        // 우선순위 큐 활용
        PriorityQueue<Road> pq = new PriorityQueue<>();
        // 초기 설정 추가
        pq.offer(new Road(1, 0));
        // 우선순위 큐가 빌 때까지 반복
        while(!pq.isEmpty()){
            // 현재 최소값 반환
            Road cur = pq.poll();
            // 최소값으로 변경!
            dp[cur.v] = Math.min(dp[cur.v], cur.w);

            // 인접 지역 탐색
            for(Road r : adjList.get(cur.v)){
                // 비용 계산
                int nextW = cur.w+r.w;
                // 새로운 비용이 더 클 경우 패스
                if(dp[r.v] <= nextW) continue;
                // 새로운 도로 추가!
                Road next = new Road(r.v, nextW);
                pq.offer(next);
            }
        }

        // 정답 입력
        sb.append(dp[N]);
        System.out.println(sb);
    }
}
