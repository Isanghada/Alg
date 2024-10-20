package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3078
// - 슬라이딩 윈도우 : 범위내의 이름 길이 카운팅!을 통해 탐색
public class _20_Solution_1 {
    public static final int MAX = 20;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 학생 수
        int K = Integer.parseInt(st.nextToken());   // 범위

        // 성적별 이름 길이
        int[] names = new int[N];
        for(int n = 0; n < N; n++) names[n] = in.readLine().length();

        // 카운팅!
        int[] count = new int[MAX+1];
        for(int n = 0; n <= K; n++) count[names[n]]++;

        // 정답 초기화
        long answer = 0;
        // 마지막 순위는 확인하지 않아도 되므로! 마지막 바로 앞까지 탐색
        int limit = N - 1;
        for(int n = 0; n < limit; n++){
            // 본인 이름 제외!
            count[names[n]]--;

            answer += count[names[n]];

            // 다음 범위 계산!
            int next = n + K + 1;
            if(next < N) count[names[next]]++;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
