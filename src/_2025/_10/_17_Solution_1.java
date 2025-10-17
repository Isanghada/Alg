package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

// https://www.acmicpc.net/problem/32133
// -
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new TreeMap<>();

        for(int n = 0; n < N; n++){
            char[] orders = in.readLine().toCharArray();
            StringBuilder order = new StringBuilder();

            for(char ch : orders){
                order.append(ch);
                String cur = order.toString();

                if(!map.containsKey(cur)) map.put(cur, 0);
                map.put(cur, map.get(cur)+1);
            }
        }


        boolean flag = false;
        int answer = 10000;
        String answerOrder = "";
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String key =  entry.getKey();
            int count = entry.getValue();

            if(count <= K){
                if(key.length() < answer){
                    flag = true;
                    answer = key.length();
                    answerOrder = getAnswerOrder(key);
                }
            }

        }

        if(flag) sb.append(answer).append("\n").append(answerOrder);
        else sb.append(-1);

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static String getAnswerOrder(String key) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < key.length(); i++){
            if(key.charAt(i) == 'R') result.append('S');
            else if(key.charAt(i) == 'S') result.append('P');
            else result.append('R');
        }

        return result.toString();
    }
}
