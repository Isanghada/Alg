package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16509
// - BFS : 상이 이동할 수 있는 모든 위치 탐색!
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] start = getPoint(in.readLine());  // 상의 위치
        int[] end = getPoint(in.readLine());    // 왕의 위치

        // 정답 출력
        // - BFS를 통해 왕으로 이동하는 최소 횟수 계산!
        sb.append(getCount(start, end, 10, 9));
        System.out.println(sb.toString());
    }
    // 이동 클래스
    private static class DELTA{
        int[] d;        // 이동 변수
        int[][] checks; // 이동 경로!
        public DELTA(int[] d, int [][] checks){
            this.d = d;
            this.checks = checks;
        }
    }
    private static final DELTA[] D = new DELTA[]{
            new DELTA(new int[]{-3, -2}, new int[][]{{-1, 0}, {-1, -1}}),
            new DELTA(new int[]{-3, 2}, new int[][]{{-1, 0}, {-1, 1}}),

            new DELTA(new int[]{3, -2}, new int[][]{{1, 0}, {1, -1}}),
            new DELTA(new int[]{3, 2}, new int[][]{{1, 0}, {1, 1}}),

            new DELTA(new int[]{-2, -3}, new int[][]{{0, -1}, {-1, -1}}),
            new DELTA(new int[]{2, -3}, new int[][]{{0, -1}, {1, -1}}),

            new DELTA(new int[]{-2, 3}, new int[][]{{0, 1}, {-1, 1}}),
            new DELTA(new int[]{2, 3}, new int[][]{{0, 1}, {1, 1}})
    };
    private static int getCount(int[] start, int[] end, int rSize, int cSize) {
        // 덱, 방문 배열 초기화
        Deque<int[]> deque = new LinkedList<>();
        boolean[][] visited = new boolean[rSize][cSize];

        // 초기값 입력!
        deque.offerLast(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        // 덱이 빌떄까지 반복
        while(!deque.isEmpty()){
            // 현재 위치 반환
            int[] cur = deque.pollFirst();

//            System.out.println(cur[0]+", "+cur[1]+", "+cur[2]);

            // 왕의 위치일 경우 이동 횟수 반환!
            if(isEquals(cur, end)) return cur[2];
            else{
                // 다음 위치 탐색
                for(DELTA d : D){
                    // 다음 좌표 계산
                    int[] next = new int[]{
                        cur[0] + d.d[0],
                        cur[1] + d.d[1],
                        cur[2] + 1
                    };

                    // 범위를 벗어나거나 이미 방문한 경우 패스
                    if(next[0] < 0 || next[0] >= rSize || next[1] < 0 || next[1] >= cSize ||
                            visited[next[0]][next[1]]
                    ) continue;

                    // 경로상 다른 기물이 없는 경우만 이동!
                    if(isPossible(cur, d.checks, end)){
                        deque.offerLast(next);
                        visited[next[0]][next[1]] = true;
                    }
                }
            }
        }
        // 왕의 위치로 이동할 수 없다면 -1 반환
        return -1;
    }
    // 경로상 다른 기물 여부 확인 함수
    private static boolean isPossible(int[] point, int[][] checks, int[] end) {
        int[] p = new int[]{point[0], point[1]};
        for(int i = 0; i < 2; i++){
            p[0] += checks[i][0];
            p[1] += checks[i][1];
            // 다른 기물이 있다면 false
            if(isEquals(p, end)) return false;
        }
        // 다른 기물이 없다면 true
        return true;
    }

    private static boolean isEquals(int[] cur, int[] end) {
        if(cur[0] == end[0] && cur[1] == end[1]) return true;
        else return false;
    }

    private static int[] getPoint(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int[] point = new int[]{
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };
        return point;
    }
}
