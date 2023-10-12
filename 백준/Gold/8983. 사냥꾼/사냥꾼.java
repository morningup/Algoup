
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 100000 마리의 동물을 100000개의 사대에서 위치를 찾으려면 시간 복잡도가 폭발!! 이분탐색으로 적절한 사대를 찾는 것이 포인트
 * 
 * @category #이분탐색
 * @note http://jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1896&sca=99 와 같은 문제
 */
public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    static int M, N, L;
    static int[] guns;

    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));
        tokens = new StringTokenizer(input.readLine());
        M = Integer.parseInt(tokens.nextToken()); // 사대의 수: 100,000
        N = Integer.parseInt(tokens.nextToken());    // 동물의 수: 100,000
        L = Integer.parseInt(tokens.nextToken());// 사정 거리

        guns = new int[M];
        tokens = new StringTokenizer(input.readLine());
        for (int m = 0; m < M; m++) {
            guns[m] = Integer.parseInt(tokens.nextToken());
        }

        // 이진 탐색을 위해 총들의 위치를 정렬!!
        Arrays.sort(guns);
        
        int targetCnt = 0;
        
        for (int n = 0; n < N; n++) {
            tokens = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            // 어차피 안되는 경우 제거하기.
            if (y > L || guns[0] - L > x || guns[M - 1] + L < x) {
                continue;
            }
            
            // 쏠수 있는 사대를 찾아보자 - binarySearch
            int idx  =Arrays.binarySearch(guns, x);
            if(idx>=0) {
                targetCnt++;
            }else {
                idx = (idx + 1)*-1;
                // 왼쪽 사대에서 쏠수 있을까?
                if(idx-1>=0 &&  inCoverage(x, y, guns[idx-1])) {
                    targetCnt++;
                }else if(idx< guns.length && inCoverage(x, y, guns[idx])) {
                    targetCnt++;
                }
            }
        }
        System.out.println(targetCnt);
    }
    /**
     * 
     * @param ax 동물의 x
     * @param ay 동물의 y
     * @param x  사대의 x
     * @return
     */
    private static boolean inCoverage(int ax, int ay, int x) {
        return Math.abs(ax-x) + ay <=L;
    }
    
    
    // REMOVE_START
    private static String src = "4 8 4\r\n" +
                                "6 1 4 9\r\n" +
                                "7 2\r\n" +
                                "3 3\r\n" +
                                "4 5\r\n" +
                                "5 1\r\n" +
                                "2 2\r\n" +
                                "1 4\r\n" +
                                "8 4\r\n" +
                                "9 4";
    // REMOVE_END

}
