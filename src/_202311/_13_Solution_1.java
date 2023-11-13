package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1684
// - 각 값에서 가장 작은 값을 뺸 값들의 최대 공약수를 구하면 된다.
// - 이때, 가장 작은 값은 제외한다
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        // 수의 개수
        int N = Integer.parseInt(in.readLine());

        // 수열 입력
        int[] numbers = new int[N];
        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

        // 오름차순 정렬
        Arrays.sort(numbers);

        // 가장 작은 값
        int i = 0;
        int min = numbers[0];
        // 각 값을 최소값만큼 감소
        for (;i < N; i++) if ((numbers[i] -= min) != 0) break;
        // 모두가 최소값인 경우 최소값 반환
        if (i == N) sb.append(min);
        // 값이 남은 경우
        else{
            // 남은 값중 최소값으로 초기화
            int answer = numbers[i++];
            // 남은 모든 값들의 최대 공약수 계산
            for(; i < N; i++) answer = gcd(answer, numbers[i] -= min);
            // 정답 반환
            sb.append(answer);
        }
        System.out.println(sb);
    }

    private static int gcd(int a, int b) {
        int r = -1;
        while(r != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
