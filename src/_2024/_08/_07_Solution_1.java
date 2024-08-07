package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2671
// - 정규표현식 : 잠수함 엔진 패턴인 (100~1~|01)~ 을 정규표현식으로 탐색!
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String sound = in.readLine();
        String pattern = "(100+1+|01)+";

        // 정답 출력
        sb.append(sound.matches(pattern) ? "SUBMARINE" : "NOISE");
        System.out.println(sb);
    }
}
