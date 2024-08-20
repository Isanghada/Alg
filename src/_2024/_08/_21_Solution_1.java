package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21772
// - 브루트포스 : 가능한 모든 경우 탐색!
public class _21_Solution_1 {
    private static int R, C, T; // 방 크기 및 시간
    private static char[][] MAP;    // 방 정보 배열
    private static int[] G; // 가희의 위치
    // 이동 방향
    private static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());   // 방 행 크기
        C = Integer.parseInt(st.nextToken());   // 방 열 크기
        T = Integer.parseInt(st.nextToken());   // 남은 시간

        // 방 정보 입력
        MAP = new char[R][C];
        for(int r = 0;r < R; r++){
            MAP[r] = in.readLine().toCharArray();
            for(int c = 0; c < C; c++){
                // 가희의 위치 입력
                if(MAP[r][c] == 'G') G = new int[]{r, c};
            }
        }
//        System.out.println(R+", "+C+", "+T);
//        for(char[] m : MAP) System.out.println(Arrays.toString(m));
        
        // 정답 출력
        // - dfs를 통해 가장 많은 고구마의 개수 탐색
        sb.append(dfs(G, T));
        System.out.println(sb);
    }

    private static int dfs(int[] g, int t) {
        // 시간이 없는 경우 0 반환!
        if(t == 0) return 0;

        int count = 0;  // 먹은 고구마의 최대 개수
        char prev;  // 이동할 위치의 원래 값
        int plus;   // 고구마 여부!
        for(int[] d : DELTA){
            // 다음 위치!
            int[] next = new int[]{g[0]+d[0], g[1]+d[1]};

            // 아래의 경우 패스
            // - 범위를 벗어나는 경우
            // - 장애물이 있는 경우
            if(next[0] < 0 || next[0] >= R || next[1] < 0 || next[1] >= C ||
                    MAP[next[0]][next[1]] == '#'
            ) continue;

            // 다음 위치에 고구마가 있으면 1, 없으면 0!
            plus = (MAP[next[0]][next[1]] == 'S') ? 1 : 0;
            // 다음 위치의 원래 값!
            prev= MAP[next[0]][next[1]];

            // 다음 위치로 이동! : 고구마를 먹으므로 값 변환
            MAP[next[0]][next[1]] = '.';
            // 고구마 최대 값 갱신!
            count = Math.max(count, dfs(next, t-1)+plus);
            // 원래 상태로 복구!
            MAP[next[0]][next[1]] = prev;
        }
        return count;
    }
}