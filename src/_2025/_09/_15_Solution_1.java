package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14846
// - 누적합 : 각 숫자별로 누적합을 계산하여 범위안에 숫자가 존재하는지 확인!
public class _15_Solution_1 {
    // 숫자 종류
    static final int NUMBER = 10;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 행렬 크기
        int N =Integer.parseInt(in.readLine());
        // 숫자별 누적합
        int[][][] sum = new int[NUMBER+1][N+1][N+1];

        StringTokenizer st = null;
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= N; c++) {
                int num = Integer.parseInt(st.nextToken());
                sum[num][r][c]++;
                for(int number = 0; number<= NUMBER; number++) sum[number][r][c] += sum[number][r][c-1];
            }
        }

        for(int c = 1; c <= N; c++){
            for(int r = 1; r <= N; r++){
                for(int number = 0; number<= NUMBER; number++) sum[number][r][c] += sum[number][r-1][c];
            }
        }

        // 쿼리의 수

        int Q = Integer.parseInt(in.readLine());
        while(Q-- > 0){
            // 왼쪽 윗칸
            st = new StringTokenizer(in.readLine());
            int[] startPoint = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };
            // 오른쪽 아랫칸
            int[] endPoint = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };

            // 숫자의 수
            int count = 0;
            // 모든 숫자 탐색
            for(int number = 0; number<= NUMBER; number++){
                // 숫자 개수
                int total = sum[number][endPoint[0]][endPoint[1]]
                        + sum[number][startPoint[0]-1][startPoint[1]-1]
                        - sum[number][endPoint[0]][startPoint[1]-1]
                        - sum[number][startPoint[0]-1][endPoint[1]];
                // 숫자가 존재하면 count 증가
                count += total > 0 ? 1 : 0;
            }

            sb.append(count).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
