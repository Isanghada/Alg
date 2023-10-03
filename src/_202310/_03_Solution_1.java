package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1034
// - 브르투 포스로 해결 : 가능한 모든 조합 탐색
// - 각 행을 기준으로 같은 패턴이 몇개인지 확인하고 가장 많은 패턴을 선택.
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 테이블 크기 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 테이블 값 입력 : 램프 상태
        int[][] table = new int[N][M];
        for(int i = 0 ; i < N; i++){
            String input = in.readLine();
            for(int j = 0; j < M; j++) table[i][j] = input.charAt(j) - '0';
        }

        // 스위치 누르는 횟수
        int K = Integer.parseInt(in.readLine());
        // - 짝수, 홀수 여부
        boolean isEven = (K % 2 == 0);
        // - 최대 50회까지 가능이므로 음음
        K = Math.min(K, 50);

        // 정답 초기화
        int answer = 0;
        // 모든 행을 기준으로 탐색
        for(int r = 0; r < N; r++){
            // 기준 행의 0 개수 확인
            int zeroCount = 0;
            for(int c = 0; c < M; c++) if(table[r][c] == 0) zeroCount++;

            // K번 보다 0의 개수가 많거나 짝수, 홀수 여부가 다른 경우 패스
            if(zeroCount > K || isEven != (zeroCount % 2 == 0)) continue;

            // 동일 패턴 횟수 초기화
            int count = 0;
            // 모든 행 확인
            for(int row = 0; row < N; row++){
                // 같은 패턴인지 여부
                boolean flag = true;
                for(int col = 0; col < M; col++){
                    // 열의 값이 같을 경우 패스
                    if(table[r][col] == table[row][col]) continue;
                    // 다를 경우
                    // - flag 변경, 종료
                    flag = false;
                    break;
                }
                // 같은 패턴일 경우 count 증가
                if(flag) count++;
            }
            // answer과 count 중 최대값으로 변경
            answer = Math.max(answer, count);
        }

        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }
}
