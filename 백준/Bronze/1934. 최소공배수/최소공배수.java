import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// 11655번 ROT13
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] li = new int[n];
        for(int i=0; i<n; i++){
            int a = in.nextInt();
            int b = in.nextInt();

            int d = gcd(a, b);	// 최대공약수
            int t = a * b / d;
            li[i] = t;
        }
        for(int i=0; i<n; i++){
            System.out.println(li[i]);
        }


    }

    public static int gcd(int a, int b) {
        if(a<b){
            int temp =a;
            a = b;
            b = temp; //a는 b보다 커야하는게 유클리드 호제법의 조건
        }

        while (b != 0) {
            int r = a % b; // 나머지를 구해준다.

            // GCD(a, b) = GCD(b, r)이므로 변환한다.
            a = b;
            b = r;
        }
        return a;
    }

}