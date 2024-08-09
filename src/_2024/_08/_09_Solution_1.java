package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/31498
// - 이분 탐색 : 토카가 움직일 수 있는 최대 횟수를 이분 탐색을 통해 계산!
//               이를 기준으로 돌돌이가 이동할 수 있는 거리와 비교하여
//               집에 도착할 수 있는지 확인!
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        st = new StringTokenizer(in.readLine());
        long C = A + Long.parseLong(st.nextToken());
        long D = Long.parseLong(st.nextToken());
        long K = Long.parseLong(in.readLine());

        if(K == 0){
            long last = (long) Math.ceil(A / (double)B);
            long t = A - sumOfToka(last, B, K);
            long d = C - sumOfDolDol(last, D);
            if(t < 0) t = 0;
            if(d < 0) d = 0;

            if(d - t > 0) sb.append(last);
            else sb.append(-1);
        }else if(isPossible(A, B, K)){
            long last = findLastToka(A, B, K);
            long t = A - sumOfToka(last, B, K);
            long d = C - sumOfDolDol(last, D);

            if(t < 0) t = 0;
            if(d < 0) d = 0;

            if(d - t > 0) sb.append(last);
            else sb.append(-1);
        }else sb.append(-1);

        // 정답 출력
        System.out.println(sb);
    }

    private static boolean isPossible(long a, long b, long k) {
        long last = b / k;
        long distance = (b % k > 0) ? (b % k): 0;
        distance += sumOfToka(last, b, k);

        return (a <= distance) ? true : false;
    }

    private static long findLastToka(long a, long b, long k) {
        long last = 0;
        long left = 1;
        long right = b / k + 1;
        while(left <= right){
            long mid = (left + right) / 2;
            if(sumOfToka(mid, b, k) < a ) left = mid+1;
            else{
                last = mid;
                right = mid-1;
            }
        }
        return last;
    }

    private static long sumOfDolDol(long n, long d) {
        long doldol = d * n;
        return doldol;
    }

    private static long sumOfToka(long n, long b, long k) {
        long toka = (n * (2*b - (n-1)*k)) / 2;
        return toka;
    }
}
