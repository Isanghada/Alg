package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25391
// - 그래프 : 1. 그래프 탐색(깊이 or 너비)를 통해 한 그룹에 속하는지 확인
//            2. 오일러 경로 조건(차수가 홀수인 경우가 0 또는 2)를 만족하는지 확인
public class _25_Solution_1 {
    static List<Integer>[] adjList; // 간선 리스트
    static boolean[] visited;       // 방문 배열
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int V = Integer.parseInt(st.nextToken());   // 정점의 수
        int E = Integer.parseInt(st.nextToken());   // 간선의 수

        adjList = new List[V+1];
        visited = new boolean[V+1];
        // 간선 정보 입력 : 양방향
        for(int v = 1; v <= V; v++) adjList[v] = new ArrayList<>();
        while(E-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 그룹의 수
        int count = 0;
        for(int v = 1; v <= V; v++){
            if(visited[v]) continue;
            bfs(v);
            count++;
        }
        
        // 그룹이 여러개인 경우 불가능하므로 NO 출력
        if(count != 1) sb.append("NO");
        else{
            // 차수가 홀수인 경우 체크
            int odd = 0;
            for(int v = 1; v <= V; v++) if((adjList[v].size() & 1) == 1) odd++;

            // 차수가 홀수인 경우가 0 또는 2인 경우 가능
            if(odd == 0 || odd == 2) sb.append("YES");
            // 아닐 경우 불가능
            else sb.append("NO");
        }

        // 결과 반환
        System.out.println(sb);
    }

    private static void bfs(int v) {
        Deque<Integer> deque = new LinkedList<>();

        deque.offerLast(v);
        visited[v] = true;
        while(!deque.isEmpty()){
            int cur = deque.pollFirst();

            for(int next : adjList[cur]){
                if(visited[next]) continue;
                deque.offerLast(next);
                visited[next] = true;
            }
        }
    }
}
