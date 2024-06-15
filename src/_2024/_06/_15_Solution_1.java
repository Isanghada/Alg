package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22251
// - 브루트포스 : 가능한 모든 경우를 계산!
//                각 숫자로 변환하기 위해 필요한 개수 계산! (NUMBER_MAP)
//                모든 층으로 변경할 때 총 개수를 계산하여 비교
public class _15_Solution_1 {
    // 숫자 변환 MAP
    // - NUMBER_MAP[i][j] : i에서 j로 변경할 때 필요한 개수
    public static int[][] NUMBER_MAP = {
            {0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
            {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
            {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
            {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
            {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
            {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
            {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
            {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
            {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
            {2, 4, 3, 1, 2, 1, 2, 3, 1, 0}
    };
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 최대 층
        int K = Integer.parseInt(st.nextToken());   // 디스플레이 개수
        int P = Integer.parseInt(st.nextToken());   // 최대 개수
        int X = Integer.parseInt(st.nextToken());   // 현재 층

        // 현재 층을 디스플레이로 변환
        int[] start = getNumArr(K, X);

        // 정답 초기화
        int answer = 0;
        // 1층부터 N층까지 모두 확인
        for(int step = 1; step <= N; step++){
            // 현재 층일 경우 패스
            if(step == X) continue;
            // 도착 층을 디스플레이로 변환
            int[] end = getNumArr(K, step);
            // 필요한 개수 계산
            int changeCount = getCount(end, start, K);

            // P개 이하인 경우 정답 증가
            if(changeCount <= P) {
//                System.out.println(Arrays.toString(end));
                answer++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
    // NUMBER_MAP을 활용해 필요한 개수 계산
    private static int getCount(int[] end, int[] start, int k) {
        int count = 0;
        for(int i = 0; i < k; i++){
            count += NUMBER_MAP[start[i]][end[i]];
        }

        return count;
    }
    // x를 디스플레이로 변환
    private static int[] getNumArr(int k, int x) {
        int[] result = new int[k];
        for(int i = k-1; i >= 0; i--){
            result[i] = x % 10;
            x /= 10;
        }
        return result;
    }
}
