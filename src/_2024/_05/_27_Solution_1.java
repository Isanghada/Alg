package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15918
// - 백트래킹 : X, Y 위치의 값을 정해두고 가능한 모든 경우 탐색
public class _27_Solution_1 {
    // ANSWER : 가능한 경우의 수
    // LEN : 랭퍼드 수열의 길이
    // N : 사용할 숫자 범위
    // X, Y : 값이 정해진 위치
    public static int ANSWER, LEN, N, X, Y;
    // 랭퍼드 수열
    public static int[] SEQUENCE;
    // 사용한 숫자 배열
    public static boolean[] USED;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 값 입력 : X, Y는 인덱스를 0부터 시작하기 위해 1씩 감소
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken())-1;
        Y = Integer.parseInt(st.nextToken())-1;

        // 랭퍼드 수열 길이
        LEN = 2 * N;
        // 랭퍼드 수열 초기화
        SEQUENCE = new int[LEN];
        Arrays.fill(SEQUENCE, -1);
        // 사용 숫자 배열 초기화
        USED = new boolean[13];

        // X, Y 위치의 값 입력
        int num = Y-X-1;
        USED[num] = true;
        SEQUENCE[X] = num;
        SEQUENCE[Y] = num;

        // 정답 초기화
        ANSWER = 0;
        // 첫 인덱스부터 백트래킹
        // - 가능한 모든 값을 입력하며 확인!
        backtracking(0);

        // 정답 반환
        sb.append(ANSWER);
        System.out.println(sb);
    }
    //
    private static void backtracking(int step) {
        // 모든 값을 입력한 경우 : 정답 증가!
        if(step == LEN) ANSWER++;
        // 이미 선택된 값이 있는 경우 다음 인덱스 탐색
        else if(SEQUENCE[step] != -1) backtracking(step+1);
        // 선택된 값이 없는 경우 모든 값 체크!
        else{
            for(int num = 1; num <= N; num++){
                // 다음 위치
                int next = step + num + 1;
                // 이미 사용한 숫자이거나 범위를 벗어나거나 다음 위치에 선택된 값이 있는 경우 패스
                if(USED[num] || next >= LEN || SEQUENCE[next] != -1) continue;
                // num 체크
                SEQUENCE[step] = SEQUENCE[next] = num;
                USED[num] = true;
                // 다음 위치 탐색
                backtracking(step+1);
                // num 반환
                SEQUENCE[step] = SEQUENCE[next] = -1;
                USED[num] = false;
            }
        }
    }
}
