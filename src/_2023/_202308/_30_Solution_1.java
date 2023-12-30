package _2023._202308;

// https://school.programmers.co.kr/learn/courses/30/lessons/12920
// - 우선순위큐를 사용해 Min Heap을 활용하면 된다! => 효율성 시간 초과!
// - 이분 탐색을 활용하는 방식 => "파라매트릭 서치"라고 하는 방식
public class _30_Solution_1 {
//    // 코어 상태 클래스
//    public class Core implements Comparable<Core>{
//        int num;    // 코어 번호
//        int end;    // 작업 종료 시간
//
//        public Core(int num, int end){
//            this.num = num;
//            this.end = end;
//        }
//
//        // 작업 종료 시간 기준 오름차순, 코어 번호 기준 오름차순
//        // - 작업이 일찍 끝나는 코어부터 작업 시작
//        // - 여러개의 코어가 있다면 앞의 코어부터 시작
//        @Override
//        public int compareTo(Core o) {
//            int result = this.end - o.end;
//            return result == 0 ? this.num - o.num : result;
//        }
//    }
//    public int solution(int n, int[] cores) {
//        // 정답 초기화
//        int answer = 0;
//
//        if (n <= cores.length) { // n이 core의 개수보다 작을 경우 n번째 코어를 리턴
//            return n;
//        }
//
//        n -= cores.length;
//
//        // 우선순위큐로 (작업 종료 시간 -> 코어 번호) 기준 Min Heap으로 사용
//        PriorityQueue<Core> priorityQueue = new PriorityQueue<>();
//        // 코어 수 만큼 추가
//        // cores로 작업 처리 시간을 쉽게 확인하기 위해 코어 번호는 0부터 시작
//        for(int idx = 0; idx < cores.length; idx++){
//            priorityQueue.offer(new Core(idx, cores[idx]));
//        }
//
//        // 작업 개수만큼 루트 노드 기준 작업 진행
//        int time = 0;
//        for(;n > 0; n--){
//            // 루트 코어(작업을 진행할 코어) 반환
//            Core cur = priorityQueue.poll();
//
//            time = Math.max(time, cur.end);
//            // 작업 진행시 종료 시간 업데이트하여 다시 추가
//            priorityQueue.offer(new Core(cur.num, time+cores[cur.num]));
//            answer = cur.num;
//        }
//        return answer+1;
//    }
    public int solution(int n, int[] cores) {
        // 정답 초기화
        int answer = 0;

        // 작업 최소 시간, 최대 시간
        int min = 0;
        // - 가장 작은 값으로 n개의 작업 하는 것이 최대!
        // - 따라서 어떤 값이라도 n개의 작업을 하는 것이 최대값이 될 수 있음.
        int max = cores[0] * n;

        // 최종 종료 시간
        int time = 0;
        // 작업할 수 있는 개수
        int totalCount = 0;

        // 시간 기준 이분 탐색 진행
        while(min <= max){
            // mid 계산
            int mid = (min+max) / 2;

            // mid 시간 기준 작업량 계산
            int count = getCount(mid, cores);

            // n개 이상의 작업을 할 수 있다면
            if(count >= n){
                // max 변경
                max = mid - 1;
                // time 업데이트
                time = mid;
                // totalCount 업데이트
                totalCount = count;
            // 작업을 전부 진행할 수 없는 경우
            }else{
                // min 업데이트
                min = mid + 1;
            }
        }

        // 작업할 수 있는 개수(totalCount)에서 작업할 개수(n)를 제외해준다!
        // - totalCount가 n보다 큰 경우는 작업을 진행하지 않아도 되는 후순위 코어도 진행하기 때문
        // - 따라서, 넘어서는 만큼 후순위의 코어를 제외하고 첫 코어가 정답!
        totalCount -= n;
        for(int i = cores.length-1; i>=0; i--){
            // time에서 나누어 떨어지는 경우가 작업을 진행하는 후보군!
            if (time % cores[i] == 0) {
                // 후순위 코어가 아닐 때가 정답
                if (totalCount == 0) {
                    answer = i+1;
                    break;
                }
                // 후순위 코어일 경우 totalCount만 감소
                totalCount--;
            }
        }
        return answer;
    }

    private int getCount(int time, int[] cores) {
            int count = cores.length;

            for(int core : cores) count += (time / core);
            return count;
    }
}
