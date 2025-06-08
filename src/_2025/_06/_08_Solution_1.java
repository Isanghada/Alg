package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27977
// -
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] stations = new int[N+2];
        stations[N+1] = L;
        st = new StringTokenizer(in.readLine());
        int max = 0;
        for(int n = 1; n <= N; n++) {
            stations[n] = Integer.parseInt(st.nextToken());
            max = Math.max(max, stations[n] - stations[n-1]);
        }

        max = Math.max(max, stations[N+1] - stations[N]);

        // 정답 출력
        sb.append(binarySearch(L, max, N, K, stations));
        System.out.println(sb);
    }

    private static int binarySearch(int right, int left, int n, int k, int[] stations) {
        while(left <= right){
            int mid = (left + right) / 2;
            if(check(mid, k, stations, n)) right = mid - 1;
            else left = mid + 1;

        }

        return left;
    }

    private static boolean check(int battery, int k, int[] stations, int n) {
        int count = 0;
        int move = 0;
        for(int i = 1; i <= n+1; i++){
            if(stations[i] - move > battery){
                count++;
                move = stations[i-1];
            }
        }

        return count <= k;
    }
}
