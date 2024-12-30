package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/16563
// - 소인수분해 : 모든 수의 최소 소인수 계산!
//                최소 소인수를 활용해 다음 소인수 탐색
public class _30_Solution_1 {
    // 최대값
    public static final int MAX = 5_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 최소 소인수 배열 : 각 숫자의 최소 소인수
        int[] minPrime = new int[MAX+1];
        for(int num = 1; num <= MAX; num++) minPrime[num] = num;
        setMinPrime(minPrime);

        // 숫자 개수
        int N = Integer.parseInt(in.readLine());
        // 숫자 입력
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int num : numbers){
            do{
                sb.append(minPrime[num]).append(" ");
                num /= minPrime[num];
            }while(num != 1);
            sb.append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static void setMinPrime(int[] minPrime) {
        int LIMIT = (int) Math.sqrt(MAX);
        for(int num = 2; num <= LIMIT; num++){
            if(minPrime[num] == num){
                for(int next = num * num; next <= MAX; next += num){
                    if(minPrime[next] == next) minPrime[next] = num;
                }
            }
        }
    }
}