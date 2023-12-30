package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2313
// - 각 보석 라인별로 DP를 통해 최대 가치 게산
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 보석 라인 수
        int N = Integer.parseInt(in.readLine());
        // 가치의 합
        long total = 0;
        // 보석 라인마다 반복
        for(int i = 0; i < N; i++){
            // 보석의 수
            int L = Integer.parseInt(in.readLine());
            // 보석 가치 배열
            int[] jewelry = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

            // 최대 보석 가치
            int sum = jewelry[0];
            // 최대 보석 가치일 경우의 범위
            int sumStart = 0, sumEnd = 0;
            // 현재 범위
            int start = 0, end = 0;

            // 차례로 계산
            for(int idx = 1; idx < L; idx++){
                // 현재 보석의 가치가 이전 보석 가치와의 합 이상일 경우
                if(jewelry[idx] >= jewelry[idx-1] + jewelry[idx]){
                    // 현재 가치로 범위 변경!
                    start = idx;
                    end = idx;
                    // 아닐 경우
                }else{
                    // 이전 가치와 합
                    jewelry[idx] = jewelry[idx-1] + jewelry[idx];
                    end = idx;
                }

                //sum보다 현재 보석 가치가 높을 경우
                if(jewelry[idx] > sum){
                    // sum 변경
                    sum = jewelry[idx];
                    // 범위 변경
                    sumStart = start;
                    sumEnd = end;
                    // 값이 같고 범위가 더 작을 경우
                }else if(jewelry[idx] == sum && sumEnd - sumStart > end - start){
                    // 범위 변경
                    sumStart = start;
                    sumEnd = end;
                }
            }
            // 최대 가치만큼 증가
            total += sum;
            // 범위를 StringBuilder에 추가
            sb.append(sumStart+1).append(" ").append(sumEnd+1).append("\n");
        }

        // 정답 출력
        System.out.println(total);
        System.out.println(sb);
    }
}
