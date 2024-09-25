package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12886
// - BFS : 두 그룹의 개수만 알면 나머지는 구할 수 있으므로, 두 그룹의 개수를 기준으로 탐색!
public class _25_Solution_1 {
    // 돌 그룹 클래스
    public static class Stone{
        int a;  //
        int b;  //
        int c;  //
        public Stone(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 정답 출력
        sb.append(bfs(A, B, C) ? 1 : 0);
        System.out.println(sb);
    }

    private static boolean bfs(int a, int b, int c) {
        if((a+b+c) % 3 != 0) return false;

        Deque<Stone> deque = new LinkedList<>();
        boolean[][] visited = new boolean[1501][1501];

        deque.offerLast(new Stone(a, b, c));
        visited[a][b] = true;

        while(!deque.isEmpty()){
            Stone cur = deque.pollFirst();

            if(cur.a == cur.b && cur.b == cur.c) return true;

            if(cur.a != cur.b){
                Stone next = new Stone(
                        cur.a > cur.b ? cur.a - cur.b : cur.a * 2,
                        cur.a > cur.b ? cur.b * 2 : cur.b - cur.a,
                        cur.c
                );
                if(!visited[next.a][next.b]){
                    deque.offerLast(next);
                    visited[next.a][next.b] = true;
                }
            }

            if(cur.a != cur.c){
                Stone next = new Stone(
                        cur.a > cur.c ? cur.a - cur.c : cur.a * 2,
                        cur.b,
                        cur.a > cur.c ? cur.c * 2 : cur.c - cur.a
                );
                if(!visited[next.a][next.b]){
                    deque.offerLast(next);
                    visited[next.a][next.b] = true;
                }
            }

            if(cur.b != cur.c){
                Stone next = new Stone(
                        cur.a,
                        cur.b > cur.c ? cur.b - cur.c : cur.b * 2,
                        cur.b > cur.c ? cur.c * 2 : cur.c - cur.b
                );
                if(!visited[next.a][next.b]){
                    deque.offerLast(next);
                    visited[next.a][next.b] = true;
                }
            }
        }

        return false;
    }
}