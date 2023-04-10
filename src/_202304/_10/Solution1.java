package _202304._10;

import java.util.Deque;
import java.util.LinkedList;

public class Solution1 {
    public int solution(int[][] maps) {
        int answer = -1;

        int n = maps.length-1;
        int m = maps[0].length-1;

        boolean[][] visited = new boolean[n+1][m+1];
        visited[0][0] = true;

        Deque<int[]> deque = new LinkedList<>();
        deque.offerLast(new int[]{0, 0, 1});

        int[][] delta = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while(!deque.isEmpty()){
            int[] cur = deque.pollFirst();
            if(cur[0] == n && cur[1] == m){
                answer = cur[2];
                break;
            }

            for(int[] d : delta){
                int[] newPoint = new int[]{cur[0]+d[0], cur[1]+d[1], cur[2]+1};

                if(newPoint[0] < 0 || newPoint[0] > n || newPoint[1] < 0 || newPoint[1] > m
                    || visited[newPoint[0]][newPoint[1]]
                    || maps[newPoint[0]][newPoint[1]] == 0
                )
                    continue;

                visited[newPoint[0]][newPoint[1]] = true;
                deque.offerLast(newPoint);
            }
        }

        return answer;
    }
}
