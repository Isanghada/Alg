package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/9011
// - 구현 : 뒤에서 부터 값을 구하며 해결
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 정수의 개수
            int N = Integer.parseInt(in.readLine());
            // R 배열
            int[] R = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
            // 숫자 사용 여부
            boolean[] isUsed = new boolean[N+1];

            // 가능 여부
            boolean isPossible = true;
            // S 배열 초기화
            int[] S = new int[N];
            // 가장 뒤에서 부터 숫자 계산
            for(int i = N-1; i >= 0; i--){
                // 기본적으로 (R[i]의 수 + 1)
                // - R[i] : S[0]~S[i-1]의 범위에서 S[i]의 값보다 작은 수의 개수
                int s = R[i] + 1;
                // s 계산하기
                for(; s <= N; s++){
                    // 이미 사용한 숫자는 패스
                    if(isUsed[s]) continue;
                    // s보다 작은 사용되지 않은 숫자의 개수
                    int cnt = 0;
                    for(int k = 1; k < s; k++){
                        if(!isUsed[k]) cnt++;
                    }
                    // R[i]와 cnt의 값이 동일한 경우 해당 값이 s
                    if(cnt == R[i]){
                        S[i] = s;
                        isUsed[s] = true;
                        break;
                    }
                }
                // s가 N초과일 경우 : 불가능
                if(s > N){
                    isPossible = false;
                    break;
                }
            }
            // 가능한 경우 : S 배열의 값 차례로 출력
            if(isPossible) for(int num : S) sb.append(num).append(" ");
            // 불가능한 경우 : IMPOSSIBLE 출력
            else sb.append("IMPOSSIBLE");
            sb.append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
