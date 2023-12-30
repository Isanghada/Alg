package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.acmicpc.net/problem/3980
// - 백트래킹 : 모든 가능한 조건 확인!
public class _19_Solution_1 {
    // 능력치를 담을 클래스
    public static class Ability{
        int position;   // 포지션
        int stat;       // 능력
        public Ability(int position, int stat){
            this.position = position;
            this.stat = stat;
        }
        @Override
        public String toString() {
            StringBuilder toString = new StringBuilder();
            toString.append("[ position=").append(position).append(", stat=").append(stat).append(" ]");
            return toString.toString();
        }
    }
    public static final int PLAYER_NUMBER = 11; // 선발 선수의 수
    public static int ANSWER;                   // 정답
    public static boolean[] isSelected;         // 포지션 선발 여부 배열
    public static List<Ability>[] PLAYERS;      // 선수별 포지션 리스트
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 수
        int T = Integer.parseInt(in.readLine());

        while(T-- > 0){
            ANSWER = 0;                         // 능력의 합 초기화
            PLAYERS = new List[PLAYER_NUMBER];  // 선수별 포지션 리스트 초기화
            for(int i = 0; i < PLAYER_NUMBER; i++) PLAYERS[i] = new ArrayList<>();

            // 포지션 추가
            for(int idx = 0; idx < PLAYER_NUMBER; idx++){
                int[] stats = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
                for(int position = 0; position < PLAYER_NUMBER; position++){
                    // 가능한 포지션만 추가
                    if(stats[position] != 0){
                        PLAYERS[idx].add(new Ability(position, stats[position]));
                    }
                }
            }

            // 포지션 선발 여부 초기화
            isSelected = new boolean[PLAYER_NUMBER];
            // 능력치의 최대값 계산
            getMaxAbility(0, 0);
            // 정답 출력
            sb.append(ANSWER).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    // 능력치 최대값 계산 함수 : 재귀를 통해 모든 경우 탐색
    private static void getMaxAbility(int step, int sum) {
        // 모든 포지션 선발했을 경우
        if(step == PLAYER_NUMBER){
            // 최대값으로 갱신
            ANSWER = Math.max(ANSWER, sum);
        }else{
            // 현재 선수의 가능한 포지션 모두 탐색
            for(Ability player : PLAYERS[step]){
                // 이미 선발된 포지션인 경우 : 패스
                if(isSelected[player.position]) continue;
                // 포지션 선발 체크
                isSelected[player.position] = true;
                // 다음 선수 탐색
                getMaxAbility(step+1, sum+player.stat);
                // 포지션 선발 체크 해제
                isSelected[player.position] = false;
            }
        }

    }
}
