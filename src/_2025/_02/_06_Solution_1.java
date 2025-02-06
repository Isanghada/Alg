package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/15661
// - BFS : BFS를 통해 팬을 만나지 않고 리프 노드까지 이동할 수 있는지 확인
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점의 수
        int M = Integer.parseInt(st.nextToken());   // 간선의 수

        // 인접 리스트 초기화
        List<Integer>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();

        // 간선 정보 입력 : 방향 그래프
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjList[u].add(v);
        }

        // 팬의 수
        int S = Integer.parseInt(in.readLine());
        
        // 팬 위치 정보 입력
        Set<Integer> fanClub = new HashSet<>();
        fanClub.addAll(Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));

        // 정답 출력
        // - bfs를 통해 팬을 만나지 않고 여행을 끝낼 수 있는지 확인
        sb.append(bfs(N, 1, adjList, fanClub));
        System.out.println(sb);
    }

    private static String bfs(int n, int start, List<Integer>[] adjList, Set<Integer> fanClub) {
        // 시작 정점에 팬이 위치하지 않은 경우 BFS 진행
        if(!fanClub.contains(start)){
            // 덱 초기화
            Deque<Integer> deque = new LinkedList<>();
            // 방문 배열 초기화
            boolean[] visited = new boolean[n+1];

            // 초기값 설정 : start
            deque.offerLast(start);
            visited[start] = true;

            // 덱이 빌 때까지 반복
            while(!deque.isEmpty()){
                // 현재 노드 반환
                int node = deque.pollFirst();

                // 리프 노드인 경우 종료 : yes
                // - 이동할 수 없는 경우
                if(adjList[node].isEmpty()) return "yes";

                // 다음 정점 탐색
                for(int next : adjList[node]){
                    // 이미 방문했거나 팬이 위치한 경우 패스
                    if(visited[next] || fanClub.contains(next)) continue;

                    // 정점 추가
                    deque.offerLast(next);
                    visited[next] = true;
                }
            }
        }
        // 팬을 만나는 경우 : Yes
        return "Yes";
    }
}
