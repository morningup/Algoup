import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.Buffer;
import java.util.*;



public class Main {

    //자연수 n과 m이 주어짐  자연수 n의 m개의 수열을 모두 출력하는 코드
    public static int[] arr;//수열을 담기위한 arr배열 생성
    public static int N,M; //입력을 받기위해 N,M변수 생성
    public static StringBuilder sb = new StringBuilder(); //한꺼번에 수열을 출력하기위해 sb생성

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken()); 

        arr = new int[M]; //

        dfs(1,0);
        System.out.println(sb);

    }

    public static void dfs(int at, int depth){
        if(depth == M){
            for(int val: arr){
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        //신기하게 이 코드를 사용하면 오류가 발생 이유는 찾지못한상태
        /*        
        if(depth == M){
            for(int i=0; i<M; i++){
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
        }*/

        for(int i=at; i<=N; i++){
            arr[depth] = i;
            dfs(i+1 , depth+1);
        }
        //예를들어 n이 5이고 m이 2라면
        
        //depth가 0일때 위의 for문에서 나올수있는 i는 아래와 같이 5개이다.
        //      depth가 1일때는 i+1의 값이 재귀적으로 at에 담기므로 현재 담은 i보다 무조건 적으로 1이 크면서 n까지의 값들이 담기게 된다
        // 1    {2,3,4,5}  
        // 2    {3,4,5}
        // 3    {4,5}
        // 4    {5}
        // 5   //예외적으로 5는 dfs로 for문을 돌려고 하겠지만 5보다 큰 범위는 N보다 큰범위이기때문에 돌지않고 나온다.
        //이후 dfs를 다시 돌면서 if문에 걸려서 해당 수열들을 다 sb에 저장하고 return으로 종료된다.
        
    }



}
