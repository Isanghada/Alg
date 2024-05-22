package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15927
// - 팰린드롬 : 팰린드롬인 경우 1개의 문자만 제거한 부분 문자열이 팰린드롬이 아니고,
//              팰린드롬이 아닌 경우 전체 문자열이 팰린드롬이 아니다.
//              이떄, 문자열이 1개의 문자로만 이루어져있는 경우 모든 부분 문자열이 팰린드롬이다.
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열
        String input = in.readLine();
        // 팰린드롬이 아닌 부분 문자열 존재 여부 플래그
        boolean flag = false;
        int half = input.length() / 2;
        for(int i = 0; i < half; i++){
            // 팰린드롬인지 확인 => 아닌 경우 input 출력
            if(input.charAt(i) != input.charAt(input.length() - i - 1)){
                sb.append(input.length());
                break;
            // 모든 문자열이 같지 않은 경우 flag로 변환
            }else if(input.charAt(i) != input.charAt(i+1)) flag = true;
        }

        if(sb.length() == 0) {
            // 팰린드롬이 아닌 부분 문자열이 있는 경우 (문자열 길이 - 1) 출력
            if (flag) sb.append(input.length() - 1);
                // 모든 부분 문자열이 팰린드롬인 경우 -1 출력
            else sb.append(-1);
        }

        // 정답 출력
        System.out.println(sb);
    }
}
