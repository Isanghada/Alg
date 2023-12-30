package _2023._202304._15;

import java.util.PriorityQueue;

public class Solution1 {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        int len = enemy.length;

        // 우선순위큐에 이전 값들을 추가하며 최대값 유지.
        // - 우선순위큐 기본은 오름차순 정렬!. 내림차순을 위해선 comparator을 따로 설정하거나 Comparable 인터페이스 사용
        // - 만약, 막을 수 없을 경우 최대값 복원. => 무적 사용
        // - 전부 막았거나, 막을 수 없는데 무적 횟수도 없다면 종료.
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer i1, Integer i2) -> i2 - i1);
        for (int i = 0; i < len; i++) {
            pq.offer(enemy[i]);
            n -= enemy[i];
            if(n < 0) {
                if(k > 0 && !pq.isEmpty()) {
                    n += pq.poll();
                    k--;
                }else break;
            }
            answer++;
        }

        return answer;
    }
}
