package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32631
// -
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        long[] A = new long[N+1];
        for(int i = 1; i <= N; i++) A[i] = A[i-1] + Long.parseLong(st.nextToken());

        st = new StringTokenizer(in.readLine());
        long[] B = new long[N+1];
        for(int i = 1; i <= N; i++) B[i] = B[i-1] + Long.parseLong(st.nextToken());

        long[] removeA = getRemoveBag(A, N, K);
        long[] removeB = getRemoveBag(B, N, K);


        long answer = Long.MAX_VALUE;
        for(int removeCountA = 0; removeCountA <= K; removeCountA++){
            int removeCountB = K - removeCountA;
            answer = Math.min(answer, Math.max(
                    removeA[removeCountA],
                    removeB[removeCountB]
            ));
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static long[] getRemoveBag(long[] bag, int n, int k) {
        long[] removeBag = new long[k+1];
        Arrays.fill(removeBag, Long.MAX_VALUE);

        for(int start = 0; start <= k; start++){
            final int limitEnd = k -start;
            for(int end = 0; end <= limitEnd; end++){
                int remove = start+end;
                removeBag[remove] = Math.min(removeBag[remove], bag[n-end] - bag[start]);
            }
        }

        return removeBag;
    }
}
