package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14588
// - 플로이드 와샬 : 친구 관계를 그래프로 표현하고, 플로이드 와샬을 통해 가까운 정도를 계산!
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 선분의 수
        int N = Integer.parseInt(in.readLine());

        // 선분 정보 입력
        StringTokenizer st = null;
        int[][] lines = new int[N+1][2];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            lines[n] = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };
        }

        // 친구 관계 입력!
        // - 친구 관계가 없는 경우 1000000!
        int[][] floyd = new int[N+1][N+1];
        for(int n = 1; n <= N; n++) Arrays.fill(floyd[n], 1000000);
        for(int a = 1; a <= N; a++){
            floyd[a][a] = 0;
            for(int b = a + 1; b <= N; b++){
                // 친구인 경우 : 양방향으로 입력!
                if(isFriend(lines[a], lines[b])){
                    floyd[a][b] = 1;
                    floyd[b][a] = 1;
                }
            }
        }
        // 친구 관계를 기반으로 플로이드-와샬 알고리즘 진행!
        getFloyd(floyd);

        // 질문 개수
        int Q = Integer.parseInt(in.readLine());
        while(Q-- > 0){
            // 질문 입력
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 친구 관계 정보 출력
            sb.append(floyd[a][b] != 1000000 ? floyd[a][b] : -1).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static int[][] getFloyd(int[][] floyd) {
        final int n = floyd.length - 1;

        for(int k = 1; k <= n; k++){
            for(int a = 1; a <= n; a++){
                for(int b = 1; b <= n; b++){
                    floyd[a][b] = Math.min(floyd[a][b], floyd[a][k]+floyd[k][b]);
                }
            }
        }

        return floyd;
    }

    private static boolean isFriend(int[] a, int[] b) {
        // b가 a에 포함되는 경우 혹은 a가 b에 포함되는 경우!
        return (a[0] <= b[0] && b[0] <= a[1]) || (a[0] <= b[1] && b[1] <= a[1]) ||
                (b[0] <= a[0] && a[0] <= b[1]) || (b[0] <= a[1] && a[1] <= b[1]);
    }
}
