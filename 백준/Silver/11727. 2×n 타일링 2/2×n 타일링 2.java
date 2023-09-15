import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        //dp 점화식 (2*1과 2*2타일 두개 사용가능)
        //이전 문제는 모든타일을 2*1만 사용하는 반면 이제는 2*2타일도 사용할수있음
        //즉 n=3이라면 나타나는 가짓수는 처음의 식인 3개에다가
        //2*1타일을 2*2로도 표현할 가짓수가 n-2 즉 n이 1인곳에 2배가 늘어나므로
        //(n-1) + 2*(n-2) = 3 + 2*1 = 5가 나오게 된다.
        //여기서 2*1은 n=1일때 타일에 2*2의 여분이 남을때 이전과달리 2*1을 두장붙이는것뿐아니라 2*2도 붙일수있다.
        //d[1] = 1,  d[2] = 2 , d[3]=3
        //d[i] = d[i-1] +d[i-2]
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[1000];
        arr[0] = 1;
        arr[1] = 3;

        for(int i= 2; i<N; i++){
            arr[i] = arr[i-1] + (arr[i-2]*2);
            arr[i] = arr[i] % 10007;
        }

        System.out.println(arr[N-1]); //N-1은 배열의 0부터 넣어주었으므로

    }



}