package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1175
// - BFS를 통해 최소 시간 탐색
// - 같은 이동 방향으로 이동할 수 없고, 선물 전달 여부로 방문한 곳을 따로 표시!
public class _19_Solution_1 {
    // 좌표별 이동 정보 클래스
    public static class Point{
        int r;          // 행 좌표
        int c;          // 열 좌표
        int deltaType;  // 이전 이동 방향
        int giftType;   // 선물 전달 여부 (0 : 미전달, (1, 2) : 둘 중 하나의 선물 전달
        int time;       // 걸린 시간

        public Point(int r, int c, int deltaType, int giftType, int time){
            this.r = r;
            this.c = c;
            this.deltaType = deltaType;
            this.giftType = giftType;
            this.time = time;
        }
    }
    // 함수에서 원활한 사용을 위해 static 선언
    public static int N;    // 행 길이
    public static int M;    // 열 길이
    public static char[][] map; // 교실 정보
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 교실 크기 입력
        N = Integer.parseInt(st.nextToken());   // 행 길이
        M = Integer.parseInt(st.nextToken());   // 열 길이

        // 교실 입력
        map = new char[N][M];
        // 시작점 초기화
        int[] S = new int[2];
        // 선물 전달 지점 초기화
        List<int[]> C = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            // 교실 정보 입력
            map[i] = in.readLine().toCharArray();
            // 시작점과 선물 전달 지점 탐색
            for(int j = 0; j < M; j++){
                // 시작점 설정
                if(map[i][j] == 'S'){
                    S[0] = i;
                    S[1] = j;
                // 선물 전달 지점 추가
                }else if(map[i][j] == 'C') C.add(new int[]{i, j});
            }
        }

        // 최소 시간 반환
        sb.append(getMinTime(S, C));
        // 정답 출력
        System.out.println(sb);
    }

    // 이동 방향 : 상, 하, 좌, 우
    public static int[][] DELTA = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 최소 시간 계산 함수 : BFS를 통해 모든 선물을 전달하는 최소 시간 계산
    // - s : 시작 좌표
    // - c : 선물 전달 좌표 
    private static int getMinTime(int[] s, List<int[]> c) {
        // 덱 초기화
        Deque<Point> pointDeque = new LinkedList<>();
        // 방문 행렬 초기화
        // - visited[giftType][deltaType][r][c]
        //   - giftType : 선물 전달 여부(0 : 미전달, 1 : c의 인덱스 0 전달 완료, 2 : c의 인덱스 1 전달 완료)
        //   - deltaType : 이동 방향
        //   - r : 행 좌표
        //   - c : 열 좌표
        boolean[][][][] visited = new boolean[3][4][N][M];

        // 시작점을 기준으로 추가 : deltaType은 0 ~ 3과 겹치지 않게 설정
        pointDeque.offerLast(new Point(s[0], s[1], -1, 0, 0));
        // 덱이 빌때까지 반복
        while(!pointDeque.isEmpty()){
            // 현재 좌표 정보 반환
            Point cur = pointDeque.pollFirst();
//            StringBuilder sb = new StringBuilder();
//            sb.append("[").append(cur.r).append(", ").append(cur.c).append(", ").append(cur.deltaType).append(", ").append(cur.giftType).append(", ").append(cur.time).append("]");
//            System.out.println(sb);

            // 상, 하, 좌, 우 탐색
            for(int type = 0 ; type < 4; type++){
                // 같은 이동 방향일 경우 패스
                if(cur.deltaType == type) continue;
                // 다음 좌표 정보 생성
                Point next = new Point(cur.r+DELTA[type][0], cur.c+DELTA[type][1], type, cur.giftType, cur.time+1);

                // 좌표를 벗어나거나 이동불가능한 지점일 경우 패스
                if(next.r < 0 || next.r >= N || next.c < 0 || next.c >= M ||
                        map[next.r][next.c] == '#'
                ) continue;
                // 선물 전달 좌표일 경우
                if(map[next.r][next.c] == 'C'){
                    // 전달하는 선물의 타입 계산
                    int gType = 0;
                    // 인덱스 0과 동일한 좌표일 경우 1
                    if(next.r == c.get(0)[0] && next.c == c.get(0)[1]) gType = 1;
                    // 아닐 경우 2로 설정
                    else gType = 2;

                    // 다음 좌표 giftType이 0인 경우 giftType을 변경
                    if(next.giftType == 0) next.giftType = gType;
                    // giftType이 다를 경우 : 모든 선물을 전달한 것이므로 시간 반환
                    else if(next.giftType != gType) return next.time;
                }
                // 이미 방문한 좌표일 경우 패스
                if(visited[next.giftType][next.deltaType][next.r][next.c]) continue;
                // 좌표 정보를 덱에 추가
                pointDeque.offerLast(next);
                // 방문 표시
                visited[next.giftType][next.deltaType][next.r][next.c] = true;
            }
        }

        return -1;
    }
}
