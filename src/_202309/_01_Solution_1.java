package _202309;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/12979?language=java
// - 범위를 나누고 해당 범위에 필요한 기지국의 수를 계산!
// - 기지국의 범위는 동일하므로 앞의 기지국이 뒤의 기지국 범위를 넘어설 수 없음.
public class _01_Solution_1 {
    public int solution(int n, int[] stations, int w) {
        // 정답 초기화
        int answer = 0;
        
        // 마지막 인덱스 상수
        final int lastIdx = stations.length - 1;
        // 기지국 범위 상수
        final int RANGE = 2 * w + 1;
        
        // 각 기지국 별로 앞, 뒤의 범위 확인
        for(int idx = 0; idx < stations.length; idx++){
            // left right 입력
            int left;
            int right = stations[idx] - w - 1;
            // 첫 기지국일 경우 left는 1로 고정
            if(idx == 0){
                left = 1;
            // 아닐 경우 이전 기지국의 오른쪽 범위 + 1
            }else{
                left = stations[idx-1] + w + 1;
            }
            
            // 왼쪽 범위 계산
            int curRange = right - left + 1;
            // 0보다 클 경우만 진행
            if(curRange > 0) {
                // 몫만큼 증가!
                answer += curRange / RANGE;
                // 나머지가 있을 경우 1개의 기지국 추가
                answer += curRange % RANGE > 0 ? 1 : 0;
            }

            // 마지막 인덱스일 경우 오른쪽 범위까지 확인
            if(idx == lastIdx) {
                //오른쪽 범위 계산
                curRange = n - (stations[idx] + w);
                // 0보다 클 경우만 기지국 추가!
                if(curRange < 0) continue;
                // 몫만큼 증가
                answer += curRange / RANGE;
                // 나머지가 있을 경우 1개의 기지국 추가
                answer += curRange % RANGE > 0 ? 1 : 0;
            }
        }

        return answer;
    }
}
