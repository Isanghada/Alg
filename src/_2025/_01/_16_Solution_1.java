package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1633
// - DP : 가능한 경우 중 최대값이 되는 경우 선택!
public class _16_Solution_1 {
    // 선수 클래스
    static class Player{
        int white;  // 백팀일 경우 점수
        int black;  // 흑팀일 경우 점수
        public Player(int white, int black){
            this.white = white;
            this.black = black;
        }
    }
    static final int TEAM = 15;         // 팀 인원수
    static List<Player> PLAYER_LIST;    // 선수 리스트
    // dp[player][white][black] : player가 선택되었을 때 최대값!
    static int[][][] dp;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PLAYER_LIST = new ArrayList<>();
        PLAYER_LIST.add(new Player(0, 0));

        StringTokenizer st = null;
        while(true){
            String player = in.readLine();
            if(player == null || player.equals("")) break;
            st = new StringTokenizer(player);
            PLAYER_LIST.add(new Player(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        int playerCount = PLAYER_LIST.size()-1;
        dp = new int[playerCount+1][TEAM+1][TEAM+1];
        getDP(playerCount, TEAM, TEAM);

        // 정답 출력
        sb.append(dp[playerCount][TEAM][TEAM]);
        System.out.println(sb);
    }

    private static int getDP(int player, int white, int black) {
        if(player == 0 || (white == 0 && black == 0)) return 0;

        int result = dp[player][white][black];
        if(result == 0){
            result = Math.max(result, getDP(player-1, white, black));
            if(white > 0) {
                result = Math.max(result, getDP(player - 1, white - 1, black)
                                          + PLAYER_LIST.get(player).white);
            }
            if(black > 0){
                result = Math.max(result, getDP(player-1, white, black-1)
                                          +PLAYER_LIST.get(player).black);
            }
        }

        return dp[player][white][black] = result;
    }
}
