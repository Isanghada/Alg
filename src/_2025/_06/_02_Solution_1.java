package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27297
// - 정렬 : 맨해튼 거리는 같은 차원의 좌표만 영향을 받으므로 각 차원의 중앙값 선택!
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] points = new long[N][M];
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(in.readLine());
            for(int n = 0; n < N; n++) points[n][m] = Long.parseLong(st.nextToken());
        }

        long answer = 0;
        long[] answerPointArr = new long[N];
        for(int n = 0; n < N; n++){
            Arrays.sort(points[n]);
            answerPointArr[n] = points[n][M/2];
            for(long point : points[n]) answer += Math.abs(answerPointArr[n] - point);
        }

        sb.append(answer).append("\n");
        for(long point : answerPointArr) sb.append(point).append(" ");

        // 정답 반환
        System.out.println(sb);
    }
}
