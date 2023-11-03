package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/2504
// - 스택 자료구조 활용!
// - 올바른 괄호인 경우 값을 계산해나가며 해결
// - 올바르지 않은 경우 정답을 0으로 반환
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 괄호 입력
        char[] string = in.readLine().toCharArray();
        // 덱 초기화 : 스택처럼 활용
        Deque<String> deque = new LinkedList<>();

        // 모든 괄호 탐색
        for(char word : string){
            // 닫는 괄호인 경우
            if(word == ')' || word == ']'){
                // 값 계산
                long num = 0;
                // 여는 괄호가 나올 때 까지 모든 값 합 계산
                while(!deque.isEmpty() && !deque.peekLast().equals("(") && !deque.peekLast().equals("["))
                    num += Long.parseLong(deque.pollLast());

                // 값이 0인 경우 괄호 내에 값이 없는 것이므로 1로 변경
                num = (num == 0 ? 1 : num);

                // 덱이 비었다면 종료!
                if(deque.isEmpty()) break;

                // 여는 괄호 반환
                char cur = deque.pollLast().charAt(0);
                // 올바른 괄호인 경우 num에 각 괄호의 값 곱하여 계산
                if(word == ')' && cur == '(') num *= 2;
                else if(word ==']' && cur == '[') num *= 3;
                // 올바르지 않은 경우 종료
                else {
                    // 덱의 모든 내용을 초기화
                    deque.clear();
                    break;
                }
                // 계산한 값을 덱에 추가
                deque.offerLast(String.valueOf(num));
            // 여는 괄호인 경우 덱에 추가
            }else deque.offerLast(String.valueOf(word));
        }

        // 정답 계산!
        long answer = 0;
        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 값 반환
            String cur = deque.pollLast();
            // 여는 괄호인 경우 0 반환!
            if(cur.equals("(") || cur.equals("[")) {
                answer = 0;
                break;
            }
            // 값 만큼 증가!
            answer += Long.parseLong(cur);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
