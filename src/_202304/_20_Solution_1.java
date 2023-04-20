package _202304;

import java.util.*;

// 전체 경우 탐색
public class _20_Solution_1 {
    public int solution(int x, int y, int n) {
        int[] count = new int[y + 1];
        Arrays.fill(count, -1);  // 아직 계산되지 않은 경우 -1로 초기화
        count[x] = 0;  // 시작점의 연산 횟수는 0

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 첫 번째 연산
            int next = current + n;
            if (next <= y && count[next] == -1) {
                count[next] = count[current] + 1;
                queue.offer(next);
            }

            // 두 번째 연산
            next = current * 2;
            if (next <= y && count[next] == -1) {
                count[next] = count[current] + 1;
                queue.offer(next);
            }

            // 세 번째 연산
            next = current * 3;
            if (next <= y && count[next] == -1) {
                count[next] = count[current] + 1;
                queue.offer(next);
            }
        }

        return count[y];
    }
}
