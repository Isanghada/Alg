package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11265
// - 플로이드 와샬 : 각 지점에서 모든 지점으로 가는 최소값 계산!
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 파티장의 수
        int M = Integer.parseInt(st.nextToken());   // 손님의 수

        // 파티장 이동 시간 정보 입력
        int[][] floyd = new int[N+1][N+1];
        for(int r = 1; r <= N; r++) {
            st = new StringTokenizer(in.readLine());
            for (int c = 1; c <= N; c++) {
                floyd[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드-와샬을 통해 최소값 계산
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(i == j) continue;
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k]+floyd[k][j]);
                }
            }
        }

//        for(int r = 1; r <= N; r++){
//            System.out.println(Arrays.toString(floyd[r]));
//        }

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());   // 출발 파티장
            int B = Integer.parseInt(st.nextToken());   // 도착 파티장
            int C = Integer.parseInt(st.nextToken());   // 다음 파티까지 걸리는 시간

            // 가능한 경우
            if(floyd[A][B] <= C) sb.append("Enjoy other party\n");
            // 불가능한 경우
            else sb.append("Stay here\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
