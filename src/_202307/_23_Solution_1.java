package _202307;

import java.util.HashSet;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/43162?language=java
// 유니온-파인드 연습!
public class _23_Solution_1 {
    public int solution(int n, int[][] computers) {
        // 각자의 부모 정점 배열
        int[] parent = new int[n+1];
        // 본인 번호로 초기화
        for(int i = 1; i <= n; i++) parent[i] = i;

        // 연결된 정점에 대해 union 실행 (연결)
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                if (computers[i][j] == 1)   union(parent, i+1, j+1);
            }
        }
        
        // 같은 정점을 제외하기 위해 Set 사용
        Set<Integer> net = new HashSet<>();
        // 각 네트워크의 부모 정점 추가
        for(int i = 1; i <= n; i++){
            int v = find(parent, i);
            net.add(v);
        }
        // System.out.println(net);
        // net의 크기가 네트워크의 개수.(서로 다른 네트워크의 수)
        return net.size();
    }

    // 재귀를 통해 부모 정점이 자기 자신인 경우 찾기
    public static int  find(int[] parent, int x){
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    // a, b를 서로 연결시켜주는 작업.
    // 부모 정점을 똑같이 설정.
    public static void union(int[] parent, int a, int b){
        a = find(parent, a);
        b = find(parent, b);

        if(a <= b)  parent[b] = a;
        else parent[a] = b;
    }
}
