package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/13913
// - BFS : 이동 경로를 저장하며 최적의 경로 탐색!
public class _19_Solution_1 {
    // 좌표 클래스
    public static class Point{
        int node;   // 좌표
        int time;   // 시간
        public Point(int node, int time){
            this.node = node;
            this.time = time;
        }
    }
    // 경로 클래스
    public static class Route{
        int time;                   // 시간
        List<Integer> routeList;    // 이동 경로
        public Route(int time){
            this.time = time;
            this.routeList = new ArrayList<>();
        }
        public Route(int time, List<Integer> routeList){
            this.time = time;
            this.routeList = new ArrayList<>();
            this.routeList.addAll(routeList);
        }
        public void setRouteList(List<Integer> routeList) {
            this.routeList.addAll(routeList);
        }
    }
    // 최대 좌표
    public static final int MAX = 100_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 시작 위치
        int K = Integer.parseInt(st.nextToken());   // 도착 위치

        // BFS를 통해 최적 이동 경로 계산
        Route answer = bfs(N, K);

        sb.append(answer.time).append("\n");
        for(int route : answer.routeList) sb.append(route).append(" ");

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static Route bfs(int n, int k) {
        // 덱 초기화
        Deque<Point> deque = new LinkedList<>();
        // 방문 배열
        boolean[] visited = new boolean[MAX+1];
        // 각 지점에서의 이전 이동 경로 초기화
        int[] routes = new int[MAX+1];
        Arrays.fill(routes, -1);

        // 초기값 설정 : n
        deque.offerLast(new Point(n, 0));
        visited[n] = true;
        routes[n] = n;

        Route result = null;
        while(!deque.isEmpty()){
            // 현재 좌표
            Point cur = deque.pollFirst();

            // 도착 지점일 경우 갱신 후 종료
            if(cur.node == k){
                // 경로 계산 : 도착 지점부터 출발 지점까지 경로 계산
                List<Integer> routeList = new ArrayList<>();
                while(k != routes[k]){
                    routeList.add(k);
                    k = routes[k];
                }
                routeList.add(n);
                Collections.reverse(routeList);

                // 정답 갱신
                result = new Route(cur.time, routeList);
                break;
            }

            // 다음 좌표 계산
            // - (-1칸 이동), (1칸 이동), (현재 좌표 2배 이동)
            Point[] nextPoints = new Point[]{
                    new Point(cur.node-1, cur.time+1),
                    new Point(cur.node+1, cur.time+1),
                    new Point(cur.node*2, cur.time+1)
            };

            for(Point next : nextPoints){
                // 좌표 범위를 벗어나거나 이미 방문한 경우 패스
                if(next.node > MAX || next.node < 0 || visited[next.node]) continue;
                deque.offerLast(next);
                visited[next.node] = true;
                routes[next.node] = cur.node;
            }
        }

        return result;
    }
}
