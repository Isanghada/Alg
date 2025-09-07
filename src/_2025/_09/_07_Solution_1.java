package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24553
// - 에드혹 : 직접 규칙을 찾다보면 10의 배수일 떄 승우(1)가 이기고, 아닐 경우 상윤(0)이가 이긴다.
//      - 1 ~ 9     : 상윤 승
//      - 10        : 승우 승
//      - 11 ~ 19   : 상윤 승
//      - 20        : 승우 승
//      - ...
//
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        while(T-- > 0 ){
            long N = Long.parseLong(in.readLine());
            sb.append(N % 10 == 0 ? 1 : 0).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
