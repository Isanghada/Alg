package _2023._202310;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/1838
// - 누적합과 브루트 포스로 해결
// - 최대 인원수를 누적합으로 계산하고 가능한 모든 경우를 탐색하여 최대 거리 계산
public class _02_Solution_1 {
    public int solution(int n, int m, int[][] timetable) {
        // 시간별 인원수 배열 : 누적합으로 계산
        int[] timeSum = new int[722];
        // - 입장 시간 :  +1
        // - 퇴장 시간 + 1 : -1
        for(int[] time : timetable) {
            timeSum[time[0] - 600]++;
            timeSum[time[1] - 599]--;
        }

        // 최대 인원수
        int max = 0;
        // - 누적합으로 인원수를 계산하며 max 계산
        for(int i = 1; i <= 720; i++){
            timeSum[i] += timeSum[i-1];
            max = Math.max(max, timeSum[i]);
        }

        // 최대 인원수가 1명 이하인 경우 0 반환
        if(max <= 1) return 0;

        // 최대 거리부터 모든 경우 탐색
        for(int dist = 2 * (n - 1); dist >= 1; dist--){
            // 시작 행의 열 좌표
            for(int start = 0; start < n; start++){
                // 좌표 리스트
                List<int[]> list = new ArrayList<>();
                // 좌표 개수
                int cnt = 0;
                // 모든 행 좌표
                for(int r = 0; r < n; r++){
                    // 모든 열 좌표
                    for(int c = 0; c < n; c++){
                        // 시작 행일 경우 start 미만인 열 좌표는 패스
                        if(r == 0 && c < start) continue;

                        // dist 가능 여부
                        boolean flag = true;
                        // 모든 좌표와 비교
                        for(int[] p : list){
                            // dist 이상인 경우 패스
                            if(Math.abs(p[0] - r) + Math.abs(p[1] - c) >= dist) continue;
                            // flag 변경 후 종료
                            flag = false;
                            break;
                        }

                        // 가능한 경우
                        if(flag){
                            // cnt와 max가 같은 경우 dist 반환
                            if(++cnt == max) return dist;
                            // 아닐 경우 좌표 추가
                            list.add(new int[] {r, c});
                        }
                    }
                }
            }
        }

        return 0;
    }
}
