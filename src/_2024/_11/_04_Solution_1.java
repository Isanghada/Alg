package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20928
// - 그리디 : 첫 위치부터 가능한 위치 차례로 탐색!
public class _04_Solution_1 {
    // 환승 정보를 담을 클래스
    public static class Node{
        int idx;    // 현재 인덱스
        int count;  // 환승 횟수
        public Node(int idx, int count){
            this.idx = idx;
            this.count = count;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 인력거꾼 인원수
        int M = Integer.parseInt(st.nextToken());   // 목표 위치

        // 인력거꾼 위치
        int[] P = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 인력거꾼 이동 거리
        int[] X = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 방문 배열 초기화
        boolean[] visited = new boolean[N+1];

        // 초기 정보 추가
        deque.offerLast(new Node(0, 0));
        visited[0] = true;

        // 탐색할 인덱스!
        int idx = 0;
        // 정답 초기화
        int answer = 1_000_000;
        while (!deque.isEmpty()) {
            // 현재 정보 반환
            Node cur = deque.pollFirst();

            // 이동 범위 계산
            int maxDistance = P[cur.idx] + X[cur.idx];
            // 목표에 도달할 경우 정답 갱신 후 종료
            if(maxDistance >= M) {
                answer =cur.count;
                break;
            }

            // 다음 인덱스 탐색!
            int tempIdx = idx+1;
            while(tempIdx < N){
                // 방문한 경우 패스
                if(visited[tempIdx]){
                    tempIdx++;
                    continue;
                }

                // tempIdx에 방문할 수 있는 경우 정보 추가!
                if(maxDistance >= P[tempIdx]){
                    deque.offerLast(new Node(tempIdx, cur.count+1));
                    visited[tempIdx] = true;
                    idx = tempIdx;
                // 불가능한 경우 종료
                }else break;
                tempIdx++;
            }
        }

        // 정답 반환
        sb.append(answer != 1_000_000 ? answer : -1);
        System.out.println(sb);
    }
}
