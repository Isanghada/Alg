package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/1662
// - 스택을 활용해 계산
// - 압축된 문자열을 차례로 스택에 추가하며 길이 계산!
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 압축된 문자열 입력
        char[] encoding = in.readLine().toCharArray();
        // 덱 초기화 : 스택으로 활용
        Deque<Integer> deque = new LinkedList<>();

        // 모든 문자열 실행
        for(int i = 0; i < encoding.length; i++){
            // 현재 단어
            char word = encoding[i];
            // 여는 괄호인 경우
            // - 덱에 -1 추가 : 0~9, 괄호로 구성되어 있으므로 괄호를 식별하기 위해 -1 사용
            if(word == '(') deque.offerLast(-1);
            // 닫는 괄호인 경우
            else if(word == ')'){
                // 길이 초기화
                int cnt = 0;
                // 여는 괄호가 나올 때까지 길이 증가!
                while(deque.peekLast() != -1) cnt += deque.pollLast();
                // 여는 괄호 제거
                deque.pollLast();
                // 여는 괄호 앞의 정수와 곱하여 길이 추가!
                deque.offerLast(cnt * deque.pollLast());
            // 정수인 경우
            }else {
                // 뒤의 문자가 여는 괄호인 경우 숫자 그대로 추가
                if((i < (encoding.length - 1)) && encoding[i+1] == '(') deque.offerLast(word - '0');
                // 뒤의 문자가 여는 괄호가 아닌 경우 문자열을 의미
                // - 1 추가
                else deque.offerLast(1);
            }
        }
        // 정답 초기화
        int answer = 0;
        // 덱의 모든 값을 더하여 길이 계산
        while(!deque.isEmpty()) answer += deque.pollLast();

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
