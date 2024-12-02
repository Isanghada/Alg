package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13424
// - 구현 : 각 역에 대한 정보를 구성하고, 공사에 따라 갱신
//          링크드리스트 개념을 활용해 연결 정보 체크
// -------------------------------------------------
// - 어디서 시간 초과가 난거지..
public class _03_Solution_1 {
    // 노드 클래스 : 현재 역에 연결된 다음, 이전 역 번호!
    public static class Node{
        int next;   // 다음 역
        int prev;   // 이전 역

        public Node(int next, int prev) {
            this.next = next;
            this.prev = prev;
        }
        @Override
        public String toString(){
            return "[next="+next+", prev="+prev+"]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 역의 개수
        int M = Integer.parseInt(st.nextToken());   // 공사 횟수

        // 역 정보 입력
        int[] stations = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 연결 정보 입력
        Map<Integer, Node> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            int next = i + 1;
            if(next >= N) next = 0;
            int prev = i - 1;
            if(prev < 0) prev = N-1;
            map.put(stations[i], new Node(stations[next], stations[prev]));
        }

        // 공사 명령
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            String cmd = st.nextToken();    // 공사 명령
            // 다음 역 추가
            if(cmd.equals("BN")) sb.append(bn(map, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            // 이전 역 추가
            else if(cmd.equals("BP")) sb.append(bp(map, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            // 다음 역 폐쇄
            else if(cmd.equals("CN")) sb.append(cn(map, Integer.parseInt(st.nextToken())));
            // 이전 역 폐쇄
            else sb.append(cp(map, Integer.parseInt(st.nextToken())));

            sb.append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static int cp(Map<Integer, Node> map, int i) {
        Node targetNode = map.get(i);

        int remove = targetNode.prev;
        Node removeNode = map.get(remove);
        Node removePrevNode = map.get(removeNode.prev);

        targetNode.prev = removeNode.prev;
        removePrevNode.next = removeNode.next;

        map.remove(remove);
        if(map.size() == 1) {
            targetNode.next = i;
            targetNode.prev = i;
        }

        return remove;
    }

    private static int cn(Map<Integer, Node> map, int i) {
        Node targetNode = map.get(i);

        int remove = targetNode.next;
        Node removeNode = map.get(remove);
        Node removeNextNode = map.get(removeNode.next);

        targetNode.next = removeNode.next;
        removeNextNode.prev = removeNode.prev;

        map.remove(remove);
        if(map.size() == 1) {
            targetNode.next = i;
            targetNode.prev = i;
        }

        return remove;
    }

    private static int bp(Map<Integer, Node> map, int i, int j) {
        Node targetNode = map.get(i);
        Node targetPrevNode = map.get(targetNode.prev);
        map.put(j, new Node(i, targetNode.prev));

        targetNode.prev = j;
        targetPrevNode.next = j;

        return map.get(j).prev;
    }

    private static int bn(Map<Integer, Node> map, int i, int j) {
        Node targetNode = map.get(i);
        Node targetNextNode = map.get(targetNode.next);
        map.put(j, new Node(targetNode.next, i));

        targetNode.next = j;
        targetNextNode.prev = j;

        return map.get(j).next;
    }
}
