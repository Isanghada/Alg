package _202308;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/133500
// - 모든 등대를 하나씩 켜고 끌 때의 상태를 확인하는 방식 : 시간 초과!
// - 트리 형식으로 생각하고 DFS로 모든 노드에 대한 상태 확인
//   - 간선이 n-1개로 순환이 생길 수 없으므로 트리로 생각할 수 있음.
//   - 어떤 노드라도 루트로 생각할 수 있음.
//   - 리프 노드까지 내려간 후 연결된 노드들의 점등 여부를 체크하면 해결.
public class _31_Solution_1 {
//    public static int answer;
//    public static Map<Integer, List<Integer>> adjList;
//    public static int[] isPossible;
//    public int solution(int n, int[][] lighthouse) {
//        answer = Integer.MAX_VALUE;
//
//        isPossible = new int[n+1];
//        adjList = new HashMap<>();
//        for(int node = 1; node <= n; node++) adjList.put(node, new ArrayList<>());
//
//        for(int[] light : lighthouse){
//            adjList.get(light[0]).add(light[1]);
//            adjList.get(light[1]).add(light[0]);
//        }
//
//        getMinCount(1, n, 0);
//
//        return answer;
//    }
//
//    private void getMinCount(int node, int nodeCount, int count) {
//        boolean flag = true;
//        if(answer <= count) return;
//        for(int v = 1; v <= nodeCount; v++){
//            if(isPossible[v] == 0){
//                flag = false;
//                break;
//            }
//        }
//        if(flag){
//            answer = Math.min(answer, count);
//            return;
//        }
//
//        for(int next = node; next <= nodeCount; next++){
//            isPossible[next] += 1;
//            for(int v : adjList.get(next)){
//                isPossible[v] +=1;
//            }
//            getMinCount(next+1, nodeCount, count+1);
//            isPossible[next] -= 1;
//            for(int v : adjList.get(next)){
//                isPossible[v] -=1;
//            }
//            getMinCount(next+1, nodeCount, count);
//        }
//    }

    // 정답
    public static int answer;
    // 인접 리스트
    public static Map<Integer, List<Integer>> adjList;
    public int solution(int n, int[][] lighthouse) {
        // 정답 초기화
        answer = 0;

        // 인접 리스트 초기화
        adjList = new HashMap<>();
        for(int node = 1; node <= n; node++) adjList.put(node, new ArrayList<>());

        // 간선 연결
        // - 양방향이므로 양쪽 모두 연결
        for(int[] light : lighthouse){
            adjList.get(light[0]).add(light[1]);
            adjList.get(light[1]).add(light[0]);
        }

        // 1번 노드를 루트로하는 트리로 DFS 탐색
        getMinCount(1, -1);

        return answer;
    }

    private boolean getMinCount(int cur, int before) {
        // 리프 노드일 경우 before 점등을 위해 true 반환
        // - 단, 현재 노드가 루트가 아닐 경우! (before가 -1인 경우 루트!)
        if(adjList.get(cur).size() == 1 && before != -1) return true;

        // 점등 여부 플래그 : false로 초기화
        boolean flag = false;
        // 연결된 등대를 확인하여 점등 여부 체크
        for(int next : adjList.get(cur)){
            // before와의 점등 여부는 패스
            if(next == before) continue;
            // 점등 여부 체크
            // - true인 경우 flag 변경
            // - 모든 노드를 확인해야 하므로 중단(break)하지 않음
            if(getMinCount(next, cur)){
                flag = true;
            }
        }

        // cur 노드를 점등해야하는 경우
        if(flag){
            // 정답 증가
            answer++;
            // before는 점등하지 않아도 되므로 false 반환
            return false;
        }
        
        // cur 노드를 점등하지 않으므로 before 노드를 점등하기 위해 true 반환
        return true;
    }
}
