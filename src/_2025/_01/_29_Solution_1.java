package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17179
// - 이분 탐색 : 최소 길이를 기준으로 이분 탐색 실행
//               케이크가 최소 길이(mid) 이상의 조각 (M+1)개가 나올 수 있는지 체크
//                - M번 자르게 되면 케이크는 (M+1) 조각으로 나누어짐.
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 자르는 횟수 목록 길이
        int M = Integer.parseInt(st.nextToken());   // 자를 수 있는 지점 개수
        int L = Integer.parseInt(st.nextToken());   // 케이크의 길이

        // 자를 수 있는 위치 입력
        int[] points = new int[M+1];
        for(int i = 0; i < M; i++) points[i] = Integer.parseInt(in.readLine());
        // - 마지막 지점 체크를 위해 길이도 추가
        points[M] = L;

        while(N-- > 0){
            // 자르는 횟수
            int Q = Integer.parseInt(in.readLine());

            // 정답 초기화
            int answer = 0;

            int left = 0;   // 시작 
            int right = L;  // 끝
            while(left <= right){
                // 최소 길이 설정
                int mid = (left+right)/2;
                // 가능하다면 최대값 갱신 후 left 증가
                if(check(mid, points, Q)){
                    left = mid + 1;
                    answer = mid;
                // 불가능하다면 right 감소
                }else right = mid -1;
            }

            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean check(int target, int[] points, int q) {
        int prev = 0;
        // target 길이 이상의 조각이 나오는 경우 체크
        for(int i = 0; i < points.length; i++){
            if(points[i] - prev >= target){
                q--;
                prev = points[i];
            }
        }
        // target 길이 이상의 조각이 (q+1)개 이상인 경우 true 아닐 경우 false
        return q < 0 ? true : false;
    }
}