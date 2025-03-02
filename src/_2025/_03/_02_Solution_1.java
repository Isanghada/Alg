package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11688
// -
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        long abLcm = lcm(A, B);
        if(L % abLcm == 0) {
            List<Long> divisorList = divisor(L);

            for(long C : divisorList) {
                if (lcm(abLcm, C) == L) {
                    sb.append(C);
                    break;
                }
            }
        }
        else sb.append(-1);

        // 정답 반환
        System.out.println(sb);
    }

    private static List<Long> divisor(long target) {
        List<Long> divisorList = new ArrayList<>();

        long  limit = ((long)Math.sqrt(target)) + 1;
        for(long num = 1; num < limit; num++){
            if(target % num == 0){
                divisorList.add(num);
                divisorList.add(target / num);
            }
        }
        Collections.sort(divisorList);
        return divisorList;
    }

    private static long lcm(long a, long b) {
        long gcd = gcd(a, b);
        return a * b / gcd;
    }

    private static long gcd(long a, long b) {
        while(b != 0){
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
