package _2023._202309;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2531
// - 슬라이딩 윈도우 활용
// -
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        // System.setIn(new FileInputStream("src/_2023/_202309/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        // 입력값 설정
        st = new StringTokenizer(in.readLine());
        int d = Integer.parseInt(st.nextToken()); // 접시의 수
        int k = Integer.parseInt(st.nextToken()); // 초밥의 종류
        int n = Integer.parseInt(st.nextToken()); // 연속으로 먹는 초밥의 양
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 초밥
    
        // 접시를 n + k개로 마지막 접시에서도 확인할 수 있도록 설정
        int[] dishArr = new int[n+k];
        // 접시 입력 : n개의 접시 입력
        for(int idx = 0; idx < n; idx++){
            dishArr[idx] = Integer.parseInt(in.readLine());
        }
        // n+1부터의 접시를 0부터 반복하여 입력
        for(int idx = n; idx < n + k; idx++){
            dishArr[idx] = dishArr[idx-n];
        }

        // 먹을 수 있는 초밥 확인 배열
        // - 1이상인 경우 먹을 수 있는 초밥
        int[] eatArr = new int[d+1];
        eatArr[c] = 1;  // 쿠폰 초밥 설정
        int sum = 1;    // 현재 최대 초밥 종류

        // 처음 k개의 초밥 먹는 경우 설정!
        for(int idx = 0; idx < k; idx++) {
            eatArr[dishArr[idx]]++;
            // eatArr[초밥 종류]가 1인 경우에만 sum 증가
            if(eatArr[dishArr[idx]] == 1) sum++;
        }

        // 정답 초기화
        int answer = sum;
        // - 슬라이딩 윈도우 기법을 활용하여 right 증가, left 감소!
        for(int idx = k; idx < n+k; idx++){
            // 왼쪽 초밥 종류 : 먹지 않는 초밥
            int left = dishArr[idx-k];
            // 오른쪽 초밥 종류 : 새로 먹을 초밥
            int right = dishArr[idx];

            // left 감소!
            eatArr[left]--;
            // 0인 경우 sum 감소
            if(eatArr[left] == 0) sum--;
            // right 증가
            eatArr[right]++;
            // 1인 경우 sum 증가
            if(eatArr[right] == 1) sum++;
            // 정답을 최대값으로 변경
            answer = Math.max(answer, sum);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
