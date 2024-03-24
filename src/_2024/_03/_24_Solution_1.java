package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1790
// - 구현 : 각 숫자를 추가했을 때 길이를 차례로 계산하여 k번째 자리를 구함
// - 참고 : https://code-lab1.tistory.com/145 (더 간단한 방법)
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 마지막 숫자
        int K = Integer.parseInt(st.nextToken());   // 숫자를 구할 자리

        // 현재까지의 문자열 길이
        int count = 0;
        // 1부터 N까지 차례로 계산
        for(int num = 1; num <= N; num++){
            // 현재 숫자를 문자열로 변환
            int cur = ((int)Math.floor(Math.log10(num))) + 1;
            // 현재 숫자를 추가했을 때 길이가 K 미만인 경우 길이만큼 증가
            if(count + cur < K) count += cur;
            // K이상일 경우 현재 숫자에서 위치 계산하여 출력
            else{
                sb.append(String.valueOf(num).charAt(K-count-1));
                break;
            }
        }
        // 추가된 문자열이 없는 경우 : 불가능한 경우이므로 -1 출력
        if(sb.length() == 0) sb.append(-1);

        // 정답 반환
        System.out.println(sb);
    }
}
