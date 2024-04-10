package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21315
// - 브루트포스 : 모든 경우 탐색
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 카드 개수
        int N = Integer.parseInt(in.readLine());

        // 섞은 결과의 카드 상태
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] input = new int[N];
        for(int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());

//        System.out.println(Arrays.toString(input));
//        System.out.println("-----------------");
        // 초기 카드 상태
        int[] card = new int[N];
        // 정답 여부 플래그
        boolean flag = false;
        // 처음 섞을 때의 K
        for(int k1 = 1; pow(2, k1) < N ; k1++){
            // 두번째 섞을 때의 K
            for(int k2 = 1; pow(2, k2) <=N; k2++){
                // card 상태 초기화
                for(int i = 0; i < N; i++) card[i] = i+1;
                // k1으로 섞기
                shuffle(card, k1);
//                System.out.println("========");
                // k2으로 섞기
                shuffle(card, k2);
//                System.out.println("========/");
                // 입력값과 동일하면 출력값 입력 후 종료
                if(isEquals(input, card)){
                    flag = true;
                    sb.append(k1).append(" ").append(k2);
                    break;
                }
            }
            if(flag) break;
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
    // 동일 여부 확인 함수
    private static boolean isEquals(int[] input, int[] card) {
        for(int i = 0; i < input.length; i++){
            if(input[i] != card[i]) return false;
        }
        return true;
    }
    // 섞기 함수
    private static void shuffle(int[] card, int k){
        // 섞는 범위 : 0 ~ range
        int range = card.length;
        // i의 최대값
        int limit = k+1;
        for(int i = 1; i <= limit; i++){
            // 위로 올리는 카드의 수 게산
            int count = pow(2, k - i + 1);
            // range범위에서 count만큼 위로 올리기
            swap(card, range, count);
            // range는 count로 갱신
            range = count;
//            System.out.println(Arrays.toString(card));
        }
    }
    // 카드 위치 변경 함수
    private static void swap(int[] card, int range, int count){
        Deque<Integer> deque = new LinkedList<>();
        // range의 뒤에서 count 개수의 카드를 위로 올리기
        for(int i = range-count; i < range; i++) deque.offerLast(card[i]);
        // range의 뒤에서 count 개수 제외하고 모두 뒤로 옮기기
        for(int i = 0; i < (range-count); i++) deque.offerLast(card[i]);

        int idx = 0;
        while(!deque.isEmpty()) card[idx++] = deque.pollFirst();
    }
    private static int pow(int a, int b){
        if(b == 0) return 1;
        if((b & 1) == 1) return a * pow(a, b-1);
        else return pow(a*a, (b >> 1));
    }
}
