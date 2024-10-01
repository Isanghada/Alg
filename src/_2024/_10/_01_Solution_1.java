package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2022
// - 참고 : https://velog.io/@kry-p/%EB%B0%B1%EC%A4%80-2022-%EC%82%AC%EB%8B%A4%EB%A6%AC-Java
// - 이분 탐색 : 너비를 기준으로 이분 탐색을 진행하며,
//               삼각형의 닮음을 이용해 교차점의 높이를 계산하여 C와 비교!
public class _01_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        double X = Double.parseDouble(st.nextToken());  // 사다리 X
        double Y = Double.parseDouble(st.nextToken());  // 사라리 Y
        double C = Double.parseDouble(st.nextToken());  // 교차점

        // left, right를 너비로 생각!
        double left = 0, right = Math.min(X, Y);
        // 차이가 0.001 이상인 경우 반복!
        while(right - left >= 0.001){
            // 현재 너비 계산!
            double mid = (left + right) / 2;
            // 피타고라스를 통해 X, Y의 높이 계산
            double hX = Math.sqrt(Math.pow(X, 2) - Math.pow(mid, 2));
            double hY = Math.sqrt(Math.pow(Y, 2) - Math.pow(mid, 2));

            // 교차점의 높이 계산
            // - 1. 닮은 공식 활용 : 닮은꼴의 탄젠트 값은 동일하다!
            //                       c/midX = hX/(midX+mdxY) => midX = c(midX+midY)/hX
            //                       c/midY = hY/(midX+mdxY) => midY = c(midX+midY)/hY
            // - 2. 너비 구하기 : mid = midX+midY
            //                        = c(midX+midY)/hX + c(midX+midY)/hY
            //                        = c(midX+midY)/hX + c(midX+midY)/hY
            //                        = c ( mid/hX + mid/hY )
            // - 3. 교차점 높이 구하기 : c = mid / (mid/hX + mid/hY)
            //                             = 1 / (1 / hX + 1 / hY)
            //                             = hX * hY / (hX+hY)
            double result = (hX * hY) / (hX+hY);

            // 직접 구한 교차점 높이 result와 제시된 값 C를 비교하여 left, right 갱신!
            // - result가 더 크면 result를 줄이기 위해 너비를 증가시킨다! => left 증가
            if(result >= C) left = mid;
            // - result가 더 작으면 result를 높이기 위해 너비를 감소시킨다! => right 감소
            else right = mid;
//            System.out.println(result+","+ mid);
//            System.out.println("[ "+left+", "+right+" ]");
        }
        // 정답 입력
        sb.append(String.format("%.3f", right));
        System.out.println(sb);
    }
}
