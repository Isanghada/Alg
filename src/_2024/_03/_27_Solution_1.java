package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10427
// - 누적합 : 빌린 돈을 오름차순 정렬하여 누적합 계산하여 활용
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 돈을 빌린 횟수
            // 빌린 돈 배열
            long[] A = new long[N+1];
            for(int i = 1; i <= N; i++) A[i] = Long.parseLong(st.nextToken());

            // 오름차순 정렬 후 누적합 계산
            Arrays.sort(A);
            for(int i = 1; i <= N; i++) A[i] += A[i-1];

            // 정답 초기화
            long answer = 0;
            // S(1)은 0이므로 2부터 N까지 최소값 계산
            for(int m = 2; m <= N; m++){
                // 최대값으로 초기화
                long s = Long.MAX_VALUE;
                // 모든 값을 탐색하여 최소값 갱신
                for(int i = m; i <= N; i++) s = Math.min(s, (A[i] - A[i-1]) * m - (A[i] - A[i-m]));
                // 정답 S(m) 증가!
                answer += s;
            }
            // 테스트케이스 정답 출력
            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
