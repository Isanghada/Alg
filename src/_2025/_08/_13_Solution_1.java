package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/4370
// -
public class _13_Solution_1 {
    static final String B = "Baekjoon wins.\n";
    static final String D = "Donghyuk wins.\n";
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String input = in.readLine();
            if(input == null || input.equals(null)) break;

            long N = Long.parseLong(input);
            long p = 1;
            while(true){
                p *= 9;
                if(p >= N){
                    sb.append(B);
                    break;
                }
                p *= 2;;
                if(p >= N){
                    sb.append(D);
                    break;
                }
            }
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
