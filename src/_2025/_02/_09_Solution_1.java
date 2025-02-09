package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15831
// - 투 포인터 : 범위의 검은돌, 흰돌의 개수를 체크하며 조건을 만족하는 가장 긴 구간 탐색
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 길이
        int B = Integer.parseInt(st.nextToken());   // 최대 검은돌
        int W = Integer.parseInt(st.nextToken());   // 최소 흰돌

        // 구간 정보
        String pebbles = in.readLine();

        // 정답 초기화
        int answer = 0;

        int left = -1;
        int right = -1;
        int black = 0;  // 검은돌 개수
        int white = 0;  // 흰돌 개수
        do{
            if(black <= B){
                right++;
                if(right == N) break;
                if(pebbles.charAt(right) == 'B') black++;
                else white++;
            }else{
                left++;
                if(pebbles.charAt(left) == 'B') black--;
                else white--;
            }

            if(black <= B && white >= W) {
                // System.out.println(right+", "+left+"======"+black+", "+white);
                answer = Math.max(answer, black+white);
            }
        }while(left <= right);


        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
