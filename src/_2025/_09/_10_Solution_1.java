package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/22965
// -
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int k = 1;
        boolean flag = true;
        for(int i = 1; i < N; i++){
            if(A[i] < A[i-1]) k++;
            if(k >= 2 && A[i] > A[0]){
                flag = false;
                break;
            }
        }

        // 정답 출력
        sb.append(flag ? Math.min(k, 3) : 3);
        System.out.println(sb.toString());
    }
}
