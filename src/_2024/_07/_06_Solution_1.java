package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/25401
// - 브루트포스 : 가능한 공차를 구하여 모두 탐색!
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 크기
        int N = Integer.parseInt(in.readLine());
        // 수열 입력
        int[] X = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 초기화
        int answer = N;
        
        // 만들 수 있는 공차 모두 탐색!
        for(int a = 0; a < N; a++){
            for(int b = a+1; b < N; b++){
                int diff = (X[b] - X[a]);
                int step = b-a;
                // 공차가 될 수 없는 경우 패스
                if(diff % step == 0){
                    // 공차 계산
                    int d = diff / step;
                    // 횟수
                    int count = 0;
                    // 수열의 모든 부분 재계산
                    for(int k = 0; k < N; k++){
                        // 변환값!
                        int change = X[a] + (k-a)*d;
                        // 원래 값과 다른 경우 횟수 증가
                        if(X[k] != change) count++;
                    }
                    // 최소값 갱신!
                    answer = Math.min(answer, count);
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
