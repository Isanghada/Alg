package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10159
// - 플로이드 : 무거운 경우, 가벼운 경우를 따로 체크!
//              각각의 경우를 플로이드-와샬을 통해 탐색
//              두 그래프에서 모두 도달할 수 없는 경우 불가능!
public class _28_Solution_1 {
    public static final int MAX = 100_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 물건의 개수
        int N = Integer.parseInt(in.readLine());
        // 무게 비교 개수!
        int M = Integer.parseInt(in.readLine());

        StringTokenizer st = null;

        // 무거운 경우
        int[][] heavy = new int[N+1][N+1];
        // 가벼운 경우
        int[][] light = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++) {
                heavy[i][j] = MAX;
                light[i][j] = MAX;
            }
            heavy[i][i] = 0;
            light[i][i] = 0;
        }

        // 무게 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int heavyThing = Integer.parseInt(st.nextToken());
            int lightThing = Integer.parseInt(st.nextToken());

            heavy[heavyThing][lightThing] = 1;
            light[lightThing][heavyThing] = 1;
        }

        // 플로이드-와샬
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(i == j) continue;
                    heavy[i][j] = Math.min(heavy[i][j], heavy[i][k] + heavy[k][j]);
                    light[i][j] = Math.min(light[i][j], light[i][k] + light[k][j]);
                }
            }
        }

        // 모든 경우 탐색!
        for(int start = 1; start <= N; start++){
            int count = 0;
            for(int end = 1; end <= N; end++){
                // 무거운 경우, 가벼운 경우 모두 불가능할 때 증가!
                if(heavy[start][end] == MAX && light[start][end] == MAX) count++;
            }
            sb.append(count).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
