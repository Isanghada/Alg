package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/8901
// -
public class _15_Solution_1 {
    static final int ALPHABET = 26;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] store = in.readLine().toCharArray();

        for(int i = 0; i < N; i++){
            int count = store[i] - 'A';
            if(K-count <= 0) {
                store[i] = (char) ('A' + K - 1);
                K = 0;
                break;
            }
            K -= count;
        }

        if(K == 1) K = 0;
        if(K > 0){
            K--;
            for(int i = N - 1; i >= 0; i--) {
                int count = 'Z' - 'A' - (store[i] - 'A');
                if(K - count <= 0){
                    store[i] = (char)(store[i] + K);
                    K = 0;
                    break;
                }
                K -= count;
            }
        }

        sb.append((K == 0 ? String.valueOf(store) : -1));

        // 정답 출력
        System.out.println(sb);
    }
}
