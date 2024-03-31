package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2174
// - 구현 : 각 명령을 차례로 진행
public class _31_Solution_1 {
    // 변수 선언
    public static int A, B, N, M;
    // 맵 선언
    public static int[][] MAP;
    // 로봇 클래스 구현
    public static class Robot{
        int row;    // 행
        int col;    // 열
        int move;   // 방향(0-N, 1-E, 2-S, 3-W)
        public Robot(int row, int col, int move){
            this.row = row;
            this.col = col;
            this.move = move;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());   // 열 길이
        B = Integer.parseInt(st.nextToken());   // 행 길이
        // 맵 초기화
        MAP = new int[B+1][A+1];

        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 로봇의 수
        M = Integer.parseInt(st.nextToken());   // 명령의 수

        // 방향->정수 맵
        Map<Character, Integer> moveType = new HashMap<>();
        moveType.put('N', 0);
        moveType.put('E', 1);
        moveType.put('S', 2);
        moveType.put('W', 3);

        // 로봇 초기화
        Robot[] robots = new Robot[N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(in.readLine());
            int col = Integer.parseInt(st.nextToken()); // 열
            int row = Integer.parseInt(st.nextToken()); // 행
            char move = st.nextToken().charAt(0);       // 방향
            // 로봇 입력
            robots[i] = new Robot(row, col, moveType.get(move));
            // 맵에 표시
            MAP[robots[i].row][robots[i].col] = i;
        }

        // 방향별 이동 변수
        // - DELTA[0] : 북쪽 이동
        // - DELTA[1] : 동쪽 이동
        // - DELTA[2] : 남쪽 이동
        // - DELTA[3] : 서쪽 이동
        final int[][] DELTA = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        // 충돌 플래그
        boolean flag = false;
        
        // 명령 수행
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int idx = Integer.parseInt(st.nextToken());     // 명령받는 로봇
            char command = st.nextToken().charAt(0);        // 명령
            int count = Integer.parseInt(st.nextToken());   // 반복 횟수

            // 왼쪽 전환인 경우
            if(command == 'L'){
                robots[idx].move -= (count % 4);
                if(robots[idx].move < 0) robots[idx].move += 4;
            // 오른쪽 전환인 경우
            }else if(command == 'R'){
                robots[idx].move += (count % 4);
                if(robots[idx].move >= 4) robots[idx].move -= 4;
            // 위치 이동인 경우
            }else{
                // 원래 위치 0으로 변환
                MAP[robots[idx].row][robots[idx].col] = 0;
                // count만큼 이동
                while(count-- > 0){
                    robots[idx].row += DELTA[robots[idx].move][0];  // 로봇 위치 변경
                    robots[idx].col += DELTA[robots[idx].move][1];  // 로봇 위치 변경
                    // 범위를 벗어난 경우 : 문구 출력
                    if(robots[idx].row < 1 || robots[idx].row > B ||
                            robots[idx].col < 1 || robots[idx].col > A
                    ){
                        sb.append("Robot ").append(idx).append(" crashes into the wall");
                    // 다른 로봇에 충돌한 경우 : 문구 출력
                    }else if(MAP[robots[idx].row][robots[idx].col] != 0){
                        sb.append("Robot ").append(idx).append(" crashes into robot ").append(MAP[robots[idx].row][robots[idx].col]);
                    // 충돌하지 않은 경우 : 패스
                    }else continue;
                    
                    // 충돌이 일어난 경우
                    // 플래그 변경 및 종료
                    flag = true;
                    break;
                }
                // 충돌이 일어난 경우 종료
                if(flag) break;
                // 이동 위치 idx로 변환
                MAP[robots[idx].row][robots[idx].col] = idx;
            }
        }
        // 충돌이 일어나지 않은 경우 OK 출력
        if(!flag) sb.append("OK");
        // 정답 반환
        System.out.println(sb);
    }
}