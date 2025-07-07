package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5638
// - 브루트포스 : 가능한 모든 경우 중 최소값 탐색!
public class _07_Solution_1 {
    // 수문 클래스
    static class Sluice{
        int flowRate;   // 유량
        int cost;       // 피해 비용
        public Sluice(int flowRate, int cost){
            this.flowRate = flowRate;
            this.cost = cost;
        }
    }
    static int N, V, T;
    static Sluice[] SLUICE_ARR;
    static long ANSWER;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        SLUICE_ARR = new Sluice[N];

        // 수문 정보 입력
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            SLUICE_ARR[i] = new Sluice(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 테스트케이스
        int M = Integer.parseInt(in.readLine());
        for(int m = 1; m <= M; m++){
            st = new StringTokenizer(in.readLine());
            V = Integer.parseInt(st.nextToken());   // 목표값
            T = Integer.parseInt(st.nextToken());   // 시간

            // 정답 초기화
            ANSWER = Long.MAX_VALUE;
            // 백트래킹을 통해 최소값 탐색
            backtracking(0, 0, 0L, 0L);

            sb.append("Case ").append(m).append(": ")
                    .append(ANSWER == Long.MAX_VALUE ? "IMPOSSIBLE" : ANSWER)
                    .append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static void backtracking(int step, int start, long sum, long cost) {
        // System.out.println(step+", "+start+", "+sum+", "+cost+"===");
        // 목표이상인 경우 정답 갱신 후 종료
        if(sum >= V) {
            ANSWER = Math.min(ANSWER, cost);
            return;
        }

        // 종료
        if(step == N || ANSWER <= cost) return;

        for(int i = start; i < N; i++){
            backtracking(
                    step+1,
                    i+1,
                    sum+SLUICE_ARR[i].flowRate*T,
                    cost+SLUICE_ARR[i].cost
            );
        }
    }
}
