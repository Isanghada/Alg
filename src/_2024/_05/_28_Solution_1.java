package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/13265
// - BFS : 각 정점을 기준으로 차례로 탐색하며 색칠할 수 있는지 확인
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;

        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());   // 원의 수
            int m = Integer.parseInt(st.nextToken());   // 직선의 수

            // 연결된 원 집합
            Set<Integer>[] childSet = new Set[n+1];
            for(int num = 1; num <= n; num++) childSet[num] = new HashSet();
            // 연결된 원 정보 입력
            while(m-- > 0){
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                childSet[x].add(y);
                childSet[y].add(x);
            }

            // 원 색깔 배열
            int[] colorArr = new int[n+1];
            // 불가능 플래그
            boolean flag = false;

            // 1번 원부터 차레로 탐색
            for(int start = 1; start <= n; start++){
                // 이미 칠해진 경우 패스
                if(colorArr[start] != 0) continue;
                // start지점부터 BFS로 2가지 색으로 칠할 수 없는 경우 flag 갱신 후 종료
                if(!bfs(childSet, colorArr, start)){
                    flag = true;
                    break;
                }
            }
            // 가능한 경우 : possible 출력
            if(!flag) sb.append("possible\n");
            // 불가능한 경우 : impossible 출력
            else sb.append("impossible\n");

        }

        // 정답 반환
        System.out.println(sb);
    }
    // BFS 함수 : start를 기준으로 가능한 모든 지점 탐색
    private static boolean bfs(Set<Integer>[] childSet, int[] colorArr, int start) {
        // start를 기준으로 초기 정보 입력
        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(start);
        colorArr[start] = 1;

        // deque이 빌 떄가지 반복
        while(!deque.isEmpty()){
            // 현재 지점 반환
            int cur = deque.pollFirst();
            // 연결된 다음 원의 색깔
            int nextColor = colorArr[cur] * (-1);
            // 다음 원 탐색
            for(int next : childSet[cur]){
                // 다음 원이 색이 없는 경우 : nextColor로 변경!
                if(colorArr[next] == 0){
                    deque.offerLast(next);
                    colorArr[next] = nextColor;
                // 다음 원의 색이 있는 경우 : 비교하여 같은 경우 false 반환
                }else if(colorArr[next] != nextColor) return false;
            }
        }
        // 가능한 경우 true 반환
        return true;
    }
}
