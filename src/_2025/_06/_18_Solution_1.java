package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24229
// - 그리디 : 판자의 정보를 체크하고, 가능한 모든 경우 탐색!
//              1. 연결할 수 있는 판자 모두 연결
//              2. 좌표 0부터 시작하여 이동할 수 있는 판자 탐색!
public class _18_Solution_1 {
    // 노드 클래스 : 판자
    static class Node implements Comparable<Node>{
        int start;  // 시작
        int end;    // 끝
        int jump;   // 점프 범위
        public Node(int start, int end){
            this.start = start;
            this.end = end;
            this.jump = this.end - this.start;
        }
        // 끝 지점 갱신
        public void updateEnd(int end){
            this.end = end;
            this.jump = this.end - this.start;
        }
        // 최대 이동 좌표
        public int getMove(){
            return this.end + jump;
        }
        public Node copy(){
            return new Node(this.start, this.end);
        }
        @Override
        public int compareTo(Node o){
            int diff = Integer.compare(this.start, o.start);
            return diff == 0 ? Integer.compare(this.end, o.end) : diff;
        }
        @Override
        public String toString(){
            return "[ start="+this.start+", end="+this.end+", jump="+this.jump+" ]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 판자 개수
        int N = Integer.parseInt(in.readLine());

        // 판자 정보 입력
        Node[] nodes = new Node[N];
        StringTokenizer st = null;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(in.readLine());
            nodes[n] = new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 판자 정렬!
        Arrays.sort(nodes);

        // 판자 연결
        Deque<Node> deque = new LinkedList<>();
        deque.offerLast(nodes[0].copy());

        for(int n = 1; n < N; n++){
            Node cur = deque.peekLast();

            // 새로운 판자 시작이 현재 판자 범위 내일 경우 연결
            if(cur.start <= nodes[n].start && nodes[n].start <= cur.end){
                cur.updateEnd(Math.max(cur.end, nodes[n].end));
            // 아닐 경우 새로운 판자로 추가
            }else deque.offerLast(nodes[n].copy());
        }

        // System.out.println(deque);

        // 이동 가능 경우의 판자 정보!
        Deque<Node> moveDeque = new LinkedList<>();
        moveDeque.offerLast(deque.pollFirst());

        // 정답 초기화
        int answer = 0;
        while(!moveDeque.isEmpty()){
            Node cur = moveDeque.pollFirst();
            // 정답 갱신
            answer = Math.max(answer, cur.end);

            // 이동 가능한 판자 추가!
            int move = cur.getMove();
            while(!deque.isEmpty() && deque.peekFirst().start <= move) moveDeque.offerLast(deque.pollFirst());
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
