package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16965
// - BFS : 이동 가능한 경로를 차례로 확인
public class _31_Solution_1 {
    // 범위 클래스
    public static class Range{
        int x;                  // 구간 시작
        int y;                  // 구간 끝
        Set<Integer> adjSet;    // 이동 가능한 구간
        public Range(int x, int y){
            this.x = x;
            this.y = y;
            this.adjSet = new HashSet<>();
        }
        // 이동 가능 여부 계산 함수
        public boolean isMoving(Range next){
            // this -> next로 이동 가능하면 true, 이동 불가능이면 false
            if((this.x > next.x & this.x < next.y) ||
                    (this.y > next.x & this.y < next.y)
            ) return true;
            else return false;
        }
        @Override
        public String toString(){
            StringBuilder result = new StringBuilder();
            result.append("[ x = ").append(this.x)
                  .append(", y = ").append(this.y)
                  .append(" ]");
            return result.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 쿼리의 개수
        int N = Integer.parseInt(in.readLine());

        // 범위 리스트!
        List<Range> list = new ArrayList<>();
        // - 처음 추가된 구간이 1번째이므로 허수 추가
        list.add(null);

        // 구간의 개수
        int count = 0;
        StringTokenizer st = null;

        // 쿼리 개수 만큼 반복 
        while(N-- > 0){
            // 쿼리 입력
            st = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(st.nextToken());    // 타입 : 1(추가), 2(이동 가능 여부)
            int a = Integer.parseInt(st.nextToken());       // 구간 x or a 번째 구간
            int b = Integer.parseInt(st.nextToken());       // 구간 y or b 번째 구간

            // 1인 경우
            if(type == 1) {
                // 구간 개수 증가
                count++;
                // 새로운 구간 추가
                list.add(new Range(a, b));
                // 이전 구간과 비교하여 이동 가능하면 adjSet에 추가!
                for(int idx = 1; idx < count; idx++){
                    if(list.get(idx).isMoving(list.get(count))) list.get(idx).adjSet.add(count);
                    if(list.get(count).isMoving(list.get(idx))) list.get(count).adjSet.add(idx);
                }
            // 2인 경우 : BFS를 통해 탐색
            // - a -> b 이동이 가능하면 1, 불가능하면 0 반환
            }else sb.append(isPossible(list, a, b) ? 1 : 0).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
    // 이동 여부 탐색 함수 : BFS를 통해 탐색
    // - list : 구간 리스트
    // - a : 시작 구간
    // - b : 끝 구간
    public static boolean isPossible(List<Range> list, int a, int b){
        // 덱 초기화
        Deque<Integer> deque = new LinkedList<>();
        // 방문 배열
        boolean[] visited = new boolean[list.size()];

        // 초기값 설정 : a 구간 체크
        deque.offerLast(a);
        visited[a] = true;

        // 덱이 빌때까지 반복
        while(!deque.isEmpty()){
            // 현재 구간 반환
            int cur = deque.pollFirst();
            // b에 도착한 경우 true 반환
            if(cur == b) return true;

            // cur에서 가능한 구간 탐색
            for(int next : list.get(cur).adjSet){
                // 이미 방문한 경우 패스
                if(visited[next]) continue;

                // 새로운 구간 추가
                deque.offerLast(next);
                visited[next] = true;
            }
        }
        // b에 도달하지 못하는 경우 false 반환
        return false;
    }
}