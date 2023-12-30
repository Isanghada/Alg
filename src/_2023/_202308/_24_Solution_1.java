package _2023._202308;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/138475?language=java
// - 각 숫자가 나오는 횟수는 배열로 계산
//   - 횟수별 오름차순, 숫자별 내림차순 정렬
// - starts를 (index, num)으로 해주고 num 기준 오름차순.
//   - 횟수 배열의 끝에서 탐색 시작.
//   - num보다 작은 값들은 넘기며 진행.
// - 다른 사람 풀이보다가 DP 레전드 풀이봄 : 굉장하다
//   - 1 ~ e까지 => e부터 최대값을 결정하며 진행.
//   - num - 1에서의 최대값은 num-1 혹은 num
//   - 2가지 경우만 있으므로 이를 비교하며 최대값을 결정해나감.
public class _24_Solution_1 {
    class Value implements Comparable<Value>{
        int num;
        int count;

        public Value(int num, int count){
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Value o) {
            int v = this.count - o.count;
            return v == 0 ? o.num - this.num : v;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[")
                    .append(this.num)
                    .append(", ")
                    .append(this.count)
                    .append("]\n");
            return sb.toString();
        }
    }

    public class Index implements Comparable<Index>{
        int index;
        int num;

        public Index(int index, int num){
            this.index = index;
            this.num = num;
        }

        @Override
        public int compareTo(Index o) {
            return this.num - o.num;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[")
                    .append(this.index)
                    .append(", ")
                    .append(this.num)
                    .append("]\n");
            return sb.toString();
        }
    }
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        List<Index> indexList = new ArrayList<>();
        for(int idx = 0; idx < starts.length; idx++){
            indexList.add(new Index(idx, starts[idx]));
        }
        Collections.sort(indexList);
        System.out.println(indexList);

        Value[] arr = new Value[e+1];
        for(int i = 0; i <= e; i++){
            arr[i] = new Value(i, 0);
        }

        for(int i = 1; i <= e; i++){
            for(int j = i; j <= e; j++){
                long value = (long)i * j;
                if (value > e) break;
                if(i == j) arr[(int)value].count += 1;
                else arr[(int)value].count += 2;
            }
        }

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        int maxIndex = arr.length - 1;
        for(Index cur : indexList){
            while(arr[maxIndex].num < cur.num){
                 maxIndex--;
            }
            answer[cur.index] = arr[maxIndex].num;
        }

        return answer;
    }
    /* DP 풀이 방식
    - 레전드; 사람들 어케 이런 생각함?
    public int[] solution(int e, int[] starts) {
        int[] res = new int[starts.length];

        int[] count = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                count[i * j]++;
            }
        }

        int[] max = new int[e + 1];
        max[e] = e;
        for (int i = e - 1; i >= 1; i--) {
            if (count[i] >= count[max[i + 1]]) {
                max[i] = i;
            } else {
                max[i] = max[i + 1];
            }
        }

        for (int i = 0; i < res.length; i++) {
            res[i] = max[starts[i]];
        }
        return res;
    }
     */
}
