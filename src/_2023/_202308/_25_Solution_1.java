package _2023._202308;

// https://school.programmers.co.kr/learn/courses/30/lessons/150367
// - 재귀를 통한 풀이!
// - 이진 트리를 만들 수 없는 경우의 조건 찾는 것이 중요
public class _25_Solution_1 {
    public int[] solution(long[] numbers) {
        // 정답 배열 초기화
        int[] answer = new int[numbers.length];

        // 레벨별 노드의 개수를 빠르게 확인하기 위해 배열에 입력
        // - couyntArr[1] : 1레벨일 때의 노드의 수
        // - 레벨 n일때 노드의 수는 (2^n - 1)
        int[] countArr = new int[7];
        countArr[1] = 2;
        // - 2의 제곱승 먼저 계산
        for(int idx = 2; idx <= 6; idx++) countArr[idx] = countArr[idx-1] * 2;
        // - 이후 -1을 통해 노드 개수 완성
        for(int idx = 1; idx <= 6; idx++) countArr[idx] -= 1;

        // 모든 숫자에 대해 진행
        for(int idx = 0; idx < numbers.length; idx++){
            // 2진수 변환
            String binary = Long.toBinaryString(numbers[idx]);
            // 포화 이진 트리로 만들기 위해 2진수의 길이가 노드의 수 이상일 경우만 확인
            for(int countIdx = 1; countIdx <= 6; countIdx++){
                if(countArr[countIdx] >= binary.length()){
                    // 0을 추가하기 위해 StringBuilder 활용
                    StringBuilder sb = new StringBuilder();
                    
                    // 노드의 수와 2진수의 길이 차 계산
                    int diff = countArr[countIdx] - binary.length();
                    // 차이가 2진수 길이보다 길 경우 종료
                    // - 루트는 무조건 1이여야 이진 트리로 만들 수 있으므로!
                    if(diff > binary.length() ) break;

                    // 차이만큼 0 추가
                    while(diff > 0) {
                        diff--;
                        sb.append("0");
                    }
                    // 2진수 추가
                    sb.append(binary);
                    // 2진수 변환
                    binary = sb.toString();
                    
                    // 포화 이진 트리 가능한지 확인
                    answer[idx] = isPossible(binary, 0, binary.length()-1);
                    // 가능할 경우 종료
                    if(answer[idx] == 1) break;
                }
            }
        }
        return answer;
    }

    // 포화 이진 트리 확인 함수 : 가능하다면 1, 불가능하다면 0 반환
    // - binary : 포화 이진 트리를 확인할 문자열
    // - start : 시작 인덱스
    // - end : 끝 인덱스
    private int isPossible(String binary, int start, int end) {
        // 리프 노드일 경우 1 반환
        if(start == end) return 1;

        // mid : 루트 노드 인덱스
        int mid = (start + end) / 2;
        // left : mid의 왼쪽 자식 노드 인덱스
        int left = (start + mid - 1) / 2;
        // right : mid의 오른쪽 자식 노드 인덱스
        int right = (end + mid + 1) / 2;

        // 루트가 0이고 자식 노드 중 1이 있으면 불가능 하므로 0 반환
        if(binary.charAt(mid) == '0' && (binary.charAt(left) == '1' || binary.charAt(right) == '1')) return 0;
        
        // 자식 서브 트리 중 불가능한 경우가 있다면 0 반환
        if(isPossible(binary, start, mid-1) == 0 || isPossible(binary, mid+1, end) == 0) return 0;

        // 모두 가능하면 1 반환
        return 1;
    }
}
