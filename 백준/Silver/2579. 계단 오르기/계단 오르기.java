import java.util.Scanner;

public class Main {

    static Integer dp[];
    static int arr[];
    //계단오르기 (탑다운 or 바텀업)
    //규칙
    /*계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
      연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
      마지막 도착 계단은 반드시 밟아야 한다.*/

    //경우의수:n-3을 밟고 n-1번 계단을 밟고 n으로 오는 경우 ,  n-2번을 밟고 n으로 오는 경우 2가지가 존재
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt(); //계단수 입력

        dp = new Integer[N + 1]; // 재귀 dp는 int[] 배열이 아닌 Integer[] 객체배열을 쓸 것이다.
        //n개의 계단이라고 했을때 각 계단의 최대값
        arr = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = in.nextInt(); //계단번호에 들어가있는 값들 대입
        }

        dp[0] = arr[0];	// 디폴트값이 null이므로 0으로 초기화 해주어야한다.
        dp[1] = arr[1]; //

        // N 이 1이 입력될 수도 있기 때문에 예외처리를 해줄 필요가 있다.
        if(N >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        System.out.println(find(N));

    }

    //※ DP와 메모이제이션 개념을 혼용해서 사용하는 경우도 있는데, 엄밀히 말하면
    // 메모이제이션은 이전에 계산된 결과를 일시적으로 기록해 놓은 넓은 개념을 의미하므로 DP와는 별도의 개념이다

    static int find(int N) {
        // 아직 탐색하지 않는 N번째 계단일 경우
        if(dp[N] == null) { //각 계단내에 최대값이 안나온경우 해당 계단번호의 dp의 값은 null 그러면 아래의 재귀 진행
            dp[N] = Math.max(find(N - 2), find(N - 3) + arr[N - 1]) + arr[N];
            //arr[N-1]가 find가 아닌 이유:
            // find(N-1)을 호출하여 N = 5 값을 넣었다고 가정하면 DP[4] 이 메모이제이션이 되어있다고 할 때
            // 이전 계단(N-3)을 밟은 상태인지 알 수 있는가 알 수 없기때문에 직접 배열에서 가져오는것이다.
        }

        return dp[N];
    }

    /*import java.util.Scanner; 바텀업 방식

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] stair = new int[301];
        int[] score = new int[301];
        for (int i = 1; i <= N; i++)
            stair[i] = sc.nextInt();

        score[1] = stair[1];
        score[2] = stair[1] + stair[2];
        score[3] = Math.max(stair[1], stair[2]) + stair[3];

        for (int n = 4; n <= N; n++) {
            score[n] = Math.max(score[n - 3] + stair[n - 1], score[n - 2]) + stair[n];
        }

        System.out.println(score[N]);
    }
}*/

}
