package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2073
// - DP : 파이프를 하나씩 사용하며 가능한 길이의 최대 수도관 용량 체크
public class _08_Solution_1{
    // 파이프 클래스
    static class Pipe implements Comparable<Pipe>{
        int l;  // 길이
        int c;  // 용량
        public Pipe(int l, int c){
            this.l = l;
            this.c = c;
        }
        // 1. 길이 기준 오름차순 정렬
        // 2. 용량 기준 내림차순 정렬
        @Override
        public int compareTo(Pipe p){
            if(this.l == p.l) return p.c - this.c;
            return this.l - p.l;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[ l=").append(this.l)
                    .append(", c=").append(this.c)
                    .append(" ]");
            return sb.toString();
        }
    }
    // 최대 길이
    static final int D_MAX = 100_000;
    // 파이프 최대 용량 : 2^23 이지만 편의를 위해 10_000_000로 설정
    static final int P_MAX = 10_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int D = Integer.parseInt(st.nextToken());   // 목표 길이
        int P = Integer.parseInt(st.nextToken());   // 파이프 개수

        // 파이프 정보 입력
        Pipe[] pipes = new Pipe[P+1];
        pipes[0] = new Pipe(0, 0);
        for(int i = 1; i <= P; i++){
            st = new StringTokenizer(in.readLine());
            pipes[i] = new Pipe(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 파이프 정렬 : 목표 길이를 넘어가는 경우 종료하기 위해 활용
        Arrays.sort(pipes);

        // dp 초기화
        // - 최대값을 찾아야하므로 -1로 초기화
        // - 길이 0인경우 P_MAX로 초기화
        int[][] dp = new int[P+1][D+1];
        for(int p = 0; p <= P; p++) {
            Arrays.fill(dp[p], -1);
            dp[p][0] = P_MAX;
        }

        // 정답 초기화
        int answer = 0;
        // 모든 파이프 체크
        for(int p = 1; p <= P; p++){
            // 현재 파이프 길이가 목표 길이를 넘을 경우 종료
            if(pipes[p].l > D) break;
            // 모든 길이 체크
            for(int d = 1; d <= D; d++){
                // 이전 용량으로 초기화
                dp[p][d] = dp[p-1][d];
                // 현재 파이프를 연결할 수 있는 경우 최대값 갱신
                //  1. 파이프의 길이가 d 이하인 경우
                //  2. 이전 길이(d-pipes[p].l)가 -1이 아닌 경우
                if(pipes[p].l <= d && dp[p-1][d-pipes[p].l] != -1) {
                                        // 이전 용량
                    dp[p][d] = Math.max(dp[p][d],
                                        // (현재 파이프 용량, 이전 길이의 용량) 중 최소값
                                        Math.min(pipes[p].c, dp[p-1][d-pipes[p].l]));
                }
            }
            // 정답 최대값으로 갱신
            answer = Math.max(answer, dp[p][D]);
        }
//        System.out.println(Arrays.toString(pipes));
//        for(int[] d : dp) System.out.println(Arrays.toString(d));

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
