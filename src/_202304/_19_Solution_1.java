package _202304;

import java.util.Arrays;

// 전체 경우 탐색
public class _19_Solution_1 {
    public long solution(int[] weights) {
        long answer = 0;

        // 빠른 계산을 위해 정렬
        Arrays.sort(weights);

        int count = 0;
        int weightLen = weights.length;
        for(int i = 0; i <weightLen - 1; i++){
            if(i != 0){
                // 직전과 같으면 이전값을 빼고 더한다.
                if (weights[i] == weights[i-1]){
                    count--;
                    answer += count;
                    continue;
                }
            }
            // 새로운 값일 경우 0으로 초기화
            count = 0;
            // i 이후의 모든 수와 비교
            for(int j = i + 1; j < weightLen; j++){
                if(
                        weights[i] == weights[j] ||
                        weights[i] * 2 == weights[j] * 3 ||
                        weights[i] * 2 == weights[j] * 4 ||
                        weights[i] * 3 == weights[j] * 2 ||
                        weights[i] * 3 == weights[j] * 4 ||
                        weights[i] * 4 == weights[j] * 2 ||
                        weights[i] * 4 == weights[j] * 3
                ) count++;
            }
            answer += count;
        }

        return answer;
    }

    // HashMap을 이용한 풀이
    /*public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        HashMap<Double,Integer> map = new HashMap<>();

        for(Integer weight:weights){
            double w = Double.valueOf(weight);
            if(map.containsKey(w)){
                answer+=map.get(w);
            }
            map.put(w,map.getOrDefault(w,0)+1);
            map.put(w*4/3,map.getOrDefault(w*4/3,0)+1);
            map.put(w*1.5,map.getOrDefault(w*1.5,0)+1);
            map.put(w*2,map.getOrDefault(w*2,0)+1);
        }

        return answer;
    }*/
}
