package _2023._202304;

// 전체 경우 탐색
public class _17_Solution_1 {
    public static int answer;  // 정답
    public int solution(int storey) {
        answer = Integer.MAX_VALUE;
        calculation(storey, 0);
        return answer;
    }

    // 최소 횟수 계산 : 재귀 활용
    private void calculation(int storey, int value) {
        // 현재 층이 0이면 종료
        if(storey == 0) {
            answer = Math.min(answer, value);
        }
        else{
            // 현재 일의 자리 계산
            int cur = storey % 10;
            // 일의 자리 제거
            int next = storey / 10;

            // -cur로 이동
            calculation(next, value + cur);
            // (10-cur)로 이동 : 단, 5층 미만은 내려가는게 빠르므로 재귀 X
            if(cur >= 5) calculation(next+1, value + (10 - cur));
        }
    }
}
