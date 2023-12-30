package _2023._202305;

import java.util.Arrays;
import java.util.Comparator;

public class _25_Solution_1 {
    public int solution(int[][] targets) {
        // 최소 1개 이상의 미사일을 발사하므로 1로 초기화
        int answer = 1;

        // targets를 s(시작점)를 기준으로 오름차순 정렬
        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int diff = Integer.compare(o1[0], o2[0]);
                return diff == 0 ? -Integer.compare(o1[1], o2[1]): diff;
            }
        });

        // 시작점, 종료점 초기화
        int start = -1;
        int end = Integer.MAX_VALUE;
        for(int[] target : targets){
            // 이전까지의 범위와 겹치는지 확인
            if(target[0] <= end-1){
                // 범위 업데이트
                // - 시작점 : 최대값
                // - 종료점 : 최소값
                start = Math.max(start, target[0]);
                end = Math.min(end, target[1]);
            // 범위가 겹치지 않는 경우
            }else{
                // 정답 증가
                answer++;
                // 시작점, 종료점 현재 범위로 초기화
                start = target[0];
                end = target[1];
            }
        }

        return answer;
    }
}
