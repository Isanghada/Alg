package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23327
// - 누적합 : 인기도, 재미의 누적합을 활용하여 범위에 맞게 계산
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 팀의 수
        int Q = Integer.parseInt(st.nextToken());   // 쿼리의 수

        st = new StringTokenizer(in.readLine());
        long[] popularityArr = new long[N+1];   // 인기도 누적합
        long[] sum = new long[N+1];             // 재미 누적합
        for(int i = 1; i <= N; i++) {
            int p = Integer.parseInt(st.nextToken());
            popularityArr[i] = popularityArr[i-1] + p;
            sum[i] = sum[i-1] + p * popularityArr[i-1];
        }


        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            // 범위 입력 : 시작, 끝 모두 포함
            int l = Integer.parseInt(st.nextToken());   // 시작
            int r = Integer.parseInt(st.nextToken());   // 끝

            // 리그전의 재미 계산
            // - sum[r] : 1 ~ r까지의 재미 누적합
            // - sum[l] : 1 ~ l까지의 재미 누적합
            // - (popularityArr[r] - popularityArr[l-1]) * popularityArr[l-1]
            //      : 1 ~ (l-1)과 l ~ r의 리그전 재미 누적합
            long answer = sum[r] - sum[l-1]
                    - (popularityArr[r] - popularityArr[l-1]) * popularityArr[l-1];

            sb.append(answer).append("\n");
        }


        // 정답 반환
        System.out.println(sb);
    }
}