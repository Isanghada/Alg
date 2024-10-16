package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16234
// - BFS : 변화가 생기지 않을 때까지 BFS를 통해 인구 이동!
public class _16_Solution_1 {
    // 이동 변수
    public static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 노드 클래스 : 좌표 정보
    private static class Node {
        int row;
        int col;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    // 연합 클래스 : 인구의 합과 포함된 연합 좌표 정보 리스트
    public static class Union{
        int sum;
        List<Node> list;
        public Union(){
            this.sum = 0;
            this.list = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 땅 크기
        int L = Integer.parseInt(st.nextToken());   // 인구 차이 최소값
        int R = Integer.parseInt(st.nextToken());   // 인구 차이 최대값

        // 땅 정보!
        int[][] land = new int[N][N];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c <N; c++) land[r][c] = Integer.parseInt(st.nextToken());
        }

        // 정답 출력
        // - 인구 이동 횟수 계산
        sb.append(move(land, N, L, R));
        System.out.println(sb.toString());
    }

    private static int move(int[][] land, int n, int l, int r) {
        int answer = 0;
        // 인구가 이동하지 않는 경우까지 반복!
        while(true){
            boolean isMove = false;
            // 방문 배열
            boolean[][] visited = new boolean[n][n];
            // 모든 좌표 탐색!
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!visited[i][j]){
                        // BFS를 통해 연합 정보 반환
                        Union union = bfs(land, visited, n, l, r, i, j);
                        // 연합이 이루어진 경우 : 인구 이동
                        if(union.list.size() > 1){
                            change(land, union);
                            isMove = true;
                        }
                    }
                }
            }
            // 인구가 이동하지 않으면 answer 반환
            if(!isMove) return answer;
            // 인구가 이동하면 answer 증가
            answer++;
        }
    }

    // 연합 정보를 토대로 인구 이동
    private static void change(int[][] land, Union union) {
        int avg = union.sum / union.list.size();
        for(Node node : union.list){
            land[node.row][node.col] = avg;
        }
    }

    // (i, j) 좌표를 기준으로 BFS를 이용해 연합 정보 탐색
    private static Union bfs(int[][] land, boolean[][] visited, int n, int l, int r, int i, int j) {
        Deque<Node> deque = new ArrayDeque<>();

        deque.offerLast(new Node(i, j));
        visited[i][j] = true;

        Union result = new Union();
        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();
            result.sum += land[cur.row][cur.col];
            result.list.add(cur);
            for(int[] d : DELTA){
                Node next = new Node(cur.row+d[0], cur.col+d[1]);
                if(next.row >= 0 && next.row < n &&
                        next.col >= 0 && next.col < n &&
                        !visited[next.row][next.col]
                ){
                    int diff = Math.abs(land[cur.row][cur.col] - land[next.row][next.col]);
                    if(l <= diff && diff <= r){
                        deque.offerLast(next);
                        visited[next.row][next.col] = true;
                    }
                }
            }
        }
        return result;
    }
}
