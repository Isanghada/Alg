package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23843
// - 우선순위큐를 사용해 충전시간이 최소인 콘센트에서 충전
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());   // 기기의 수
        int M = Integer.parseInt(st.nextToken());   // 콘센트의 수

        // 기기별 충전 시간 리스트
        Integer[] devices = Arrays.stream(in.readLine().split(" ")).map(Integer::new).toArray(Integer[]::new);
        // 내림차순 정렬
        Arrays.sort(devices, Collections.reverseOrder());

        // 우선순위큐 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 콘센트의 수 만큼 추가!
        for(int i = 0; i < M; i++) pq.offer(0);

        // 모든 기기를 차례로 충전!
        for(int time : devices){
            // 충전 시간이 최소인 곳에서 충전
            int cur = pq.poll() + time;
            pq.add(cur);
        }
        // 가장 오래 걸린 경우를 찾기 위해 1개만 남기고 모두 제거
        while(pq.size() != 1) pq.poll();

        // 정답 반환
        sb.append(pq.poll());
        System.out.println(sb);
    }
}
