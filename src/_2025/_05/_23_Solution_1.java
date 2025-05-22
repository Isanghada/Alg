package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2653
// - 구현 : 인접 행렬을 통해 그래프를 차례로 탐색하여 안정된 집합인지 체크
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 사람의 수
        int N = Integer.parseInt(in.readLine());
        
        // 인접 행렬 정보 입력
        int[][] adjArr = new int[N+1][N+1];
        StringTokenizer st = null;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 1; j <= N; j++) adjArr[i][j] = Integer.parseInt(st.nextToken());
        }

        int count = 0;                              // 소집단의 수
        boolean[] visited = new boolean[N+1];       // 집단 소속 여부
        StringBuilder group = new StringBuilder();  // 소집단별 번호
        
        // 번호가 작은 순부터 차례로 체크
        for(int n = 1; n <= N; n++){
            // 이미 집단에 소속된 경우 패스
            if(visited[n]) continue;

            // 소집단 초기화
            List<Integer> groupList = new ArrayList<>();
            // 모든 사람 체크
            for(int num = 1; num <= N; num++){
                // 싫어하는 경우 패스
                if(adjArr[n][num] == 1) continue;
                // 이미 집단에 소속된 번호(num)인 경우
                // - 불안정한 집단이므로 0 반환
                if(visited[num]){
                    System.out.println(0);
                    return;
                }

                // 소속 여부 갱신
                visited[num] = true;
                // 소집단에 추가
                groupList.add(num);
            }

            // 소집단의 안정성 체크
            // - 소집단에 소속된 사람들이 모두 친하지 체크
            for(int a : groupList){
                for(int b : groupList){
                    // 친하지 않은 경우
                    // - 불안정한 집단이므로 0 반환
                    if(adjArr[a][b] == 1){
                        System.out.println(0);
                        return;
                    }
                }
            }

            // 소집단의 인원이 1명인 경우
            // - 불안정한 집단이므로 0 반환
            if(groupList.size() == 1){
                System.out.println(0);
                return;
            }

            // 안정된 집합인 경우
            // - count 증가
            // - 소집단 인원 출력
            count++;
            for(int num : groupList){
                group.append(num).append(" ");
            }
            group.append("\n");
        }

        sb.append(count).append("\n").append(group.toString());

        // 정답 출력
        System.out.println(sb);
    }
}
