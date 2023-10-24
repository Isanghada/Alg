package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1239
// - 브루트포스 : 가능한 모든 경우 탐색!
//   - 가장 큰 부분이 50 초과 : 0개
//   - 가장 큰 부분이 50 : 1개
//   - 가장 큰 부분이 50 미만 : 탐색 필요
public class _24_Solution_1 {
    // 정답, 차트 부분의 개수, 비트마스크(모두 확인한 경우)
    public static int answer, N, BIT;
    // 차트 정보
    public static int[] chart;
    // 선의 위치!
    public static boolean[] check;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 차트 부분의 개수 입력
        N = Integer.parseInt(in.readLine());

        // 차트 정보 입력
        chart = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
        
        // 차트츼 가장 큰 부분
        int max = -1;
        for(int c : chart) max = Math.max(max, c);

        // 50 초과인 경우 : 0 반환
        if(max > 50) sb.append(0);
        // 50인 경우 : 1 반환
        else if(max == 50) sb.append(1);
        // 50 미만인 경우 : 모든 경우 탐색!
        else{
            // 정답 초기화
            answer = 0;
            // 모든 부분을 확인한 경우의 비트마스크.
            BIT = (1 << N) - 1;
            // 선 위치 초기화
            check = new boolean[51];
            // 0인 경우는 무조건 선이 있음.
            check[0] = true;
            // 최대 선의 개수 확인
            backtracking(0, 0, false, 0);
            // 정답 반환
            sb.append(answer);
        }

        System.out.println(sb);
    }

    // 백트래킹을 통해 모든 경우 탐색 : 재귀 활용
    // - cnt : 중앙을 지나는 선의 개수
    // - sum : 차트 누적
    // - flag : 차트 누적이 50 이상인지 여부
    // - bitmask : 현재까지 사용한 차트 부분
    private static void backtracking(int cnt, int sum, boolean flag, int bitmask) {
        // 모든 부분을 사용한 경우
        if(bitmask == BIT){
            // 정답을 최대값으로 변경
            answer = Math.max(answer, cnt);
            return;
        }
        // 모든 차트 부분 확인
        for(int i = 0; i < N; i++){
            // 이미 확인한 경우 패스!
            if((bitmask & (1 << i)) >= 1) continue;
            // 새로 선을 그을 위치 계산
            int temp = chart[i] + sum;

            // 아직 50을 넘기지 않은 경우
            if(!flag){
                // 새로운 선이 50을 넘길 경우
                if(temp >= 50){
                    // 해당 선이 중앙을 지날 경우
                    if(check[temp % 50]) backtracking(cnt+1, temp % 50, true, bitmask | (1 << i));
                        // 해당 선이 중앙을 지나지 않을 경우
                    else backtracking(cnt, temp % 50, true, bitmask | (1 << i));
                }else{
                    // 새로운 선 긋기!
                    check[temp] = true;
                    // 다음 부분 확인
                    backtracking(cnt, temp, false, bitmask | (1 << i));
                    // 선 제거!
                    check[temp] = false;
                }
            }else{
                // 선이 중앙을 지날 경우
                if(check[temp]) backtracking(cnt + 1, temp, true, bitmask | (1 << i));
                // 선이 중앙을 지나지 않을 경우
                else backtracking(cnt, temp, true, bitmask | (1 << i));
            }
        }
    }
}
