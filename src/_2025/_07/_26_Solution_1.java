package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20500
// - 백트래킹 : 모든 조합을 탐색하여 가능한 경우 체크
public class _26_Solution_1 {
    static final int NAN = Integer.MAX_VALUE;   // 불가능한 경우!
    static int N, M;
    static int[] DOOR, SWITCH_BIT, BIT;
    static int[][] ROTATE;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 문의 개수
        M = Integer.parseInt(st.nextToken());   // 스위치 개수

        // 문 초기 정보
        DOOR = new int[N];
        String input = in.readLine();
        for(int i = 0; i < N; i++) DOOR[i] = input.charAt(i) == '0' ? -1 : 1;

        // 스위치 정보
        ROTATE = new int[M][N];
        for(int m = 0; m < M; m++){
            input = in.readLine();
            for(int i = 0; i < N; i++) ROTATE[m][i] = input.charAt(i) - '0';
        }

        // 각 경우의 스위치 사용 비트마스크 배열
        SWITCH_BIT = initSwitch(2*N+1);
        Arrays.fill(SWITCH_BIT, NAN);
        
        // 비트 초기화
        BIT = initBit(M);

        // 백트래킹을 통해 모든 경우 탐색
        backtracking(0, 0);

        for(int i = 0 ; i < 2 * N + 1; i++) {
            // 불가능한 경우 -1 반환
            if(SWITCH_BIT[i] == NAN) sb.append("-1\n");
            else{
                // 스위치 개수
                int count = 0;
                // 사용 스위치
                StringBuilder number = new StringBuilder();
                for(int num = 0; num < M; num++){
                    if((SWITCH_BIT[i] & BIT[num]) > 0){
                        count++;
                        number.append(num+1).append(" ");
                    }
                }
                sb.append(count).append(" ").append(number).append("\n");
            }
            // System.out.println(SWITCH_BIT[i]);
        }

        // 정답 반환
        System.out.println(sb.toString().trim());
    }

    private static void backtracking(int bit, int start) {
        int num = getNumber(DOOR);
        if(SWITCH_BIT[num] == NAN) SWITCH_BIT[num] = bit;
        for(int i = start; i < M; i++){
            updateDoor(ROTATE[i]);
            backtracking(bit | BIT[i], i+1);
            updateDoor(ROTATE[i]);
        }
    }

    private static void updateDoor(int[] rotate) {
        for(int i = 0; i < N; i++){
            if(rotate[i] == 1) DOOR[i] = -DOOR[i];
        }
    }

    private static int getNumber(int[] door) {
        int result = N;
        for(int num : door) result += num;
        return result;
    }

    private static int[] initSwitch(int size) {
        int[] result = new int[size];
        Arrays.fill(result, NAN);

        return result;
    }

    private static int[] initBit(int size) {
        int[] bit = new int[size];
        bit[0] = 1;
        for(int i = 1; i < size; i++) bit[i] = bit[i-1] << 1;
        return bit;
    }
}
