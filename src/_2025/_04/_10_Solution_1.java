package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23829
// - 누적합 :  1. 나무의 좌표를 기준으로 정렬 후 누적합 계산
//              2. 사진 좌표를 기준으로 이분 탐색
//                  - 사진 좌표 기준 이하인 나무의 개수 혹은 기준 이상인 나무의 개수 체크!
//              3. 이분 탐색 값을 토대로 점수 계산
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 나무의 수
        int Q = Integer.parseInt(st.nextToken());   // 사진의 수

        int[] treePoints = new int[N+1];    // 나무 좌표
        long[] treeSum = new long[N+1];     // 나무 좌표 누적합

        // 나무 좌표 입력
        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++) treePoints[i] = Integer.parseInt(st.nextToken());

        // 나무 좌표 정렬
        Arrays.sort(treePoints);
        
        // System.out.println(Arrays.toString(treePoints));
        // 나무 좌표 누적합 계산
        for(int i = 1; i <= N; i++) treeSum[i] = treeSum[i-1] + treePoints[i];

        // 사진을 찍는 위치마다 점수 출력
        for(int i = 0; i < Q; i++){
            long point = Integer.parseInt(in.readLine());    // 사진 좌표
            // - 사진찍는 좌표와 나무의 좌표가 같을 경우 점수는 0이므로
            //   left, right 모두에 포함되어도 점수에 영향이 없다.
            int left = binarySearch(treePoints, N, point);  // point 좌표 이하인 나무의 수
            int right = N - left;                           // point 좌표 이상인 나무의 수

            // point 이하인 나무의 점수 + point 이상인 나무의 점수
            long answer = point * left - treeSum[left] +
                          treeSum[N] - treeSum[left] - point * right;
            // 정답 출력
            sb.append(answer).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString().trim());
    }

    private static int binarySearch(int[] treePoints, int n, long point) {
        int left = 0;
        int right = n;

        while(left <= right){
            int mid = (left + right) / 2;
            if(treePoints[mid] == point) return mid;
            else if(treePoints[mid] < point) left = mid + 1;
            else right = mid - 1;
        }

        return right;
    }
}
