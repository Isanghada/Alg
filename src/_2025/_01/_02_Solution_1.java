package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3343
// - 참고 : https://bleron.tistory.com/178
// - 브루트포스 : 가성비가 나쁜 세트를 기준으로 모든 경우 탐색
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long N = Long.parseLong(st.nextToken());    // 목표 개수
        long A = Long.parseLong(st.nextToken());    // 세트 A
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());    // 세트 C
        long D = Long.parseLong(st.nextToken());

        // A 세트의 가성비가 나쁘도록 스왑
        if(B * C < D * A){
            long temp = A;
            A = C;
            C = temp;

            temp = B;
            B = D;
            D = temp;
        }

        // 정답 초기화
        long answer = Long.MAX_VALUE;
        // A 세트를 사는 모든 경우 탐색
        for(int a = 0; a < C; a++){
            long n = N - a * A;
            if(n < 0) n = 0;

            long c = n / C;
            if(n % C != 0) c += 1;

            answer = Math.min(answer, a * B + c * D);

            // 목표 개수를 넘어간 경우 종료
            if(n == 0) break;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
