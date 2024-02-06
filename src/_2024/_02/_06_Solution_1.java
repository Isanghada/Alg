package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15661
// - 브루트포스 : 가능한 모든 경우 탐색
//                한 팀을 선택하면 나머지는 자동으로 선택.
public class _06_Solution_1 {
    // 변수 설정
    public static int N;
    public static int[] BITMASK;
    public static int[][] STATUS;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 인원수
        N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;

        // 능력치 배열
        STATUS = new int[N][N];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++) STATUS[r][c] = Integer.parseInt(st.nextToken());
        }

        // 4명인 경우 1 : 3과 3 : 1인 경우는 동일하다.
        // 따라서, 팀원 선택시 최대값은 N/2
        final int LIMIT = N / 2;
        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // 팀원 수가 작은 쪽의 인원수
        for(int team = 1; team <= LIMIT; team++){
            // 비트마스트 초기화
            BITMASK = new int[N];
            // 인원수만큼 체크
            for(int idx = N - team; idx < N; idx++) BITMASK[idx] = 1;

            // nextPermutation을 통해 모든 경우 탐색
            do{
                // 정답 갱신 : 차이의 최소값
                answer = Math.min(answer, getDiff());
            }while(nextPermutation(BITMASK));
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // 능력치 차이 계산 함수
    private static int getDiff() {
        int startScore = 0; // 팀 능력치
        int linkScore = 0;  // 팀 능력치

        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                // 팀이 다른 경우 패스
                if(BITMASK[i] != BITMASK[j]) continue;
                // 0번 팀인 경우 : 스타트 팀
                if(BITMASK[i] == 0) startScore += STATUS[i][j]+STATUS[j][i];
                // 1번 팀인 경우 : 링크 팀
                else linkScore += STATUS[i][j]+STATUS[j][i];
             }
        }
//        System.out.println(Arrays.toString(BITMASK));
//        System.out.println(startScore +", "+linkScore+", "+Math.abs(startScore - linkScore));
//        System.out.println("-------------------");

        // 두 팀 차이의 절대값 반환
        return Math.abs(startScore - linkScore);
    }

    // nextPermutation 함수 : 반복을 통해 다음 경우 탐색
    private static boolean nextPermutation(int[] bitmask) {
        int i = bitmask.length - 1;
        while(i > 0 && bitmask[i-1] >= bitmask[i]) i--;
        if(i == 0) return false;

        int j = bitmask.length - 1;
        while(bitmask[i-1] >= bitmask[j]) j--;
        swap(bitmask, i-1, j);

        int k = bitmask.length - 1;
        while(k > i) swap(bitmask, i++, k--);

        return true;
    }

    // 배열 값 교체 함수
    private static void swap(int[] bitmask, int a, int b) {
        int temp = bitmask[a];
        bitmask[a] = bitmask[b];
        bitmask[b] = temp;
    }
}
