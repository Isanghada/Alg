package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/1464
// - 그리디 : 문자열을 뒤집을지 말지 인접한 문자를 비교하여 결정
//            deque를 활용하여
//            1. 뒤집는 경우는 새로운 문자를 Fisrt에 추가
//            2. 뒤집지 않는 경우는 새로운 문자를 Last에 추가
//            3. 모든 문자열을 탐색한 경우 -> 역순으로 문자열 출력
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기 문자열
        String S = in.readLine();

        // 덱 초기화
        // - 첫 문자 추가
        Deque<Character> change = new LinkedList<>();
        change.offerLast(S.charAt(0));

        // 문자열의 모든 문자 체크
        for(int i = 1; i < S.length(); i++){
            char c = S.charAt(i);
            // 현재 문자가 이전 문자보다 클 경우 : First에 추가
            if(change.peekLast() < c) change.offerFirst(c);
            // 현재 문자가 이전 문자보다 작거나 같은 경우 : Last에 추가
            else change.offerLast(c);
        }

        // 최종적인 결과를 역순으로 출력
        while(!change.isEmpty()) sb.append(change.pollLast());

        // 정답 출력
        System.out.println(sb);
    }
}
