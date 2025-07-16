package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/3095
// - 누적합 : 가운데 행과 열이 1로 되어야 하므로 1인 좌표를 기준으로 가능한 정사각형 개수 탐색!
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());    // 행렬 크기
        int[][] arr = new int[N+1][N+1];            // 행렬 정보
        int[][] sumArr = new int[N+1][N+1];         // 1의 개수 누적합
        for(int r = 1; r <= N; r++){
            String input = in.readLine();
            for(int c = 1; c <= N; c++) {
                arr[r][c] = input.charAt(c-1) - '0';
                sumArr[r][c] = sumArr[r-1][c] + sumArr[r][c-1] - sumArr[r-1][c-1] + arr[r][c];
            }
        }

        // 정답 초기화
        int answer = 0;
        // 최소 길이가 3이어야 하므로 (2, 2) ~ (N-1, N-1)까지를 기준으로 탐색
        for(int r = 2; r < N; r++){
            for(int c = 2; c < N; c++){
                // 기준 좌표값이 1이 아닌 경우 패스
                if(arr[r][c] != 1) continue;

                int target = 5; // 목표 1의 개수
                int count = 1;  // (r, c)기준 각 변까지의 거리

                // 행렬 범위이 경우 반복!
                while(checkRange(r - count, c - count, N) && checkRange(r + count, c + count, N)){
                    // 1의 개수
                    int sum = getSum(sumArr, r, c, count);
                    // 조건을 만족하는 정사각형인 경우
                    if(sum == target && checkPlus(arr, r, c, count)){
                        answer++;       // 정답 증가
                        count++;        // 거리 증가
                        target += 4;    // 목표 1의 개수 증가
                    // 아닐 경우 종료
                    }else break;
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static boolean checkPlus(int[][] arr, int r, int c, int count) {
        if(arr[r+count][c] == 1 &&
                arr[r-count][c] == 1 &&
                arr[r][c+count] == 1 &&
                arr[r][c-count] == 1
        ) return true;
        return false;
    }

    private static int getSum(int[][] sumArr, int r, int c, int count) {
        return sumArr[r+count][c+count]
                - sumArr[r+count][c-count-1]
                - sumArr[r-count-1][c+count]
                + sumArr[r-count-1][c-count-1];
    }

    private static boolean checkRange(int r, int c, int n) {
        if(r < 1 || r > n || c < 1 || c > n) return false;
        return true;
    }
}
