package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3987
// - 브루트포스 : 시작 좌표에서 모든 방향으로 이동하며 탐색
public class _03_Solution_1 {
    public static String DIR = "URDL";
    // 이동 변수
    public static int[][] DELTA = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기

        // 항성계 정보
        char[][] map = new char[N][M];
        for(int r = 0; r < N; r++){
            map[r] = in.readLine().toCharArray();
        }

        st = new StringTokenizer(in.readLine());
        int PR = Integer.parseInt(st.nextToken()) - 1;  // 시작 행
        int PC = Integer.parseInt(st.nextToken()) - 1;  // 시작 열

        // 최대 시간
        int time = 0;
        // 초기 방향
        int dir = 0;
        // 4방향 모두 탐색
        for(int d = 0; d < 4; d++){
            // 현재 좌표 설정
            int cr = PR;
            int cc = PC;
            // 이동 방향 설정
            int cd = d;
            // 현재 시간 설정
            int ct = 1;
            // 최대 시간을 넘어갈 경우 종료
            while(true){
                // 이동 방향으로 이동
                int nr = cr + DELTA[cd][0];
                int nc = cc + DELTA[cd][1];

                if(nr == PR && nc == PC && d == cd){
                    ct = 1000000;
                    break;
                }

                // 이동할 수 없는 경우 종료
                if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 'C') break;

                // 행성인 경우 이동 방향 변경
                if(map[nr][nc] != '.') cd = change(map[nr][nc], cd);

                // 좌표 이동
                cr = nr;
                cc = nc;
                // 시간 증가
                ct++;
            }
            // 최대 시간보다 길게 이동한 경우 정답 갱신!
            if(time < ct){
                time = ct;
                dir = d;
            }
        }

        // 정답 반환
        // - 이동 방향 출력
        // - 무한히 전파되면 Voyager, 아니라면 시간 출력
        sb.append(DIR.charAt(dir)).append("\n").append(time == 1000000 ? "Voyager" : time);
        System.out.println(sb);
    }

    // 상, 우, 하, 좌 순서
    private static int change(char c, int cd) {
        int nd = 0;
        if(c == '/'){
            if(cd == 0) nd = 1;
            else if(cd == 1) nd = 0;
            else if(cd == 2) nd = 3;
            else nd = 2;
        }else{
            if(cd == 0) nd = 3;
            else if(cd == 1) nd = 2;
            else if(cd == 2) nd = 1;
            else nd = 0;
        }

        return nd;
    }
}
