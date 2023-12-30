package _2023._202309;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/1833
// - 구간합을 통해 내부에 쐐기가 있는지 확인
// - 좌표 범위가 넓으므로 구간합을 사용하기 위해 좌표 압축 사용
//   - 좌표의 수가 최대 5000개 이므로 0 ~ 2^31 => 0 ~ 4999로 변경
public class _02_Solution_1 {
    public int solution(int n, int[][] data) {
        // 정답 초기화
        int answer = 0;

        // 좌표를 Set을 통해 중복 제거
        Set<Integer> setX = new HashSet<>();
        Set<Integer> setY = new HashSet<>();

        // X, Y 좌표 추가
        for(int[] point : data){
            setX.add(point[0]);
            setY.add(point[1]);
        }

        // 리스트에 추가하여 정렬
        List<Integer> listX = new ArrayList<>(setX);
        List<Integer> listY = new ArrayList<>(setY);
        Collections.sort(listX);
        Collections.sort(listY);

        // dp 초기화 : 쐐기가 있는 곳에 1 입력
        int[][] dp = new int[5000][5000];
        for(int index = 0; index < n; index++){
            // 좌표를 변경하여 0 ~ 4999로 변경
            data[index][0] = listX.indexOf(data[index][0]);
            data[index][1] = listY.indexOf(data[index][1]);

            // 해당 좌표에 쐐기 표시
            dp[data[index][0]][data[index][1]] = 1;
        }

        // 구간합을 통해 쐐기의 개수 계산
        // 범위 내에 있을 경우에만 계산
        for(int r = 0 ; r < 5000; r++){
            for(int c = 0 ; c < 5000; c++){
                if (r > 0) dp[r][c] += dp[r-1][c];
                if (c > 0) dp[r][c] += dp[r][c-1];
                if (r > 0 && c > 0) dp[r][c] -= dp[r-1][c-1];
            }
        }

        // 좌표를 정렬하여 계산의 편의성 확보
        // - x 좌표 기준 오름차순, y 좌표 기준 오름차순.
        // Arrays.sort(dp, (o1, o2) -> {
        //     int result = o1[0] - o2[0];
        //     return result == 0 ? o1[1] - o2[1] : result;
        // });

        // 모든 경우의 수 탐색
        for(int i = 0; i < n; i++){
            for(int j = i+1; j <n; j++){
                // 각 좌표의 최소, 최대로 좌표 입력
                int x1 = Math.min(data[i][0], data[j][0]);
                int y1 = Math.min(data[i][1], data[j][1]);
                int x2 = Math.max(data[i][0], data[j][0]);
                int y2 = Math.max(data[i][1], data[j][1]);

                // 대각을 이루지 못하는 경우 패스.
                if(x1 == x2 || y1 == y2) continue;

                // 공간 내에 쐐기의 수 확인
                int count = dp[x2-1][y2-1] - dp[x2-1][y1] - dp[x1][y2-1] + dp[x1][y1];
                // 쐐기가 없는 경우 텐트 설치 가능
                if(count == 0) answer++;
            }
        }

        return answer;
    }
}
