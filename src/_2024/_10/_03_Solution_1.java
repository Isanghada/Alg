package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13424
// - 플로이드-와샬 : 각 노드에서 다른 노드로 가는 최소 거리를 모두 계산하여
//                   모임 장소에 따라 이동 거리의 합이 최소인 경우 탐색
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 방의 수
            int M = Integer.parseInt(st.nextToken());   // 비밀통로의 수

            // 인접 행렬 : 이동 정보 입력
            int[][] adjArr = new int[N+1][N+1];
            for(int r = 1 ; r <= N; r++){
                Arrays.fill(adjArr[r], 1_000_000);
                adjArr[r][r] = 0;
            }
            while(M-- > 0){
                st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                adjArr[a][b] = c;
                adjArr[b][a] = c;
            }

            // 플로이드-와샬을 통해 각 정점에서 다른 정점으로 이동하는 최소 거리 계산
            int[][] floyd = getFloyd(adjArr, N);

//            for(int[] f : floyd) System.out.println(Arrays.toString(f));

            // 친구의 수
            int K = Integer.parseInt(in.readLine());
            // 친구별 방 번호
            int[] friends = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

            // 정답 초기화
            int answer = 0;
            // 거리 초기화
            int distance = 1_000_000;
            // 각 방을 모임 장소로 설정하여 최소값 탐색!
            for(int end = 1; end <= N; end++){
                // 이동 거리의 합 계산
                int sum = 0;
                for(int start : friends) sum += floyd[start][end];

//                System.out.println(end+", "+sum);
                // 정답 갱신!
                if(sum < distance) {
                    answer = end;
                    distance = sum;
                }
            }
//            System.out.println("-------");
            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static int[][] getFloyd(int[][] adjArr, int n) {
        int[][] floyd = new int[n+1][n+1];
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= n; c++) floyd[r][c] = adjArr[r][c];
        }

        for(int k = 1; k <= n; k++){
            for(int r = 1; r <= n; r++){
                for(int c = 1; c <= n; c++){
                    floyd[r][c] = Math.min(floyd[r][c], floyd[r][k]+floyd[k][c]);
                }
            }
        }

        return floyd;
    }
}
