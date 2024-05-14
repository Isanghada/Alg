package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/8901
// - 브루트포스 : 각 개수를 차례로 정하여 계산!
//                ab, bc의 수를 정하면 ca는 자동으로 계산할 수 있다!
public class _15_Solution_1 {
    // 용액의 수
    public static final int SIZE = 3;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            // 용액별 개수
            st = new StringTokenizer(in.readLine());
            int[] countOfThings = new int[SIZE];
            for(int i = 0; i < SIZE; i++) countOfThings[i] = Integer.parseInt(st.nextToken());

            // 조합별 가격
            st = new StringTokenizer(in.readLine());
            int[] valueOfThings = new int[SIZE];
            for(int i = 0; i < SIZE; i++) valueOfThings[i] = Integer.parseInt(st.nextToken());

            // 정답 초기화
            int answer = 0;
            // ab, bc의 개수를 정하고 ca를 계산하여 최대값 갱신!
            for(int ab = 0; ab <= Math.min(countOfThings[0], countOfThings[1]); ab++){
                for(int bc = 0; bc <= Math.min(countOfThings[1]-ab, countOfThings[2]); bc++){
                    int ca = Math.min(countOfThings[0] - ab, countOfThings[2] - bc);
                    answer = Math.max(answer, ab * valueOfThings[0] + bc * valueOfThings[1] + ca * valueOfThings[2]);
                }
            }
            // 정답 반환
            sb.append(answer).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
