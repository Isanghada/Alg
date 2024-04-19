package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2166
// - 브루투포스 : 1번 주사위의 아래, 위가 정해지면 이후의 주사위는 1가지 경우만 나오므로
//                1번 주사위가 될 수 있는 모든 경우에서 최대값 선택!
public class _19_Solution_1 {
    public static int answer;                   // 정답
    public static final int DICE_MAX_NUM = 7;   // 주사위의 숫자
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 주사위의 수
        int N = Integer.parseInt(in.readLine());

        // 주사위별 대응되는 숫자
        int[][] diceArr = new int[N][DICE_MAX_NUM];
        // 주사위 정보 입력
        for(int i = 0; i < N; i++) {
            diceArr[i] = getDice(new StringTokenizer(in.readLine()));
//            System.out.println(Arrays.toString(diceArr[i]));
        }

        // 정답 초기화
        answer = 0;
        // 1번 주사위의 아래가 1인 경우부터 6인경우까지 모두 진행
        for(int past = 1; past < DICE_MAX_NUM; past++) getMaxValue(0, N, past, 0, diceArr);

        sb.append(answer);
        System.out.println(sb.toString());
    }
    // 대응되는 인덱스 정보
    // - (a f), (b d), (c e)
    // - (1 6), (2 4), (3 5)
    public static int[] opposition = new int[]{0, 6, 4, 5};
    public static int[] getDice(StringTokenizer st){
        // 주사위 정보
        int[] dice = new int[DICE_MAX_NUM];
        // 주사위 단면도
        int[] origin = new int[DICE_MAX_NUM];
        for(int i = 1; i < DICE_MAX_NUM; i++) origin[i] = Integer.parseInt(st.nextToken());

        // 각 숫자별로 대응되는 숫자 정보로 변환
        for(int i = 1; i < 4; i++){
            dice[origin[i]] = origin[opposition[i]];
            dice[origin[opposition[i]]] = origin[i];
        }

        return dice;
    }
    // 브루트포스 : 각 주사위가 될 수 있는 결과를 재귀로 계산
    public static void getMaxValue(int step, int N, int past, int total, int[][] diceArr){
        // 모든 주사위를 확인한 경우
        if(step == N){
//            System.out.println("total = "+ total);
            // 최대값으로 갱신
            answer = Math.max(answer, total);
        }else{
            for(int dice = 6; dice > 0; dice--){
                // 아래와 위에 나오는 숫자가 아닌 경우 중 최대값 선택 후 다음 주사위 확인
                if(past != dice && diceArr[step][past] != dice){
//                    System.out.print("["+dice+", ("+past+", "+diceArr[step][past]+")], ");
                    getMaxValue(step+1, N, diceArr[step][past], total + dice, diceArr);
                    break;
                }
            }
        }
    }
}
