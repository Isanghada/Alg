package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16958
// - 플로이드-와샬 : 1. 각 도시에서 다른 도시로 직접 이동하는 시간 계산
//                   2. 플로이들-와샬을 통해 각 도시에서 다른 도시로 가는 최소 이동 시간 계산
//                   3. 계산된 값을 통해 최소 이동 시간 반환
public class _21_Solution_1 {
    // 도시 클래스
    static class City{
        int type;   // 도시 종류(0-일반 도시, 1-특별한 도시)
        int r;      // 행 좌표
        int c;      // 열 좌표
        public City(int type, int r, int c){
            this.type = type;
            this.r = r;
            this.c = c;
        }
    }
    static final int MAX = 100_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int T = Integer.parseInt(st.nextToken());   // 텔레포트 시간
        
        // 도시 정보 입력
        City[] cities = new City[N+1];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            cities[n] = new City(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 인접 행렬 계산
        // - 도시 정보를 바탕으로 계산
        int[][] adjArr = new int[N+1][N+1];
        for(int start = 1; start <= N; start++){
            for(int end = 1; end <= N; end++){
                if(start == end) continue;
                adjArr[start][end] = getTime(cities, start, end, T);
            }
        }

        int[][] floyd = getFloyd(adjArr, N);

        int M = Integer.parseInt(in.readLine());
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(floyd[start][end]).append("\n");
        }


        // 정답 출력
        System.out.println(sb);
    }

    /**
     * adjArr를 바탕으로 플로이드-와샬 알고리즘 실행
     * 각 도시에서 모든 도시로 이동하는 최소 이동 시간 계산
     * @param adjArr
     * @param n
     * @return int[][] floyd
     */
    private static int[][] getFloyd(int[][] adjArr, int n) {
        // 플로이드 배열 초기화
        // - adjArr 활용
        int[][] floyd = new int[n+1][n+1];
        for(int start = 1; start <= n; start++) {
            for(int end = 1; end <= n; end++){
                floyd[start][end] = adjArr[start][end];
            }
        }

        // (start, end)로 이동할 때 mid를 거쳐서가는 경우와 비교!
        for(int mid = 1; mid <= n; mid++){
            for(int start = 1; start <= n; start++){
                for(int end = 1; end <= n; end++){
                    if(start == end) continue;
                    floyd[start][end] = Math.min(floyd[start][end],
                            floyd[start][mid]+floyd[mid][end]);
                }
            }
        }


        return floyd;
    }

    /**
     * start, end 간의 이동 시간 계산 함수
     * 1. 기본 이동 시간을 통해 계산!
     * 2. 텔레포트가 가능한 도시라면 계산한 시간과 텔레포트 시간을 비교하여 최소값 선택
     */
    private static int getTime(City[] cities, int start, int end, int t) {
        // 기본 이동 시간 계산
        int time = Math.abs(cities[start].r - cities[end].r)
                + Math.abs(cities[start].c - cities[end].c);

        // 텔레포트가 가능하다면 최소값 선택
        if(cities[start].type == 1 && cities[end].type == 1){
            time = Math.min(time, t);
        }

        // 최소 이동 시간 반환
        return time;
    }
}