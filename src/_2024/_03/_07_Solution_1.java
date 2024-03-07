package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2171
// - 브루트포스 : 대각선 위치에 있는 2개의 점을 선택하고 직사각형이 가능한지 확인
public class _07_Solution_1 {
    // 좌표 클래스
    public static class Node{
        int x;  // x 좌표
        int y;  // y 좌표
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return "[ x="+this.x+", y="+this.y+" ]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 좌표 개수
        int N = Integer.parseInt(in.readLine());

        // 좌표를 담을 Map : 탐색을 위해 활용
        Map<Integer, Set<Integer>> nodeMap = new HashMap<>();
        // 좌표 배열
        Node[] nodeArr = new Node[N];

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            // 좌표 입력
            st = new StringTokenizer(in.readLine());
            // 좌표 추가
            nodeArr[i] = new Node(Integer.parseInt(st.nextToken()),
                                  Integer.parseInt(st.nextToken())
                                 );
            // Map에 정보 추가
            if(!nodeMap.containsKey(nodeArr[i].x)) nodeMap.put(nodeArr[i].x, new HashSet<>());
            nodeMap.get(nodeArr[i].x).add(nodeArr[i].y);
        }
        // 정답 초기화
        long answer = 0;
        // 2개의 점을 선택하여 탐색
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                // 1. 2개의 좌표가 대각선에 위치하는지 확인
                // 2. 2개의 좌표를 조합한 좌표가 존재하는지 확인
                if(nodeArr[i].x != nodeArr[j].x &&
                        nodeArr[i].y != nodeArr[j].y &&
                        nodeMap.get(nodeArr[i].x).contains(nodeArr[j].y) &&
                        nodeMap.get(nodeArr[j].x).contains(nodeArr[i].y)
                ) {
//                    System.out.println(nodeArr[i]);
//                    System.out.println(nodeArr[j]);
//                    System.out.println("--------------");
                    answer++;
                }
            }
        }

        // 정답 출력
        // - 1개의 직사각형에 대해 총 2개가 입력되므로 2로 나눈 값을 반환
        sb.append(answer / 2);
        System.out.println(sb);
    }
}
