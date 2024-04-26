package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23559
// - 그리디 : 가능한 만큼 상대적 만족도가 큰 5000원을 선택하고 이후 나머지를 1000원 선택!
public class _27_Solution_1 {
    public static final int PRICE_TYPE = 2;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 남은 학기 일수
        int X = Integer.parseInt(st.nextToken());   // 현재 잔액

        // 만족도 배열!
        int[][] flavorArr = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            flavorArr[i][0] = Integer.parseInt(st.nextToken()); // 5000원 메뉴 만족도
            flavorArr[i][1] = Integer.parseInt(st.nextToken()); // 1000원 메뉴 만족도
        }

        // 상대적 만족도 기준 내림차순 정렬!
        // - 상대적 만족도 : (5000원 메뉴 만족도) - (1000원 메뉴 만족도)
        Arrays.sort(flavorArr, (f1, f2)->{
            return (f2[0] - f2[1]) - (f1[0] - f1[1]);
        });

        // 정답 초기화
        int answer = 0;

        // 현재 인덱스
        int n = 0;
        // 5000원 메뉴를 선택할 수 있는 경우 반복
        while(X - ((N - n) * 1000) >= 4000){
            // 1000원 메뉴가 더 만족도가 높을 경우 종료!
            if(flavorArr[n][1] > flavorArr[n][0]) break;
            // 5000원 메뉴 만족도 만큼 증가
            answer += flavorArr[n++][0];
            // 현재 잔액 감소
            X -= 5000;
        }

        // 남은 일정은 1000원 메뉴 선택!
        while(n < N) answer += flavorArr[n++][1];

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
