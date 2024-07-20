package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21278
// - 브루트포스 : 플로이드-와샬 알고리즘으로 각 지점에서의 최단 거리 계산 후,
//                두 건물을 선택하는 모든 경우 탐색!
public class _20_Solution_1 {
    // 최대값
    public static final int MAX = 10000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 건물의 수
        int M = Integer.parseInt(st.nextToken());   // 도로의 수

        // 인접 행렬 초기화
        int[][] adj = new int[N+1][N+1];
        for(int a = 1; a <= N; a++){
            for(int b = 1; b <= N; b++){
                if(a == b) adj[a][b] = 0;
                else adj[a][b] = MAX;
            }
        }

        // 도로 정보 입력 : 양방향으로 1의 거리를 가짐!
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a][b] = 1;
            adj[b][a] = 1;
        }

        // 플로이드-와샬 알고리즘을 통해 각 건물에서 최단 거리 계산!
        floyd(adj, N);

        // 최단 거리 배열을 활용해 최적의 건물 탐색!
        int[] answer = getAnswer(adj, N);

        // 정답 출력
        sb.append(answer[0]).append(" ").append(answer[1]).append(" ").append(answer[2] * 2);
        System.out.println(sb.toString());
    }
    // 최적의 건물 탐색 함수 : 브루트포스
    // - 가능한 모든 건물 조합 탐색!
    private static int[] getAnswer(int[][] adj, int n) {
        // 정답 초기화
        int[] result = new int[]{1, 2, MAX*n};
        // a, b를 오름차순으로 선택!
        for(int a = 1; a <= n; a++){
            for(int b = a+1; b <= n; b++){
                // a, b일 경우의 거리 계산
                int sum = 0;
                for(int c = 1; c <= n; c++) sum += Math.min(adj[a][c], adj[b][c]);
                // 현재 결과가 더 작을 경우 결과 갱신!
                if(sum < result[2]){
                    result[0] = a;
                    result[1] = b;
                    result[2] = sum;
                }
            }
        }
        // 정답 반환
        return result;
    }
    // 플로이드-와샬 함수
    private static void floyd(int[][] adj, int n) {
        for(int k = 1; k <= n; k++){
            for(int a = 1; a <= n; a++){
                for(int b = 1; b <= n; b++){
                    if(a == b) continue;
                    adj[a][b] = Math.min(adj[a][b], adj[a][k] + adj[k][b]);
                }
            }
        }
    }
}
