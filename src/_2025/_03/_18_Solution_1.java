package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23085
// - BFS : 앞면, 뒷면 동전의 개수를 기준으로 BFS로 최소 횟수 탐색!
public class _18_Solution_1 {
    // 코인 클래스
    static class Coin{
        int front;  // 앞면의 수
        int back;   // 뒷면의 수

        public Coin(int front, int back){
            this.front = front;
            this.back = back;
        }
        public void plusFront(){
            this.front++;
        }
        public void plusBack(){
            this.back++;
        }
    }
    static final char FRONT = 'H', BACK = 'T';
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 동전 개수
        int K = Integer.parseInt(st.nextToken());   // 뒤집기 개수

        // 코인 정보
        char[] coinArr = in.readLine().toCharArray();
        
        // 초기 코인 정보 입력
        Coin start = new Coin(0, 0);
        for(char coin : coinArr){
            if(coin == FRONT) start.plusFront();
            else if(coin == BACK) start.plusBack();
        }

        // 정답 출력
        // - bfs를 통해 최소 횟수 탐색
        sb.append(bfs(start, N, K));
        System.out.println(sb);
    }

    private static int bfs(Coin start, int n, int k) {
        Deque<Coin> deque = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];

        deque.offerLast(start);
        visited[start.front][start.back] = true;

        int count = 0;
        while(!deque.isEmpty()){
            int size = deque.size();
            while(size-- > 0){
                Coin cur = deque.pollFirst();
                if(cur.back == n) return count;

                for(int frontChange = Math.min(k, cur.front); frontChange >= 0; frontChange--){
                    int backChange = k - frontChange;
                    if(backChange > cur.back) continue;

                    int diff = frontChange - backChange;
                    Coin next = new Coin(cur.front - diff, cur.back + diff);

                    if(visited[next.front][next.back]) continue;
                    deque.offerLast(next);
                    visited[next.front][next.back] = true;
                }

            }
            count++;
        }


        return -1;
    }
}
