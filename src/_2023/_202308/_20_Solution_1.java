package _2023._202308;

// https://school.programmers.co.kr/learn/courses/30/lessons/43238?language=java
// 이분 탐색 응용 : 어렵네ㅔ
// - 전체 시간을 mid로 하고 해당 시간 안에 n 이상의 사람을 검사할 수 있는지 확인
public class _20_Solution_1 {
    public long solution(int n, int[] times) {
        // 정답 초기화 : 최소값을 찾아야 하므로 최대값으로
        long answer = Long.MAX_VALUE;

        // 가장 오래 걸리는 시간 확인
        long max_time = 0;
        for(long time : times) max_time = Math.max(max_time, time);

        // 이분 탐색 대상 : 걸린 시간
        // 0분 ~ (max_time * n)분
        long start = 0;
        long end = max_time * n;

        // 이분 탐색 실행
        // - mid 분으로 전체 사람을 검사할 수 있는지 확인.
        // - 각 검사관별 검사할 수 있는 인원 계산하여 합산
        while(start <= end){
            // 현재 mid값 계산
            long mid = (start + end) / 2;

            // 검사할 수 있는 인원수
            long total = 0;

            // 각 검사관별 mid분 동안 검사할 수 있는 사람 수 계산하여 합산
            for(long time : times) total += mid / time;

            // n명 이상을 검사할 수 있는 경우
            if(total >= n){
                // 정답 최소값으로 갱신
                answer = Math.min(answer, mid);
                // end 갱신
                end = mid - 1;
            // 아닐 경우 start 갱신
            }else start = mid + 1;
        }

        return answer;
    }
}
