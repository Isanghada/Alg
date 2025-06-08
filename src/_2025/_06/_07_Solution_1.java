package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// https://www.acmicpc.net/problem/23749
// -
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        String card = in.readLine().replaceAll(" ", "");

        // 정답 출력
        sb.append(bfs(card));
        System.out.println(sb);
    }

    private static int bfs(String card) {
        Set<String> cardSet = new HashSet<>();

        Deque<String> deque = new LinkedList<>();
        deque.offerLast(card);
        cardSet.add(card);

        int count = 0;
        boolean flag = false;
        while(!deque.isEmpty()){
            int size = deque.size();
            while(size-- > 0){
                String cur = deque.pollFirst();
                if(isWin(cur)) {
                    flag = true;
                    break;
                }
                for(int n = 0; n < cur.length(); n++){
                    String next = cur.charAt(n) + cur.substring(0, n) + cur.substring(n+1, cur.length());
                    if(cardSet.contains(next)) continue;

                    deque.offerLast(next);
                    cardSet.add(next);
                }
            }
            if(flag) break;
            count++;
        }

        return count;
    }

    private static boolean isWin(String card) {
        int score = 0;
        for(int i = 0; i < card.length(); i += 2){
            char a = card.charAt(i);
            char b = card.charAt(i+1);

            if(a == 'O' && b =='X') score++;
            else if(a == 'X' && b =='O') score--;
        }

        return score > 0;
    }
}
