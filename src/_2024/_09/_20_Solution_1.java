package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/14226
// - BFS : 각 단계에서 가능한 모든 작업을 진행하며 최소 경우 탐색
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 이모티콘의 수
        int S = Integer.parseInt(in.readLine());

        // 정답 출력
        // - BFS 통해 최소값 탐색
        sb.append(bfs(S));
        System.out.println(sb.toString());
    }
    // 노드 클래스
    private static class Node{
        int emoticon;   // 이모티콘의 수
        int clipboard;  // 클립보드
        int count;      // 작업 횟수
        public Node(int emoticon, int clipboard, int count){
            this.emoticon = emoticon;
            this.clipboard = clipboard;
            this.count = count;
        }
    }
    private static int bfs(int s) {
        int count = 0;

        // 덱
        Deque<Node> deque = new LinkedList<>();
        // 방문 배열
        // - visited[clipboard][emoticon] : clipboard를 가지고 있을 때 emoticon개 일 경우
        //   - clipboard : 클립보드 정보
        //   - emoticon : 이모티콘 개수
        boolean[][] visited = new boolean[1001][1001];

        // 초기 정보 설정
        deque.offerLast(new Node(1, 0, 0));
        visited[0][1] = true;

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();

            // 목표 개수일 경우 최소값 갱신!
            if(cur.emoticon == s){
                count = cur.count;
                break;
            }

            // 1. 클립보드 재설정 작업
            if(!visited[cur.emoticon][cur.emoticon]){
                deque.offerLast(new Node(cur.emoticon, cur.emoticon, cur.count+1));
                visited[cur.emoticon][cur.emoticon] = true;
            }

            // 2. 클립보드 내용 붙여넣기 작업
            int nextEmoticon = cur.clipboard + cur.emoticon;
            if(cur.clipboard != 0 && nextEmoticon <= 1000 && !visited[cur.clipboard][nextEmoticon]) {
                deque.offerLast(new Node(nextEmoticon, cur.clipboard, cur.count+1));
                visited[cur.clipboard][nextEmoticon] = true;
            }

            // 3. 이모티콘 1개 삭제 작업.
            nextEmoticon = cur.emoticon - 1;
            if(nextEmoticon > 0 && !visited[cur.clipboard][nextEmoticon]){
                deque.offerLast(new Node(nextEmoticon, cur.clipboard, cur.count+1));
                visited[cur.clipboard][nextEmoticon] = true;
            }

        }

        return count;
    }
}
