package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3190
// - 구현 : 뱀의 이동 방식을 구현하여 이동 방향에 따라 체크
public class _08_Solution_1 {
    // 이동 방향
    public static int[][] DELTA = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    // 회전 클래스
    public static class Spin{
        int time;
        String d;
        public Spin(int time, String d){
            this.time = time;
            this.d = d;
        }
    }
    // 노드 클래스
    public static class Node{
        int row;
        int col;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());    // 보드 크기
        int K = Integer.parseInt(in.readLine());    // 사과 개수

        // 사과 정보 입력
        StringTokenizer st = null;
        int[][] map = new int[N+1][N+1];
        while(K-- > 0){
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }

        // 이동 방향 정보 추가!
        int L = Integer.parseInt(in.readLine());
        Deque<Spin> spin = new LinkedList<>();
        while(L-- > 0){
            st = new StringTokenizer(in.readLine());
            spin.offerLast(new Spin(
                    Integer.parseInt(st.nextToken()),
                    st.nextToken()
            ));
        }

        // 뱀 정보 입력!
        Deque<Node> snake = new LinkedList<>();
        int row = 1, col = 1, time = 0, d = 1;
        snake.offerLast(new Node(row, col));
        map[row][col] = 2;

        while(true){
            // 다음 뱀 위치 갱신!
            int dR = row + DELTA[d][0];
            int dC = col + DELTA[d][1];

            // 시간 증가
            time++;

            // 범위를 벗어나거나 자신과 부딪히는 경우 패스
            if(dR < 1 || dC < 1 || dR > N || dC > N || map[dR][dC] == 2) break;
            // 사과가 없는 경우 : 꼬리 제거!
            if(map[dR][dC] == 0){
                Node node = snake.pollFirst();
                map[node.row][node.col] = 0;
            }
            // 이동 방향이 남은 경우
            if(!spin.isEmpty()){
                // 이동 방향을 바꾸는 경우!
                if(time == spin.peekFirst().time){
                    Spin s = spin.pollFirst();
                    if(s.d.equals("L")) d = (d - 1 < 0) ? 3 : d - 1;
                    else d = (d + 1 > 3) ? 0 : d + 1;
                }
            }
            // 뱀 위치에 2 입력
            map[dR][dC] = 2;
            // 새로운 뱀 정보 추가!
            snake.offerLast(new Node(dR, dC));
            row = dR;
            col = dC;
        }

        // 정답 출력
        sb.append(time);
        System.out.println(sb);
    }
}
