package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16498
// - 이분 탐색 : 하나의 카드를 결정하고 해당 카드 기준으로 가장 가까운 값을 이분 탐색으로 확인
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());   // A 카드의 수
        int B = Integer.parseInt(st.nextToken());   // B 카드의 수
        int C = Integer.parseInt(st.nextToken());   // C 카드의 수

        // 카드 정보
        List<Integer>[] cardList = new List[3];
        for(int i = 0; i < 3; i++){
            cardList[i] = new ArrayList<>();

            st = new StringTokenizer(in.readLine());
            while(st.hasMoreTokens()) cardList[i].add(Integer.parseInt(st.nextToken()));
            // 카드 정렬!
            Collections.sort(cardList[i]);
        }
        
        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // A 카드를 결정하고, B와 C를 이분 탐색으로 확인!
        for(int i = 0; i < A; i++){
            // 벌점이 없는 경우 종료!
            if(answer == 0) break;

            int[]cardAB = new int[2];

            // A 카드 선택!
            cardAB[0] = cardList[0].get(i);

            // B 카드 확인
            cardAB[1] = search(cardAB[0], cardList[1]);

            int[] cardC = new int[2];
            // A, B 기준 C 카드 확인!
            for(int card = 0; card < 2; card++){
                cardC[card] = search(cardAB[card], cardList[2]);

                int max = Math.max(cardAB[0], Math.max(cardAB[1], cardC[card]));
                int min = Math.min(cardAB[0], Math.min(cardAB[1], cardC[card]));
                answer = Math.min(answer, Math.abs(max-min));
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static int search(int target, List<Integer> cards) {
        int left = 0;
        int right = cards.size() - 1;
        int mid = (left + right) / 2;
        int result = cards.get(mid);
        while(left <= right){
            mid = (left + right) / 2;
            if(cards.get(mid) == target) return target;
            else if(cards.get(mid) < target) left = mid+1;
            else right = mid-1;
            if(check(cards.get(mid), target, result)) result = cards.get(mid);
        }
        return result;
    }

    private static boolean check(int card, int target, int cur) {
        return Math.abs(card - target) < Math.abs(cur - target);
    }
}
