package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/5052
// - 참고 : https://innovation123.tistory.com/116#%EA%B5%AC%ED%98%84-1
// - 트라이 : 트라이 알고리즘을 통해 접두사를 포함하는 경우 체크
public class _01_Solution_1 {
    // 노드 클래스
    static class Node{
        Map<Character, Node> child; // 자식 노드
        boolean end;                // 단어 끝 여부
        public Node(){
            this.child = new HashMap<>();
            this.end = false;
        }
    }
    // 트라이 클래스
    static class Trie{
        Node root;  // 루트 노드
        public Trie(){
            this.root = new Node();
        }
        // 단어 삽입! : 접두사를 포함하는지 체크
        public boolean insert(String word){
            Node node = this.root;

            // 단어의 모든 문자 체크
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                // 문자가 자식노드에 없다면 추가!
                node.child.putIfAbsent(ch, new Node());
                // 자식 노드로 변경
                node = node.child.get(ch);
                // 단어의 끝인 경우 종료
                if(node.end) break;
            }
            // 접두사를 포함하는 경우 false 반환
            if(node.end) return false;

            // 접두사를 포함하지 않는 경우
            // => 단어의 끝이므로 단어 끝 체크
            node.end = true;

            return true;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            int N = Integer.parseInt(in.readLine());

            // 단어 입력
            String[] wordArr = new String[N];
            for(int i = 0; i < N; i++) wordArr[i] = in.readLine();

            // 단어를 정렬하여 차례대로 체크
            Arrays.sort(wordArr);

            // 트라이 초기화
            Trie trie = new Trie();
            // 일관성 여부
            boolean flag = true;
            for(String word : wordArr){
                // 단어를 trie에 추가하며 일관성 여부 체크
                if(!trie.insert(word)){
                    flag = false;
                    break;
                }
            }

            sb.append((flag) ? "YES" : "NO").append("\n");
        }

        // 정답 입력
        System.out.println(sb);
    }
}
