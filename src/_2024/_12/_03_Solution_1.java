package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23309
// - 구현 : 각 역에 대한 정보를 구성하고, 공사에 따라 갱신
//          링크드리스트 개념을 활용해 연결 정보 체크
// -------------------------------------------------
// - 시간 초과 : map을 통해 구현했을 때 시간 초과 발생 => remove 과정까지 넣은 것이 문제로 보임
//               배열로 변경하고, remove 과정없이 연결만 끊어주며 진행
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
    public static final int MAX = 1_000_000;
    public static Node[] nodeArr;
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
        nodeArr = new Node[MAX+1];
        for(int i = 0; i <= MAX; i++) nodeArr[i] = new Node(0, 0);

        for(int i = 0; i < N; i++){
            int next = i + 1;
            if(next >= N) next = 0;
            int prev = i - 1;
            if(prev < 0) prev = N-1;
            // nodeArr[stations[i]] = new Node(stations[next], stations[prev]);
            nodeArr[stations[i]].next = stations[next];
            nodeArr[stations[i]].prev = stations[prev];
        }

        // 공사 명령
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            String cmd = st.nextToken();    // 공사 명령
            // 다음 역 추가
            if(cmd.equals("BN")) sb.append(bn(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                // 이전 역 추가
            else if(cmd.equals("BP")) sb.append(bp(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                // 다음 역 폐쇄
            else if(cmd.equals("CN")) sb.append(cn(Integer.parseInt(st.nextToken())));
                // 이전 역 폐쇄
            else sb.append(cp(Integer.parseInt(st.nextToken())));

            sb.append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static int cp(int i) {
        int remove = nodeArr[i].prev;

        nodeArr[nodeArr[remove].prev].next = nodeArr[remove].next;
        nodeArr[nodeArr[remove].next].prev = nodeArr[remove].prev;
        return remove;
    }

    private static int cn(int i) {
        int remove = nodeArr[i].next;

        nodeArr[nodeArr[remove].prev].next = nodeArr[remove].next;
        nodeArr[nodeArr[remove].next].prev = nodeArr[remove].prev;

        return remove;
    }

    private static int bp(int i, int j) {
        int prev = nodeArr[i].prev;
        Node prevNode = nodeArr[prev];
        // nodeArr[j] = new Node(i, prev);
        nodeArr[j].next = i;
        nodeArr[j].prev = prev;

        nodeArr[i].prev = j;
        prevNode.next = j;

        return prev;
    }

    private static int bn(int i, int j) {
        int next = nodeArr[i].next;
        Node nextNode = nodeArr[next];
        // nodeArr[j] = new Node(next, i);
        nodeArr[j].next = next;
        nodeArr[j].prev = i;

        nodeArr[i].next = j;
        nextNode.prev = j;

        return next;
    }
}
