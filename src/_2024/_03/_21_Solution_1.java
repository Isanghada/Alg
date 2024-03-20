package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/12919
// - 브루트포스 : 가능한 모든 경우 탐색
public class _21_Solution_1 {
    public static int answer;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 입력
        String S = in.readLine();   // 시작 문자열
        String T = in.readLine();   // 타겟 문자열

        // 정답 초기화
        answer = 0;
        // 재귀를 통해 모든 경우 탐색
        dfs(S, T);

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // dfs 함수 : 재귀를 통해 t가 s와 동일하게 바뀔 수 있는지 확인
    private static void dfs(String s, String t) {
        // s와 t가 동일하다면 정답을 1로 변경하고 종료
        if(s.equals(t)) {
            answer = 1;
            return;
        }
        // s의 길이가 t이상일 경우 종료
        if(s.length() >= t.length()) return;
        // t의 마지막 문자가 A일 경우 제거하여 비교
        if(t.charAt(t.length()-1) == 'A') dfs(s, t.substring(0, t.length()-1));
        // t의 첫 문자가 B일 경우 제거하고 뒤집어 비교
        if(t.charAt(0) == 'B') dfs(s, new StringBuilder(t.substring(1, t.length())).reverse().toString());
    }
}