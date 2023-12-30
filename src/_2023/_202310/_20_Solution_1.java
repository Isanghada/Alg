package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1175
// - 그리디 : 열 기준으로 현재 높이 이상의 모든 쓰레기를 치우는 방향으로 진행.
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202310/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 방 크기 입력
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기

        // 방 정보 초기화
        int[][] map = new int[N][M];
        // 열별 쓰레기 위치를 담기 위해 우선순위큐 초기화
        PriorityQueue<Integer>[] pq = new PriorityQueue[M];
        for(int c = 0; c < M; c++) pq[c] = new PriorityQueue<>();

        // 쓰레기의 수
        int cnt = 0;
        // 방 정보 입력
        for(int r = 0; r < N ; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < M; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                // 현재 좌표에 쓰레기가 있다면 우선순위큐에 추가
                if(map[r][c] == 1) {
                    pq[c].add(r);
                    cnt++;
                }
            }
        }

        // 정답 초기화
        int answer = 0;
        // 치울 쓰레기가 있는 경우 로직 진행
        if(cnt > 0) {
            // 마지막 열에서부터 차례로 로봇 추가
            for (int c = M - 1; c >= 0; c--) {
                // 현재 행 좌표
                int r = N - 1;
                // 쓰레기 여부를 c부터 확인
                for (int curC = c; curC >= 0; curC--) {
                    // 쓰레기가 없다면 패스
                    if (pq[curC].isEmpty()) continue;
                    // 현재 열의 최소 행 입력
                    int curR = pq[curC].peek();
                    // 쓰레기를 치울 수 있는 경우
                    if (r >= curR) {
                        // 우선순위큐에서 차례로 최소값을 확인하며 치울 수 있는 쓰레기 제거
                        while (!pq[curC].isEmpty() && r >= pq[curC].peek()) {
                            pq[curC].poll();
                            cnt--;
                        }
                        // 현재 행 좌표를 curR로 변경
                        r = curR;
                    }
                }
                // 쓰레기를 치웠으므로 로봇 추가
                answer++;
                // 모든 쓰레기를 치웠다면 종료
                if (cnt == 0) break;
            }
        }
        // 정답 반환
        sb.append(answer);
        // 정답 출력
        System.out.println(sb);
    }
}
