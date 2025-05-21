package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20917
// -
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 콘센트의 수
            int S = Integer.parseInt(st.nextToken());   // 팀의 수

            // 콘센트 위치 입력
            int[] X = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // - 이분 탐색을 위해 오름차순 정렬
            Arrays.sort(X);

            // 이분 탐생을 통해 최대 거리 계산
            sb.append(binarySearch(N, S-1, X)).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static int binarySearch(int n, int s, int[] x) {
        int result = 0;
        int left = 1;
        int right = x[n-1] - x[0];
        while(left <= right){
            int mid = (left + right) / 2;

            // mid 거리 만큼 떨어질 수 있다면 result와 left 갱신
            if(possible(n, s, x, mid)) {
                result = Math.max(result, mid);
                left = mid + 1;
            }
            // 불가능한 경우 right 갱신
            else right = mid - 1;

        }
        return result;
    }

    // target 만큼 떨어질 수 있는지 체크
    // - 상한 함수를 통해 target 이상인 거리 중 최소값 선택!
    private static boolean possible(int n, int s, int[] x, int target) {
        // 처음 위치는 첫 좌표로 고정
        int idx = 0;
        while(s-- > 0){
            // 상한을 통해 다음 위치 선택
            idx = upperBound(n, idx, target, x);
            // - 불가능한 경우 false 반환
            if(idx >= n) return false;
        }

        return true;
    }

    private static int upperBound(int n, int idx, int target, int[] x) {
        int value = x[idx];

        int left = idx+1;
        int right = n-1;
        while(left <= right){
            int mid = (left + right) / 2;

            int diff = x[mid] - value;
            if(diff >= target) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}
