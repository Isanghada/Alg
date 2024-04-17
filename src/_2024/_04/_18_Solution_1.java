package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12934
// - 그리디 : 최대 턴 N을 구하여 x가 될 수 있는 최소 횟수 계산!
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long X = Long.parseLong(st.nextToken());    // 윤호의 점수
        long Y = Long.parseLong(st.nextToken());    // 동혁의 점수

        // 1번째 턴부터 마지막 턴까지의 점수 합
        long total = X + Y;
        // 최대 턴
        // - total 최대값은 2 * 10^12
        // - total == N*(N+1)/2 이므로 N은 2000000을 넘을 수 없다!
        long N = 1;
        while(true){
            // N턴까지 점수합
            long sum = (N+1)*N / 2;
            // total보다 sum이 클 경우 : 불가능한 경우이므로 -1 반환
            if(total < sum) {
                sb.append(-1);
                break;
            // 가능한 경우
            }else if(total == sum){
                // 윤호 점수가 되기 위해 승리해야할 최소 횟수 계산
                int answer = 0;
                // 마지막 턴부터 차례로 X가 이겨야 하는 횟수 계산
                // - X가 0보다 작아질 때까지 감소
                // - 음수가 되면 해당 값보다 작은 값이 필요하지만 1 ~ N까지 모든 수가 있으므로 가능하다.
                while(X > 0){
                    X -= N--;
                    answer++;
                }
                sb.append(answer);
                break;
            // sum이 작을 경우 N증가!
            }else N++;
        }

        System.out.println(sb.toString());
    }
}
