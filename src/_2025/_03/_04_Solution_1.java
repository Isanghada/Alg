package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1595
// - DFS : 2번의 DFS를 통해 트리의 지름(가장 먼 도시의 거리) 계산
public class _04_Solution_1 {
    // 간선 클래스
    static class Edge{
        int city;   // 도착 도시
        long cost;  // 도로 길이
        public Edge(int city, long cost){
            this.city = city;
            this.cost = cost;
        }
    }
    // 최대 도시의 수
    static final int MAX = 10_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = null;


        // 연결 리스트 초기화
        List<Edge>[] edgeList = new List[MAX+1];
        for(int i = 1; i <= MAX; i++) edgeList[i] = new ArrayList<>();

        // 도로 정보 입력
        while(true){
            String line = in.readLine();

            // 빈 줄이거나 입력이 없는 경우 종료
            if( line == null || line.isEmpty()) break;
            st = new StringTokenizer(line);

            // 도로 정보 추가
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            System.out.println(city1+", "+city2+", "+cost);

            edgeList[city1].add(new Edge(city2, cost));
            edgeList[city2].add(new Edge(city1, cost));
        }

        // 1번 도시가 있는 경우
        // - 1번 기준으로 가장 먼 도시 계산 : start
        // - start 기준으로 가장 먼 도시 계산 : end
        if(!edgeList[1].isEmpty()){
            Edge start = getMostDistantCity(1, edgeList);
            Edge end = getMostDistantCity(start.city, edgeList);
            sb.append(end.cost);
        // 1번 도시가 없는 경우 : 도시가 없으므로 0 반환
        }else sb.append(0);

        // 정답 반환
        System.out.println(sb);
    }

    private static Edge getMostDistantCity(int start, List<Edge>[] edgeList) {
        Edge result = new Edge(start, 0);

        Deque<Edge> deque = new LinkedList<>();
        boolean[] visited = new boolean[MAX+1];

        deque.offerLast(new Edge(start, 0));
        visited[start] = true;

        while(!deque.isEmpty()){
            Edge cur =  deque.pollLast();

            if(result.cost < cur.cost) result = cur;

            for(Edge e : edgeList[cur.city]){
                if(visited[e.city]) continue;

                Edge next = new Edge(e.city, e.cost+cur.cost);

                deque.offerLast(next);
                visited[next.city] = true;
            }
        }

        return result;
    }
}
