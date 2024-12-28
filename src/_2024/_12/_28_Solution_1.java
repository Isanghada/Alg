package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21940
// - 플로이드-와샬 : 각 도시에서 다른 도시로 가는 시간 계산!
//                   모든 도시를 탐색하며 도시 X 탐색
public class _28_Solution_1 {
    static final int MAX = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int M = Integer.parseInt(st.nextToken());   // 도로의 수

        // 도로 초기화
        int[][] map = new int[N+1][N+1];
        for(int n = 1; n <= N; n++) {
            Arrays.fill(map[n], MAX);
            map[n][n] = 0;
        }

        // 도로 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            map[a][b] = Math.min(map[a][b], t);
        }

        // 플로이드-와샬
        floyd(map, N);

        // 총 인원
        int K = Integer.parseInt(in.readLine());
        // 시작 도시 정보
        int[] friends = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 왕복 시간 최대 중 최소!
        int minTime = MAX;
        // 정답 리스트
        List<Integer> answerList = new ArrayList<>();

        // 모든 도시 탐색
        for(int city = 1; city <= N; city++){
            // 최대 시간 계산
            int maxTime = 0;
            for(int friend : friends) maxTime = Math.max(maxTime, map[friend][city]+map[city][friend]);

            // 이동할 수 없거나 minTime보다 오래 걸리는 경우 패스
            if(maxTime >= MAX || maxTime > minTime) continue;

            // maxTime이 더 작게 걸리는 경우 : minTime 갱신, answerList 초기화
            if(maxTime < minTime){
                minTime = maxTime;
                answerList = new ArrayList<>();
            }
            // 도시 추가
            answerList.add(city);
        }

        for(int city : answerList) sb.append(city).append(" ");

        // 정답 출력
        System.out.println(sb);
    }

    private static void floyd(int[][] map, int n) {
        for(int mid = 1; mid <= n; mid++){
            for(int start = 1; start <= n; start++){
                for(int end = 1; end <= n; end++){
                    if(start == end) continue;
                    map[start][end] = Math.min(map[start][end], map[start][mid]+map[mid][end]);
                }
            }
        }
    }
}
