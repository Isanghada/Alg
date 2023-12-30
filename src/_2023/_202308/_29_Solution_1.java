package _2023._202308;

import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/214288
// - 각 상담사 인원수에 따른 대기 시간을 계산하고 Backtracking으로 모든 경우 탐색
public class _29_Solution_1 {
    // 정답
    public static int answer;
    // 상담사 인원수별 대기시간
    public static int[][] waitTimeArr;
    public int solution(int k, int n, int[][] reqs) {
        // 정답 초기화
        answer = Integer.MAX_VALUE;

        // 대기시간 초기화
        // - [i][j] : i 종류의 상담사가 j명이 경우의 대기 시간
        // - k+1 : 상담사 타입을 1 ~ k로 사용하기 위해
        // - n-k+2 : 상담사 인원수는 최소 1 최대 n-k+1이므로
        waitTimeArr = new int[k+1][n-k+2];

        // 타입별, 인원수별 대기시간 계산
        // - type : 상담 종류
        // - count : 인원수
        for(int type = 1; type <= k; type++){
            for(int count = 1; count <= n-k+1; count++){
                // 대기 시간
                int wait = 0;

                // 우선순위큐로 가장 빨리 끝나는 상담사에 신청
                PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
                // 인원수 만큼 추가
                for(int c = 0; c < count; c++) priorityQueue.offer(0);

                // 전체 상담 요청에서 해당하는 타입만 사용
                for(int[] req : reqs){
                    // 타입이 다른 경우 패스
                    if(type != req[2]) continue;;

                    // 가장 빨리 끝나는 상담사 반환
                    int time = priorityQueue.poll();
                    // 차이 반환
                    int diff = time - req[0];
                    // 음수가 되는 경우는 상담이 끝나는 시간보다
                    // 요청하는 시간이 늦는 경우이므로 대기 시간이 없음
                    wait += diff >= 0 ? diff : 0;
                    // 상담 끝나는 시간, 요청 시간 중 큰 값 선택 후 상담 시간을 더해서 우선순위큐에 추가
                    priorityQueue.offer(Math.max(time, req[0]) + req[1]);
                }

                // 대기시간이 0인경우 상담사가 늘어나도 동일하므로
                // 이번 count부터 모두 0 입력 (초기값이 0이므로 fill을 사용하지 않아도 된다)
                if(wait == 0) {
//                    Arrays.fill(waitTimeArr[type], count, n-k+2, 0);
                    break;
                // 대기 시간이 있는 경우 해당 값 입력
                }else waitTimeArr[type][count] = wait;
            }
        }

//        for(int[] waitTime : waitTimeArr)
//            System.out.println(Arrays.toString(waitTime));

        // 최소 대기 시간 계산 함수
        getMinWaitTime(k, n, 0, 1, 0);
        return answer;
    }

    // 최소 대기 시간 계산 함수
    // - k : 상담 타입의 수
    // - n : 전체 상담사의 수
    // - totalCount : 배정된 상담사의 수
    // - type : 현재 상담 타입
    // - waitTime : 대기 시간
    private void getMinWaitTime(int k, int n, int totalCount, int type, int waitTime) {
        // 대기 시간이 현재 정답 이상이거나 배정된 상담사수가 전체 상담사 이상일 경우 종료
        if(answer <= waitTime || totalCount >= n) return;
        // 마지막 상담 타입일 경우
        if(type == k){
            // 배정할 수 있는 최대의 상담사일 경우의 대기시간 추가
            waitTime += waitTimeArr[type][n-totalCount];
            // 정답 최소값으로 업데이트
            answer = Math.min(answer, waitTime);
            return;
        }
        // 배정 가능한 인원수로 모두 진행
        for(int count = 1; count <= n-k+1; count++){
            // 다음 상담 타입으로 재귀
            getMinWaitTime(k, n, totalCount+count, type+1, waitTime + waitTimeArr[type][count]);
        }
    }
}
