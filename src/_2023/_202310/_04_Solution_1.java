package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/1132
// - 그리디 알고리즘 : 모든 알파벳에 대한 자리수의 합을 계산해 큰 수에 큰 값 할당
// - 단, 모든 숫자를 사용하고 0이 되면 안되는 수가 가장 작을 경우 할당되는 값을 변경
public class _04_Solution_1 {
    // 각 알파벳에 대한 정보를 담을 클래스
    public static class Node{
        char alp;   // 알파벳
        long count;  // 자릿수 카운트
        boolean nonZero;    // 0 불가 여부

        public Node(char alp, int count, boolean nonZero){
            this.alp = alp;
            this.count = count;
            this.nonZero = nonZero;
        }

        @Override
        public String toString() {
            return "["+alp+", " + count +", " + nonZero+"]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202310/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 숫자의 수
        int N = Integer.parseInt(in.readLine());

        // 자리수 계산
        // - key : 알파벳
        // - value : Node 클래스
        Map<Character, Node> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            // 알파벳 입력
            String input = in.readLine();
            // 모든 값에 대해 자리수 계산하여 추가
            for(int j = 0; j < input.length(); j++){
                // 해당 알파벳이 처음인 경우 map에 추가
                if(!map.containsKey(input.charAt(j))){
                    map.put(input.charAt(j), new Node(input.charAt(j), 0, false));
                }
                // 첫 자리수인경우 0이 불가능이므로 nonZero 변경
                if(j == 0) map.get(input.charAt(j)).nonZero = true;
                // 자리수 계산하여 증가
                int digit = (input.length() - j - 1);
                map.get(input.charAt(j)).count += getCount(digit);
            }
        }

        // Map의 value를 배열로 변환
        Node[] nodeArr = map.values().stream().toArray(Node[]::new);
        // count 기준 오름차순 정렬
        Arrays.sort(nodeArr, (o1, o2) -> {
            long diff = o2.count - o1.count;
            if(diff > 0) return 1;
            else if (diff == 0) return 0;
            else return -1;
        });

//        System.out.println(Arrays.toString(nodeArr));
        // 가장 count가 작은 값 확인
        int last = nodeArr.length-1;
        // - 모든 숫자를 사용하고 마지막 값이 0이 불가능한 경우
        if(last == 9 && nodeArr[last].nonZero){
            // 변경할 최소값 탐색
            int changeIndex = last;
            // 0이 가능한 가장 작은 값의 인덱스 탐색
            while(nodeArr[changeIndex].nonZero){
                changeIndex--;
            }
            // 인접한 값과 바꾸며 changeIndex를 마지막까지 이동
            for(int idx = changeIndex; idx < last; idx++){
                Node temp = nodeArr[idx];
                nodeArr[idx] = nodeArr[idx+1];
                nodeArr[idx+1] = temp;
            }
        }

        // 정답 초기화 : 값이 클 수 있으므로 long으로 할당
        long answer = 0;
        // 할당할 값 : 9부터 차례로 할당
        long num = 9;
        // 각 알파벳에 대해 값을 할당해 계산
        for(Node node : nodeArr){
            answer += node.count * (num--);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static long getCount(int digit) {
        long count = 1;
        while(digit-- > 0) count *= 10;
        return count;
    }
}
