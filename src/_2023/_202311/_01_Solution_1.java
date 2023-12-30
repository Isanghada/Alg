package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1025
// - 브루트 포스로 해결 : 가능한 모든 조합 탐색
public class _01_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202311/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 보드 크기 입력
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기

        // 보드 입력
        int[][] board = new int[N][M];
        for(int r = 0; r < N; r++){
            char[] inputArr = in.readLine().toCharArray();
            for(int c = 0; c < M; c++) board[r][c] = inputArr[c] - '0';
        }

        // 정답 초기화 : 완접 제곱수를 만들 수 없는 경우 -1 반환!
        int answer = -1;
        // (0, 0)부터 (N-1, M-1)을 기준으로 모든 등차수열로 확인!
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                for(int dR = -N; dR < N; dR++){
                    for(int dC = -M; dC < M; dC++){
                        // 서로 다른 칸으로 해야하기 때문에 움직임이 없는 경우는 패스!
                        if(dR == 0 && dC == 0) continue;

                        // 현재 숫자
                        int sum = 0;
                        int nextR = r;  // 다음 행 좌표
                        int nextC = c;  // 다음 열 좌표
                        // 범위 내에서 반복
                        while(nextR >= 0 && nextR < N && nextC >= 0 && nextC < M){
                            // 숫자 계산
                            sum = sum * 10 + board[nextR][nextC];
                            // 완전 제곱수이면 정답과 비교하여 큰 값으로 변경
                            if(Math.sqrt(sum) - (int)Math.sqrt(sum) == 0) answer = Math.max(answer, sum);
                            // 다음 좌표로 이동
                            nextR += dR;
                            nextC += dC;
                        }
                    }
                }
            }
        }

        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }
}
