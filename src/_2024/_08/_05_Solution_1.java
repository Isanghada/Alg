package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20208
// - 브루트포스 : 집에서 시작하여 가능한 모든 경우 탐색!
public class _05_Solution_1 {
    // N : 마을 크기
    // M : 초기 체력
    // H : 체력 회복
    // ANSWER : 정답(마실 수 있는 민트초코우유의 최대 개수)
    public static int N, M, H, ANSWER;
    // BIT : 비트 값!
    public static int[] BIT;
    // MAP : 마을 정보 배열
    public static int[][] MAP;
    // MILK_LIST : 우유 리스트
    public static List<Point> MILK_LIST;
    // HOME : 집
    public static Point HOME;
    // 좌표 클래스
    public static class Point{
        int row;    // 행 좌표
        int col;    // 열 좌표
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 마을 크기
        M = Integer.parseInt(st.nextToken());   // 초기 체력
        H = Integer.parseInt(st.nextToken());   // 체력 회복

        // 마을 정보 초기화
        MAP = new int[N][N];
        // 우유 리스트 초기화
        MILK_LIST = new ArrayList<>();
        // 마을 정보 입력
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++) {
                MAP[r][c] = Integer.parseInt(st.nextToken());
                // 집일 경우 HOME 입력
                if(MAP[r][c] == 1) HOME = new Point(r, c);
                // 우유일 경우 리스트에 추가
                else if(MAP[r][c] == 2) MILK_LIST.add(new Point(r, c));
            }
        }

        // 비트 정보 초기화 : 비트마스크 계산에 활용
        BIT = new int[10];
        BIT[0] = 1;
        for(int bit = 1; bit < 10; bit++) BIT[bit] = BIT[bit-1] << 1;

        // 브루트포스 함수 : 모든 경우 탐색!
        bruteforce(0, HOME, M, 0, 0);

        // 정답 반환
        sb.append(ANSWER);
        System.out.println(sb);
    }

    private static void bruteforce(int step, Point cur, int hp, int bitmask, int count) {
        // 현재 지점에서 집으로 돌아갈 수 있는 경우 : 최대값으로 갱신!
        if(getCostOfMoving(cur, HOME) <= hp) ANSWER = Math.max(ANSWER, count);

        // 모든 우유 탐색!
        for(int idx = 0; idx < MILK_LIST.size(); idx++){
            // 이미 방문한 경우 패스
            if((bitmask & BIT[idx]) > 0) continue;
            // 비용 계산
            int cost = getCostOfMoving(cur, MILK_LIST.get(idx));
            // 현재 체력으로 가능한 경우 탐색
            if(cost <= hp){
                bruteforce(step+1,
                        MILK_LIST.get(idx),
                        hp-cost+H,
                        bitmask | BIT[idx],
                        count+1
                );
            }
        }
    }

    private static int getCostOfMoving(Point cur, Point next) {
        int cost = Math.abs(next.row - cur.row) + Math.abs(next.col - cur.col);
        return cost;
    }
}
