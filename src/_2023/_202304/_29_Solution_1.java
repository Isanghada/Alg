package _2023._202304;

import java.util.HashMap;
import java.util.Map;

public class _29_Solution_1 {
    // 최소 피로도를 담을 변수
    public static int answer;
    // 광석별로 곡괭이 사용시 피로도를 담을 변수
    public static Map<String, Integer[]> degree = null;
    public int solution(int[] picks, String[] minerals) {
        // 피로도의 최소값을 구해야하므로 최대값으로 초기화
        answer = Integer.MAX_VALUE;
        // 곡괭이의 수 계산
        int countPicks = 0;
        for(int pick : picks) countPicks += pick;
        // 광석의 수 계산
        int countMinerals = minerals.length;

        // 광석별 곡괭이 사용시 피로도 입력
        degree = new HashMap<>();
        degree.put("diamond", new Integer[]{1, 5, 25});
        degree.put("iron", new Integer[]{1, 1, 5});
        degree.put("stone", new Integer[]{1, 1, 1});

        // 백트래킹을 통해 가능한 모든 경우 탐색
        backtracking(0, countPicks, countMinerals, picks, minerals, 0);

        return answer;
    }

    // 백트래킹을 통해 가능한 모든 경우의 피로도 계산 후 최소값으로 변경
    private void backtracking(int step, int countPicks, int countMinerals, int[] picks, String[] minerals, int value) {
        // 모든 곡괭이를 썼거나 광석을 모두 캔 경우
        // 피로도 비교 후 최소값으로 업데이트
        if(step == countPicks || step * 5 >= countMinerals){
            answer = Math.min(answer, value);
        }else{
            // 광석 시작 인덱스 : 현재 곡괭이 사용 개수에 따라 선택
            int startIdx = step * 5;
            // 광석 마지막 인덱스 : 시작 인덱스 기준 + 5, 미네랄 수 중 작은 값 선택
            int endIdx = Math.min(startIdx + 5, countMinerals);
            // 모든 곡괭이별로 현재 단계 실행
            for(int pickIdx = 0; pickIdx < 3; pickIdx++){
                // 곡괭이를 모두 사용했다면 패스.
                if (picks[pickIdx] == 0) continue;
                // 곡괭이 수 감소
                picks[pickIdx]--;
                // 현재 곡괭이 사용시 피로도 계산
                int curValue = 0;
                // 최대 5개의 광석 채취
                for(int mineralIdx = startIdx; mineralIdx < endIdx; mineralIdx++){
                    curValue += degree.get(minerals[mineralIdx])[pickIdx];
                }
                // 다음 곡괭이를 사용하기 위해 재귀.
                backtracking(step+1, countPicks, countMinerals, picks, minerals, value+curValue);
                // 곡괭이 수 증가
                picks[pickIdx]++;
            }
        }
    }
}
