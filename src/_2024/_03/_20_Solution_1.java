package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/1394
// - 조합론 : 사용 가능한 문자를 매핑하고 차례로 패스워드 순서 계산
public class _20_Solution_1 {
    private static final int MOD = 900528;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int idx = 1;    // 사용 가능 문자 순서
        // 사용 가능 문자 Map
        Map<Character, Integer> wordMap = new HashMap<>();
        // 사용 가능 문자 입력
        for(char word : in.readLine().toCharArray()) wordMap.put(word, idx++);
        // 사용 가능 문자 사이즈
        int wordSize = wordMap.size();

        // 패스워드 입력
        String password = in.readLine();
    
        // 정답 초기화
        int answer = 0;
        // 패스워드 문자를 차례로 탐색
        for(char p : password.toCharArray()){
            answer *= wordSize;
            answer += wordMap.get(p);
            answer %= MOD;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
