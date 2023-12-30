package _2023._202304._12;

import java.util.Deque;
import java.util.LinkedList;

public class Solution1 {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];
        int[][] delta = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] == 0 || visited[i][j]) continue;

                numberOfArea++;

                Deque<int[]> deque = new LinkedList<>();
                int standard = picture[i][j];
                deque.offerLast(new int[]{i, j});
                visited[i][j] = true;

                int curArea = 0;
                while(!deque.isEmpty()){
                    int[] cur = deque.pollFirst();
                    curArea++;

                    for(int[] d : delta){
                        int newI = cur[0] + d[0];
                        int newJ = cur[1] + d[1];

                        if(newI < 0 || newI >= m || newJ < 0 || newJ >= n ||
                                picture[newI][newJ] != standard ||
                                visited[newI][newJ]
                        ) continue;

                        visited[newI][newJ] = true;
                        deque.offerLast(new int[] {newI, newJ});
                    }
                }
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, curArea);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}
