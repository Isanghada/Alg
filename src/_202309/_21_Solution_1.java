package _202309;

// https://school.programmers.co.kr/learn/courses/30/lessons/12984
// - 이분 탐색 활용!
// - 어떻게 작은 방향을 찾을지 고민을 많이 했다.
// - 초기 left, right 코스트를 비교하는 방법을 생각했는데 같은 경우가 있을 것 같아 폐기
// - mid와 mid+1 혹은 mid-1의 값을 비교하여 코스트가 작아지는 방향으로 탐색!
public class _21_Solution_1 {
    // 함수에 활용한 변수들 static 선언
    public static long N;   // 배열 크기
    public static int P;    // 추가 코스트
    public static int Q;    // 제거 코스트
    public static int[][] land; // 지형 상태

    public long solution(int[][] land, int P, int Q) {
        // 정답 초기화
        long answer = Long.MAX_VALUE;

        // 입력값 설정
        this.N = land.length;
        this.P = P;
        this.Q = Q;
        this.land = land;

        // 이분 탐색을 위해 left, right 설정
        // - left : 땅의 최소 높이
        // - right : 땅의 최고 높이
        long left = Integer.MAX_VALUE;
        long right = 0;

        // - 모든 지형을 탐색해 left, right 입력
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                if(left > land[r][c]) left = land[r][c];
                if(right < land[r][c]) right = land[r][c];
            }
        }

        // 이분 탐색을 통해 최소 코스트 탐색
        while(left <= right){
            // 중간 계산
            long mid = (left + right) / 2;

            // 중간 기준 2개의 코스트 계산
            // - 일반적으로 한 쪽이 값이 작음. => 값이 작아지는 방향으로 재탐색
            // - 동일한 경우도 있지만, 그런 경우는 해당 값이 최소.
            long leftCost = getCost(mid);
            long rightCost = getCost(mid+1);

            // 왼쪽 코스트가 작은 경우
            if(leftCost < rightCost){
                // 값 최소값으로 변경
                answer = Math.min(answer, leftCost);
                // right 범위 조정
                right = mid - 1;
            // 오른쪽 코스틀가 작은 경우
            }else{
                // 값 최소값으로 변경
                answer = Math.min(answer, rightCost);
                // left 벙뮈 조정
                left = mid + 1;
            }
        }

        return answer;
    }

    // 코스트 계산 함수 : mid를 기준으로 코스트 계산
    private long getCost(long mid) {
        // 코스트 초기화
        long cost = 0;

        // 모든 좌표에 대해 비용 계산
        for(int r = 0; r < this.N; r++){
            for(int c = 0; c < this.N; c++){
                // 높이가 mid와 동일한 경우 패스
                if(this.land[r][c] == mid) continue;
                // 차이 계산
                long diff = this.land[r][c] - mid;
                // 차이 * ((-추가 비용) or 삭제 비용)
                // - diff가 음수인 경우 추가해야하므로 추가 비용
                // - diff가 양수인 경우 삭제해야하므로 삭제 비용
                cost += diff * (diff < 0 ? -P : Q);
            }
        }
        
        // 값 반환
        return cost;
    }
}
