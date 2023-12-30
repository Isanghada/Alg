package _2023._202309;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1337
// - 가능한 모든 경우 탐색
// - 시작점 포함 5개의 값 확인 -> 시작점기준 마지막 값이 (시작점 + 4)보다 큰 경우 개수를 1개씩 줄이며 탐색
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202309/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 배열 길이
        int N = Integer.parseInt(in.readLine());
        // 배열 초기화
        int[] arr = new int[N];
        // 값 입력
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(in.readLine());
        // 정렬
        Arrays.sort(arr);
        
        
        // 정답 초기값 설정 : 최소값을 구해야하므로 최대값으로 초기화
        int answer = Integer.MAX_VALUE;
        // i : 시작점
        // j : 끝점
        for(int i = 0; i < N; i++){
            int last = arr[i] + 4;
            for(int j = Math.min(i+4, arr.length-1); j >= i; j--){
                // 마지막 값 보다 클 경우 패스
                if(last < arr[j]) continue;
                // 이닐 경우 (j-i+1)개가 사용할 수 있으므로 5개중 해당 값을 제외한 값과 정답 중 최소값 선택
                answer = Math.min(answer, (5 - j + i -1));
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
