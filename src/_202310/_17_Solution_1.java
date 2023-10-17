package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2042
// - MST 문제 => 참고 : https://brorica.tistory.com/118
// - 문제를 이해하는게 가장 어려웠다..이해하고 나니 할만했다..
// - 크루스칼, 유니온-파인드 활용 => 최소 신장 트리를 구하고 우선 순위에 따라 도로 추가 연결
public class _17_Solution_1 {
    public static class Edge{
        int start, end;

        public Edge(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static int[] answer;     // 정답
    public static int[] parents;    // 도시의 부모 노드
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int M = Integer.parseInt(st.nextToken());   // 도로의 수

        // 도로 정보 추가
        // - Edge(a, b) : a와 b가 도로로 연결된 것을 의미.
        // - 양방향이지만 우선순위가 높은 것만 추가하기 위해 a < b 인 경우만 추가
        List<Edge> edgeList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            char[] map = in.readLine().toCharArray();
            for(int j = i+1; j < N; j++) if(map[j] == 'Y') edgeList.add(new Edge(i, j));
        }

        // 값 초기화
        answer = new int[N];    // 정답 초기화
        parents = new int[N];   // 부모 노드 초기화 : 자기 자신이 부모
        for(int node = 0; node < N; node++) parents[node] = node;

        // 도로 연결 성공시 도시별 도로의 수 반환
        if(makeStreet(N, M, edgeList)) for(int count : answer) sb.append(count).append(" ");
        // 연결 실패시 -1 반환
        else sb.append(-1);

        // 정답 출력
        System.out.println(sb);
    }

    // 도로 연결 함수 : 크루스칼 알고리즘 실행 -> 도로의 가중치가 동일하므로 우선순위가 높은 순으로 진행.
    // - 도로 연결 여부는 유니온-파인드로 확인
    private static boolean makeStreet(int n, int m, List<Edge> edgeList) {
        // 연결된 도로의 수
        int unionCount = 0;
        // 도로의 수가 m 보다 작은 경우 실패
        if(edgeList.size() < m) return false;

        // 처음에 연결되지 않아도 되는 도로 리스트
        // - 이미 연결된 도시 간의 도로 이므로 m개의 도로를 채울 때 활용
        List<Edge> remainList = new ArrayList<>();
        // 도로 연결 : 최소 신장 트리로 모든 도시를 이어주는 도로 n-1개 연결
        for(int idx = 0; idx < edgeList.size(); idx++){
            // 현재 도로 반환
            Edge cur = edgeList.get(idx);
            // 연결 성공일 경우
            if(union(cur.start, cur.end)){
                // 정답에 도시별 도로 개수 증가
                answer[cur.start]++;
                answer[cur.end]++;
                // 연결된 도로의 수 증가
                unionCount++;
            // 실패일 경우 : 리스트에 도로 추가
            }else remainList.add(cur);
        }

        // 도시가 모두 연결되지 않은 경우 실패
        if(unionCount != n -1) return false;

        // 추가해야하는 도로의 수 계산
        int remainCount = m - n + 1;

        // 우선수위가 높은 순서대로 도로 추가 연결
        for(int idx = 0; idx < remainCount; idx++){
            Edge cur = remainList.get(idx);
            answer[cur.start]++;
            answer[cur.end]++;
        }

        // 성공
        return true;
    }

    // 유니온 함수 : 도시 연결 여부 확인용
    // - start : 시작 도시
    // - end : 끝 도시
    private static boolean union(int start, int end) {
        // start의 부모 노드 탐색
        int startParent = find(start);
        // end의 부모 노드 탐색
        int endParent = find(end);

        // 부모 노드가 같을 경우 : 이미 연결된 경우이므로 연결하지 않음
        if(startParent == endParent) return false;
        // 부모 노드가 다를 경우 : 부모 노드의 값이 큰 쪽을 작은 쪽으로 연결
        else if(startParent < endParent) parents[endParent] = startParent;
        else parents[startParent] = endParent;

        // 도로 연결
        return true;
    }

    // 부모 노드 탐색 함수 : 재귀를 통해 루트 노드 확인
    private static int find(int node) {
        // 부모 노드가 자기 자신일 경우 : 루트
        if(parents[node] == node) return node;
        // 재귀를 통해 루트 노드를 찾으며 부모 노드를 루트 노드로 변경 : 빠른 탐색을 위해
        else return parents[node] = find(parents[node]);
    }
}
