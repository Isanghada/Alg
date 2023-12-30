package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15558
// - BFS를 통해 가능한 경우 탐색
public class _20_Solution_1 {
    public static int N, K;
    public static char[][] BOARD;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202311/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());   // 칸의 크기
        K = Integer.parseInt(st.nextToken());   // 반대편 이동 칸수
        
        // 칸 정보 : 칸 크기를 벗어나는 경우는 1로 초기화
        // - [0][i] : 왼쪽 칸 i번째 칸
        // - [1][i] : 오른쪽 칸 i번째 칸
        BOARD = new char[2][N+K+1];
        for(int i = 0; i < 2; i++){
            char[] input = in.readLine().toCharArray();
            for(int idx = 0; idx < N; idx++){
                BOARD[i][idx] = input[idx];
            }

            for(int idx = N; idx < BOARD[i].length; idx++)
                BOARD[i][idx] = '1';
        }

        // 정답 출력
        // - BFS를 통해 가능하면 1, 불가능한 경우 0 반환
        sb.append(isClear());
        System.out.println(sb);
    }

    // 클리어 여부 탐색 함수 : BFS를 통해 가능 여부 탐색
    private static int isClear() {
        // 이동 방향
        final int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, K}};

        // 덱 초기화
        Deque<int[]> deque = new LinkedList<>();
        // 방문 배열
        boolean[][] visited = new boolean[2][N+K+1];

        // 기본값 설정 : 왼쪽 칸, 처음 칸
        deque.offerLast(new int[]{0, 0, -1});
        visited[0][0] = true;

        // 덱이 빌 때가지 반복
        while(!deque.isEmpty()){
            // 현재 값 반환
            int[] cur = deque.pollFirst();

//            System.out.println(cur[0]+", "+cur[1]+", "+cur[2]);
            // 클리어한 경우 1 반환
            if(cur[1] >= N) return 1;

            // 다음 값 계산
            for(int[] d : DELTA){
                // 다음 좌표
                int[] next = new int[] {cur[0], cur[1] + d[1], cur[2] + 1};
                // 반대편으로 이동한 경우 변환
                if(d[0] == 1) next[0] = (next[0] == 0 ? 1 : 0);
//                System.out.println(next[0]+", "+next[1]+", "+next[2]);

                // 아래의 경우 패스
                // - 범위를 벗어난 경우
                // - 이미 방문한 경우
                // - 위험한 지역인 경우
                // - 시간안에 도달할 수 없는 경우
                if(next[1] < 0 ||
                        visited[next[0]][next[1]] ||
                        BOARD[next[0]][next[1]] == '0' ||
                        next[1] <= next[2])
                    continue;

                // 다음 좌표 추가!
                deque.offerLast(next);
                visited[next[0]][next[1]] = true;
            }
        }

        // 클리어가 불가능한 경우 0 반환
        return 0;
    }
}
