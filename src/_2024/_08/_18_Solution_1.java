package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1195
// - 구현 : 기어가 맞물리는 모든 경우 탐색
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 기어 정보 입력
        String up = in.readLine();
        String down = in.readLine();

        // 정답 출력
        // - 킥다운 계산
        sb.append(kinkDown(up, down));
        System.out.println(sb.toString());
    }

    private static int kinkDown(String up, String down) {
        int result = up.length() + down.length();
        for (int i = -down.length(); i <= up.length(); i++) {
            boolean isPossible = true;
            for (int j = 0; j < up.length(); j++) {
                isPossible &= ((j - i < 0 || j - i >= down.length()) ?
                        0 : down.charAt(j - i) - '0') + up.charAt(j) - '0' <= 3;
            }
            if (isPossible) {
                result = Math.min(
                        result,
                        Math.max(i + down.length(), up.length()) - Math.min(i, 0)
                );
            }
        }
        return result;
    }

}
