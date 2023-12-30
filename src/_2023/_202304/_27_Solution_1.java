package _2023._202304;

public class _27_Solution_1 {
    public int[] solution(int[] sequence, int k) {
        // 정답 초기화
        int[] answer = new int[]{-1, -1};

        // 배열의 길이222
        int n = sequence.length;

        int left = 0;   // 왼쪽 포인터
        int right = 0;  // 오른쪽 포인터
        int curSum = sequence[0];   // 현재 누적합

        // 투 포인터로 모든 경우 탐색
        while(true){
            // k인 경우 정답 업데이트
            if(curSum == k){
                // 정답이 나오지 않은 경우
                // - 현재 부분 수열 입력
                if(answer[0] == -1) {
                    answer[0] = left;
                    answer[1] = right;
                }else{
                    // 부분 수열의 길이 비교
                    int curDiff = right - left;
                    int answerDiff = answer[1] - answer[0];
                    // 작은 경우 현재 부분 수열로 업데이트
                    if(curDiff < answerDiff){
                        answer[0] = left;
                        answer[1] = right;
                    // 같을 경우 left와 answer[0]을 비교하여 작은 부분 수열로 업데이트
                    }else if(curDiff == answerDiff){
                        if(left < answer[0]){
                            answer[0] = left;
                            answer[1] = right;
                        }
                    }
                }
            }
            // 수열의 끝까지 온 경우 종료
            if(left == n && right == n) break;
            // 목표값보다 작거나 같고 right가 n보다 작은 경우
            // - right 이동 : 부분 수열의 합 증가
            if(curSum <= k && right < n){
                right++;
                if(right < n) curSum += sequence[right];
            // 목표값보다 크고 left가 n보다 작은 경우
            // - left 이동 : 부분 수열의 합 감소
            }else if(curSum > k && left < n){
                if(left < n) curSum -= sequence[left];
                left++;
            // 아닐 경우 종료
            }else break;
        }
        return answer;
    }
}
