package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/26651
// - 그리디 : A, Z만 추가하여 문자열 생성!
//            (A의 개수 * Z의 개수)의 부분 문자열을 가짐!
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 찾으려는 부분 문자열의 수
        int X = Integer.parseInt(in.readLine());

        // A의 수
        int countOfA = (int) Math.sqrt(X);
        // Z의 수
        int countOfZ = countOfA;
        // X 값을 넘지 않는 최대 Z의 수 계산
        while(countOfA * countOfZ < X) countOfZ++;
        countOfZ--;

        // 추가로 만들어야 하는 부분 문자열의 수
        X -= countOfA * countOfZ;
        // A, Z의 수에 따라 문자열 생성
        while(countOfA-- > 0) sb.append("A");
        sb.append("BCDEFGHIJKLMNOPQRSTUVWXY");
        while(countOfZ-- > 0) sb.append("Z");

        // 추가 문자열 생성
        while(X-- > 0) sb.append("A");
        sb.append("BCDEFGHIJKLMNOPQRSTUVWXYZ");

        // 정답 출력
        System.out.println(sb);
    }
}
