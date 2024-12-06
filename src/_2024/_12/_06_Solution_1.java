package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6987
// - 백트래킹
public class _06_Solution_1 {
    // 경기별 팀 배열
    public static int[][] matches;
    // 가능한 결과 플래그
    public static boolean possible;
    public static final int TEAM = 6, TYPE = 3, ROOP = 4;
    // 경기별 결과 종류
    public static final int[][] DELTA = new int[][]{{0, 2}, {1, 1}, {2, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 경기 횟수
        int maxSize = 0;
        for(int i = 1; i < TEAM; i++) maxSize += i;

        // 경기별 팀 정보
        int index = 0;
        matches = new int[maxSize][2];
        for(int i = 0; i < TEAM; i++){
            for(int j = i+1; j < TEAM; j++){
                matches[index][0] = i;
                matches[index][1] = j;
                index++;
            }
        }

        StringTokenizer st = null;
        for(int i = 0; i < ROOP; i++){
            // 전체 결과 가능 여부
            // - 백트래킹을 통해 가능한 경우 갱신
            possible = false;
            // 경기 결과 수 가능 여부
            // - 각 팀당 5경기가 아닐 경우 갱신
            boolean flag = true;

            st = new StringTokenizer(in.readLine());
            int[][] result = new int[TEAM][TYPE];
            for(int t = 0; t < TEAM; t++){
                result[t] = new int[]{
                        Integer.parseInt(st.nextToken()),   // 이긴 경기
                        Integer.parseInt(st.nextToken()),   // 미긴 경기
                        Integer.parseInt(st.nextToken())    // 진 경기
                };

                // 경기의 합이 5가 아닌 경우 flag 변경 후 종료
                if(sum(result[t]) != 5){
                    flag = false;
                    break;
                }
            }

            if(flag){
                // 백트래킹을 통해 가능한 결과인지 확인
                backtracking(result, 0, maxSize);
                if(possible) sb.append(1);
                else sb.append(0);
            }
            else sb.append(0);

            sb.append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static void backtracking(int[][] result, int size, int maxSize) {
        if(possible) return;
        if(size == maxSize){
            possible = true;
            return;
        }

        int teamA = matches[size][0];
        int teamB = matches[size][1];

        for(int[] type : DELTA){
            if(possible) return;
            if(result[teamA][type[0]] > 0 && result[teamB][type[1]] > 0){
                result[teamA][type[0]]--;
                result[teamB][type[1]]--;
                backtracking(result, size+1, maxSize);
                result[teamA][type[0]]++;
                result[teamB][type[1]]++;
            }
        }
    }

    private static int sum(int[] result) {
        int sum = 0;
        for(int v : result) sum += v;
        return sum;
    }
}
