package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14699
// - BFS : 해밍 거리가 1인 경우를 인접 노드로 체크하고,
//         해당 값을 활용해 BFS로 경로 계산
public class _26_Solution_1 {
    // 노드 클래스
    public static class Node{
        int node;       // 현재 노드
        String numbers; // 경로
        public Node(int node, String numbers){
            this.node = node;
            this.numbers = numbers;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 노드의 수
        int K = Integer.parseInt(st.nextToken());   // 비트 길이

        // 비트 정보
        String[] bits = new String[N+1];
        // 인접 리스트
        List<Integer>[] adjList = new List[N+1];
        for(int i = 1; i <= N; i++) {
            bits[i] = in.readLine();
            adjList[i] = new ArrayList<>();

            // 새로운 값과 이전 값의 해밍 거리 계산!
            // - 해밍 거리가 1인 경우 adjList에 추가
            for(int j = 1; j < i; j++){
                if(hammingDistance(bits[j], bits[i])){
                    adjList[i].add(j);
                    adjList[j].add(i);
                }
            }
        }

//        for(int i = 0; i < N; i++){
//            System.out.println(bits[i]+", "+adjList[i]);
//        }

        st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken());   // 시작 노드
        int end = Integer.parseInt(st.nextToken());     // 끝 노드

        // bfs를 통해 결과 반환
        sb.append(bfs(adjList, start, end, N));

        // 정답 반환
        System.out.println(sb);
    }

    private static String bfs(List<Integer>[] adjList, int start, int end, int n) {
        Deque<Node> deque = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        deque.offerLast(new Node(start, String.valueOf(start)));
        visited[start] = true;

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();
            if(cur.node == end) return cur.numbers;

            for(int nextNode : adjList[cur.node]){
                if(visited[nextNode]) continue;

                Node next = new Node(nextNode, cur.numbers+" "+nextNode);
                deque.offerLast(next);
                visited[nextNode] = true;
            }
        }

        return "-1";
    }

    private static boolean hammingDistance(String bit1, String bit2) {
        int count = 0;
        for(int i=0; i < bit1.length() && count < 2; i++){
            if(bit1.charAt(i) != bit2.charAt(i)) count++;
        }

        return (count == 1) ? true : false;
    }
}
