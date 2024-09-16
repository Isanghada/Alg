package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13397
// - 참고 : https://dingdingmin-back-end-developer.tistory.com/entry/%EB%B0%B1%EC%A4%80-13397%EC%9E%90%EB%B0%94-java-%EA%B5%AC%EA%B0%84-%EB%82%98%EB%88%84%EA%B8%B0-2
// - 이분 탐색 : 구간 점수의 최대값을 target으로 설정하여 이분 탐색
public class _16_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 배열 크기
        int M = Integer.parseInt(st.nextToken());   // 구간의 수

        // 최대값 설정
        int right = 0;
        // 배열 초기화
        int[] numbers = new int[N];

        // 배열 입력 : right 갱신!
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, numbers[i]);
        }

        // left 설정 후 이분 탐색 진행
        int left = 0;
        while(left < right){
            // 구간 점수 최대값 설정
            int mid = (left + right) / 2;
            // mid값을 토대로 구간을 M개로 나눌 수 있다면 right 갱신!
            if(binarySearch(numbers, N, mid) <= M) right = mid;
            // 나눌 수 없다면 left 갱신
            else left = mid + 1;
        }

        // 정답 출력
        sb.append(right);
        System.out.println(sb.toString());
    }

    private static int binarySearch(int[] numbers, int n, int target) {
        int count = 1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            min = Math.min(min, numbers[i]);
            max = Math.max(max, numbers[i]);
            // 구간 점수가 target보다 큰 경우 구간 분리!
            if(max - min > target) {
                count++;
                max = min = numbers[i];
            }
        }
        return count;
    }
}
