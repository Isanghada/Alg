package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/27231
// - 백트래킹 : 가능한 모든 조합을 구하고 차례로 확인
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트케이스 수
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            String number = in.readLine();
            // 무한 여부
            boolean flag = true;
            // 모든 자리수가 0, 1로 이루어진 경우 true
            for(int i = 0; i < number.length(); i++) flag = (number.charAt(i) - '0' >= 2 ? false : flag);
            // 무한인 경우 문구 출력
            if(flag) sb.append("Hello, BOJ 2023!\n");
            else{
                // 가능한 모든 조합 계산
                Set<Integer> numSet = getPossibleNumber(number);
                // 최대값 탐색
                int max = Collections.max(numSet);
                // 정답 초기화 : m이 1인 경우 무조건 가능하므로 1로 초기화
                int answer = 1;
                // 각 자리수 초기화
                int[] num = new int[number.length()];
                for(int i = 0; i < number.length(); i++) num[i] = number.charAt(i) - '0';
                // m이 2인 경우부터 모두 계산
                for(int step = 2; ; step++){
                    // 합 초기화
                    int sum = 0;
                    // 각 자리수 제곱하여 계산
                    for(int i = 0; i < number.length(); i++){
                        num[i] *= number.charAt(i) - '0';
                        sum += num[i];
                    }
                    // 가능한 숫자가 있는 경우 정답 증가
                    if(numSet.contains(sum)) answer++;
                    // 최대값 이상일 경우 종료
                    if(sum >= max) break;
                }
                sb.append(answer).append("\n");
//                System.out.println(numSet);
            }
        }

        // 정답 반환
        System.out.println(sb);
    }
    // 가능한 조합 탐색 함수 :
    private static Set<Integer> getPossibleNumber(String number) {
        // set 초기화
        Set<Integer> s = new HashSet<>();
        // 초기값 설정
        s.add(Integer.parseInt(number.toString()));
        // 가능한 조합 탐색
        for(int i = 1; i< number.length(); i++){
            // i인덱스 기준으로 substring 구분하여 가능한 조합 탐색
            Set<Integer> temp = getPossibleNumber(number.substring(i, number.length()));
            // (0 ~ i)의 현재값 설정
            int cur = Integer.parseInt(number.substring(0, i));
            // temp의 모든 값과 더하여 set에 추가
            for(Integer num : temp) s.add(cur+num);
        }
        return s;
    }
}
