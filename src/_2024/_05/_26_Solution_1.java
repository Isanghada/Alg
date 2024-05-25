package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1469
// - 브루트포스 : 가능한 모든 경우 탐색!
public class _26_Solution_1 {
    // X의 크기
    public static int N;
    // S : 집합 X
    // ANSWER : 정답
    // TEMP : 백트래킹 임시 배열
    public static int[] S, ANSWER, TEMP;
    // 정답 플래그
    public static boolean FLAG;
    // 숫자 사용 여부
    public static boolean[] USED;

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 값 초기화
        S = new int[N];
        ANSWER = new int[2*N];
        TEMP = new int[2*N];
        USED = new boolean[17];
        FLAG = false;
        // X의 크기 입력
        N = Integer.parseInt(in.readLine());
        // 집합 X 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());

        // S 정렬 : 사전순으로 가장 빠른 경우를 출력하기 위해 작은 값부터 탐색
        Arrays.sort(S);
        // TEMP 초기화
        Arrays.fill(TEMP, -1);
        // 백트래킹 : 첫 인덱스부터 차례로 계산
        backtracking(0);

        // 수열이 있는 경우 수열 반환
        if(FLAG) for(int num : ANSWER) sb.append(num).append(" ");
        // 수열이 없는 경우 -1 반환
        else sb.append(-1);

        // 정답 반환
        System.out.println(sb);
    }
    // 백트래킹 함수 : step 인덱스의 값을 재귀를 통해 탐색
    private static void backtracking(int step) {
        // 답을 찾은 경우 반환
        if(FLAG) return;
        // 마지막 인덱스인 경우
        if(2 * N == step){
            // 답 갱신!
            for(int i = 0; i < TEMP.length; i++) ANSWER[i] = TEMP[i];
            FLAG = true;
        // 현재 위치에 값이 있는 경우 다음 위치 탐색
        }else if(TEMP[step] != -1) backtracking(step+1);
        else{
            // 값 차례로 탐색
            for(int s : S){
                // 이미 사용되었거나, 범위를 벗어나거나, 해당 위치에 값이 있는 경우 패스
                if(USED[s] || (2*N) <= (step+s+1) || TEMP[step+s+1] != -1) continue;
                // 사용 체크
                TEMP[step] = TEMP[step+s+1] = s;
                USED[s] = true;
                // 다음 위치 탐색
                backtracking(step+1);
                // 사용 반환
                USED[s] = false;
                TEMP[step] = TEMP[step+s+1] = -1;
            }
        }
    }
}
