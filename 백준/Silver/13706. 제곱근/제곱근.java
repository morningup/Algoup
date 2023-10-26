import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger bi = new BigInteger(br.readLine()); //자리수가 큰 숫자는 BigInteger사용
        System.out.println(bi.sqrt()); //제곱근 구하기
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}

/*    public static int sqrt(int x)
    { 이분탐색으로 sqrt(제곱근 찾기)
        // 기본 케이스
        if (x < 2) {
            return x;
        }
 
        // `sqrt(x)`의 바닥을 저장하기 위해
        int result = 0;
 
        // `x`의 제곱근은 `x > 1`에 대해 `x/2`보다 클 수 없습니다.
        int start = 1;
        int end = x/2;
 
        while (start <= end)
        {
            // 중간 요소 찾기
            int mid = (start + end) / 2;
            long sqr = mid*mid;
 
            // `x`가 완전제곱수이면 `mid`를 반환합니다.
            if (sqr == x) {
                return mid;
            }
 
            // `mid×mid`가 `x`보다 작은 경우
            else if (sqr < x)
            {
                // 왼쪽 검색 공간을 버립니다.
                start = mid + 1;
 
                // 바닥이 필요하므로 결과 업데이트
                result = mid;
            }
 
            // `mid×mid`가 `x`보다 큰 경우
            else {
                // 올바른 검색 공간을 버립니다.
                end = mid - 1;
            }
        }
 
        // 결과 반환
        return result;
    }
 */