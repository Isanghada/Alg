package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13422
// - 슬라이딩 윈도우 : 연속된 구간이므로 슬라이딩 윈도우를 통해 누적합을 차례로 확인!
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 집의 수
            int M = Integer.parseInt(st.nextToken());   // 범위
            int K = Integer.parseInt(st.nextToken());   // 경보

            int LIMIT = N * 2;

            // 마지막 집이 첫번째 집과 연결되어 있으므로 배열을 집의 수 2배로 설정
            int[] houses = new int[LIMIT];
            st = new StringTokenizer(in.readLine());
            for(int i = 0; i < N; i++){
                houses[i] = houses[i+N] = Integer.parseInt(st.nextToken());
            }

            int left = 0;   // 시작
            int right = 0;  // 끝(미포함)
            int sum = 0;    // 합
            do{ sum += houses[right++];}
            while( right < M );

            // 정답
            int answer = 0;
            // 범위 내에서 반복!
            // - 집의 수와 범위가 동일한 경우 1개의 경우 뿐이므로 바로 종료
            do{
                // 경보가 울리지 않으면 정답 증가
                if(sum < K) answer++;
                // 다음 위치로 이동
                sum -= houses[left++];
                sum += houses[right++];
            }while(N != M && left < N && right < LIMIT);

            // 정답 반환
            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
