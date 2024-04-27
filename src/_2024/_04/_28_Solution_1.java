package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

// https://www.acmicpc.net/problem/1914
// - 재귀 : 각 원판을 차례로 이동!
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 원판의 수
        int N = Integer.parseInt(in.readLine());

        // 원판을 옮겨야 하는 횟수 : 2^n - 1
        // - int, long을 넘어서는 횟수가 나오므로 BitInteger 활용
        BigInteger count = new BigInteger("2");
        sb.append(count.pow(N).subtract(BigInteger.ONE)).append("\n");

        // 원판이 20개 이하인 겨우 수행 과정 출력
        if(N < 21){
            hanoi(N, 1, 3, 2, sb);
        }

        System.out.println(sb);
    }

    private static void hanoi(int n, int from, int to, int pass, StringBuilder sb) {
        if(n == 1) addHanoi(from, to, sb);
        else{
            hanoi(n-1, from, pass, to, sb);
            addHanoi(from, to, sb);
            hanoi(n-1, pass, to, from, sb);
        }
    }

    private static void addHanoi(int from, int to, StringBuilder sb) {
        sb.append(from).append(" ")
                .append(to).append("\n");
    }
}
