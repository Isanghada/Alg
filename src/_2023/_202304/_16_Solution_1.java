package _2023._202304;

import java.util.Arrays;

public class _16_Solution_1 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;    // 초기값 설정

        // 조건에 맞게 정렬
        // - 1. col 기준 오름차순
        // - 2. 기본키(첫째 열) 기준 내림차순
        Arrays.sort(data, (d1, d2) -> {
            int diff = d1[col - 1] - d2[col - 1];
            if(diff == 0){
                return d2[0] - d1[0];
            }
            return diff;
        });
        
        int colLen = data[0].length; // 열 개수

        // 값 계산
        // - 각 행의 S값
        // - 이전 값에 bitwiseXOR
        for(int i = row_begin-1; i < row_end; i++){

            // S값 계산 : 기준 행의 열을 행 번호로 나머지를 계산하여 합한다.
            int S = 0;
            for(int j = 0; j < colLen; j++) {
                S += data[i][j] % (i+1);
            }

            /* 초기값을 0으로 하면 바로 XOR 가능..ㅋㅋ 잘못생각했음.
            // 초기값이라면 다른 것 없이 해당 값 입력.
            if(answer == -1) answer = S;
            // 초기값이 아니라면 bitwiseXOR을 계산하여 누적.
            else answer = answer ^ S;
            */

            // bitwiseXOR : 다르면 1, 같으면 0
            // - 초기값이 0이라면 어떤 수를 XOR 하더라도 해당 값으로 나옴.
            // - ex) 0 ^ 15 = 0000(2) ^ 1111(2) = 1111(2) = 15
            answer = answer ^ S;
        }

        return answer;
    }
}
