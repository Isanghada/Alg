package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25908
// - 수학 : 약수가 아닌 배수를 구하는 식으로 변경!
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int S = Integer.parseInt(st.nextToken());   // 구간 시작
        int T = Integer.parseInt(st.nextToken());   // 구간 끝

        // 결과 반환
        // - [1, T]의 값 - [1, S-1]의 값
        sb.append(getValue(T) - getValue((S-1)));
        System.out.println(sb);
    }

    private static int getValue(int num) {
        int result = 0;
        for(int n = 1; n <= num; n++) result += (num / n) * ((n & 1) == 0 ? 1: -1);

        return result;
    }
}
