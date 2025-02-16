package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25306
// - XOR : 숫자를 이진수로 변환했을 때 마지막 2비트가 (00, 01, 10, 11)로 이어지는
//         4개의 숫자를 XOR할 경우 앞의 비트와 상관없이 0이 된다.
//         따라서, XOR 결과가 0으로 나오는 범위를 제외하고 나머지 부분만 따로 계산!
public class _16_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long A = Long.parseLong(st.nextToken());    // 시작
        long B = Long.parseLong(st.nextToken());    // 끝

        // 정답 초기화
        long answer = A;
        // - 범위가 8미만인 경우 : 시작, 끝에서 계산할 때 겹칠 수 있는 경우!
        if((B - A + 1) < 8){
            // 모든 범위 직접 계산
            for(long num = A+1; num <= B; num++) answer ^= num;
        // - 범위가 8이상인 경우 : 시작, 끝에서 계산할 때 겹치지 않는 경우!
        }else{
            // 시작 지점부터 나머지가 0일 때까지 계산
            for(long a = A + 1; a <= B; a++){
                if(a % 4 == 0) break;
                answer ^= a;
            }
            // 끝 지점부터 나머지가 3일 때까지 계산
            for(long b = B; b >= A; b--){
                if(b % 4 == 3) break;
                answer ^= b;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
