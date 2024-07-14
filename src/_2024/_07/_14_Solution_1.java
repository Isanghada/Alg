package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/28325
// - 그리디 : 쪽방이 없는 경우의 범위를 구하여 차례로 계산
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 방의 수
        int N = Integer.parseInt(in.readLine());
        // 방 정보 입력
        long[] C = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::new).toArray();

        // 처음으로 쪽방이 있는 경우 탐색
        int count = 0;
        for(; count < N; count++) if(C[count] != 0) break;

        // 정답 초기화
        long answer = 0;
        // - 쪽방이 없는 경우 : 전체의 절반!
        if(count == N) answer = N / 2;
        // - 쪽방이 있는 경우
        else{
            // 쪽방을 마지막 번호로 설정
            for(int idx = count; idx < N; idx++){
                long temp = C[idx];
                C[idx] = C[idx-count];
                C[idx-count] = temp;
            }
            // 첫 방부터 차례로 계산
            for(int start = 0; start < N; start++){
                // 쪽방이 있는 경우 : 쪽방 사용
                if(C[start] != 0) answer += C[start];
                else{
                    // 쪽방이 없는 범위 계산
                    int end = start;
                    while(end < N && C[end] == 0) {
                        end++;
                    }
                    // 범위의 최대 경우 게산
                    answer += (end - start + 1) / 2;
                    // start 변환!
                    start = end -1;
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}

