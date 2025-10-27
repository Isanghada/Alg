package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15752
// -
public class _26_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        int[] X = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(X);

        int[] count = new int[N];
        for(int i = 0; i < N; i++) count[next(i, X, N)]++;

        int answer = 0;
        for(int i = 0; i < N; i++){
            if(count[i] == 0) answer++;
            else if (i < next(i, X, N) && next(next(i, X, N), X, N) == i && count[i] == 1 && count[next(i, X, N)] == 1){
                answer++;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static int next(int idx, int[] x, int n) {
        if(idx == 0) return idx+1;
        else if(idx == (n-1)) return idx-1;
        else{
            int left = x[idx] - x[idx-1];
            int right = x[idx+1] - x[idx];

            if(left <= right) return idx-1;
            else return idx+1;
        }
    }
}
