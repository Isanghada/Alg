package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2660
// - DFS, BFS를 통해 각 회원의 점수 계산!
// - 점수가 낮은 사람들을 리스트에 추가하여 정답 반환!
public class _07_Solution_1 {
    public static int score;    // 회장 점수
    public static List<Integer> answerList; // 회장 리스트
    public static int N;    // 회원의 수
    public static Map<Integer, Set<Integer>> adjList;   // 친구 인접 리스트
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 점수 초기화!
        score = Integer.MAX_VALUE;
        // 회장 리스트 초기화
        answerList = new ArrayList<>();
        // 인접 리스트 초기화
        adjList = new HashMap<>();

        // 회원의 수 입력
        N = Integer.parseInt(in.readLine());
        // 인접 리스트 추가
        for(int num = 1;  num <= N; num++) adjList.put(num, new HashSet<>());

        // 친구 관계 추가
        while(true){
            // 친구 관계 입력
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 마지막 입력인 경우 종료
            if(a == -1 && b == -1) break;

            // 양방향이므로 a, b에 모두 추가
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        // 모든 회원의 점수 계산하여 정답 계산
        for(int num : adjList.keySet()) getScore(num);
        
        // 회장 점수, 인원수 출력
        sb.append(score).append(" ").append(answerList.size()).append("\n");
        // 회장 리스트 출력
        for(Integer answer : answerList)sb.append(answer).append(" ");
        System.out.println(sb);
    }

    // 점수 계산 함수 : BFS를 통해 기준 회원의 점수 게산
    // num : 점수를 계산하려는 회원 번호
    private static void getScore(int num) {
        // 덱 초기화
        Deque<int[]> deque = new LinkedList<>();
        // 방문 배열 초기화
        boolean[] visited = new boolean[N+1];

        // num 기준 초기값 설정
        deque.offerLast(new int[] {num, 0});
        visited[num] = true;

        // 점수 초기화 : 점수의 최대값 계산!
        int curScore = 0;
        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 정보 반환
            int[] cur = deque.pollFirst();

            // 현재 점수가 더 크다면 변경!
            if(cur[1] > curScore) curScore = cur[1];

            // 인접한 친구 모두 방문
            for(int next : adjList.get(cur[0])){
                // 이미 방문한 경우 패스
                if(visited[next]) continue;

                // 새로운 친구 정보 추가 : 현재 점수의 +1
                deque.offerLast(new int[] {next, cur[1] + 1});
                visited[next] = true;
            }
        }
        // 현재 점수가 회장 점수보다 크다면 동작 없이 종료
        if(curScore > score) return;
        // 현재 점수가 회장 점수보다 작다면 회장 점수와 리스트 초기화!
        else if(curScore < score) {
            score = curScore;
            answerList.clear();
        }
        // 회장 리스트에 현재 회원 추가
        answerList.add(num);
    }
}
