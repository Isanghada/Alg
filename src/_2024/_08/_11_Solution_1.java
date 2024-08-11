package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13703
// - DP : 탑 다운 DP를 통해 살아남는 모든 경우의 수 계산!
public class _11_Solution_1 {
    static long[][] DP;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int K = Integer.parseInt(st.nextToken());   // 초기 높이
        int N = Integer.parseInt(st.nextToken());   // 시간
        // DP 초기화
        DP = new long[K+N+1][N+1];

        // 정답 출력
        // - 재귀를 통해 탑다운 방식으로 살아남는 경우의 수 계산!
        sb.append(topDownDP(K, N));
        System.out.println(sb.toString());
    }

    private static long topDownDP(int depth, int time) {
        if(DP[depth][time] != 0) return DP[depth][time];

        if(depth == 0) return 0;
        else if(time == 0) return 1;

        return DP[depth][time] = topDownDP(depth-1, time-1)
                                + topDownDP(depth+1, time-1);
    }
}
