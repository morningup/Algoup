import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        /*Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int count = 0;
        이러면 절대 최소 예상 횟수를 구할수없음
        while(x > 1){
            if(x%3 ==0){
                x /=3 ;
                count +=1;
            }
            else if(x%2 ==0){
                x /=2;
                count +=1;
            }
            else{
                x -= 1;
                count +=1;
            }
        }*/
        //dp문제를 판단하는 기준 (ex:반복되는 연산으로 최소 연산횟수 찾기)
        //N에서 가능한 모든 연산과정을 수행하고 이 중에서 최소연산을 구해야함
        //이 문제와 같은 DP문제들은
        //Top down(1) 과 Botton up(2)방식으로 나뉜다.
        //1번 방식은 재귀문을 통해서 해결하고
        //2번 방식은 반복을 이용해서 해결한다.

        //재귀적 방식으로는 총 3가지가 있다. 1.%3 ==0 A[index/3] +1 , 2.%2 ==0 B[index/2] +1 , C[index-1] +1
        //예를들어 11이다 이 경우 2로도 3으로도 안나누어 지기때문에 C인 -1을 진행하고 10이되면 10에는 3이라는 최소횟수가 있기때문에
        //최소횟수 3과 거기에 1을뺀 횟수인 1을 더해 최소횟수는 4가된다.
        //[index] =min(A,B,C)
// index//n값 : 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
        //횟수: 0 1 1 2 3 2 3 3 2  3 4  3  4  4  4  4

        //이건 bottom up방식(반복 방식)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        int dp[] = new int[number+1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= number; i++){
            dp[i] = dp[i-1] + 1;
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
        }
        System.out.println(dp[number]);
        br.close();

        /*top down 방식(재귀 방식)
        import java.io.BufferedReader;
        import java.io.InputStreamReader;

        public class IncorrectAnswer {
            static int N;

            public static void main(String[] args) throws Exception{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                N = Integer.parseInt(br.readLine());

                dp = new int[N + 1]; //입력값보다 한개더 큰사이즈로 설정(0부터 시작이므로)

                int ans = solve(N, 0); //숫자 , 연산횟수

                System.out.println(ans);
            }

            private static int solve(int num, int cnt) {

                // 도착지 -> 현재까지의 count 반환
                if(num == 1) {
                    return cnt;
                }

                // 가장 많이 탐색해도 N을 넘어가지 않음
                int result1 = N+1;
                int result2 = N+1;
                int result3 = N+1;

                // 1, 2, 3번 선택한 결과의 최선 결과를 저장
                if(num % 3 == 0) {
                    result1 = solve(num/3, cnt+1);
                }
                if(num % 2 == 0) {
                    result2 = solve(num/2, cnt+1);
                }

                result3 = solve(num-1, cnt+1);


                // 각 결과값을 비교해 그 중에서의 최솟값 선택하고 저장
                int min = Math.min(result1, Math.min(result2, result3)); //result2 와 result3사이의 비교후 값을 다시 result1과 비교

                //	현재 위치에서의 최솟값을 반환
                return min;
            }
        }*/

    }
}