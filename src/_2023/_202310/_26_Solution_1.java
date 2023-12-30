package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1424
// - 가능한 모든 경우 탐색
public class _26_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202310/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 노래의 수
        long N = Long.parseLong(in.readLine());
        // 노래 길이
        long L = Long.parseLong(in.readLine());
        // 시디의 용량
        long C = Long.parseLong(in.readLine());

        // 정답 초기화 : 최대값
        long answer = Long.MAX_VALUE;
        // 한 앨범에 가능한 최대의 수부터 차례로 계산
        for(long album = (C+1) / (L+1); album > 0; album--){
            // 13의 배수이거나 노래의 수보다 클 경우 패스
            if(album % 13 == 0 || album > N) continue;

            // 앨범에 수록한 뒤 남은 곡 계산
            long r = N % album;
            // 0개 초과인 경우
            if(r > 0){
                // 13의 배수이고 앨범 수록곡 보다 1 작은 경우 : 앨범 +2개
                if(r % 13 == 0 && r + 1 == album) r = 2;
                // 아닐 경우 : 앨범 +1 개
                else r = 1;
            }
            // 최소값으로 변경
            answer = Math.min(answer, N / album + r);
//            System.out.println(answer+", "+album);
        }
        sb.append(answer);
        System.out.println(sb);
    }
}
