import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        //dp 점화식
        //d[1] = 1,  d[2] = 2 , d[3]=3
        //d[i] = d[i-1] +d[i-2]
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int ans = 0;
        int[] arr = new int[1000];
        arr[0] = 1;
        arr[1] = 2;

        for(int i= 2; i<N; i++){
            arr[i] = arr[i-1] + arr[i-2];
            arr[i] = arr[i] % 10007;
        }

        System.out.println(arr[N-1]); //N-1은 배열의 0부터 넣어주었으므로

    }



}