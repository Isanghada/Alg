package _202304._14;

public class Solution1 {
    public long solution(int k, int d) {
        long answer = 0;

        for(int i = 0; i <= d; i += k){
            long d2 = d * d;
            long i2 = i * i;
            int top = (int) Math.sqrt(d2 - i2);
            answer += (top / k) + 1;
        }

        return answer;
    }
}
