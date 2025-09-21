package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14714
// -
public class _21_Solution_1 {
    static class Node{
        int count;
        int a;
        int b;
        public Node(int count, int a, int b){
            this.count = count;
            this.a = a;
            this.b = b;
        }
    }
    static final int TYPE = 2;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int DA = Integer.parseInt(st.nextToken());
        int DB = Integer.parseInt(st.nextToken());

        int[][][] visited = new int[TYPE][N+1][N+1];
        bfs(N, A, B, DA, DB, visited);

        int answer = Integer.MAX_VALUE;
        for(int n =1; n <= N; n++){
            if(visited[0][n][n] != 0) answer = Math.min(answer, visited[0][n][n]);
            if(visited[1][n][n] != 0) answer = Math.min(answer, visited[1][n][n]);
        }

//        for(int n =1; n <= N; n++){
//            System.out.println(Arrays.toString(visited[0][n]));
//        }

        //System.out.println("-----");

//        for(int n =1; n <= N; n++){
//            System.out.println(Arrays.toString(visited[1][n]));
//        }

        // 정답 출력
        sb.append(answer == Integer.MAX_VALUE ? "Evil Galazy" : answer - 1);
        System.out.println(sb);
    }

    private static void bfs(int n, int a, int b, int da, int db, int[][][] visited) {
        Deque<Node> deque = new LinkedList<>();

        deque.offerLast(new Node(1, a, b));
        visited[0][a][b] = 1;

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();
            int nextCount = cur.count+1;

            if((cur.count & 1) == 1){
                int rightA = cur.a + da;
                if(rightA > n) rightA -= n;
                int leftA = cur.a - da;
                if(leftA < 1) leftA += n;

                if(visited[0][rightA][cur.b] == 0){
                    visited[0][rightA][cur.b] = nextCount;
                    deque.offerLast(new Node(nextCount, rightA, cur.b));
                }

                if(visited[0][leftA][cur.b] == 0){
                    visited[0][leftA][cur.b] = nextCount;
                    deque.offerLast(new Node(nextCount, leftA, cur.b));
                }

            }else{
                int rightB = cur.b + db;
                if(rightB > n) rightB -= n;
                int leftB = cur.b - db;
                if(leftB < 1) leftB += n;

                if(visited[1][cur.a][rightB] == 0){
                    visited[1][cur.a][rightB] = nextCount;
                    deque.offerLast(new Node(nextCount, cur.a, rightB));
                }

                if(visited[1][cur.a][leftB] == 0){
                    visited[1][cur.a][leftB] = nextCount;
                    deque.offerLast(new Node(nextCount, cur.a, leftB));
                }
            }
        }
    }
}