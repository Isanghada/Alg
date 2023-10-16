package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Semaphore;

// https://www.acmicpc.net/problem/2042
// - 세그먼트 트리 활용 : https://yoongrammer.tistory.com/103, https://cano721.tistory.com/38
//   - 시간복잡도 : O(MlongN)
//   - M : 값 변경 횟수
//   - N : 수의 개수
public class _16_Solution_1 {
    // 세그먼트 트리 클래스
    private static class SegmentTree{
        long[] tree;    // 완전 이진 트리 형태!
        int[] numberIndex;  // 각 숫자의 인덱스를 빨리 찾기 위해 사용.
        int treeSize;   // 트리 크기.

        // 배열들 초기화
        public SegmentTree(int arrSize){
            // 높이 계산
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
            // 배열 초기화
            this.numberIndex = new int[arrSize+1];
            this.treeSize = (int) Math.pow(2, h+1);
            tree = new long[treeSize];
        }
        // 초기 트리 설정
        // - arr : 숫자 배열
        // - node : 현재 노드
        // - start : 숫자 배열의 시작 인덱스
        // - end : 숫자 배열의 끝 인덱스
        public long init(long[] arr, int node, int start, int end){
            // 리프 노드일 경우
            if(start == end) {
                // 숫자 배열의 트리에 인덱스 설정
                this.numberIndex[start] = node;
                // 노드에 값 입력 및 반환
                return this.tree[node] = arr[start];
            }
            // 현재 노드의 값은 자식 노드의 합!
            return tree[node] = init(arr, node*2, start, (start+end) / 2) +
                    init(arr, node*2+1, (start+end)/2+1, end);
        }

        // 값 변경 : 리프 노드부터 루트 노드까지 값 변경
        // - node : 현재 노드
        // - diff : 값 변화량
        public void update(int node, long diff){
            this.tree[node] += diff;
            // 루트가 아닌 경우 부모 노드로 이동
            if(node > 1) update(node / 2, diff);
        }

        // 구간합 : 순회하며 구간합 계산
        // - node : 현재 노드
        // - start : 숫자 배열 시작 인덱스
        // - end : 숫자 배열 끝 인덱스
        // - left : 구간합 시작 인덱스
        // - right : 구간합 끝 인덱스
        public long sum(int node, int start, int end, int left, int right){
            // 범위를 벗어날 경우 0 반환
            if(left > end || right < start) return 0;
            // 구간이 포함될 경우 값 반환
            if(left <= start && end <= right) return this.tree[node];
            // 자식 노드로 이동하며 알맞은 값을 반환
            return sum(node*2, start, (start+end)/2, left, right) +
                    sum(node*2+1, (start+end) / 2 + 1, end, left, right);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        // 입력값!
        int N = Integer.parseInt(st.nextToken());   // 숫자의 개수
        int M = Integer.parseInt(st.nextToken());   // 값 변경 횟수
        int K = Integer.parseInt(st.nextToken());   // 구간합 구하는 횟수

        // 숫자 배열 초기화 : 인덱스 1부터 시작
        long[] numberArr= new long[N+1];
        for(int idx = 1; idx <= N; idx++) numberArr[idx] = Long.parseLong(in.readLine());

        // 세그먼트 트리 초기화
        SegmentTree segmentTree = new SegmentTree(N);
        // 초기값 설정
        segmentTree.init(numberArr, 1, 1, N);
//        System.out.println(Arrays.toString(segmentTree.tree));

        // 출력값 초기화
        StringBuilder sb = new StringBuilder();
        // 구간합 계산 횟수
        int count = 0;
        // 모든 구간합 계산 후 종료
        while(count < K){
            // 명령 입력
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());   // 명령 타입
            int b = Integer.parseInt(st.nextToken());   // 값 변경 혹은 구간 시작 인덱스
            long c = Long.parseLong(st.nextToken());  // 변경할 값 혹은 구간 끝 인덱스
            // a == 1인 경우 : 값 변경
            if(a == 1){
                // 변화량 계산
                long diff = c - numberArr[b];
                // 숫자 배열에서 값 변경
                numberArr[b] = c;
                // 트리의 값 변화!
                segmentTree.update(segmentTree.numberIndex[b], diff);
                continue;
            }
            // 트리에서 구간합 계산
            sb.append(segmentTree.sum(1, 1, N, b, (int)c)).append("\n");
            count++;
        }
        // 정답 출력
        System.out.println(sb);
    }
}
