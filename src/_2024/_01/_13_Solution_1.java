package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27896
// - 그리디 : 순서대로 배급하며 불만도가 기준을 넘어서면 만족도가 큰 순서대로 가지 배급
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 학생의 수
        int M = Integer.parseInt(st.nextToken());   // 불만족 기준

        // 학생 만족도 배열
        int[] X = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 우선순위 큐 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;   // 불만족 총합
        int answer = 0; // 가지 배급 개수
        
        // 학생 순서대로 배급
        for(int x : X){
            sum += x;       // 불만족 증가!
            pq.offer(x);    // 만족도 추가

            // 기준 이상인 경우 : 현재까지의 최대값부터 가지 배급
            while(sum >= M) {
                sum -= 2 * pq.poll();
                answer++;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
