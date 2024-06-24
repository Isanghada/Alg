package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14671
// - 참고 : https://organize-study.tistory.com/24
// - 실제로 해보면 규칙적으로 반복하므로, 같은 범위가 아닌 4개의 좌표가 필요하다!
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] checks = new boolean[2][2];
        while(K-- > 0){
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            checks[r%2][c%2] = true;
        }

        // 정답 출력
        sb.append((checks[0][0] && checks[0][1] && checks[1][0] && checks[1][1]) ? "YES":"NO");
        System.out.println(sb);
    }
}
