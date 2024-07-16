package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11758
// - CCW 알고리즘 : https://degurii.tistory.com/47#code
// - 기하학 : CCW 알고리즘을 통해 세 점의 방향성 판단
public class _16_Solution_1 {
    // 점의 개수
    public static int SIZE = 3;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 점 정보 입력
        int[][] p = new int[SIZE+1][2];
        for(int i = 0; i < SIZE; i++) p[i] = inputPoint(in.readLine());
        p[SIZE] = p[0];

        // ccw 함수로 값 계산 후 결과 출력
        int ccw = ccw(p);
        // - 0인 경우 : 직선
        if(ccw == 0) sb.append(0);
        // - 양수인 경우 : 반시계 방향
        else if(ccw > 0) sb.append(1);
        // - 음수인 경우 : 시계 방향
        else sb.append(-1);

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static int ccw(int[][] p) {
        int a = p[0][0] * p[1][1] + p[1][0]*p[2][1] + p[2][0]*p[3][1];
        int b = p[0][1] * p[1][0] + p[1][1]*p[2][0] + p[2][1]*p[3][0];
        return a - b;
    }

    private static int[] inputPoint(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int[] p = new int[]{
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };
        return p;
    }
}
