package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/6198
// - 참고 : https://meojiktard.tistory.com/17
// - 모노스택 : 특정 원소를 제거하여 정렬된 상태를 유지하는 형태
// - 각 건물의 옥상을 볼 수 있는 빌딩들을 스택에 추가!
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 빌딩의 수
        int N = Integer.parseInt(in.readLine());
        
        // 정답 초기화
        long answer = 0;
        // 덱 초기화
        Deque<Integer> deque = new LinkedList<>();
        
        // 건물 차례로 확인
        for(int idx = 0; idx < N; idx++) {
            // idx 번째의 건물 높이
            int height = Integer.parseInt(in.readLine());

            // 덱이 비지 않았다면 반복 :
            while(!deque.isEmpty()){
                // 추가된 건물의 높이가 현재 건물 이하인 경우 반환!
                // - 현재 건물 옥상을 볼 수 없는 경우
                if(deque.peekLast() <= height) deque.pollLast();
                // 아닐 경우 : 종료
                else break;
            }
            // 덱에 있는 건물의 수가 현재 건물의 옥상을 볼 수 있는 건물의 수
            answer += deque.size();
            // 덱에 현재 건물 추가!
            deque.offerLast(height);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
