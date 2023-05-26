package _202305;

import java.util.ArrayList;
import java.util.List;

public class _26_Solution_1 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        // 가능한 배달 거리 리스트 : 먼 거리부터 배달
        List<Integer> deliverList = getDistanceArr(cap, n,deliveries);
        // 가능한 수거 거리 리스트 : 먼 거리부터 수거
        List<Integer> pickupList = getDistanceArr(cap,n, pickups);
        
        // 배달, 수거 가능 리스트의 사이즈를 동일하게 설정
        // - 작은 쪽에 0 추가
        setSameSize(deliverList, pickupList);

        // 배달/수거 길이
        final int N = deliverList.size();
        // 각 배달/수거의 길이 중 긴 경우를 선택하여 거리 계산
        for(int i = 0; i < N; i++){
            answer += Math.max(deliverList.get(i), pickupList.get(i)) * 2;
        }
        return answer;
    }

    // 길이 조정 함수
    // - deliverList, pickupList 중 길이가 작은 쪽에 0을 추가하여 동일하게 설정
    private void setSameSize(List<Integer> deliverList, List<Integer> pickupList) {
        int deliverSize = deliverList.size();
        int pickupSize = pickupList.size();
        if(deliverSize == pickupSize) return;
        else if(deliverSize < pickupSize){
            for(;deliverSize < pickupSize; deliverSize++) deliverList.add(0);
        }else{
            for(;pickupSize < deliverSize; pickupSize++) pickupList.add(0);
        }

    }
    
    // 가능한 거리 리스트 반환 함수
    // - 입력값에 대해 cap만큼 배달 혹은 수거할 수 있는 거리 리스트 반환
    // - 거리는 먼 거리부터 계산
    private List<Integer> getDistanceArr(int cap, int n, int[] inputs) {
        // 결과 리스트
        List<Integer> result = new ArrayList<>();

        // 시작 거리 초기화 : 가장 먼 경우
        int d = n-1;
        while(true){
            int count = 0;  // 배달/수거한 개수
            int temp = d;   // 가장 먼 거리
            // 인덱스 범위까지만 반복
            while(d >= 0){
                // temp위치가 0이라면 d로 변경
                if (inputs[temp] == 0) temp = d;
                // cap을 초과하지 않는 경우
                if(count + inputs[d] <= cap){
                    // count 추가
                    count += inputs[d];
                    // d 감소
                    d--;
                // cap을 초과하는 경우
                }else{
                    // count가 cap이 될때까지만 추가
                    inputs[d] -= cap - count;
                    // 반복 종료
                    break;
                }
            }
            // temp위치가 0보다 큰 경우에 result에 추가
            // - 거리는 1부터 시작하므로 temp + 1로 추가
            if(inputs[temp] > 0) result.add(temp+1);
            // d가 0보다 작을 경우 종료
            if(d < 0) break;
        }

        return result;
    }
}