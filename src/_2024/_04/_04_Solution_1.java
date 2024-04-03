package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23254
// - 그리디 : 점수 상승 폭이 큰 과목부터 차례로 공부하는 방식으로 계산
public class _04_Solution_1 {
    // 노드 클래스 : 과목별 정보
    public static class Node implements Comparable<Node>{
        int score;  // 현재 점수
        int plus;   // 점수 상승 폭
        public Node(int score, int plus){
            this.score = score;
            this.plus = plus;
        }
        // 점수 상승 폭 기준 오름차순 정렬
        @Override
        public int compareTo(Node o){
            return o.plus - this.plus;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = 24 * Integer.parseInt(st.nextToken());  // 공부 가능한 시간
        int M = Integer.parseInt(st.nextToken());        // 과목의 수

        // 과목 기본 점수 입력
        st = new StringTokenizer(in.readLine());
        int[] A = new int[M];
        for(int i = 0; i < M; i++) A[i] = Integer.parseInt(st.nextToken());

        // 점수 상승 정보 기준으로 pq 입력
        st = new StringTokenizer(in.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < M; i++){
            // 100점을 넘어가면 안되므로 최대 100점이 되게끔 점수 상승 정보 중 최소값 계산
            int plus = Math.min(100 - A[i], Integer.parseInt(st.nextToken()));
            pq.offer(new Node(A[i], plus));
        }

        // 모든 시간을 다 사용하거나 모든 점수가 100점이 될 때까지 반복
        while(N > 0 && pq.peek().score < 100){
            // 점수 상승 정보가 가장 큰 과목 선택
            Node cur = pq.poll();

            // 현재 plus로 공부할 수 있는 최대 시간 계산
            int n = Math.min((100 - cur.score) / cur.plus, N);
            // 점수 상승
            cur.score += cur.plus * n;
            // plus 갱신
            cur.plus = Math.min(100 - cur.score, cur.plus);
            // pq에 다시 추가
            pq.offer(cur);

            // 공부할 수 있는 시간 갱신
            N -= n;
        }

        // 과목의 점수 합 계산
        int answer = 0;
        while(!pq.isEmpty()) answer += pq.poll().score;

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
