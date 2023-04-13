package _202304._13;

import java.util.*;

public class Solution1 {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int size : tangerine){
            if(map.containsKey(size)){
                map.put(size, map.get(size) + 1);
            }else{
                map.put(size, 1);
            }
        }

        int count = 0;
        List<Integer> values = new ArrayList<>(map.values());
        Collections.sort(values, Collections.reverseOrder());
        for(int value : values){
            count += value;
            answer += 1;
            if(count >= k) break;
        }

        return answer;
    }
}
