package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27923
// -
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] M = new int[N+1];
        int[] T = new int[N+1];

        int kolaSize = Math.max(N+1, K+1);
        int[] kola = new int[kolaSize];

        st =new StringTokenizer(in.readLine());
        int maxM = 0;
        for(int n = 1; n <= N; n++) {
            M[n] = Integer.parseInt(st.nextToken());
            maxM = Math.max(maxM, M[n]);
        }

        st = new StringTokenizer(in.readLine());
        for(int k = 1; k <= K; k++) {
            int t = Integer.parseInt(st.nextToken());
            T[t]++;
            if(t + L <= N) T[t+L]--;
        }

        kola[0] = 1;
        maxM++;
        for(int k = 1; k < kolaSize; k++) kola[k] = Math.min(kola[k-1] * 2, maxM);
        System.out.println(Arrays.toString(kola));

        for(int n = 1; n <= N; n++) {
            T[n] += T[n-1];
        }

        Arrays.sort(M);
        Arrays.sort(T);

        long answer = 0;
        for(int n = N; n >= 1; n--) answer += M[n] / kola[T[n]];

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
