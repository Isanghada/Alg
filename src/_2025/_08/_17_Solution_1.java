package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32347
// -
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int[] A = new int[N+1];
        for(int n = 1; n <= N; n++) {
            int a = Integer.parseInt(st.nextToken());
            A[n] = a == 1 ? n : 0;
        }
        for(int n = N-1; n > 0; n--){
            if(A[n] == 0) A[n] = A[n+1];
        }

        int right = N - 1;
        int left = 1;
        while(left <= right){
            int mid = (right + left) / 2;

            if(check(A, mid, N, K)) right = mid - 1;
            else left = mid + 1;
        }

        // 정답 출력
        sb.append(left);
        System.out.println(sb.toString());
    }

    private static boolean check(int[] a, int target, int n, int k) {
        Deque<Integer> deque = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        deque.offerLast(n);
        visited[n] = true;
        while(!deque.isEmpty() && k-- > 0){
            int size = deque.size();
            while(size -- > 0){
                int cur = deque.pollFirst();

                int next = Math.max(1, cur - target);
                if(next == 1) return true;
                else{
                    next = a[next];
                    while(!visited[next]) {
                        deque.offerLast(next);
                        visited[next] = true;
                        next = a[next+1];
                    }
                }
            }
        }

        return false;
    }
}
