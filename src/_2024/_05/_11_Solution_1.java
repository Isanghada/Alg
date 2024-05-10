package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2467
// - 이분탐색 : 각 값을 기준으로 특성값이 0에 가까이 될 수 있도록 이분탐색으로 계산
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 용액의 수
        int N = Integer.parseInt(in.readLine());

        // 용액 정보
        int[] liquidArr = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) liquidArr[i] = Integer.parseInt(st.nextToken());

        // 오름차순 정렬
        Arrays.sort(liquidArr);

        // 특성값과 0의 차이 절대값!
        int min = Integer.MAX_VALUE;
        // min을 만드는 left, right 값
        int minLeft = 0, minRight = 0;

        // 각 값 별로 이분탐색 진행
        for(int i = 0; i < N; i++){
            // i를 기준을로 left, right 설정
            int left = i+1;
            int right = N - 1;
            // 이분 탐색 진행
            while(left <= right){
                // 중앙 인덱스
                int mid = (left + right) / 2;
                // 현재 특성값과 0의 차이
                int sum = Math.abs(liquidArr[i] + liquidArr[mid]);

                // min이 더 클 경우 값 갱신!
                if(min > sum){
                    min = sum;
                    minLeft = liquidArr[i];
                    minRight = liquidArr[mid];
                }

                if(liquidArr[mid] >= -liquidArr[i]) right = mid - 1;
                else left = mid + 1;
            }
        }

        // 정답 출력
        sb.append(minLeft).append(" ").append(minRight);
        System.out.println(sb);
    }
}
