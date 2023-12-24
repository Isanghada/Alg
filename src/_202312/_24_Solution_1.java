package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14217
// - 다익스트라 : 1번 도시(수도) 기준으로 각 도시로 가는 거리 계산
public class _24_Solution_1 {
    // N : 도시의 수
    // M : 초기 도로의 수
    public static int N, M;
    // adjSet : 도로 연결 상태
    public static Map<Integer, Set<Integer>> adjSet;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 도시의 수
        M = Integer.parseInt(st.nextToken());   // 도로의 수

        // 도로 정보 입력
        adjSet = new HashMap<>();
        for(int node = 1; node <= N; node++) adjSet.put(node, new HashSet<>());

        // 도로는 양방향이므로 양쪽 모두 추가
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            adjSet.get(node1).add(node2);
            adjSet.get(node2).add(node1);
        }

        int Q = Integer.parseInt(in.readLine());    // 도로 정비 계획의 수
        
        
        while(Q-- > 0){
            // 정비 계획 입력
            st = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(st.nextToken());    // 도로 정비 계획
            int node1 = Integer.parseInt(st.nextToken());   // 도시1
            int node2 = Integer.parseInt(st.nextToken());   // 도시2

            // 1인 경우 도로 생성
            if(type == 1) makeStreet(node1, node2);
            // 2인 경우 도로 제거
            else deleteStreet(node1, node2);

            // 정비된 도로 상태로 다익스트라 실행! : 1번 도시(수도) 기준
            sb.append(getDijkstra());
        }

        // 정답 반환
        System.out.println(sb);
    }
    // 도시 정보를 담을 클래스
    private static class Point implements Comparable<Point>{
        int city;   // 도시 번호
        int count;  // 경로 길이
        public Point(int city, int count){
            this.city = city;
            this.count = count;
        }
        // 경로 길이 기준 오름차순 정렬
        @Override
        public int compareTo(Point o){
            return this.count - o.count;
        }
    }

    // 다익스트라 함수 : 1번 도시(수도) 기준 다익스트라 실행 후 결과 반환
    private static String getDijkstra() {
        StringBuilder sb = new StringBuilder();

        // 다익스트라 초기화 : -1(연결되지 않은 경우)
        int[] dijkstra = new int[N+1];
        Arrays.fill(dijkstra, -1);

        // 우선순위 큐 초기화
        PriorityQueue<Point> pq = new PriorityQueue<>();

        // 초기값 설정 : 1번 도시
        pq.offer(new Point(1, 0));

        // 우선순위 큐가 빌 때까지 반복
        while(!pq.isEmpty()){
            // 현재 도시 반환
            Point cur = pq.poll();

            // 처음 방문한 도시인 경우
            if(dijkstra[cur.city] == -1){
                // 경로 길이 업데이트
                dijkstra[cur.city] = cur.count;

                // 연결된 도시 방문!
                for(int node : adjSet.get(cur.city)){
                    // 이미 방문한 경우 패스
                    if(dijkstra[node] != -1) continue;

                    // 우선순위 큐에 새로운 도시 정보 추가
                    pq.offer(new Point(node, cur.count+1));
                }
            }
        }

        // 다익스트라 결과 생성!
        for(int node = 1; node <= N; node++)  sb.append(dijkstra[node]).append(" ");
        sb.append("\n");

        // 결과 반환
        return sb.toString();
    }

    // 도로 삭제 함수 : 연결된 도로 삭제
    private static void deleteStreet(int node1, int node2) {
        adjSet.get(node1).remove(node2);
        adjSet.get(node2).remove(node1);
    }

    // 도로 생성 함수 : 새로운 도로 생성
    private static void makeStreet(int node1, int node2) {
        adjSet.get(node1).add(node2);
        adjSet.get(node2).add(node1);
    }
}
