package _202309;

// https://school.programmers.co.kr/learn/courses/30/lessons/131702
// - DFS를 활용하지만 모든 좌표에 대해 진행하는 것이 아닌 첫 행에 대해서만 계산
// - 첫 번째 행이 정해지면 아래 행은 정해진 대로만 회전을 해야하기 때문.
// - 모든 행을 회전시킨 후 값 확인하여 최소값으로 갱신.
public class _18_Solution_1 {
    // 길이, 정답
    private int N, answer;
    // 시계 상태 2차원 배열
    private int[][] clockHands;
    // 첫 번째 행 회전 횟수 배열
    private int[] rotateArr;
    // 같이 회전할 좌표를 구하기 위한 값.
    private int[][] DELTA = new int[][]{{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public int solution(int[][] clockHands) {
        //초기화
        N = clockHands.length;          // 배열 길이
        answer = Integer.MAX_VALUE;     // 정답 초기화
        rotateArr = new int[N];         // 행 회전 횟수 배열
        this.clockHands = clockHands;   // 시계 상태 배열

        // DFS를 통해 회전 횟수 선택
        dfs(0);

        return answer;
    }

    // 최소 회전 횟수 계산 함수 : DFS 활용
    // - step : 첫 번째 행의 열 인덱스!
    // - 첫 번째 행의 회전 횟수를 결정하면 이후 행은 자동으로 결정할 수 있음.
    // - 첫 행의 회전 횟수를 모두 결정하면 상태 확인하여 정답 계산.
    private void dfs(int step) {
        // 첫 행의 모든 열의 회전 횟수를 결정한 경우
        // - 각 좌표를 회전시키며 확인
        if(step == N){
            // 시계 상태를 담을 배열
            int[][] clockTemp = new int[N][N];
            // 회전 횟수를 담을 배열
            int[] rotateTemp = new int[N];

            // 시계 상태와 회전 횟수 초기화
            for(int r = 0; r < N; r++){
                // 시계 초기 상태 입력
                for(int c = 0; c < N; c++) clockTemp[r][c] = clockHands[r][c];
                // 첫 행의 회전 상태 입력
                rotateTemp[r] = rotateArr[r];
            }

            // 회전 횟수 초기화
            int count = 0;

            // 모든 좌표 회전하며 탐색
            for(int r = 0; r < N; r++){
                for(int c = 0; c < N; c++){
                    // 회전 횟수 증가
                    count += rotateTemp[c];
                    // 현재 좌표 (r, c)를 기준으로 회전
                    for(int[] d : DELTA){
                        int R = r + d[0];
                        int C = c + d[1];

                        // 범위를 벗어날 경우 패스.
                        if(R < 0 ||  R >= N || C < 0 || C >= N) continue;
                        // 회전 후 상태 계산 : (현재 상태 + 회전 횟수) % 4
                        clockTemp[R][C] += rotateTemp[c];
                        if(clockTemp[R][C] >= 4) clockTemp[R][C] -= 4;
                    }
                }
                
                // 다음 행의 회전 횟수 계산
                // - 기준 좌표 기준 바로 이전 행을 12시(0)으로 만들어야함.
                // - 회전 횟수 : 4 - (상태) => 4인 경우 0
                for(int c = 0; c < N; c++){
                    rotateTemp[c] = 4 - clockTemp[r][c];
                    if(rotateTemp[c] == 4) rotateTemp[c] = 0;
                }
            }

            // 모든 회전을 진행한 후 결과 확인
            // - 마지막 행의 상태가 0이 아니라면 실패
            for(int c = 0; c < N; c++){
                if(rotateTemp[c] != 0) return;
            }

//            System.out.println(count);
//            for(int[] a : clockTemp){
//                System.out.println(Arrays.toString(a));
//            }
//            System.out.println("--------------");

            // 성공한 경우 정답 최소값으로 갱신
            answer = Math.min(answer, count);
        }else{
            // 첫 번째 행 회전 횟수 결정
            // - 모든 경우 진행
            // - 0(미회전) ~ 3 회전
            for(int count = 0; count < 4; count++){
                rotateArr[step] = count;
                // 다음 열 선택
                dfs(step+1);
            }
        }
    }
}
