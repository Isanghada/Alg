package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14938
// - 플로이드 : 플로이드-와샬을 통해 각 지역에서 다른 지역으로 가는 거리 계산!
//              탐색 가능한 범위의 모든 아이템을 합하여 최대값 계산
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st= new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 지역의 수
        int M = Integer.parseInt(st.nextToken());   // 탐색 범위
        int R = Integer.parseInt(st.nextToken());   // 길의 수

        // 지역별 아이템 정보
        int[] items = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 플로이드 초기화
        int[][] floyd = new int[N][N];
        for(int n = 0; n < N; n++) {
            Arrays.fill(floyd[n], 30_000);
            floyd[n][n] = 0;
        }

        // 길 정보 입력 : 양방향!
        while(R-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken())-1;
            int B = Integer.parseInt(st.nextToken())-1;
            int C = Integer.parseInt(st.nextToken());

            floyd[A][B] = floyd[B][A] = Math.min(floyd[A][B], C);
        }

        // 플로이드 와샬을 통해 거리 계산
        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(i == j) continue;
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k]+floyd[k][j]);
                }
            }
        }

        // 정답 초기화
        int answer = 0;
        // 모든 지점 탐색
        for(int n = 0; n < N; n++){
            int itemCount = 0;
            // n번 지점에서 탐색 가능한 지역의 아이템 수의 합 계산
            for(int end = 0; end < N; end++){
                if(floyd[n][end] <= M) itemCount += items[end];
            }
            // 정답 최대값으로 갱신
            answer = Math.max(answer, itemCount);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}