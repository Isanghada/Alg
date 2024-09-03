package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2448
// - 참고 : https://velog.io/@jii0_0/%EB%B0%B1%EC%A4%80-2448.-%EB%B3%84-%EC%B0%8D%EA%B8%B0-11-Java
// - 재귀 : 크기 3인 삼각형이 반복되므로
//          최소 크기가 될 때까지 재귀로 나눈 후 구간별로 입력!
/* N = 3
            *
           * *
          *****
 */
/* N = 6
                   *
                  * *
                 *****
                *     *
               * *   * *
              ***** *****
 */
public class _03_Solution_1 {
    static char[][] STAR;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 별의 길이!
        int N = Integer.parseInt(in.readLine());
        final int COL = 2*N-1;

        // 배열 초기화! : 공백으로 초기화 후 별의 위치에만 별 입력
        STAR = new char[N][COL];
        for(int n = 0; n < N; n++) Arrays.fill(STAR[n], ' ');

        // 재귀를 통해 각 구간별 별 입력
        inputStar(0, N-1, N);

        // 정답 반환
        for(int r = 0; r < N; r++){
            for(int c = 0; c < COL; c++) sb.append(STAR[r][c]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    // 별 위치용 변수!
    public static int[][] DELTA = new int[][]{{0, 0}, {1, -1}, {1, 1}, {2, -2}, {2, -1}, {2, 0}, {2, 1}, {2, 2}};
    private static void inputStar(int r, int c, int n) {
        // n == 3 일 때 별이 최소 크기이므로 (r, c) 기준으로 별 입력!
        if(n == 3){
            for(int[] d : DELTA) STAR[r+d[0]][c+d[1]] = '*';
        }else{
            // n을 분리하여 재귀!
            int cnt = n / 2;
            inputStar(r, c, cnt);
            inputStar(r+cnt, c - cnt, cnt);
            inputStar(r+cnt, c + cnt, cnt);
        }
    }
}
