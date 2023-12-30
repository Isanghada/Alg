package _2023._202309;

// https://school.programmers.co.kr/learn/courses/30/lessons/87391
// - dp 활용 -> 시작할 수 있는 범위를 역순으로 계산
// - 도착 지점에서 queries를 역순으로 실행시키며 도달하기 위한 영역 계산
public class _16_Solution_1 {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        // 범위 초기화 : 도착 지점
        // - point[0] : 행 좌표 하한 
        // - point[1] : 행 좌표 상한
        // - point[2] : 열 좌표 하한
        // - point[3] : 열 좌표 상한
        long[] point = new long[]{x, x, y, y};

        // queries를 역순으로 실행
        for(int idx = queries.length-1; idx >= 0; idx--){
            // 실행 명령
            int command = queries[idx][0];
            // 이동 범위
            int dx = queries[idx][1];

            // 명령에 따라 역방향으로 이동!
            // - 0 : 열 번호 감소 이동
            if(command == 0){
                // 열 하한이 0이 아닌 경우에만 범위만큼 증가!
                if(point[2] > 0) point[2] += dx;
                // 열 상한은 범위를 벗어나지 않는 경우까지만 증가
                point[3] = Math.min(point[3] + dx, m-1);
            // - 1 : 열 번호 증가 이동
            }else if(command == 1){
                // 열 하한이 0까지만 감소
                point[2] = Math.max(point[2] - dx, 0);
                // 열 상한은 경계값이 아닌 경우에만 범위만큼 감소
                if(point[3] < m-1) point[3] -= dx;
            // - 2 : 행 번호 감소 이동
            }else if(command == 2){
                // 행 하한은 0이 아닌 경우에만 범위만큼 증가
                if(point[0] > 0) point[0] += dx;
                // 행 상한은 범위를 벗어나지 않는 경우까지만 증가
                point[1] = Math.min(point[1] + dx, n-1);
            // - 3 : 행 번호 증가 이동
            }else{
                // 행 하한이 0까지만 감소
                point[0] = Math.max(point[0] - dx, 0);
                // 행 상한은 경계값이 아닌 경우에만 범위만큼 감소
                if(point[1] < n-1) point[1] -= dx;
            }
            
            // 경계를 벗어나는 값이 있다면 불가능한 경우이므로 0 반환
            if(point[0] >= n || point[1] < 0 || point[2] >= m || point[3] < 0) return 0;
        }
//        System.out.println(Arrays.toString(point));
        // 행, 열 범위 만큼의 면적을 계산하여 반환
        return (1L + point[1] - point[0]) * (1L + point[3] - point[2]);
    }
}
