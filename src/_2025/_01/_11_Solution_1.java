package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2487
// - 유클리드 호제법 : 각 카드가 원래 자리로 돌아가는 반복 횟수를 구하고,
//                     모든 횟수의 최소 공배수를 유클리드 호제법으로 계산
// - 참고 : 이동 횟수를 DP로 계산하면 더욱 효율적
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 카드의 수
        int N = Integer.parseInt(in.readLine());
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        // 이동 정보
        // change[card] => card가 이동하는 순서
        int[] change = new int[N+1];
        for(int i = 1; i <= N; i++){
            int order = Integer.parseInt(st.nextToken());
            change[order] = i;
        }

        // System.out.println(Arrays.toString(change));
        // 이동 횟수 초기화
        int[] counts = new int[N+1];
        for(int card = 1; card <= N; card++){
            int idx = card; // 카드 순서
            int count = 1;  // 이동 횟수
            // 원래 자리로 복귀할 때까지 반복
            while(card != change[idx]){
                idx = change[idx];
                count++;
            }
            counts[card] = count;
        }

        // System.out.println(Arrays.toString(counts));

        // 정답 초기화
        int answer = 1;
        // 모든 카드 이동 횟수에 대해 최소 공배수 계산
        // - A, B의 최소 공배수 : A x B / 최소공배수(A, B)
        // - 유클리드 호제법 활용
        for(int card = 1; card <= N; card++){
            answer = answer * (counts[card] / gcd(answer, counts[card]));
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static int gcd(int a, int b) {
        int r = 0;
        while(b != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
