package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11404
// - 플로이드와샬 : 각 정점에서 다른 정점으로 가는 최소 비용 계산!
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());    // 도시의 수
        int M = Integer.parseInt(in.readLine());    // 노선의 수

        // 노선 정보 입력
        int[][] floyd = new int[N+1][N+1];
        for(int n = 1; n <= N; n++){
            Arrays.fill(floyd[n], 100_000_000);
            floyd[n][n] = 0;
        }

        StringTokenizer st = null;
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());    // 시작 도시
            int b = Integer.parseInt(st.nextToken());    // 도착 도시
            int c = Integer.parseInt(st.nextToken());    // 비용

            // 여러 경로가 있을 수 있으므로 최소 비용 선택!
            floyd[a][b] = Math.min(floyd[a][b], c);
        }

        floyd(floyd, N);
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                sb.append(floyd[i][j] == 100_000_000 ? 0 : floyd[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static void floyd(int[][] floyd, int n) {
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k]+floyd[k][j]);
                }
            }
        }
    }
}
