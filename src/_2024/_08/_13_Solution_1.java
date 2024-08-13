package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9660
// - 게임 이론 : 규칙을 찾아 규칙에 따라 처리하여 승리자 탐색!
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        /*
            1 => SK
            2 => CY
            3 => SK
            4 => SK
            5 => SK
            6 => SK
            7 => CY
            8 => SK
            9 => CY
            10 => SK
            11 => SK
            12 => SK
            13 => SK
            14 => CY
            ...
            => 7로 나눈 나머지가 2 또는 0인 경우 창영이가 이기는 경우이다!
        */
        long N = Long.parseLong(in.readLine());

        // 정답 출력
        sb.append(((N % 7 == 0) || (N % 7 == 2)) ? "CY" : "SK");
        System.out.println(sb.toString());
    }
}
