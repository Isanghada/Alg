package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1956
// - 플로이드 와샬 : 단방향 그래프이므로 플로이드를 통해 계산 후
//                   두 경로 a -> b, b -> a 가 존재한다면 사이클이 존재한다는 의미!
public class _07_Solution_1 {
    // 최대값
    public static final int INF = 400_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int V = Integer.parseInt(st.nextToken());   // 마을의 수
        int E = Integer.parseInt(st.nextToken());   // 도로의 수

        // 도로 정보 입력
        int[][] floyd = new int[V+1][V+1];
        for(int v = 0; v <= V; v++) Arrays.fill(floyd[v], INF);

        while(E-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            floyd[a][b] = c;
        }

        // 플로이드-와샬 : 각 도시별 연결 정보 계산
        for(int k = 1; k <= V; k++){
            for(int a = 1; a <= V; a++){
                for(int b = 1; b <= V; b++){
                    if(a == b) continue;
                    floyd[a][b] = Math.min(floyd[a][b], floyd[a][k]+floyd[k][b]);
                }
            }
        }

        // for(int[] f : floyd) System.out.println(Arrays.toString(f));

        // 정답 초기화
        int answer = INF;
        // - 두 마을 (a, b) 사이의 사이클 확인
        for(int a = 1; a <= V; a++){
            for(int b= 1; b <= V; b++){
                if(a == b) continue;
                // 사이클 유무 확인 : a -> b, b -> a 여부 확인
                if(floyd[a][b] != INF && floyd[b][a] != INF) {
                    // 최소값 갱신
                    answer = Math.min(answer, floyd[a][b] + floyd[b][a]);
                }
            }
        }

        // 정답 출력
        sb.append(answer == INF ? -1 : answer);
        System.out.println(sb);
    }
}
