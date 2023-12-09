package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1669
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int X = Integer.parseInt(st.nextToken());   // 원숭이의 키
        int Y = Integer.parseInt(st.nextToken());   // 멍멍이의 키
        int diff = Y - X;   // 키 차이
        // 키 차이가 없는 경우 0 출력
        if(diff == 0) sb.append(0);
        // 아닌 경우 계산!
        else{
            long N = 1;
            long answer = 0;

            // diff보다 작은 제곱수 찾기
            while(N*N <= diff) N++;
            N--;    // 증가된 채로 나오기에 1만큼 감소
            answer = 2*N - 1;   // 정답 초기화

            // diff을 N 재곱 만큼 감소!
            diff -= N*N;
            while(diff > 0){
                for(long i = N; i >= 1; i--){
                    if(i < diff){
                        answer++;
                        diff -= i;
                        break;
                    }
                }
            }
            // 정답 출력
            sb.append(answer);
        }

        // 정답 출력
        System.out.println(sb);
    }
}
