package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/18231
// - 그래프 : 인접 리스트를 사용해 결과 체크
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int M = Integer.parseInt(st.nextToken());   // 연결 개수

        // 인접 리스트
        List<Integer>[] adjList = new List[N+1];
        for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        // 도시 연결 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjList[u].add(v);
            adjList[v].add(u);
        }

        // 파괴된 도시의 수
        int K = Integer.parseInt(in.readLine());
        Set<Integer> removeSet = new TreeSet<>();
        
        // 파괴된 도시 정보 입력
        st = new StringTokenizer(in.readLine());
        while(K-- > 0) removeSet.add(Integer.parseInt(st.nextToken()));

        // 폭탄이 떨어진 도시 리스트
        List<Integer> cities = new ArrayList<>();
        for(int remove : removeSet){
            // 플래그 : 폭탄이 떨어질 수 있는 도시인지 체크
            boolean flag = true;
            // 현재 폭탄이 떨어진 도시가 remove인 경우 인접 도시 확인
            for(int adjCity : adjList[remove]){
                // 파괴되지 않은 도시가 있는 경우 flag 갱신 후 종료
                if(!removeSet.contains(adjCity)){
                    flag = false;
                    break;
                }
            }
            // 폭탄이 떨어질 수 있는 도시인 경우 cities에 추가
            if(flag) cities.add(remove);
        }

        // cities로 파괴되는 도시 체크
        for(int city : cities){
            removeSet.remove(city);
            for(int adjCity : adjList[city]) removeSet.remove(adjCity);
        }

        // 모든 도시가 파괴된 경우 : 폭탄이 떨어진 도시의 수, 도시 출력
        if(removeSet.size() == 0) {
            sb.append(cities.size()).append("\n");
            for(int city : cities) sb.append(city).append(" ");
        // 불가능한 경우 -1 반환
        }else sb.append(-1);

        // 정답 출력
        System.out.println(sb);
    }
}
