package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2866
// - 이분 탐색 : 제거할 행의 개수를 이분 탐색으로 결정하여 체크!
public class _10_Solution_1 {
    public static char[][] TABLE;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int R = Integer.parseInt(st.nextToken());   // 행의 수
        int C = Integer.parseInt(st.nextToken());   // 열의 수

        // 테이블 입력
        TABLE = new char[R][C];
        for(int r = 0; r < R; r++) TABLE[r] = in.readLine().toCharArray();

        // 이분 탐색을 통해 정답 계산
        int count = binarySearch(R, C);

        // 정답 출력
        sb.append(count);
        System.out.println(sb.toString());
    }
    // 이분 탐색 : mid행까지 제거가 가능한지 확인
    private static int binarySearch(int r, int c) {
        // 시작 행
        int left = 0;
        // 끝 행
        int right = r - 1;
        // 이분 탐색 진행
        while(left <= right){
            // mid 계산
            int mid = (left + right) / 2;
            // mid까지 제거할 수 있는지 확인 : mid까지 제거할 수 있다면 mid미만은 확정적으로 제거할 수 있음
            //                                 mid초과에서 반복이 없다는 의미이므로 mid 미만에서 반복이 있을 수 없음
            // - 제거할 수 있다면 left 증가
            if(isPossible(mid, r, c)) left = mid + 1;
            // - 제거할 수 없다면 right 감소
            else right = mid - 1;
        }
        return left;
    }
    // mid까지 제거한 후 남은 테이블의 값을 비교하여 가능한지 확인
    private static boolean isPossible(int mid, int r, int c) {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < c; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = mid+1; j < r; j++) sb.append(TABLE[j][i]);
            if(set.contains(sb.toString())) return false;
            set.add(sb.toString());
        }

        return true;
    }
}
