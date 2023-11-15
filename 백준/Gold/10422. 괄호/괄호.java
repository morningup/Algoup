import java.io.*;

public class Main {
    //10422 동적계획법 (DP) _TOP_DOWN 방식
    public static void main(String[] args) throws IOException {
        //인덱스가 홀수일경우 올바른 문자를 만들수 없으므로 0이다.
        //i가 0이고 2인 경우에는 각각 1개씩밖에 문자를 만들수 없으므로 1을 집어넣는다.
        //i가 4인 경우는 (()) 경우와 , ()()인 경우 총 2가지의 경우의 수가 존재한다.
        //dp[4]의 경우는 dp[4] = dp[0] x dp[2] + dp[2] x dp[0]
        //L의 길이는 최대 5000

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        long[] dp = new long[5001]; //0부터 시작이므로 5000번째까지 만듬

        dp[0] = 1;
        dp[2] = 1;

        for(int i=2; i<2501; i++){ //홀수는 괄호의 개수가 만족하지않으므로 짝수만을 반복
            for(int j=0; j<i; j++){
                dp[i*2] += (dp[j*2] * dp[(i-1-j)*2]); //
                //ex) dp[4] = dp[0]*dp[2] + dp[2]*dp[0]
                //ex) dp[6] = dp[4]*dp[0] +dp[2]*dp[2] +dp[0]*dp[4]
                //이를 수식화 하게 되면 위와 같은 수식이 등장
                dp[i*2] %= 1000000007L; // 문제에서 나누라함
            }
        }

        for(int i=0; i<t; i++){
            int a= Integer.parseInt(br.readLine());
            System.out.println(dp[a]);
        }


    }




}