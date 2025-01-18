package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1322
// - 비트마스킹 : X를 2진수로 치환했을 때 0의 위치에 K를 2진수로 치환했을 때의 값을 대치시키면 된다!
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long X = Long.parseLong(st.nextToken());    // 기준 값
        long K = Long.parseLong(st.nextToken());    // 작은 수!

        // 2진수 변환
        String binaryX = Long.toBinaryString(X);
        String binaryK = Long.toBinaryString(K);
        int lenX = binaryX.length();
        int lenK = binaryK.length();

        long answer = 0;        // 정답 초기화
        int kIdx = lenK -1;
        int idx = 0;
        while(kIdx >= 0){
            // binaryX의 값
            char ch = '0';
            if(idx < lenX) ch = binaryX.charAt(lenX - 1 - idx);

            // binaryX가 0이고 binaryK가 1인 경우 정답 증가
            if(ch == '0' && binaryK.charAt(kIdx--) == '1'){
                long value = (long) Math.pow(2, idx);
                answer |= value;
            }
            idx++;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
