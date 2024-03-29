package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10330
// - 그리디 : 연속 코드를 비트 순열로 변환하고 기존 비트 순열에서 차례로 변환
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 비트 순열 길이
        int M = Integer.parseInt(st.nextToken());   // 연속 순열 길이

        // 비트 순열
        int[] before = new int[N];
        int beforeSum = 0;  // 비트 순열의 합
        // 변환된 비트 순열
        int[][] after = new int[2][N];
        // 변환된 비트 순열의 합
        int[] afterSum = new int[2];

        // 비트 순열 입력
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) {
            before[i] = Integer.parseInt(st.nextToken());
            beforeSum += before[i];
        }
        
        // 변환된 비트 순열 입력 : 2가지 경우 모두 입력
        st = new StringTokenizer(in.readLine());
        int idx = 0;
        for(int i = 0; i < M; i++){
            int limit = idx + Integer.parseInt(st.nextToken());
            for(; idx < limit; idx++){
                if(i % 2 == 0){
                    after[0][idx] = 0;
                    after[1][idx] = 1;
                }else{
                    after[0][idx] = 1;
                    after[1][idx] = 0;
                }
                afterSum[0] += after[0][idx];
                afterSum[1] += after[1][idx];
            }
        }

//        System.out.println(Arrays.toString(before)+", "+beforeSum);
//        System.out.println(Arrays.toString(after[0])+", "+afterSum[0]);
//        System.out.println(Arrays.toString(after[1])+", "+afterSum[1]);

        // 정답 반환
        // - 각 형태로 변환하는 경우 중 최소값 출력
        sb.append(Math.min(getCount(before, after[0], beforeSum, afterSum[0], N),
                           getCount(before, after[1], beforeSum, afterSum[1], N)));
        System.out.println(sb);
    }

    // 변환 함수 : 최소 변환 횟수 반환
    private static int getCount(int[] before, int[] after, int beforeSum, int afterSum, int n) {
        // 비트 순열 합과 변환된 비트 순열 합이 다른 경우
        // - 불가능하므로 최대값 반환
        if(beforeSum != afterSum) return Integer.MAX_VALUE;

        // 비트 순열 복사
        int[] b = new int[n];
        for(int i = 0; i < n; i++) b[i] = before[i];

        // 변환 횟수 초기화
        int count = 0;
        for(int i = 0; i < n; i++){
            // 비트 순열과 변환된 비트 순열의 값이 다른 경우 변환 진행
            if(b[i] != after[i]){
//                System.out.println(Arrays.toString(b));
//                System.out.println(Arrays.toString(after));
//                System.out.println(i);
//                System.out.println("---------");
                // i 이후의 인덱스 중 after[i]와 같은 값을 가지는 경우 탐색
                int j = i + 1;
                while(b[j] != after[i]) j++;
                // 변환 횟수 증가
                count += j - i;
                // j에서부터 차례로 연산 진행
                j--;
                for(; j >= i; j--) swap(b, j, j+1);
            }
        }
        return count;
    }

    private static void swap(int[] before, int a, int b) {
        int temp = before[a];
        before[a] = before[b];
        before[b] = temp;
    }
}