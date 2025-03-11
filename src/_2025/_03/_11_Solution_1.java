package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25565
// - BFS : 너비 우선 탐색을 통해 각 동맹별 힘 계산!
//         CTP, 한솔 왕국을 제외한 나머지 왕국 중 힘이 강한 왕국부터 차례로 동맹 체결!
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 왕국의 수
        int M = Integer.parseInt(st.nextToken());   // 동맹 관계의 수

        // 인접 리스트 초기화
        List<Integer>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();
        
        // 동맹 관계 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            adjList[X].add(Y);
            adjList[Y].add(X);
        }

        st = new StringTokenizer(in.readLine());
        int ctp = Integer.parseInt(st.nextToken());     // CTP 왕국 번호
        int hansol = Integer.parseInt(st.nextToken());  // 한솔 왕국 번호
        int k = Integer.parseInt(st.nextToken());       // 추가 동맹 기회

        // 방문 배열
        boolean[] visited = new boolean[N+1];
        // - CTP 왕국의 힘
        int answer = bfs(adjList, visited, ctp);

        // 한솔 왕국의 힘 체크
        bfs(adjList, visited, hansol);
        
        // pq 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // 각 동맹의 힘 체크
        for(int n = 1; n <= N; n++){
            if(visited[n]) continue;
            pq.offer(bfs(adjList, visited, n));
        }

        // 가능한 최대 동맹 체결!
        while(!pq.isEmpty() && k > 0) {
            answer += pq.poll();
            k--;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static int bfs(List<Integer>[] adjList, boolean[] visited, int target) {
        int count = 0;

        if(visited[target]) return count;

        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(target);
        visited[target] = true;

        while(!deque.isEmpty()){
            count++;

            int cur = deque.pollFirst();
            for(int next : adjList[cur]){
                if(visited[next]) continue;
                deque.offerLast(next);
                visited[next] = true;
            }
        }

        return count;
    }
}
