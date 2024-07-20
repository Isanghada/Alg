package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11578
// - 브루트포스 : 풀 수 있는 문제를 비트마스크로 표현하고,
//                가능한 모든 조합에서 최소 팀원 탐색!
public class _21_Solution_1 {
    public static final int BIT_SIZE = 10;  // 비트 크기(= 문제의 개수)
    public static int ANSWER, TARGET, N, M; // 정답, 모든 문제 해결시 비트, 문제의 수, 학생의 수
    // 학생 정보 배열
    public static int[] STUDENTS;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 비트 정보 입력
        int[] BIT = new int[BIT_SIZE+1];
        BIT[1] = 1;
        for(int bit = 2; bit <= BIT_SIZE; bit++) BIT[bit] = BIT[bit-1] << 1;

        // 문제, 학생 수 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 학생 정보 입력
        STUDENTS = new int[M];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine());
            int o = Integer.parseInt(st.nextToken());
            // 학생이 풀 수 있는 문제를 비트마스크로 표현
            int bitmask = 0;
            while(o-- > 0){
                int problem = Integer.parseInt(st.nextToken());
                bitmask |= BIT[problem];
            }
            STUDENTS[i] = bitmask;
        }

        // 모든 문제를 풀었을 경우의 비트 계산
        for(int bit = 1; bit <= N; bit++) TARGET += BIT[bit];
        // 정답 초기화
        ANSWER = Integer.MAX_VALUE;

        // 재귀를 통해 모든 조합 탐색
        searchMinTeam(0, 0, 0);

        // 정답 출력
        sb.append((ANSWER != Integer.MAX_VALUE) ? ANSWER : -1);
        System.out.println(sb);
    }

    private static void searchMinTeam(int count, int start, int bitmask) {
        // 모든 문제를 풀 수 있는 경우 : 정답 갱신
        if(bitmask == TARGET) ANSWER = Math.min(ANSWER, count);
        // 학생의 수를 넘어설 경우 종료
        else if(start < M) {
            for(; start < M; start++){
                searchMinTeam(count+1, start+1, bitmask | STUDENTS[start]);
            }
        }
    }
}