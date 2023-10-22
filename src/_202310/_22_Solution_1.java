package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1615
// - 팬윅트리 : https://yabmoons.tistory.com/438, https://velog.io/@away0419/1615%EA%B5%90%EC%B0%A8%EA%B0%9C%EC%88%98%EC%84%B8%EA%B8%B0, https://www.acmicpc.net/source/66116758
// - 자바로는 메모리 초과가 발생한다는 듯하다..흠
public class _22_Solution_1 {
    public static int N, M, tree[];
    public static int[] edgeArr;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N + 1];
        edgeArr = new int[M];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            edgeArr[i] = start * 10000 + end;
        }
        Arrays.sort(edgeArr);

        int answer = 0;
        int cnt = 0;
        for (int edge : edgeArr) {
            int end = edge % 10000;
            answer += cnt++ - sum(end);
            update(end, 1);
        }

        sb.append(answer);
        // 정답 출력
        System.out.println(sb);
    }
    public static int sum(int index) {
        int res = 0;
        while (index > 0) {
            res += tree[index];
            index -= index & -index;
        }
        return res;
    }

    public static void update(int index, int diff) {
        while (index <= N) {
            tree[index] += diff;
            index += index & -index;
        }
    }
}
