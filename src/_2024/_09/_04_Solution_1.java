package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2631
// - LIS(최장 증가 부분 수열) : 다이나믹 프로그래밍을 통해 LIS 탐색!
//                              증가하는 부분 수열은 움직일 필요가 없으므로 나머지 부분 수열만 이동!
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 아이들의 수
        int N = Integer.parseInt(in.readLine());

        // 아이들 정보 입력
        int[] kids = new int[N+1];
        for(int n = 1; n <= N;n++) kids[n] = Integer.parseInt(in.readLine());

        // 최대 증가 부분 수열 크기!
        int maxLIS = 0;
        // LIS 초기화
        int[] lis = new int[N+1];
        // LIS 탐색!
        for(int i = 1; i <= N; i++){
            lis[i] = 1;
            for(int j = 1; j < i; j++){
                if(kids[i] > kids[j]) lis[i] = Math.max(lis[i], lis[j]+1);
            }
            // 최대값 탐색
            maxLIS = Math.max(maxLIS, lis[i]);
        }

        // 정답 반환
        // - maxLIS를 제외한 나머지 아이들 이동!
        sb.append(N - maxLIS);
        System.out.println(sb);
    }

}
