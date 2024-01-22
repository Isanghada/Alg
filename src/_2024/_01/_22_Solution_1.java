package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25682
// - 누적합 : 전체 경우의 개수를 구하고 계산
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기
        int K = Integer.parseInt(st.nextToken());   // 체스판 크기

        // 보드 누적합
        // - r % 2 == c % 2 인 경우 : 전체 보드에서 좌상단인 타입으로 검정색의 개수
        // - r % 2 != c % 2 인 경우 : 전체 보드에서 좌상단이 아닌 타입으로 흰색의 개수
        int[][] sum = new int[N+1][M+1];
        for(int r = 1; r <= N; r++){
            char[] board = (" "+in.readLine()).toCharArray();
            for(int c = 1; c <= M; c++){
                if(board[c] == 'B' && (r % 2 == c % 2)) sum[r][c] = 1;
                else if(board[c] == 'W' && (r % 2 != c % 2))sum[r][c] = 1;
            }
        }

//        for(int[] s : sum){
//            for(int value : s) System.out.printf("%3d", value);
//            System.out.println();
//        }
//        System.out.println("------------");

        /////////////////////////////////////////////////
        // 누적합 계산
        for(int r = 1; r <= N; r++){
            for(int c = 1;c <= M; c++){
                sum[r][c] += sum[r][c-1];
            }
        }
        for(int c = 1;c <= M; c++){
            for(int r = 1; r <= N; r++){
                    sum[r][c] += sum[r-1][c];
            }
        }
        /////////////////////////////////////////////////
//        for(int[] s : sum){
//            for(int value : s) System.out.printf("%3d", value);
//            System.out.println();
//        }


        // 정답 초기화
        int answer = Integer.MAX_VALUE;

        final int rowLimit = N - K + 1; // 최대 행 크기
        final int colLimit = M - K + 1; // 최대 열 크기
        final int total = K * K;    // 체스판의 칸 개수

        // 가능한 경우 탐색
        for(int r = 1; r <= rowLimit; r++){
            for(int c = 1; c <= colLimit; c++){
                int maxRow = r + K - 1; // 체스판 우하단 행 좌표
                int maxCol = c + K - 1; // 체스판 우하단 열 좌표

                // 현재 체스판의 칠해진 칸의 수
                int cur = sum[maxRow][maxCol]
                        - sum[maxRow][c-1]
                        - sum[r-1][maxCol]
                        + sum[r-1][c-1];

                // 칠해진 것을 새로 칠하는 경우, 다른 부분을 칠하는 경우 중 최소값 선택
                int count = Math.min(cur, total - cur);
//                System.out.println(r+", "+c +" : "+cur+", "+ (total - cur));

                // 정답을 최소값으로 변경
                answer = Math.min(answer, count);
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
