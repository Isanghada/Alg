package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15703
// - 그리디 : 주사위의 숫자 만큼 쌓을 수 있다!
//              1. 주사위 숫자를 기준으로 오름차순 정렬
//              2. 모든 값을 조사하며 사용한 주사위의 수 이상인 값을 가진 주사위만 활용!
//              3. 모든 주사위를 사용할 때까지 반복하여 체크
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 주사위의 수
        int N = Integer.parseInt(in.readLine());
        // pq 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 주사위 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        while(st.hasMoreTokens()) pq.offer(Integer.parseInt(st.nextToken()));

        // 정답 초기화
        int answer = 0;
        while(!pq.isEmpty()){
            // System.out.println(pq.toString()+"===");
            // 주사위 탑의 수 증가
            answer++;

            // 주사위 탑의 높이
            int count = 0;
            // 사용하지 않은 주사위 pq
            PriorityQueue<Integer> remain = new PriorityQueue<>();

            while(!pq.isEmpty()){
                // 현재 주사위의 숫자
                int num = pq.poll();
                // 사용한 주사위의 수 이상인 경우 탑 쌓기에 활용
                if(count <= num) count++;
                // 활용하지 않은 경우 remain에 추가
                else remain.offer(num);
            }
            // pq를 remain으로 갱신 : 사용하지 않은 주사위만 가지도록!
            pq = remain;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
