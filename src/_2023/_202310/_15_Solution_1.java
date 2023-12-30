package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1933
// - Tree Map 참고 : https://dev-coco.tistory.com/39
// - 각 좌표에서 가능한 최고 높이를 빠르게 탐색하기 위해 Tree Map 활용
//   - 높이별 빌딩의 수를 유지하며 0이 되는 경우 삭제
public class _15_Solution_1 {
    // 좌표별 상태를 담을 클래스
    public static class Node{
        int p;  // 좌표
        int h;  // 높이 : 빌딩 시작 좌표는 양수, 빌딩 끝 좌표는 음수 
        
        public Node(int p, int h){
            this.p = p;
            this.h = h;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202310/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 빌딩의 수
        int N = Integer.parseInt(in.readLine());
        
        // 빌딩 좌표를 담을 리스트
        // - 시작 좌표, 끝 좌표를 따로 추가
        List<Node> nodeList = new ArrayList<>();
        for(int cnt = 0; cnt < N; cnt++){
            // 빌딩 정보 입력
            st = new StringTokenizer(in.readLine());
            int L = Integer.parseInt(st.nextToken());   // 시작(왼쪽) 좌표
            int H = Integer.parseInt(st.nextToken());   // 높이
            int R = Integer.parseInt(st.nextToken());   // 끝(오른쪽) 좌표
            nodeList.add(new Node(L, H));   // 시작 좌표 추가
            nodeList.add(new Node(R, -H));  // 끝 좌표 추가
        }
        // 좌표 기준 오름차순 정렬, 높이 기준 내림차순 정렬
        Collections.sort(nodeList, (o1, o2) ->{
            int diff = o1.p - o2.p;
            return diff == 0 ? o2.h - o1.h : diff;
        });

        // 트리맵 생성 : 높이별로 빌딩의 수를 체크하고, 빠르게 최고 높이를 구하기 위해 사용
        // - key : 빌딩 높이 (현재 좌표에서 가능한 모든 건물 높이)!!
        // - value : 빌딩의 수
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());

        // 정답 리스트 초기화 : 좌표 순서대로 정보 추가
        List<Node> answerList = new ArrayList<>();
        // 모든 빌딩 정보 리스트 사용
        for(Node cur : nodeList){
            // 빌딩 시작 좌표인 경우
            if(cur.h > 0){
                // map에 해당 키 값이 없다면 생성
                if(!map.containsKey(cur.h)) map.put(cur.h, 0);
                // 건물 개수 증가
                map.put(cur.h, map.get(cur.h) + 1);
            // 빌딩 끝 좌표인 경우 (높이가 음수인 경우)
            }else{
                // key 값 계산
                int key = -cur.h;
                // 건물 개수 체크
                int val = map.get(key);
                // 1개만 남은 경우 : 현재 좌표에서 해당 높이는 사라지므로 map에서 삭제
                if(val == 1) map.remove(key);
                // 아닌 경우 : 빌딩의 개수 1 감소
                else map.put(key, val -1);
            }

            // map에 남은 빌딩의 높이가 없는 경우 : 빌딩의 높이가 0으로 바뀌는 경우
            if(map.size() == 0) answerList.add(new Node(cur.p, 0));
            // 남은 빌딩이 있는 경우 : map에 남은 최고 높이의 빌딩 높이 추가
            else answerList.add(new Node(cur.p, map.firstKey()));
        }

        // 이전 최고 높이 : 최소값으로 초기화
        int prev = Integer.MIN_VALUE;
        // 모든 정답 리스트의 값 활용
        for(Node cur : answerList){
            // prev와 값이 다른 경우
            // - 같은 경우는 변화가 없는 것이므로 출력하지 않음.
            if(prev != cur.h){
                // "좌표 높이" 값을 StringBuilder에 추가
                sb.append(cur.p).append(" ").append(cur.h).append(" ");
                // prev를 현재 높이로 업데이트.
                prev = cur.h;
            }
        }
        // 정답 출력
        System.out.println(sb);
    }
}
