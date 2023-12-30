package _2023._202305;

import java.util.*;

public class _24_Solution_1 {
    public String[] solution(String[] players, String[] callings) {
        // 플레이어의 수
        final int N = players.length;

        // 플레이어의 현재 순위(인덱스)를 담을 Map 초기화
        Map<String, Integer> playerMap = new HashMap<>();

        // players 배열을 기준으로 Map에 데이터 입력.
        for(int i = 0; i < N; i++){
            playerMap.put(players[i], i);
        }

        // call에 따라 순위 변동
        for(String call : callings){
            // 불린 선수의 인덱스
            int index = playerMap.get(call);
            // 앞의 선수 위치 변경
            // - 위치와 인덱스 모두 변경
            players[index] = players[index-1];
            playerMap.put(players[index], index);

            // 인덱스 변경
            index--;
            // 불린 선수의 위치 변경
            players[index] = call;
            playerMap.put(call, index);
        }

        return players;
    }
}
