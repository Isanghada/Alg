package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5549
// - 누적합 : 각 영역의 누적합을 계산하여 활용
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 지도 크기 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());   // 행 크기
        int N = Integer.parseInt(st.nextToken());   // 열 크기

        int K = Integer.parseInt(in.readLine());    // 조사 영역 개수

        // 지도 입력
        char[][] map = new char[M+1][N+1];
        // - 인덱스를 1부터 시작하기 위해 공백 추가
        for(int i = 1; i <= M; i++) map[i] = (" "+ in.readLine()).toCharArray();

        // 누적합 초기값 설정
        // - [0][i][j] : 정글의 (i, j) 좌표 누적합
        // - [1][i][j] : 바다의 (i, j) 좌표 누적합
        // - [2][i][j] : 얼음의 (i, j) 좌표 누적합
        int[][][] sumMap = new int[3][M+1][N+1];
        for(int r = 1 ; r <= M; r++){
            for(int c = 1; c <= N; c++){
                char cur = map[r][c];
                if(cur == 'J') sumMap[0][r][c] = 1;
                else if(cur == 'O') sumMap[1][r][c] = 1;
                else sumMap[2][r][c] = 1;
            }
        }

        // 행 기준 누적합
        for(int c = 1; c <= N; c++) {
            for (int r = 1; r <= M; r++) {
                for (int type = 0; type < 3; type++){
                    sumMap[type][r][c] += sumMap[type][r-1][c];
                }
            }
        }

        // 열 기준 누적합
        for (int r = 1; r <= M; r++) {
            for(int c = 1; c <= N; c++) {
                for (int type = 0; type < 3; type++){
                    sumMap[type][r][c] += sumMap[type][r][c-1];
                }
            }
        }

        // 조사 영역 계산
        while(K-- > 0){
            // 영역 범위 입력
            st = new StringTokenizer(in.readLine());
            // 좌상단 좌표
            int[] leftTopPoint = new int[]{Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())};
            // 우하단 좌표
            int[] rightBottomPoint = new int[]{Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())};

            // 정글, 바다, 얼음 순으로 계산하여 출력
            for(int type = 0; type < 3; type++){
                int count = sumMap[type][rightBottomPoint[0]][rightBottomPoint[1]]
                        - sumMap[type][rightBottomPoint[0]][leftTopPoint[1]-1]
                        - sumMap[type][leftTopPoint[0]-1][rightBottomPoint[1]]
                        + sumMap[type][leftTopPoint[0]-1][leftTopPoint[1]-1];
                sb.append(count).append(" ");
            }
            sb.append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
