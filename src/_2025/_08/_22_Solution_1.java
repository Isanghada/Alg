package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27313
// - 그리디 : 애니메이션 시간을 정렬한 후 이분 탐색을 통해 가능한 최대한의 개수 계산
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 애니메이션 수
        int M = Integer.parseInt(st.nextToken());   // 제한 시간
        int K = Integer.parseInt(st.nextToken());   // 동시 시청

        // 애니메이션 정보 입력 후 정렬
        int[] animations = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(animations);

        // 정답 출력
        // - 이분 탐색을 통해 최대값 계산
        sb.append(binarySearch(N, M, K, animations));
        System.out.println(sb);
    }

    private static int binarySearch(int n, int m, int k, int[] animations) {
        int left = 1;
        int right = n;

        while(left <= right){
            int mid = (left + right) / 2;

            // 가능하다면 left 갱신
            if(check(m, k, animations, mid)) left = mid + 1;
            // 불가능하다면 right 갱신
            else right = mid - 1;
        }

        return right;
    }

    private static boolean check(int m, int k, int[] animations, int target) {
        int index = target - 1;
        if(animations[index] > m) return false;

        long time = 0;
        while(index >= 0){
            time += animations[index];
            index -= k;
        }

        return time <= m;
    }
}
