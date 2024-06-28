package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10827
// - BigDecimal을 통해 풀이
// - 직접 구현할 경우 참고 : https://jaimemin.tistory.com/1076
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        BigDecimal bigDecimal = new BigDecimal(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        // 정답 출력
        sb.append(bigDecimal.pow(p).toPlainString());
        System.out.println(sb);
    }
}
