package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/3107
// - 투 포인터 : 회문인지 차례로 검사하며 아닐 경우 유사 회문 인지 검사
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스의 수
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 문자열 입력
            String input = in.readLine();
            
            int left = 0;                   // 왼쪽 인덱스
            int right = input.length()-1;   // 오른쪽 인덱스
            int result = 0;                 // 결과 초기화

            // left가 right보다 작은 동안 반복
            while(left < right){
                // 회문이 아닌 경우! : 유사 회문인지 검사
                if(input.charAt(left) != input.charAt(right)){
                    // left 인덱스의 문자를 제거하는 경우
                    StringBuilder deleteLeft = new StringBuilder(input).deleteCharAt(left);
                    // right 인덱스의 문자를 제거하는 경우
                    StringBuilder deleteRight = new StringBuilder(input).deleteCharAt(right);

                    // 유사 회문인 경우 result를 1로 변경
                    if(deleteLeft.toString().equals(deleteLeft.reverse().toString()) ||
                            deleteRight.toString().equals((deleteRight.reverse().toString()))
                    ) result = 1;
                    // 일반 문자열인 경우 result 2으로 변경
                    else result = 2;

                    // 반복문 종료
                    break;
                }
                // 인덱스 이동!
                left++;     // 왼쪽 인덱스 증가
                right--;    // 오른쪽 인덱스 감소
            }
            
            // 각 테스트케이스 결과 출력
            sb.append(result).append('\n');
        }

        // 정답 반환
        System.out.println(sb);
    }
}
