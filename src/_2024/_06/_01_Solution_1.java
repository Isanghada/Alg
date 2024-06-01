package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26260
// - 재귀 : 포화 이진 검색 트리로 만들기 위해 값을 정렬하여 중앙값을 루트로 설정!
public class _01_Solution_1 {
    public static StringBuilder ANSWER;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ANSWER = new StringBuilder();

        StringTokenizer st = null;
        // 노드의 개수
        int N = Integer.parseInt(in.readLine());

        st = new StringTokenizer(in.readLine());
        int index = 0;          // 가려진 노드 인덱스
        // 초기 노드 값 입력
        int[] A = new int[N];
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if(A[i] == -1) index = i;
        }
        // 가려진 노드의 값 갱신!
        A[index] = Integer.parseInt(in.readLine());

        // 노드 정렬
        Arrays.sort(A);

        // 범위의 중앙값을 루트로 설정!
        // 왼쪽, 오른쪽 범위를 나누어 재귀로 탐색
        postOrder(A, 0, A.length-1);

        // 정답 입력
        System.out.println(ANSWER);
    }

    private static void postOrder(int[] a, int left, int right) {
        if(left == right) ANSWER.append(a[left]).append(" ");
        else{
            int mid = (left + right) / 2;
            postOrder(a, left, mid-1);
            postOrder(a, mid+1, right);
            ANSWER.append(a[mid]).append(" ");
        }
    }
}
