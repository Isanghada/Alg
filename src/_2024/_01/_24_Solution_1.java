package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24023
// - 그리디, 비트 : 차례로 계산하며 K를 만들 수 있는지 확인
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 배열의 크기
        int K = Integer.parseInt(st.nextToken());   // 타겟 숫자

        // 배열 입력
        int[] A = Arrays.stream(("0 "+in.readLine()).split(" ")).mapToInt(Integer::new).toArray();

        int cur = 0;    // 현재까지 bitwise or 연산 값
        int start = 1;  // 현재 구간의 시작 인덱스
        boolean flag = false;   // 구간 유무 플래그
        // 끝 인덱스를 구하는 반복문
        for(int end = 1; end <= N; end++){
            // 현재 값과 K의 or 연산이 K가 아닌 경우 : 불가능한 구간
            if((A[end] | K) != K){
                cur = 0;    // cur 초기화
                start = end+1;    // 시작 인덱스 증가
            // 가능한 구간
            }else{
                // cur에 현재 값 추가!
                cur |= A[end];
                // 타겟 숫자인 경우
                if(cur == K){
                    // 정답 입력
                    sb.append(start).append(" ").append(end);
                    // 플래그 변경
                    flag = true;
                    break;
                }
            }
        }
        // 구간이 없는 경우 -1 입력
        if(!flag) sb.append(-1);

        // 정답 반환
        System.out.println(sb);
    }
}
