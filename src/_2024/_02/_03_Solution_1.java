package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1916
// - 다익스트라 : 시작 도시에서 다른 모든 도시로 이동하는 최소 비용 계산
public class _03_Solution_1 {
    // 버스 클래스
    public static class Bus implements Comparable<Bus>{
        int city;   // 도착 도시
        int cost;   // 비용
        public Bus(int city, int cost){
            this.city = city;
            this.cost = cost;
        }
        // 비용 기준 오름 차순 정렬
        @Override
        public int compareTo(Bus o){
            return this.cost - o.cost;
        }
        @Override
        public String toString(){
            return "[ city = "+city+", cost = "+cost+" ]";
        }
    }
    public static Map<Integer, List<Bus>> adjMap;   // 인접 리스트
    public static int[] dijkstra;                   // 다익스트라
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());    // 도시의 수
        int M = Integer.parseInt(in.readLine());    // 버스의 수

        // 인접 리스트 초기화
        adjMap = new HashMap<>();
        for(int city = 1; city <= N; city++) adjMap.put(city, new LinkedList<>());

        StringTokenizer st = null;
        // 버스 입력 : 방향 그래프!
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());   // 시작 도시
            int end = Integer.parseInt(st.nextToken());     // 도착 도시
            int cost = Integer.parseInt(st.nextToken());    // 비용

            adjMap.get(start).add(new Bus(end, cost));
        }

        st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken());   // 시작 도시
        int end = Integer.parseInt(st.nextToken());     // 도착 도시

        // 다익스트라 초기화
        dijkstra = new int[N+1];
        Arrays.fill(dijkstra, Integer.MAX_VALUE);

        // 우선순위 큐 쵝화
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        // 시작 도시 추가!
        pq.offer(new Bus(start, 0));
        // 다익스트라 계산
        while(!pq.isEmpty()){
            // 최소 비용인 경우 반환
            Bus cur = pq.poll();

            // 도착하지 못한 도시인 경우
            if(dijkstra[cur.city] == Integer.MAX_VALUE){
                // 다익스트라 업데이트
                dijkstra[cur.city] = cur.cost;

                // 가능한 도시 탐색
                for(Bus next : adjMap.get(cur.city)){
                    // 이미 방문한 경우 패스
                    if(dijkstra[next.city] != Integer.MAX_VALUE) continue;

                    // 새로운 비용이 이전 비용보다 작을 경우 우선순위 큐에 추가
                    if(dijkstra[next.city] > next.cost + cur.cost){
                        pq.offer(new Bus(next.city, next.cost + cur.cost));
                    }
                }
            }
        }

        // 정답 반환
        sb.append(dijkstra[end]);
        System.out.println(sb);
    }
}
