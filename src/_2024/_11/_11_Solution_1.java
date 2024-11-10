package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/
// - 스택 : 스카이라인 상태를 스택으로 관리하며 최소 건물 개수 계산
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 고도 변환 지점 개수
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        // 덱 초기화 : 초기값 0 추가
        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(0);

        // 정답 초기화
        int answer = 0;
        // 고도 변환 확인
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());   // x 좌표
            int y = Integer.parseInt(st.nextToken());   // y 좌표

            // 새로운 y좌표가 마지막 스카이라인보다 작은 경우 제거!
            // - y좌표보다 높은 좌표의 개수만큼 건물이 있다는 의미
            while(y < deque.peekLast()){
                answer++;
                deque.pollLast();
            }
            // y좌표가 마지막 스카이라인보다 클 경우 추가!
            // - 같은 경우 이어진 건물로 인시해야함
            if(y > deque.peekLast()) deque.offerLast(y);
        }

        // 남은 스카이라인 개수만큼 증가 : 초기값 0은 제외!
        answer += deque.size() - 1;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
