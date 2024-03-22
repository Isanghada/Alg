package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25603
// - 슬라이딩 윈도우 : 우선순위 큐에 각 범위를 차례로 입력하여 최대값 확인
public class _22_Solution_1 {
    // 노드 클래스
    public static class Node implements Comparable<Node>{
        int idx;    // 인덱스
        int number; // 비용
        public Node(int idx, int number){
            this.idx = idx;
            this.number = number;
        }
        // 비용 기준 오름차순 정렬
        @Override
        public int compareTo(Node o){
            return this.number - o.number;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 의뢰의 개수
        int K = Integer.parseInt(st.nextToken());   // 연속 의뢰 거절 상한

        // 의뢰 비용 배열
        int[] missions = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 우선순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 의뢰에서 첫 K범위 입력
        for(int i = 0; i < K; i++) pq.offer(new Node(i, missions[i]));

        // 범위 중 최소값 선택
        int answer = pq.peek().number;
        // 다음 범위를 입력하며 값 확인
        for(int i = K; i < N; i++){
            // 새로운 범위 입력
            int underIdx = i - K + 1;   // 현재 범위의 하한 인덱스
            pq.offer(new Node(i, missions[i])); // 새로운 값 추가
            // 하한 인덱스 미만의 값들은 제거
            while(pq.peek().idx < underIdx) pq.poll();

            // 최대값으로 갱신
            answer = Math.max(answer, pq.peek().number);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
