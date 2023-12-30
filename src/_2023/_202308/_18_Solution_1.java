package _2023._202308;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/136797?language=java
// 백트래킹 : 모든 경우를 확인하며 가지치기 적용
// - 현재 정답보다 큰 가중치가 될 경우 종료.
// => 시간 초과!!! 조건을 잘 봤어야 하는데 흠흠..
// DP : 최대 (100000 * 10 * 10 => 10000000)
// - 두 손가락이 겹치지 않도록 주의.
public class _18_Solution_1 {
    // 재귀 함수를 사용하므로 효율적인 메모리 사용을 위해 static 활용
    public static char[] charNumber;    // 입력값 char 배열로 변환용
    public static int lenNumber;    // 입력값 길이

    // 번호판의 좌표 확인용 map
    public static int[][] numberPointArr;

    public static int[][][] dp;
    public int solution(String numbers) {
        // 번호판 좌표 입력
        // - [i][0] 행 : i / 3
        // - [i][1] 열 : i % 3
        numberPointArr = new int[10][2];
        for(int i = 1; i < 10; i++){
            numberPointArr[i][0] = (i-1) / 3;
            numberPointArr[i][1] = (i-1) % 3;
        }
        // 0은 따로 입력
        numberPointArr[0][0] = 3;
        numberPointArr[0][1] = 1;
        
        // 입력값 설정
        charNumber = numbers.toCharArray(); // char 배열로 변환
        lenNumber = numbers.length();   // 입력값 길이

        dp = new int[lenNumber+1][10][10];
        for (int i = 0; i < lenNumber + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }

        // 최소 가중치 계산 : 초기에 올려져있는 번호는 4, 6
        return getMinWeight(0, 4, 6);
    }

    // 최소 가중치 계산 함수 : 재귀를 통해 진행. 현재 정답보다 가중치가 커질경우 종료.
    // - step : 현재 번호
    // - num1 : 손가락이 올려져있는 번호1
    // - num2 : 손가락이 올려져있는 번호2
    // - weight : 가중치
    private int getMinWeight(int step, int num1, int num2) {
        if(step == lenNumber){
            return 0;
        }
        if(dp[step][num1][num2] != -1) return dp[step][num1][num2];

        int next = charNumber[step] - '0';
        int result = Integer.MAX_VALUE;

        if(next != num2) result = Math.min(result, getMinWeight(step+1, next, num2) + getWeight(num1, next));
        if(next != num1) result = Math.min(result, getMinWeight(step+1, num1, next) + getWeight(num2, next));

        return dp[step][num1][num2] = result;
    }  
    
    // 가중치 계산 함수 : 행, 열 차이를 구하고 가중치 계산
    // - before : 현재 번호 좌표
    // - after : 누를 번호 좌표
    private int getWeight(int before, int after) {
        // 행 차이
        int row = Math.abs(numberPointArr[after][0] - numberPointArr[before][0]);
        // 열 차이
        int col = Math.abs(numberPointArr[after][1] - numberPointArr[before][1]);
        
        // 동일한 위치일 경우 1 반환
        if(row == 0 && col == 0) return 1;
        // 행과 열의 차이가 동일한 경우 대각선으로 움직이므로
        // row 혹은 col에 3을 곱해 반환
        else if(row == col){
            return row * 3;
        // 아닐 경우 대각선 + 상하좌우 이동
        // - 먼저 대각선 이동으로 같은 행 혹은 같은 열로 이동
        // - 이후, 남은 거리를 상하좌우로 이동
        }else{
            // 대각선 이동은 row, col 중 작은값만큼 이므로
            // row를 작은 값으로 변경!
            if(row > col){
                int temp = row;
                row = col;
                col = temp;
            }
            // 작은 값 만큼 대각선(3) 이동
            // (큰 값 - 작은 값) 만큼 상하좌우(2) 이동
            return row * 3 + (col-row) * 2;
        }
    }
}
