package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32375
// -
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(A);
        long answer = 0;
        if(N != 1){
            int left = 0;
            int right = N -1;
            while(left <= right){
                if(A[right] >= K){
                    answer++;
                    right--;
                    continue;
                }
                if(left != right && A[left] + A[right] >= K){
                    answer++;
                    right--;
                }
                left++;
            }
        }else answer += (A[0] >= K) ? 1 : 0;

        if(answer == 0) answer = -1;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
