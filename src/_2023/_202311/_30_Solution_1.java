package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20444
// - 이분탐색 : 가로, 세로 자르는 횟수를 기준으로 탐색
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 가위질의 수
        long N = Long.parseLong(st.nextToken());
        // 목표 색종이의 수
        long K = Long.parseLong(st.nextToken());

        // 이분 탐색 범위 초기화
        long left = 0;
        long right = N/2;
        // flag 초기화
        boolean flag = false;
        // 이분 탐색 : 가로로 자르는 횟수를 기준으로 이분 탐색 진행
        while(left <= right){
            long r = (left+right) / 2;  // 가로로 자르는 횟수
            long l = N - r; // 세로로 자르는 횟수

            // 총 색종이의 개수
            long total = (r+1) * (l+1);
            // 목표 개수라면 flag 변환 후 종료
            if(total == K){
                flag = true;
                break;
            // 더 작거나 많을 경우 조절
            }else if(total > K){
                right = r - 1;
            }else if(total < K){
                left = r + 1;
            }
        }

        // 정답 반환
        sb.append(flag ? "YES" : "NO");
        System.out.println(sb);
    }
}