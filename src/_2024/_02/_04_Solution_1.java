package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1240
// - BFS : 인접 리스트를 활용한 BFS로 각 정점에서 다른 정점 사이의 거리 계산
public class _04_Solution_1 {
    // 인접 노드 클래스
    public static class Node{
        int node;       // 도착 노드
        int distance;   // 거리
        public Node(int node, int distance){
            this.node = node;
            this.distance = distance;
        }
        @Override
        public String toString(){
            return "[ node = "+this.node+", distance = "+this.distance+"]";
        }
    }
    public static int[][] nodeToNode;               // 인접 배열
    public static Map<Integer, List<Node>> adjList; // 인접 리스트
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 노드의 개수
        int M = Integer.parseInt(st.nextToken());   // 거리를 알고 싶은 노드의 쌍

        nodeToNode = new int[N+1][N+1]; // 노드 사이의 거리 배열
        adjList = new HashMap<>();      // 인접 리스트 초기화
        for(int node = 1; node <= N; node++) adjList.put(node, new ArrayList<>());

        // 트리 정보 입력
        for(int idx = 1; idx < N; idx++){
            st = new StringTokenizer(in.readLine());
            int node1 = Integer.parseInt(st.nextToken());       // 노드1
            int node2 = Integer.parseInt(st.nextToken());       // 노드2
            int distance = Integer.parseInt(st.nextToken());    // 거리

            // 양방향 연결
            adjList.get(node1).add(new Node(node2, distance));
            adjList.get(node2).add(new Node(node1, distance));
        }

//        System.out.println(adjList);

        // 노드별 거리 계산
        for(int node = 1; node <= N; node++) nodeToNode[node] = bfs(node, N);

        // 노드 쌍 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());   // 시작 노드
            int end = Integer.parseInt(st.nextToken());     // 도착 노드

            // 거리 출력
            sb.append(nodeToNode[start][end]).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    // 최대 거리
    private static final int MAX = 100000000;
    // bfs 함수
    private static int[] bfs(int start, int n) {
        // 노드간 거리 배열 초기화
        int[] result = new int[n+1];
        Arrays.fill(result, MAX);

        // 덱 초기화 : 시작 노드 추가
        Deque<Node> deque = new LinkedList<>();
        deque.offerLast(new Node(start, 0));

        while(!deque.isEmpty()){
            // 현재 노드 반환
            Node cur = deque.pollFirst();

            // 노드간 거리 변경
            result[cur.node] = cur.distance;

            // 연결된 다른 노드 정보 추가
            for(Node next : adjList.get(cur.node)){
                // 이미 연결된 노드는 패스
                if(result[next.node] != MAX) continue;
                // 새로운 노드로 이동하는 정보 추가
                deque.offerLast(new Node(next.node, cur.distance + next.distance));
            }
        }

        return result;
    }
}
