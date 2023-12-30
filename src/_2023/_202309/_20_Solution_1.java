package _2023._202309;

// https://school.programmers.co.kr/learn/courses/30/lessons/60061
// - 구현 문제! : 조건을 생각해주는게 중요했음.
// - 알고리즘이 필요하지는 않았다.
public class _20_Solution_1 {
    public static boolean[][] pillar;   // 기둥 2차원 배열
    public static boolean[][] bridge;   // 보 2차원 배열
    public static int N;    // 크기
    public int[][] solution(int n, int[][] build_frame) {
        // 정답
        int[][] answer;

        // 입력값 설정
        N = n;
        pillar = new boolean[n+1][n+1];
        bridge = new boolean[n+1][n+1];

        // 구조물 개수 초기화
        int count = 0;

        // 작업순서대로 작업 진행
        for(int[] build : build_frame){
            // 기둥 명령
            if(build[2] == 0){
                // 추가하는 경우
                if(build[3] == 1){
                    // 추가할 수 잇는 위치이면 추가하고 개수 증가
                    if(checkPillar(build[1], build[0])){
                        pillar[build[1]][build[0]] = true;
                        count++;
                    }
                // 삭제하는 경우
                }else{
                    // 기둥 삭제
                    pillar[build[1]][build[0]] = false;
                    // 가능하다면 구조물 개수 감소 및 다음 명령 진행
                    if(checkDelete()){
                        count--;
                        continue;
                    }
                    // 불가능한 경우 구조물 복구
                    pillar[build[1]][build[0]] = true;
                }
            // 보 명령
            }else{
                // 추가하는 경우
                if(build[3] == 1){
                    // 추가할 수 잇는 위치이면 추가하고 개수 증가
                    if(checkBridge(build[1], build[0])){
                        bridge[build[1]][build[0]] = true;
                        count++;
                    }
                // 삭제하는 경우
                }else{
                    // 보 삭제
                    bridge[build[1]][build[0]] = false;
                    // 가능하다면 구조물 개수 감소 및 다음 명령 진행
                    if(checkDelete()){
                        count--;
                        continue;
                    }
                    // 보 복구
                    bridge[build[1]][build[0]] = true;
                }
            }
        }


        // 구조물 개수만큼 정답 초기화
        answer = new int[count][3];
        // 정답 인덱스 설정
        int index = 0;
        // 정답 [x, y, type]
        // - x : 열 좌표
        // - y : 행 좌표
        // - type : 기둥(0), 보(1)
        
        // 정렬 순서가 아래와 같으므로 열을 기준으로 행 좌표 검사하며 추가
        // - 열 기준 오름 차순
        // - 행 기준 오름 차순
        // - type 기준 오름 차순
        for(int c = 0 ; c <= N; c++){
            for(int r = 0; r <= N; r++){
                // 기둥이 있다면 정답에 추가
                if(pillar[r][c]) answer[index++] = new int[]{c, r, 0};
                // 보가 있다면 정답에 추가
                if(bridge[r][c]) answer[index++] = new int[]{c, r, 1};
            }
        }
        return answer;
    }

    // 삭제 체크 함수
    private boolean checkDelete() {
        // 모든 좌표를 탐색하며 구조물이 존재할 수 있는지 확인
        // - 불가능한 경우 false 반환
        for(int r = 0; r <= N; r++){
            for(int c = 0; c <= N; c++){
                // 기둥이 있는 경우 기둥이 존재할 수 있는지 확인
                if(pillar[r][c] && !checkPillar(r, c)) return false;
                // 보가 있는 경우 보가 존재할 수 있는지 확인
                if(bridge[r][c] && !checkBridge(r, c)) return false;
            }
        }
        // 모든 구조물이 존재할 수 있다면 true 반환
        return true;
    }

    // 기둥 체크 함수
    // - r : 행 좌표
    // - c : 열 좌표
    public boolean checkPillar(int r, int c) {
        // 아래의 경우 true 반환
        // - 바닥인 경우 : r == 0
        // - 기둥이 받쳐주는 경우 : r > 0 && pillar[r-1][c]
        // - 보가 받쳐주는 경우 : (c > 0 && bridge[r][c-1]) || bridge[r][c]
        return r == 0 || (r > 0 && pillar[r-1][c]) || (c > 0 && bridge[r][c-1]) || bridge[r][c];
    }

    // 보 체크 함수
    // - r : 행 좌표
    // - c : 열 좌표
    public boolean checkBridge(int r, int c) {
        // 아래의 경우 true 반환
        // - 기둥이 받쳐주는 경우 : (r > 0 && (pillar[r-1][c] || pillar[r-1][c+1]))
        // - 양 끝이 보인 경우 : (c > 0 && bridge[r][c-1] && bridge[r][c+1])
        return (r > 0 && (pillar[r-1][c] || pillar[r-1][c+1])) || (c > 0 && bridge[r][c-1] && bridge[r][c+1]);
    }
}
