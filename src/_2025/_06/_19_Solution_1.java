package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/6566
// -
public class _19_Solution_1 {
    static class Node implements Comparable<Node>{
        String group;
        int count;
        public Node(List<String> wordList){
            Collections.sort(wordList);

            StringBuilder sb = new StringBuilder();
            sb.append(wordList.get(0)).append(" ");
            for(int i = 1; i < wordList.size(); i++){
                if(wordList.get(i-1).equals(wordList.get(i))) continue;
                sb.append(wordList.get(i)).append(" ");
            }
            sb.append(".");

            this.group = sb.toString();
            this.count = wordList.size();
        }
        @Override
        public int compareTo(Node o){
            int diff = Integer.compare(o.count, this.count);
            return diff == 0 ? this.group.compareTo(o.group) : diff;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        Map<String, List<String>> map = new HashMap<>();
        while(true){
            String input = in.readLine();
            if(input == null || input.equals(null) || input.equals("")) break;
            // System.out.println(input);

            char[] sorted = input.toCharArray();
            Arrays.sort(sorted);
            String sortedInput = String.valueOf(sorted);

            if(!map.containsKey(sortedInput)) map.put(sortedInput, new ArrayList<>());
            map.get(sortedInput).add(input);
        }

        // System.out.println(map);
        List<Node> enneagramList = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            enneagramList.add(new Node(entry.getValue()));
        }

        Collections.sort(enneagramList);
        final int limit = Math.min(5, enneagramList.size());
        for(int i = 0; i < limit; i++){
            Node cur = enneagramList.get(i);
            sb.append("Group of size ").append(cur.count).append(": ");
                sb.append(cur.group).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
