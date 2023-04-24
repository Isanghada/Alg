package _202304;

public class _24_Solution_1 {
    public int solution(String[][] book_time) {
        int answer = 0;  // 최소한의 객실
        int MAX_TIME = 23*60+59;  // 시간을 int형으로 변환했을 때 최대값

        // 시간별로 이용하는 손님의 수
        // - 누적합을 이용해 모든 경우의 손님 수를 계산한다.
        int[] count = new int[MAX_TIME+11];
        for(String[] time : book_time){
            // 손님의 이용 시간 int형으로 변환
            // - start : 대실 시작
            // - end : 대실 종료(대실 종료 후 10분간 이용할 수 없으므로 +10)
            int start = timeToInt(time[0]);
            int end = timeToInt(time[1]) + 10;

            // 시작 시간에 손님 수 추가
            count[start]++;
            // 종료 시간에 손님 수 감소
            count[end]--;
        }

        // 모든 구간의 누적합 계산
        // - 계산을 하며 각 시간대 중 최대 손님수를 정답으로 업데이트
        for(int i = 1; i <= MAX_TIME; i++){
            // i 시간대의 손님 수 계산
            count[i] += count[i-1];
            // answer 업데이트
            answer = Math.max(answer, count[i]);
        }

        return answer;
    }

    // 시간을 int형으로 변환하는 함수
    // - HH:MM을 변환하여 분으로 반환
    private int timeToInt(String time) {
        // 시간을 :을 기준으로 분리
        String[] split = time.split(":");

        // 시간 부분은 * 60을 해주어 분으로 변환
        // 분 부분은 그대로 사용
        // - 두 값을 더하여 반환
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
