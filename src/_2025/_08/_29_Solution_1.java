package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14172
// - BFS : 각 소에서 도달 가능한 경우를 인접 리스트로 만들어 BFS로 확인
public class _29_Solution_1 {
    // 노드 클래스
    static class Node{
        int x;  // x 좌표
        int y;  // y 좌표
        int p;  // 무전기 거리
        public Node(int x, int y, int p){
            this.x = x;
            this.y = y;
            this.p = p;
        }
        // 거리 계산 메서드
        // - 노드 a, 노드 b의 거리 계산
        // - p가 정수이므로 (a, b)의 거리를 올림하여 반환
        static int distance(Node a, Node b){
            double distance = Math.sqrt(pow2(a.x - b.x) + pow2(a.y-b.y));
            return (int)Math.ceil(distance);
        }

        private static double pow2(int target) {return target * target;}
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 소의 수
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        // 소 정보
        Node[] cows = new Node[N];
        // 인접 리스트
        List<Integer>[] adjList = new List[N];
        // 소의 정보 입력
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            adjList[i] = new ArrayList<>();
            cows[i] = new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            // 인접 리스트 체크
            for(int j = 0; j < i; j++){
                int d = Node.distance(cows[j], cows[i]);

                if(cows[i].p >= d) adjList[i].add(j);
                if(cows[j].p >= d) adjList[j].add(i);
            }
        }

        // 정답 초기화
        int answer = 1;
        // 모든 소에서 체크
        for(int i = 0; i < N; i++){
            // 방문 배열 초기화
            boolean[] visited = new boolean[N];
            visited[i] = true;

            // 덱 초기화
            Deque<Integer> deque = new LinkedList<>();
            deque.offerLast(i);

            // 소의 수 초기화
            int count = 1;

            // BFS를 통해 소의 수 계산
            while(!deque.isEmpty()){
                int cur = deque.pollFirst();

                for(int next : adjList[cur]){
                    if(visited[next]) continue;

                    count++;
                    visited[next] = true;
                    deque.offerLast(next);
                }
            }
            // 정답 갱신
            answer = Math.max(answer, count);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}