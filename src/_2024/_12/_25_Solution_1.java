package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1891
// - 분할 정복 : 범위를 기준으로 (사분면 -> 좌표), (좌표 -> 사분면)을 차례로 계산
//    1. (사분면 번호 -> 좌표) 분할 정복을 통해 계산
//    2. 좌표 이동
//    3. (좌표 -> 사분면 번호) 분할 정복을 통해 계산
public class _25_Solution_1 {
    // 최대 좌표
    static long LIMIT;
    // 시작 사분면 좌표
    static long[] START_POINT;
    // 정답 : 사분면 번호
    static StringBuilder ANSWER;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int d = Integer.parseInt(st.nextToken());       // 사분면 분할 개수
        char[] number = st.nextToken().toCharArray();   // 사분면 번호

        st = new StringTokenizer(in.readLine());
        long y = Long.parseLong(st.nextToken());    // 열 이동 (양수-오른쪽, 음수-왼쪽)
        long x = Long.parseLong(st.nextToken());    // 행 이동 (양수-위쪽, 음수-아래쪽)

        // 최대 좌표
        LIMIT = (long)Math.pow(2, d);
        // 시작 사분면 좌표 초기화
        START_POINT = new long[]{0, 0};
        // 시작 사분면 계산 : 분할 정복
        numToPoint(0L, LIMIT, 0L, LIMIT, 0, d, number);
        
        // 사분면 이동
        movePoint(x, y);

        // 정답 초기화
        ANSWER = new StringBuilder();
        // 이동 가능한 좌표일 경우 사분면 번호 탐색 : 분할 정복
        if(START_POINT[0] >= 0 && START_POINT[0] < LIMIT &&
                START_POINT[1] >= 0 && START_POINT[1] < LIMIT){
            pointToNum(0, LIMIT, 0, LIMIT, 0, d);
            sb.append(ANSWER.toString());
        }else sb.append(-1);

        // 정답 출력
        System.out.println(sb);
    }
    // 좌표 -> 사분면 번호 계산 함수 : 분할 정복으로 좌표의 사분면 번호 차례로 계산
    private static void pointToNum(long startX, long endX, long startY, long endY, int index, int d) {
        if(index == d) return;

        long halfX = (startX+endX) / 2;
        long halfY = (startY+endY) / 2;

        if(START_POINT[0] >= startX && START_POINT[0] < halfX && START_POINT[1] >= halfY && START_POINT[1] < endY){
            ANSWER.append(1);
            pointToNum(startX, halfX, halfY, endY, index+1, d);
        }else if(START_POINT[0] >= startX && START_POINT[0] < halfX && START_POINT[1] >= startY && START_POINT[1] < halfY){
            ANSWER.append(2);
            pointToNum(startX, halfX, startY, halfY, index+1, d);
        }else if(START_POINT[0] >= halfX && START_POINT[0] < endX && START_POINT[1] >= startY && START_POINT[1] < halfY){
            ANSWER.append(3);
            pointToNum(halfX, endX, startY, halfY, index+1, d);
        }else if(START_POINT[0] >= halfX && START_POINT[0] < endX && START_POINT[1] >= halfY && START_POINT[1] < endY){
            ANSWER.append(4);
            pointToNum(halfX, endX, halfY, endY, index+1, d);
        }
    }

    // 사분면 번호 -> 좌표 계산 함수 : 분할 정복으로 좌표 범위를 좁히며 계산
    private static void numToPoint(long startX, long endX, long startY, long endY, int index, int d, char[] number) {
        if(index == d){
            START_POINT = new long[]{startX, startY};
            return;
        }
        if(number[index] == '1') numToPoint(startX, (startX+endX) / 2, (startY+endY) / 2, endY, index+1, d, number);
        else if(number[index] == '2') numToPoint(startX, (startX+endX) / 2, startY, (startY+endY) / 2, index+1, d, number);
        else if(number[index] == '3') numToPoint((startX+endX) / 2, endX, startY, (startY+endY) / 2, index+1, d, number);
        else if(number[index] == '4') numToPoint((startX+endX) / 2, endX, (startY+endY) / 2, endY, index+1, d, number);
    }

    private static void movePoint(long x, long y) {
        START_POINT[0] -= x;
        START_POINT[1] += y;
    }
}