package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/27943
// -
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long N = Long.parseLong(in.readLine());

        long targetIdx = (N+1) / 2;
        System.out.println(question(targetIdx));
        System.out.flush();
        String target = in.readLine();

        long under = underBoundBinarySearch(1, targetIdx, target, in);
        long upper = upperBoundBinarySearch(targetIdx, N, target, in);

        // 정답 출력
        sb.append("! ").append(under).append(" ").append(upper);
        System.out.println(sb);
    }

    private static String question(long target) {
        return String.format("? %d", target);
    }

    private static long underBoundBinarySearch(long left, long right, String target, BufferedReader in) throws Exception{
        while(left <= right){
            long mid = (left + right) / 2;
            System.out.println(question(mid));
            System.out.flush();
            String cur = in.readLine();

            if(cur.equals(target)) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    private static long upperBoundBinarySearch(long left, long right, String target, BufferedReader in) throws Exception{
        while(left <= right){
            long mid = (left + right) / 2;
            System.out.println(question(mid));
            System.out.flush();
            String cur = in.readLine();

            if(cur.equals(target)) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }
}
