package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/13021
// - 수학 : 차례로 각 범위를 칠하여 가능한 횟수 체크!
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 공의 개수
        int M = Integer.parseInt(st.nextToken());   // 기계 사용 개수

        // 공의 색깔!
        int[] ballArr = new int[N+1];

        int color = 1;
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            // color로 l, r 범위 칠하기
            setBallColor(
                    ballArr,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    color++
            );
        }

        // 정답 출력
        // - 2^(색깔의 개수) 반환!
        sb.append(pow(2, getColorCount(ballArr)));
        System.out.println(sb);
    }

    private static long pow(long a, int b) {
        if(b == 0) return 1;
        if((b & 1) == 1) return a*pow(a, b-1);
        return pow(a*a, b/2);
    }

    private static int getColorCount(int[] ballArr) {
        Set<Integer> ballColor = new HashSet<>();
        for(int color : ballArr){
            if(color == 0) continue;
            ballColor.add(color);
        }
        return ballColor.size();
    }

    private static void setBallColor(int[] ballArr, int l, int r, int color) {
        for(int ball = l; ball <= r; ball++) ballArr[ball] = color;
    }

}
