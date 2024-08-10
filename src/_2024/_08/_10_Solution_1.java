package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/15662
// - 구현 : 재귀를 통해 톱니바퀴의 회전 구현!
public class _10_Solution_1 {
    public static int[] BIT;    // 비트 배열
    public static int T;        // 톱니바퀴의 수
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 비트마스킹에 활용한 비트 계산
        // - 톱니가 8개이므로 크기 8로 설정!
        BIT = new int[8];
        BIT[0] = 1;
        for(int bit = 1; bit < 8; bit++) BIT[bit] = BIT[bit-1] << 1;

        // 톱니바퀴의 수
        T = Integer.parseInt(in.readLine());

        // 톱니바퀴 정보 입력 : S극인 경우를 해당 비트 1로 설정!
        // - 12시 : 비트 인덱스 7 => 시계 방향으로 인덱스 감소!
        int[] wheels = new int[T];
        for(int t = 0; t < T; t++) {
            String wheel = in.readLine();
            for(int i = 0; i < wheel.length(); i++){
                if(wheel.charAt(i) == '1') wheels[t] |= BIT[7-i];
            }
//            System.out.println(String.format("%8s", Integer.toBinaryString(wheels[t])));
        }

//        System.out.println("--------");
        // 회전 횟수!
        int K = Integer.parseInt(in.readLine());
        while(K-- > 0){
            // 회전 명령 입력
            int[] commands = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
            // 재귀 함수를 통해 회전!
            // - move는 이동 방향!(0-초기값, 1-왼쪽 이동, 2-오른쪽 이동)
            // - 이미 방문한 바퀴로 재전달하지 않도록하기 위해 사용!
            rotateWheel(commands[0]-1, commands[1], wheels, 0);
        }
//        System.out.println("--------");

        // 정답 출력
        sb.append(getCountOfS(wheels));
        System.out.println(sb.toString());
    }

    // S극의 수 계산 함수
    // - 12시가 비트 인덱스 7이므로 해당 값이 1인 톱니바퀴의 수 계산!
    private static int getCountOfS(int[] wheels) {
        int count = 0;
        for(int w : wheels){
//            System.out.println(String.format("%8s", Integer.toBinaryString(w)));
            if((w & BIT[7]) > 0 ) count++;
        }
        return count;
    }

    // 바퀴 회전 함수! : 재귀를 통해 전달!
    private static void rotateWheel(int target, int rotate, int[] wheels, int move) {
        // 아래의 경우 왼쪽으로 회전 전달!
        // - 가장 왼쪽 바퀴가 아닌 경우
        // - 오른쪽 이동이 아닌 경우! (즉, target의 왼쪽 바퀴가 아직 회전하지 않은 경우!)
        // - target과 왼쪽 바퀴의 맞닿은 극이 다른 경우!
        if(target > 0 && move != 2 &&
                ((wheels[target]&BIT[1]) > 0 != (wheels[target-1]&BIT[5]) > 0)) {
            rotateWheel(target - 1, (-1) * rotate, wheels, 1);
        }

        // 아래의 경우 오른쪽으로 회전 전달!
        // - 가장 오른쪽 바퀴가 아닌 경우
        // - 왼쪽 이동이 아닌 경우! (즉, target의 오른쪽 바퀴가 아직 회전하지 않은 경우!)
        // - target과 오른쪽 바퀴의 맞닿은 극이 다른 경우!
        if(target < (T-1) && move != 1 &&
                ((wheels[target]&BIT[5]) > 0 != (wheels[target+1]&BIT[1]) > 0)) {
            rotateWheel(target + 1, (-1) * rotate, wheels, 2);
        }
//        System.out.println("rotate"+", "+target+", "+rotate+", "+move);
//        System.out.println(String.format("%8s", Integer.toBinaryString(wheels[target])));

        // 회전 방향에 따라 target 바퀴 회전!
        if(rotate == 1){
            int w = wheels[target] & BIT[0];
            wheels[target] >>= 1;
            if(w > 0) wheels[target] |= BIT[7];
        }else{
            int w = wheels[target] & BIT[7];
            wheels[target] = (wheels[target] & 127) << 1;
            if(w > 0) wheels[target] |= BIT[0];
        }
//        System.out.println(String.format("%8s", Integer.toBinaryString(wheels[target])));
    }
}
