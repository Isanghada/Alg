package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14676
// - 위상 그래프 : 위상 그래프를 활용해 선행되어 건축되어야 하는 건물 체크!
//                 조건에 따라 치트키 사용 여부 판단
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 건물 개수
        int M = Integer.parseInt(st.nextToken());   // 건물 관계의 수
        int K = Integer.parseInt(st.nextToken());   // 영우의 게임 정보

        // 인접 리스트 초기화 : 방향 그래프!
        // - adjList[n] : n번 건물이 '선행 조건'인 건물 리스트
        List<Integer>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();

        int[] countOfInDegree = new int[N+1];   // 선행되어 건축되어야 하는 건물의 수
        int[] countOfBuildings = new int[N+1];  // 건축된 건물의 수

        // 건물 관계 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            // Y 건물의 선행 조건 X 건물
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            adjList[X].add(Y);
            countOfInDegree[Y]++;
        }

        boolean flag = true;
        while(K-- > 0){
            st = new StringTokenizer(in.readLine());
            int command = Integer.parseInt(st.nextToken());     // 명령 정보(1-건설, 2-파괴)
            int building = Integer.parseInt(st.nextToken());    // 건물 번호

            // 건설
            if(command == 1){
                // 진입 차수가 0인 경우
                if(countOfInDegree[building] == 0){
                    // 건물 건설
                    countOfBuildings[building]++;
                    // 건설된 건물이 1개인 경우 : 관계된 건물의 선행 조건 감소
                    if(countOfBuildings[building] == 1){
                        for(int next : adjList[building]) countOfInDegree[next]--;
                    }
                // 진입 차수가 0이 아닌 경우 : 불가능한 경우 이므로 false 반환
                }else{
                    flag = false;
                    break;
                }
            // 파괴
            }else{
                // 건설된 건물이 0개인 경우 : 불가능한 경우 이므로 false 반환
                if(countOfBuildings[building] == 0){
                    flag = false;
                    break;
                }

                // 건물 파괴
                countOfBuildings[building]--;
                // 건물이 0개가 된 경우 : 관계된 건물의 선행 조건 증가
                if(countOfBuildings[building] == 0){
                    for(int next : adjList[building]) countOfInDegree[next]++;
                }
            }
        }

        if(flag) sb.append("King-God-Emperor");
        else sb.append("Lier!");


        // 결과 반환
        System.out.println(sb);
    }
}
