package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/18869
// - 정렬, 압축 : 좌표를 압축하여 행성의 순서가 동일한지 확인
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());   // 우주의 개수
        int N = Integer.parseInt(st.nextToken());   // 행성의 개수

        // 행성 배열 초기화
        int[][] planets = new int[M][N];
        // 각 우주의 행성을 압축하여 크기 순위로 표현!
        for(int m = 0; m < M; m++){
            // 행성 입력
            st = new StringTokenizer(in.readLine());

            // 행성 크기를 담을 Set : 중복을 제거하여 순위 표현하기 위함
            Set<Integer> planetSet = new HashSet<>();
            // 행성 정보 입력
            for(int n = 0; n < N; n++){
                planets[m][n] = Integer.parseInt(st.nextToken());
                planetSet.add(planets[m][n]);
            }

            // 리스트를 사용해 행성의 크기 순위 계산
            List<Integer> planetList = new ArrayList<>(planetSet);
            Collections.sort(planetList);

            // Map을 통해 빠른 탐색이 가능하도록 데이터 구성
            Map<Integer, Integer> planetRank = new HashMap<>();
            for(int rank = 0; rank < planetList.size(); rank++) planetRank.put(planetList.get(rank), rank);

            // 행성의 크기를 순위로 변경
            for(int n = 0; n < N; n++) planets[m][n] = planetRank.get(planets[m][n]);
        }

        // 정답 초기화
        int answer = 0;
        // a우주와 b우주를 비교하여 같은 구성인지 확인
        for(int a = 0; a < M; a++){
            for(int b = a+1; b < M; b++){
                if(planetEquals(planets, a, b)) answer++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // a우주와 b우주의 값이 동일하다면 true, 아니라면 false 반환
    private static boolean planetEquals(int[][] planets, int a, int b) {
        for(int n = 0; n < planets[0].length; n++){
            if(planets[a][n] != planets[b][n]) return false;
        }

        return true;
    }
}
