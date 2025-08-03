package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26648
// -
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        int[] physicsScore= Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] computerScore= Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] mathScore= Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int target = -1;
        for(int i = 0; i < N; i ++){
            int min = getMin(physicsScore[i], computerScore[i], mathScore[i]);
            int max = getMax(physicsScore[i], computerScore[i], mathScore[i]);

            if(target >= max){
                sb.append("NO");
                break;
            }else if(min <= target) target++;
            else target = min;
        }

        if(sb.length() == 0) sb.append("YES");

        // 정답 반환
        System.out.println(sb);
    }

    private static int getMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private static int getMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
