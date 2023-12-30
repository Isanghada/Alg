package _2023._202308;

// https://school.programmers.co.kr/learn/courses/30/lessons/131703?language=java
// 브루트포스 : 열과 행을 모두 뒤집는게 아니라 하나만 정해서 뒤집고 값 확인!.
// - 열과 행 한 쪽만 뒤집고 다른 방향을 확인하면 동일하거나 뒤집혀있거나 완전 다르거나!
// - 뒤집힌 경우만큼 카운트를 늘리면 끝!
// - 완전 다를 경우는 그냥 넘어가!
public class _16_Solution_1 {
    // 메모리를 효율적으로 사용하기 위해 입력값을 static으로 사용
    public static int[][] cur,targetArr;
    // 정답 변수
    public static int answer;
    public int solution(int[][] beginning, int[][] target) {
        // 정답 초기화
        answer = -1;

        // 입력값 static 변수에 입력.
        cur = beginning;
        targetArr = target;

        // 행과 열 크기 입력.
        int row = beginning.length;
        int col = beginning[0].length;

        // 최소값을 찾는 재귀 함수 사용.
        getMinCount(0, row, col, 0);

        return answer;
    }
    // 최소값을 찾는 재귀 함수
    // - step : 현재 행 인덱스
    // - row : 행 크기
    // - col : 열 크기
    // - count : 뒤집은 횟수
    private void getMinCount(int step, int row, int col, int count) {
        // 모든 행에 동작을 한 경우
        if(step == row){
            // 각 열 상태 확인
            for(int c = 0; c < col; c++){
                // 열의 상태 확인
                // - 0 : 동일한 상태
                // - 1 : 뒤집힌 상태
                // - -1 : 완전히 다른 상태
                int compare = compareCol(row, c);
                
                // -1인 경우 종료
                if(compare == -1){
                    return;
                // 0, 1인 경우 값만큼 count 증가
                }else{
                    count += compare;
                }
            }
            // answer이 -1인 경우 값 변경
            if(answer == -1) answer = count;
            // 아니라면 최소값으로 변경
            else answer = Math.min(answer, count);
        // 행에 대한 동작별로 다음 재귀 함수 실행
        }else{
            // 행을 뒤집지 않고 다음 행 실행
            getMinCount(step+1, row, col, count);

            // 행을 뒤집고 다음 행 실행
            flipRow(step, col);
            getMinCount(step+1, row, col, count+1);

            // 행을 다시 원래 상태로 복구
            flipRow(step, col);
        }
    }

    // 행을 뒤집는 함수
    // - step : 뒤집을 행
    // - col : 열 크기
    private void flipRow(int step, int col) {
        // 해당하는 행을 뒤집는다.
        // - 0 일 경우 1로
        // - 1 일 경우 0으로
        for(int c = 0; c < col; c++){
            cur[step][c] = (cur[step][c] == 0 ? 1 : 0);
        }
    }

    // 열을 확인하는 함수
    // - row : 행 크기
    // - col : 확인할 열
    private int compareCol(int row, int col) {
        // 서로 동일한 값의 개수
        int count = 0;
        // 해당 하는 열에 대해 모든 값 확인.
        for(int r = 0; r < row; r++){
            // 값이 동일할 경우 count 증가
            if(cur[r][col] == targetArr[r][col]) count++;
        }
        // 0인 경우 : 뒤집힌 경우이므로 1 반환
        if(count == 0) return 1;
        // row와 동일한 경우 : 동일한 경우이므로 0 반환
        else if(count == row) return 0;
        // 이외의 경우 : 동일하게 만들 수 없으므로 -1 반환
        else return -1;
    }
}
