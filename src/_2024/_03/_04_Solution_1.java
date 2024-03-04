package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12931
// - 그리디 : 역으로 arr에서 모든 배열 값이 0인 것으로 변환!
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 배열 길이
        int n = Integer.parseInt(in.readLine());
        // 배열 초기화
        int[] arr = new int[n];
        
        // 배열 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int sum = 0;    // 배열 전체 합
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        // 정답 초기화
        int answer = 0;
        // 전체 합이 0일 때까지 반복
        while(sum != 0){
            // 1를 빼는 경우
            int num = 0;
            // 배열 값 중 홀수가 있다면 1 감소!
            for(int i = 0; i < n; i++){
                if(arr[i] % 2 == 1){
                    arr[i]--;
                    num++;
                }
            }
            // 홀수인 경우가 있는 경우
            if(num > 0) {
                sum -= num;     // 전체 값에서 홀수 개수만큼 감소
                answer += num;  // 정답에 홀수 개수만큼 증가
            // 모두 짝수인 경우
            }else{
                // 모든 값 2로 나누기
                for(int i =0 ; i< n; i++) arr[i] /= 2;
                // 전체 값 2로 나누기
                sum /= 2;
                // 정답 1 증가
                answer++;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
