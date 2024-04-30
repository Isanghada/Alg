package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/10476
// - Set, Map : 모든 음악의 순위를 체크하여 정렬!
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 진술의 수
        int N = Integer.parseInt(in.readLine());

        // 각 순위별 가능한 음악 집합
        Set<String>[] rankSet = new Set[102];
        for(int i = 0; i < 102; i++) rankSet[i] = new HashSet<>();

        // 음악별 가능한 순위
        Map<String, Integer> musicRankMap = new HashMap<>();

        StringTokenizer st = null;
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            st.nextToken(); // A
            st.nextToken(); // od
            
            // 진술한 순위
            int top = Integer.parseInt(st.nextToken());
            // 진술한 음악
            while(st.hasMoreTokens()){
                String music = st.nextToken();
                // 현재 음악 순위
                int musicRank = musicRankMap.getOrDefault(music, 101);
                // 새로운 진술의 순위가 더 높을 경우 갱신!
                if(musicRank > top){
                    musicRankMap.put(music, top);
                    rankSet[musicRank].remove(music);
                    rankSet[top].add(music);
                }
            }
        }

        // 각 순위를 체크하여 1개의 음악만 포함된 경우 출력
        int count = 0;  // 현재까지 순위에 포함된 노래의 수!
        for(int rank = 1; rank < 101; rank++){
            count += rankSet[rank].size();
            // 현재 순위의 곡이 1개이고 높은 순위에 곡이 포함된 경우! => 현재 순위가 확실한 경우
            if(rankSet[rank].size() == 1 && count == rank){
                for(String music : rankSet[rank]){
                    sb.append(rank).append(" ").append(music).append("\n");
                }
            }
        }
        // 정답 반환
        System.out.println(sb);
    }
}