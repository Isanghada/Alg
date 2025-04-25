package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2219
// - 플로이드-와샬 : '서로 다른 컴퓨터간 통신이 가능'하기 때문에 모든 컴퓨터는 연결되어 있음이 보장된다.
//                      플로이드-와샬을 통해 각 컴퓨터에서 다른 컴퓨터와 통신하는 최소 시간 계산
//                      평균이 최소인 경우를 선택해야하므로 합이 최소인 경우와 동일하다!
//                      따라서, 통신 시간의 합이 최소인 경우 선택
public class _26_Solution_1 {
    // 최대 통신 시간
    static final int MAX = 20_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 컴퓨터의 수
        int M = Integer.parseInt(st.nextToken());   // 회선의 수

        // 플로이드-와샬 초기화
        int[][] floyd = init(N);
        // 회선 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int value = Math.min(floyd[A][B], C);
            floyd[A][B] = value;
            floyd[B][A] = value;
        }

        // 플로이드-와샬
        setFloyd(floyd, N);

        int answer = 0; // 보안 시스템 설치 컴퓨터
        int min = MAX;  // 최소 통신 시간의 합
        for(int a = 1; a <= N; a++){
            // a를 기준으로 통신 시간의 합 계산
            int sum = 0;
            for(int b = 1; b <= N; b++) sum += floyd[a][b];

            // 최소 통신 시간인 경우 정답 갱신
            if(sum < min){
                answer = a;
                min = sum;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static void setFloyd(int[][] floyd, int n) {
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(i == k) continue;
                for(int j = 1; j <= n; j++){
                    if(i == j || j == k) continue;
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k]+floyd[k][j]);
                }
            }
        }
    }

    private static int[][] init(int n) {
        int[][] result = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++) result[i][j] = MAX;
            result[i][i] = 0;
        }
        return result;
    }
}
