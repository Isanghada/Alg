package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2310
// - DFS : DFS를 통해 가능한 모든 경우 탐색!
public class _13_Solution_1 {
    // 방 클래스
    static class Room{
        int money;          // 비용
        List<Integer> door; // 연결된 방
        public Room(int money){
            this.money = money;
            this.door = new ArrayList<>();
        }
    }
    static int N;
    static Room[] roomArr;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            // 방 개수
            N = Integer.parseInt(in.readLine());

            // 0인 경우 종료
            if(N == 0) break;

            // 방 정보 입력
            roomArr = new Room[N+1];
            for(int n = 1; n <= N; n++){
                st = new StringTokenizer(in.readLine());
                // 트롤인 경우 비용을 음수로 입력
                if(st.nextToken().charAt(0) == 'T'){
                    roomArr[n] = new Room(-Integer.parseInt(st.nextToken()));
                }else{
                    roomArr[n] = new Room(Integer.parseInt(st.nextToken()));
                }

                // 연결된 방 입력
                while(true){
                    int next = Integer.parseInt(st.nextToken());
                    if(next == 0) break;
                    roomArr[n].door.add(next);
                }
            }

            // 방문 배열 초기화
            visited = new boolean[N+1];
            visited[1] = true;

            // 깊이 우선 탐색!
            sb.append(dfs(1, 0) ? "Yes" : "No").append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean dfs(int start, int money) {
        Room room = roomArr[start];
        // 트롤 방인 경우
        if(room.money < 0) {
            // 금액 갱신
            money += room.money;
            // 통행료를 진행할 수 없는 경우 false
            if(money < 0) return false;
        }
        // 트롤 방이 아닌 경우 : 금액은 최대값으로 갱신
        else money = Math.max(money, room.money);

        // 도착한 경우 true 반환
        if(start == N) return true;

        for(int next : room.door){
            if(!visited[next]){
                visited[next] = true;
                if(dfs(next, money)) return true;
                visited[next] = false;
            }
        }

        return false;
    }
}
