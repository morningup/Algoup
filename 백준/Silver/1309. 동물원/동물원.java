import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //동물원 문제 (dp)
    //2차원 중 dp[n][2]의 배열에서 해당 우리의 사자를 집어넣는다.
    //사자는 한 우리의 들어가면 해당 곳의 행의 마주보는 쪽과 열의 마주보는 쪽에는
    //사자를 둘 수 없다. (단 사자가 아무것도 없는 상태도 경우의 수 로 포함한다.)
    //사자의 수는 무한대까지 존재 (우리의 제한사항을 제외하고)
    //즉 우리수만 만족을 한다면 사자가 1마리들어가는 경우 2마리 들어가는 경우들을
    //다 추가시켜주어야함
    //그래서 누적을 안하고 진행을 하면 dp[n-1] ~ dp[0]까지 올라가야함
    //(이는 비효율적이기 때문에)누적을 진행한다.

//우리가 생성해야하는 배열은 3*(n+1)이어야 한다.
//3이 되어야 하는 이유 :사자가 0마리인 경우도 포함을 시켜줘야하기때문에
    //이 경우를 배열에 표시하기위해 총 3개의 배열이 필요하다.
//dp[n][0]은 해당 행에 사자가 존재하지 않음을 나타냄
    //모든 칸에 다들어올수있으므로 표현한다면
    //dp[n][0] = dp[n-1][0]+dp[n-1][1]+dp[n-1][2]
//dp[n][1]의 경우는 해당 칸에 인접하는 것들은 포함되지않아야함
    //이 경우 위의 존재하는 칸이 dp[n-1][0]이나 dp[n-1][2]가 되어야함
//dp[n][2]의 경우는 dp[n-1][0]이거나 dp[n-1][1]이어야함

//이러한 경우의 수를 누적시켜야한다.

//전체적인 수식으로 표현한다면
//(for문 0~2)
//dp[n][0] = dp[n-1][0]+dp[n-1][1]+dp[n-1][2]
//dp[n][1] = dp[n-1][0]+dp[n-1][2]
//dp[n]2] = dp[n-1][0]+dp[n-1][1]
//

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int MOD = 9901;
        int N = Integer.parseInt(br.readLine());

        // dp[i][j] : i번째 줄의 j번째 칸에 동물을 놓을 수 있는 경우의 수
        // j = 0 : 아무 동물도 놓지 않음
        // j = 1 : 첫 번째 칸에 동물을 놓음
        // j = 2 : 두 번째 칸에 동물을 놓음
        long[][] dp = new long[N + 1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1; // 기저 사례

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }

        long ans = (dp[N][0] + dp[N][1] + dp[N][2]) % MOD;
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}