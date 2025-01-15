package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17088
// - 그래프 탐색 : 루트 노드에서 그래프 탐색을 통해 기가 노드까지 기둥 계산
//                 이후, 다시 기가 노드에서 모든 노드를 탐색하여 가지 계산!
public class _15_Solution_1 {
    // 노드 클래스
    static class Node{
        int num;    // 노드 번호
        int cost;   // 거리(코스트)
        public Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        List<Node>[] adjList = new List[N+1];
        for(int i = 0; i <= N; i++) adjList[i] = new ArrayList<>();

        for(int i = 1; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b,d));
            adjList[b].add(new Node(a,d));
        }

        int[] answers = new int[2];
        boolean[] visited = new boolean[N+1];

        int gigaNode = R;
        visited[R] = true;
        // 자식 노드가 1개인 경우 : 루트 노드가 기가 노드가 아니므로 기가 노드 탐색
        if(adjList[R].size() == 1){
            gigaNode = adjList[R].get(0).num;
            visited[adjList[R].get(0).num] = true;
            answers[0] += adjList[R].get(0).cost;

            while(adjList[gigaNode].size() == 2){
                for(Node child : adjList[gigaNode]){
                    if(visited[child.num]) continue;
                    gigaNode = child.num;
                    visited[gigaNode] = true;
                    answers[0] += child.cost;
                }
            }
        }

        // 덱 초기화
        // - 기가 노드부터 시작
        Deque<Node> deque = new LinkedList<>();
        deque.offerLast(new Node(gigaNode, 0));
        // 가능한 모든 노드를 탐색하여 최대값 계산
        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();
            answers[1] = Math.max(answers[1], cur.cost);

            for(Node child : adjList[cur.num]){
                if(visited[child.num]) continue;
                visited[child.num] = true;
                Node next = new Node(child.num, cur.cost + child.cost);
                deque.offerLast(next);
            }
        }

        // 정답 출력
        sb.append(answers[0]).append(" ").append(answers[1]);
        System.out.println(sb);
    }
}
