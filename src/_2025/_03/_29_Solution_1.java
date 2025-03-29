package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25187
// - BFS : 1. BSF를 통해 각 물탱크 집합의 (청정수 - 고인물) 계산!
//         2-1. 구해진 값이 0 초과인 경우 해당 집합은 청정수 가능
//         2-2. 구해진 값이 0 이하인 경우 해당 집합은 청정수 불가
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 물탱크의 수
        int M = Integer.parseInt(st.nextToken());   // 파이프의 수
        int Q = Integer.parseInt(st.nextToken());   // 물탱크 방문 횟수

        // 청정수 집합
        Set<Integer> cleanWaters = new HashSet<>();
        // 고인물 집합 : 청정수 집합으로 구별할 수 있으므로 주석 처리
        // Set<Integer> stagnantWaters = new HashSet<>();

        // 파이프 정보 : 같은 물탱크를 연결할 수 있으므로 중복 제거를 위해 set 활용
        Set<Integer>[] pipeSet = new Set[N+1];

        // 물탱크 정보 입력
        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++){
            String water = st.nextToken();
            if(water.equals("1")) cleanWaters.add(i);
            // else stagnantWaters.add(i);

            pipeSet[i] = new HashSet<>();
        }

        // 파이프 정보 입력
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            pipeSet[u].add(v);
            pipeSet[v].add(u);
        }

        boolean[] visited = new boolean[N+1];   // 방문 배열
        int[] waterSet = new int[N+1];          // 물탱크의 소속 집합 번호
        boolean[] waterType = new boolean[N+1]; // 집합의 청정수 여부
        int idx = 1;                            // 집합 번호

        for(int water = 1; water <= N; water++){
            // 방문하지 않은 물탱크인 경우 : 새로운 집합! 탐색
            if(!visited[water]){
                // 청정수 - 고인물
                int count = 0;

                // 덱 초기화
                Deque<Integer> deque = new LinkedList<>();
                deque.offerLast(water);
                visited[water] = true;

                // 덱이 빌 때까지 반복
                while(!deque.isEmpty()){
                    // 현재 물탱크
                    int cur = deque.pollFirst();

                    // 집합 표시
                    waterSet[cur] = idx;
                    // count 계산
                    // - 청정수인 경우 : 1
                    // - 고인물인 경우 : -1
                    count += cleanWaters.contains(cur) ? 1 : -1;

                    // 다음 물탱크
                    for(int next : pipeSet[cur]){
                        // 방문한 경우 패스
                        if(visited[next]) continue;

                        deque.offerLast(next);
                        visited[next] = true;
                    }
                }

                // count에 따라 청정수 여부 체크
                waterType[idx] = count > 0 ? true : false;
                // 집합 번호 증가
                idx++;
            }
        }
//        System.out.println(Arrays.toString(waterSet));
//        System.out.println(Arrays.toString(waterType));
        while(Q-- > 0){
            // 방문할 물탱크
            int K = Integer.parseInt(in.readLine());
            // 청정수 여부 출력
            sb.append(waterType[waterSet[K]] ? 1 : 0).append("\n");
        }


        // 정답 반환
        System.out.println(sb.toString().trim());
    }
}