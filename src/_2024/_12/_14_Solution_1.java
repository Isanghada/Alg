package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2412
// - BFS : 방문한 좌표를 Set으로 확인하며 가능한 모든 좌표 탐색!
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 홈의 수
        int T = Integer.parseInt(st.nextToken());   // 목표 y 좌표

        // 홈 정보 입력
        Set<String> pointSet = new HashSet<>();
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            StringBuilder s = new StringBuilder();
            s.append(st.nextToken()).append(",").append(st.nextToken());
            pointSet.add(s.toString());
        }

        // 정답 출력
        sb.append(bfs(pointSet, T));
        System.out.println(sb.toString());
    }

    private static int bfs(Set<String> pointSet, int t) {
        Deque<int[]> deque = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        deque.offerLast(new int[]{0, 0, 0});
        visited.add("0,0");

        while(!deque.isEmpty()){
            int[] cur = deque.pollFirst();

            if(cur[1] == t) return cur[2];

            for(int x = cur[0]-2; x <= cur[0]+2; x++){
                for(int y= cur[1]-2; y <= cur[1]+2; y++){
                    if(x < 0 || y < 0) continue;

                    StringBuilder p = new StringBuilder();
                    p.append(x).append(",").append(y);
                    if(pointSet.contains(p.toString()) && visited.add(p.toString())){
                        deque.offerLast(new int[]{x,y,cur[2]+1});
                    }
                }
            }
        }


        return -1;
    }
}

