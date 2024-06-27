package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25194
// - DP : 요일 체크를 DP로 확인!
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        // weeks[w] : w 요일에 일을 마치는 것이 가능한지 여부
        // - 0(월) ~ 6(일) 순으로 진행!
        boolean[] weeks = new boolean[7];
        weeks[0] = true;

        // 업무에 따라 마칠 수 있는 요일 체크!
        StringTokenizer st = new StringTokenizer(in.readLine());
        while(st.hasMoreTokens()){
            // 현재 업무가 걸리는 시간
            int time = Integer.parseInt(st.nextToken());
            // 현재까지의 요일 체크
            boolean[] temp = Arrays.copyOf(weeks, 7);
            // 모든 요일 확인
            for(int w = 0; w < 7; w++){
                // 업무를 마칠 수 있는 요일인 경우 현재 업무 추가 진행!
                if(temp[w]){
                    // 요일 계산
                    int nextWeek = (w + time) % 7;
                    // 요일 체크
                    weeks[nextWeek] = true;
                }
            }
        }

        // 정답 반환
        // - 금요일(4)에 일을 마칠 수 있는 경우 YES, 아닐 경우 NO 반환
        sb.append(weeks[4] ? "YES" : "NO");
        System.out.println(sb);
    }
}
