import java.util.Scanner;

public class Main {

    static Integer dp[];
    static int arr[];
    //계단오르기 (탑다운 or 바텀업)
    //규칙
    /*계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
      연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
      마지막 도착 계단은 반드시 밟아야 한다.*/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt(); //계단수 입력

        dp = new Integer[N + 1]; //정수형 배열 생성
        arr = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = in.nextInt(); //계단번호에 들어가있는 값들 대입
        }

        dp[0] = arr[0];	// 디폴트값이 null이므로 0으로 초기화 해주어야한다.
        dp[1] = arr[1]; //

        if(N >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        System.out.println(find(N));

    }

    static int find(int N) {
        // 아직 탐색하지 않는 N번째 계단일 경우
        if(dp[N] == null) {
            dp[N] = Math.max(find(N - 2), find(N - 3) + arr[N - 1]) + arr[N];
        }

        return dp[N];
    }

}