package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/24230
// - BFS : 루트부터 차례로 색을 칠하며 횟수 계산
public class _11_Solution_1 {
    public static int N;    // 정점의 수
    public static int[] COLOR;  // 색 정보
    public static Map<Integer, Set<Integer>> adjList;   // 연결 정보
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());    // 정점 개수 입력
        // 색 정보 입력
        COLOR = Arrays.stream(("0 "+in.readLine()).split(" ")).mapToInt(Integer::new).toArray();

        // 연결 정보 초기화
        adjList = new HashMap<>();
        for(int node = 1; node <= N; node++) adjList.put(node, new HashSet<>());

        // 연결 정보 입력
        StringTokenizer st = null;
        for(int i = 1; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 부모-자식 관계를 알 수 없으므로 양방향으로 연결
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }


        // 정답 출력
        // - 최소 색칠 횟수 계산
        sb.append(getMinCount());
        System.out.println(sb);
    }
    // 노드 정보 클래스
    private static class Node{
        int node;   // 노드 번호
        int color;  // 노드 색깔
        public Node(int node, int color){
            this.node = node;
            this.color = color;
        }
        @Override
        public String toString(){
            return "[ node = "+ node+", color = "+color+" ]";
        }
    }
    // 최소 색칠 계산 함수 : BFS를 통해 루트부터 차례로 탐색
    private static int getMinCount() {
        // 횟수 초기화
        int count = 0;

        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 방문 정보 초기화
        boolean[] visited = new boolean[N+1];

        // 초기값 설정 : 1번 노드부터 시작
        deque.offerLast(new Node(1, 0));
        visited[1] = true;

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 노드 반환
            Node cur = deque.pollFirst();
//            System.out.println(cur+", "+COLOR[cur.node]);

            // 색깔 정보가 다른 경우 색칠!
            if(cur.color != COLOR[cur.node]){
                count++;    // 횟수 증가
                cur.color = COLOR[cur.node];    // 색 변경
            }

            // 자식 노드 탐색
            for(int child : adjList.get(cur.node)){
                // 이미 방문한 경우 패스!
                if(visited[child]) continue;
                // 다음 노드 입력
                Node next = new Node(child, cur.color);

                // 덱에 추가
                deque.offerLast(next);
                // 방문 체크
                visited[child] = true;
            }
        }

        // 횟수 반환
        return count;
    }
}
