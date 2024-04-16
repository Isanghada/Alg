package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19597
// - DP : 각 경우를 체크하며 진행!
public class _16_Solution_1 {
    public static String answer;    // 테스트 케이스의 정답
    public static String[][] arr;     // 테스트 케이스의 문자열
    public static int[][] DP_;
    public static final int TYPE = 2;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 입력
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 문자열의 수 입력
            int N = Integer.parseInt(in.readLine());

            // 문자열 초기화
            arr = new String[N][2];
            // 정답 초기화
            answer = "";

            // 문자열 입력
            for(int i = 0; i < N; i++){
                arr[i][0] = in.readLine();
                arr[i][1] = getReverse(arr[i][0]);
                answer += 1;
            }

            DP_ = new int[N][TYPE];
            for(int n = 0; n < N; n++) Arrays.fill(DP_[n], -1);

            for(int n = 0; n < N; n++){
                if(n == 0){
                    if(DP(n, 0, N-1) == 1) sb.append(0);
                    else sb.append(1);
                }else{
                    String prev = (sb.charAt(sb.length()-1) == '0' ? arr[n-1][0] : arr[n-1][1]);
                    if(prev.compareTo(arr[n][0]) <= 0 && DP(n, 0, N-1) == 1) sb.append(0);
                    else sb.append(1);
                }
            }

//            System.out.println("-------------");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int DP(int step, int type, int n) {
        if(step == n) return 1;
        if(DP_[step][type] != -1) return DP_[step][type];

        DP_[step][type] = 0;
        if(arr[step][type].compareTo(arr[step+1][0]) <= 0) DP_[step][type] |= DP(step+1, 0, n);
        if(arr[step][type].compareTo(arr[step+1][1]) <= 0) DP_[step][type] |= DP(step+1, 1, n);
        return DP_[step][type];
    }

    public static String getReverse(String input){
        return (new StringBuilder(input).reverse().toString());
    }
}
