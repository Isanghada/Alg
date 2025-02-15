package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15668
// - 백트래킹 : A, B 중 하나가 숫자 1개씩만 사용하는 경우를 찾은 후
//              남은 자연수를 구하여 가능한지 체크
public class _15_Solution_1 {
    static int[] BIT;           // 비트 값
    static final int MAX = 9;   // 최대 비트
    static int ANSWER, N;       // 정답(A 값), 목표 번호
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 비트 계산
        // - 0~9 총 10개를 사용하므로 비트마스크 활용
        BIT = new int[MAX+1];
        BIT[0] = 1;
        for(int bit = 1; bit <= MAX; bit++) BIT[bit] = (BIT[bit-1] << 1);

        // 목표 번호 입력
        N = Integer.parseInt(in.readLine());

        // 정답 초기화
        ANSWER = -1;

        // 가능한 모든 경우 탐색!
        backtracking(0, 0);

        if(ANSWER == -1) sb.append(ANSWER);
        else sb.append(ANSWER).append(" + ").append(N-ANSWER);

        // 정답 출력
        System.out.println(sb.toString().trim());
    }

    private static void backtracking(int A, int bitmask) {
        // 목표 번호를 넘어서거나 정답을 찾은 경우 종료!
        if(A > N || ANSWER != -1) return;

        // A가 자연수인 경우 B 확인
        if(A > 0){
            int B = N - A;
            if(B > 0) {
                boolean flag = true;
                int curBitmask = bitmask;
                while (B > 0) {
                    int mod = B % 10;
                    if ((curBitmask & BIT[mod]) > 0) {
                        flag = false;
                        break;
                    }
                    curBitmask |= BIT[mod];
                    B = B / 10;
                }

                if (flag) ANSWER = A;
            }
        }

        for(int num = 0; num <= MAX; num++){
            if((A == 0 && num == 0) || (bitmask & BIT[num]) > 0) continue;
            backtracking(A * 10 + num, bitmask | BIT[num]);
        }
    }
}
