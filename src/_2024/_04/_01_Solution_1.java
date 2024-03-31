package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/15591
// - BFS : BFS를 통해 모든 경우 탐색
public class _01_Solution_1 {
    // 노드 클래스 : 각 동영상에 연관된 영상 정보
    public static class Node{
        int node;   // 연결된 영상 번호
        int usado;  // 유사도
        public Node(int node, int uasdo){
            this.node = node;
            this.usado = uasdo;
        }
        @Override
        public String toString(){
            return "[ node="+node+", usado="+usado+" ]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 영상의 수
        int Q = Integer.parseInt(st.nextToken());   // 질문의 수

        // 인접 리스트 초기화
        Map<Integer, List<Node>> map = new HashMap<>();
        for(int i = 1; i <= N; i++) map.put(i, new ArrayList<>());
        // 인접 리스트 입력 : 양방향이므로 두 영상에 모두 추가
        for(int i = 1; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(st.nextToken());   // 영상 번호 1
            int q = Integer.parseInt(st.nextToken());   // 영상 번호 2
            int r = Integer.parseInt(st.nextToken());   // 유사도
            map.get(p).add(new Node(q, r));
            map.get(q).add(new Node(p, r));
        }

//        for(int i = 1; i <= N; i++) System.out.println("node="+i+", "+map.get(i));

        // 질문 답변 출력
        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());   //  기준값
            int v = Integer.parseInt(st.nextToken());   //  시작 영상

            // 덱 초기화
            Deque<Integer> deque = new LinkedList<>();
            // 방문 배열 초기화
            boolean[] visited = new boolean[N+1];

            // 초기값 설정 : v번 영상으로 설정
            deque.offer(v);
            visited[v] = true;

            // 추천될 영상의 수
            int count = 0;
            // 덱이 빌 때까지 반복
            while(!deque.isEmpty()){
                // 현재 영상 번호
                int node = deque.pollFirst();
                // 인접 리스트를 통해 연결된 영상 확인
                for(Node next : map.get(node)){
                    // 이미 추천되었거나 k미만의 유사도인 경우 패스
                    if(visited[next.node] || next.usado < k) continue;
                    
                    // 덱에 영상 추가
                    deque.offer(next.node);
                    // 방문 체크
                    visited[next.node] = true;
                    // 카운트 증가
                    count++;
                }
            }
            // 추천될 영상의 수 반환
            sb.append(count).append("\n");
        }

        // 정답 입력
        System.out.println(sb);
    }
}
