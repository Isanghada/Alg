package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/33542
// - 이분 탐색 : 왼쪽 과녁을 기준으로 오른쪽 과녁에 대해 이분 탐색 진행!
public class _05_Solution_1 {
    // 노드 클래스
    static class Node implements Comparable<Node>{
        int score;  // 점수
        int num;    // 과녁 번호
        public Node(int score, int num){
            this.score = score;
            this.num = num;
        }
        // 점수 기준 오름차순, 과녁 번호 기준 오름차순 정렬
        @Override
        public int compareTo(Node n){
            if(this.score == n.score) return this.num - n.num;
            return this.score - n.score;
        }
        public String toString(){
            return "[score="+score+", num="+num+"]";
        }
    }
    // 최대값
    static final int MAX = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());   // 동현이 점수
        int B = Integer.parseInt(st.nextToken());   // 주원이 점수

        // 점수 차이
        int diff = A - B;

        // 주원이가 점수가 높은 경우 "-1 -1" 반환
        if(diff < 0) sb.append("-1 -1");
        else{
            // 과녁 개수
            int N = Integer.parseInt(in.readLine());

            int[] lefts = new int[N+1];     // 왼쪽 과녁
            int[] rights = new int[N+1];    // 오른쪽 과녁

            // 오른쪽 과녁 정렬
            Node[] sortedRights = new Node[N+1];
            sortedRights[0] = new Node(0, 0);

            // 과녁 정보 입력
            for(int i = 1; i <= N; i++){
                st = new StringTokenizer(in.readLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                lefts[i] = left;
                rights[i] = right;
                sortedRights[i] = new Node(right, i);;
            }

            // 오른쪽 과녁 정렬
            Arrays.sort(sortedRights);
            // System.out.println(Arrays.toString(sortedRights));

            int minScore = MAX; // 최소 점수
            // 0번은 과녁에 쏘지 않은 것을 의미
            int minLeft = 0;    // 최소 점수일 때 왼쪽 과녁 번호
            int minRight = 0;   // 최소 점수일 때 오른쪽 과녁 번호
            // 모든 왼쪽 과녁을 기준으로 탐색
            for(int left = 0; left <= N; left++){
                // 주원이가 이기기 불가능한 경우 패스
                if( lefts[left] + sortedRights[N].score <= diff ) continue;
                // 왼쪽 과녁만으로 이기는 경우
                if(lefts[left] > diff){
                    if(minScore > lefts[left]){
                        minScore = lefts[left];
                        minLeft = left;
                        minRight = -1;
                    }
                }else{
                    // 이분 탐색을 통해 왼쪽 과녁을 기준으로 최소 점수인 오른쪽 과녁 선택
                    Node right = binarySearch(sortedRights, diff - lefts[left] + 1, N, left);
                    // System.out.println(diff - lefts[left] + 1+", "+"left="+lefts[left]+"("+left+"), right="+right.score+"("+right.num+")");
                    // 왼쪽, 오른쪽 과녁이 다른 경우만 계산
                    if(right.num != left){
                        // 현재 점수
                        int score = lefts[left] + right.score;
                        // 최소 점수에 따라 갱신!
                        if(score < minScore){
                            minScore = score;
                            minLeft = left;
                            minRight = right.num;
                        }
                    }
                }
                // System.out.println(minScore+", "+minLeft+", "+minRight);
            }

            // 불가능한 경우 : No 반환
            if(minScore == MAX) sb.append("No");
            // 가능한 경우 : 과녁 번호 반환
            else sb.append(minLeft == 0 ? -1 : minLeft).append(" ").append(minRight == 0 ? -1 : minRight);
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static Node binarySearch(Node[] sortedRights, int target, int n, int exceptionNum) {
        int left = 0;
        int right = n;
        while(left < right){
            int mid = (left + right) / 2;
            if(sortedRights[mid].score < target) left = mid + 1;
            else right = mid;
        }

        if(sortedRights[left].num == exceptionNum){
            if(left > 0 && sortedRights[left-1].score >= target) left = left-1;
            else if(left < n) left = left + 1;
        }

        return sortedRights[left];
    }
}
