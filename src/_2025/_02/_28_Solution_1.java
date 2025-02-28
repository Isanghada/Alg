package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2370
// - 구현 : 좌표를 압축하여 포스터 결과 체크
public class _28_Solution_1 {
    // 좌표 클래스
    static class Point{
        int start;  // 시작
        int end;    // 끝
        public Point(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 포스터의 개수
        int N = Integer.parseInt(in.readLine());

        // 좌표 추가 여부
        Set<Integer> set = new HashSet<>();
        // 좌표 압축을 위한 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 포스터 정보 입력
        Point[] posters = new Point[N];
        StringTokenizer st = null;
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(in.readLine());

            posters[i] = new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            // 추가되지 않은 좌표인 경우 압축을 위해 추가
            addNumber(posters[i].start, set, pq);
            addNumber(posters[i].end, set, pq);
        }

        // 좌표 압축!
        // - 우선순위 큐에 따라 좌표값이 작은 경우부터 차례로 추가
        int idx = 0;
        Map<Integer, Integer> compressMap = new HashMap<>();
        while(!pq.isEmpty()) compressMap.put(pq.poll(), idx++);

        // 포스터 부착 정보 초기화
        int[] board = new int[compressMap.size()];
        // - 좌표를 압축하여 모든 좌표를 활용하므로 초기화할 필요 없음!
        // Arrays.fill(board, -1);
        for(int i = 0; i < N; i++){
            // 포스터 부착
            int start = compressMap.get(posters[i].start);
            int end = compressMap.get(posters[i].end);
            while(start <= end) board[start++] = i;
        }

        // 보이는 포스터의 개수 체크!
        Set<Integer> count =  new HashSet<>();
        for(int i : board) count.add(i);
        
        // 정답 반환
        sb.append(count.size());
        System.out.println(sb);
    }

    private static void addNumber(int target, Set<Integer> set, PriorityQueue<Integer> pq) {
        if(!set.contains(target)){
            set.add(target);
            pq.offer(target);
        }
    }
}
