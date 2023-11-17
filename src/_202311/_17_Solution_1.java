package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28018
// - 누적합을 통해 사용할 수 없는 좌석의 수 계산
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 댓글을 달아준 학생의 수
        int N = Integer.parseInt(in.readLine());

        // 시간별 좌석 사용수를 담을 배열
        int[] time = new int[1000002];

        // 댓글별 시간 체크 
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());   // 시작 시간
            int e = Integer.parseInt(st.nextToken());   // 종료 시간

            // 시작 시간의 사용 좌석 증가
            time[s]++;
            // 종료 시간 이후 사용 좌석 감소 : 종료시간까지 사용하므로 이후에 반환
            time[e+1]--;
        }

        // 누적합 계산 : 현재 시간대에 사용중인 좌석 수 계산
        for(int i = 1; i < time.length; i++) time[i] += time[i-1];

        // 특정 시간대의 수
        int Q = Integer.parseInt(in.readLine());
        // 특정 시간대의 사용 불가 좌석 반환
        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0; i < Q; i++){
            int q = Integer.parseInt(st.nextToken());
            sb.append(time[q]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
