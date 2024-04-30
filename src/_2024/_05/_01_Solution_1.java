package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1756
// - 구현 : 각 높이에 들어갈 수 있는 피자를 계산하여 활용
public class _01_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 오븐의 깊이
        int D = Integer.parseInt(st.nextToken());   // 피자 반죽의 개수

        // 깊이별 가능한 피자 지름 : 위의 피자 지름이하인 경우만 가능하므로 이를 계산하여 체크
        int[] depths = new int[N+1];
        depths[0] = Integer.MAX_VALUE;
        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++){
            depths[i] = Math.min(
                    depths[i-1],                        // 이전 피자 지름
                    Integer.parseInt(st.nextToken())    // 현재 피자 지름
            );
        }

        // 정답 초기화
        int answer = 0;

        // 피자가 들어간 높이
        int depth = N+1;
        st = new StringTokenizer(in.readLine());
        while(st.hasMoreTokens()){
            // 피자 지름
            int pizza = Integer.parseInt(st.nextToken());
            // 오븐 높이가 0 초과인 경우에서 반복
            while(depth > 0){
                // 현재 높이 찾기
                depth--;
                // 피자가 들어갈 수 있을 경우 D 감소 후 종료
                if(depths[depth] >= pizza){
//                    System.out.println(pizza+", "+depth+", "+depths[depth]);
                    D--;
                    break;
                }
            }
        }

        // 모든 피자가 오븐에 들어간 경우 answer 갱신
        if(D == 0) answer = depth;

        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }
}
