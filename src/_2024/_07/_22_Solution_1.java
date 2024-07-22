package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14503
// - 구현 : 동작 과정을 참고하여 구현
//          로봇 클래스를 구성하여 활용
public class _22_Solution_1 {
    // 로봇 클래스
    public static class Robot{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int type;   // 방향
        public Robot(int row, int col, int type){
            this.row = row;
            this.col = col;
            this.type = type;
        }
        // 다음 로봇 위치 생성 함수
        public static Robot next(Robot robot, int next) {
            // 로봇의 다음 위치 초기화
            Robot nextRobot = new Robot(robot.row, robot.col, robot.type-next);
            // 방향 조정!
            if(nextRobot.type < 0) nextRobot.type += 4;
            // 방향에 따라 이동
            if(nextRobot.type == 0) nextRobot.row--;
            else if(nextRobot.type == 1) nextRobot.col++;
            else if(nextRobot.type == 2) nextRobot.row++;
            else nextRobot.col--;
            return nextRobot;
        }
        // 후진 함수
        public void back() {
            if(this.type == 0) this.row++;
            else if(this.type == 1) this.col--;
            else if(this.type == 2) this.row--;
            else this.col++;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int[] size = inputSize(in.readLine());      // 방 크기
        Robot robot = inputRobot(in.readLine());    // 로봇 위치
        int[][] map = inputMap(size, in);           // 방 정보

        // 정답 출력
        // - 로봇이 청소하는 칸의 수 반환
        sb.append(getCountOfCleanBlock(size, robot, map));
        System.out.println(sb);
    }

    private static int getCountOfCleanBlock(int[] size, Robot robot, int[][] map) {
        // 청소한 칸의 수
        int count = 0;

        // 시뮬레이션!
        while(true){
            //System.out.println(robot.row+", "+robot.col+", "+robot.type);
            // 청소하지 않은 빈칸이 경우
            if(map[robot.row][robot.col] == 0) {
                // 청소 상태로 변경
                map[robot.row][robot.col] = -1;
                // 청소한 칸의 수 증가
                count++;
            }
            // 청소되지 않은 빈칸이 없는 경우!의 플래그
            boolean flag = true;
            // 반시계 방향으로 바꾸며 확인
            for(int next = 0; next < 4; next++){
                // 반시계 방향으로 이동
                Robot nextRobot = Robot.next(robot, next+1);
                // 청소되지 않은 칸인 경우 이동!
                if(map[nextRobot.row][nextRobot.col] == 0) {
                    robot = nextRobot;
                    flag = false;
                    break;
                }
            }
            // 청소되지 않은 칸이 없는 경우
            if(flag){
                // 후진 이동!
                robot.back();
                // 벽을 만나는 경우 종료
                if(robot.row < 0 || robot.row >= size[0] || robot.col < 0 || robot.col >= size[1] ||
                        map[robot.row][robot.col] == 1
                ) break;
            }
        }
//        for(int[] m : map) System.out.println(Arrays.toString(m));
        return count;
    }
    private static int[][] inputMap(int[] size, BufferedReader in) throws Exception {
        int[][] map = new int[size[0]][size[1]];
        for(int r = 0; r < size[0]; r++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int c = 0; c < size[1]; c++) map[r][c] = Integer.parseInt(st.nextToken());
        }
        return map;
    }

    private static Robot inputRobot(String input) {
        StringTokenizer st = new StringTokenizer(input);
        Robot robot = new Robot(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        );
        return robot;
    }

    private static int[] inputSize(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int[]size = new int[]{
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };
        return size;
    }
}
