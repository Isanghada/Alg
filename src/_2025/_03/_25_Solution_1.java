package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14615
// - BFS : 정방향 그래프, 역방향 그래프를 활용해
//         1번 도시에서 다른 도시로 이동하는 경우, 다른 도시에서 N번 도시로 이동하는 경우 체크 => 배열
//         각 테스트케이스마다 가능한지 확인하여 출력
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수 
        int M = Integer.parseInt(st.nextToken());   // 튜브의 수

        List<Integer>[] adjList = new List[N+1];        // 정방향 인접 리스트
        List<Integer>[] reverseAdjList = new List[N+1]; // 역방향 인접 리스트
        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            reverseAdjList[i] = new ArrayList<>();
        }
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList[start].add(end);
            reverseAdjList[end].add(start);
        }

        // 정방향 인접 리스트를 활용해 1번 도시에서 다른 도시로 이동하는 경우 체크
        boolean[] visitedStart = bfs(1, adjList, N);
        // 역방향 인접 리스트를 활용해 다른 도시에서 N번 도시로 이동하는 경우 체크
        boolean[] visitedEnd = bfs(N, reverseAdjList, N);

//        System.out.println(Arrays.toString(visitedStart));
//        System.out.println(Arrays.toString(visitedEnd));

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 반물질 폭탄 위치
            int C = Integer.parseInt(in.readLine());

            // 1번->C번, C번->N번이 가능한지 체크
            if(visitedStart[C] && visitedEnd[C]) sb.append("Defend the CTP\n");
            else sb.append("Destroyed the CTP\n");
        }

        // 결과 반환
        System.out.println(sb.toString().trim());
    }

    private static boolean[] bfs(int start, List<Integer>[] adjList, int n) {
        boolean[] visited = new boolean[n+1];
        Deque<Integer> deque = new LinkedList<>();

        visited[start] = true;
        deque.offerLast(start);

        while(!deque.isEmpty()){
            int cur = deque.pollFirst();

            for(int next : adjList[cur]){
                if(visited[next]) continue;

                visited[next] = true;
                deque.offerLast(next);
            }
        }

        return visited;
    }
}
