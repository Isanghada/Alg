package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28421
// - 브루트포스 : 수열의 모든 숫자를 카운팅하여 각 명령 탐색!
public class _16_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 수열 크기
        int Q = Integer.parseInt(st.nextToken());   // 질의 개수
        int[] A = new int[N+1];                     // 수열
        Map<Integer, Integer> arrCount = new HashMap<>();   // 수열의 숫자 카운팅

        // 수열 입력 -> 숫자 카운팅
        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
            arrCount.put(
                    A[i],
                    arrCount.getOrDefault(A[i], 0) + 1
            );
        }

        // 질의 수행
        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            int[] command = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };
            // 1번 명령 : x를 2개의 숫자로 만들 수 있는지 확인
            if(command[0] == 1){
                // x의 제곱근을 최대값으로 설정
                int limit = (int) Math.sqrt(command[1]);
                // 가능 여부 플래그 초기화
                boolean flag = false;
//                System.out.println(command[1]);
//                System.out.println(arrCount);
                // 수열 크기가 2 이상인 경우만 탐색 가능
                if(N > 1){
                    // x가 0이고, 0이 있는 경우 true 변환
                    if(command[1] == 0 && arrCount.getOrDefault(0, 0) > 0) flag = true;
                    // 1부터 limit까지 탐색
                    for(int i = 1; i <= limit; i++){
                        // x가 i로 나누어 떨어지는 경우
                        if(command[1] % i == 0){
                            // 대응되는 j 계산
                            int j = command[1] / i;
//                        System.out.println(i+", "+j);

                            // i, j가 동일한 경우 : i가 2개 이상인지 확인
                            if(i == j){
                                if(arrCount.getOrDefault(i, 0) > 1){
                                    flag = true;
                                    break;
                                }
                                // i, j가 다른 경우 : i, j가 각각 1개 이상인지 확인
                            }else{
                                if(arrCount.getOrDefault(i, 0) > 0 &&
                                        arrCount.getOrDefault(j, 0) > 0
                                ){
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }
                }
//                System.out.println("----------------");
                // flag에 따라 1 혹은 0 반환
                sb.append((flag ? 1 : 0)).append("\n");
            // 2번 명령 : A[i]를 0으로 변환
            }else {
                // A[i] 위치의 값 카운팅 감소
                arrCount.put(A[command[1]], arrCount.get(A[command[1]]) - 1);
                // 0 카운팅 증가
                arrCount.put(0, arrCount.getOrDefault(0, 0) + 1);
                // A[i]를 0으로 변환
                A[command[1]] = 0;
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}
