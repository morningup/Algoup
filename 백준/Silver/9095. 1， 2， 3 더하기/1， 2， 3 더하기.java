import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[] dp = new int[11]; //패턴
        dp[1] =1; //초기 값 초기화
        dp[2]=2;
        dp[3]=4;

        for(int j=4;j<=10;j++) { // 4부터 반복
            dp[j] = dp[j-3] + dp[j-2] + dp[j-1]; // 점화식
        }

        for(int i=0;i<t;i++) {
            int n = sc.nextInt();

            System.out.println(dp[n]);
        }
    }



}