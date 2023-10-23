import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.Buffer;
import java.util.*;



public class Main {

// (한 평면 2진트리(2차원 공간)) , (한 정육면체 옥트리(3차원공간))==쿼드트리
    //색종이 접기와 비슷한 유형의 문제 순서:왼쪽위(1구역) ->오른쪽위(2구역) ->왼쪽아래(3구역) ->오른쪽아래(4구역)
    //예제중 1구역은 전체가 0이므로 0으로 압축가능 그러나 나머지는 다시분할후 2구역의 경우 구역순서대로 0011로 압축가능
    //이후 3구역의 경우 0(0111)01로 구분가능 마지막 4구역은 전체가 1이므로 1로 된다 이어붙이면
    //(0(0011)(0(0111)01)1)이러한 표현방식이됨

    public static int N;
    public static int arr[][];
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        //StringTokenizer st ; 띄어쓰기가없어서 stringTokenizer사용불가

        for(int i=0; i<N; i++){
            //st = new StringTokenizer(br.readLine());
            String str = br.readLine();
            for(int j=0; j<N; j++){
                arr[i][j] = str.charAt(j) - '0'; //Integer.parseInt(st.nextToken()); 사용못함 ,그래서 str.charAt(j) - '0';이 코드를 통해 문자열 숫자를 정수형 숫자로 변환
                //정수형으로 변환하는 이유 : 내가 배열을 정수형으로 선언했기때문에
            }
        }//입력값 받기

        partition(0,0,N);

        System.out.println(sb);


    }

    public static boolean check(int x, int y, int size){
        int value = arr[x][y];
        for(int i=x; i<x+size; i++){
            for(int j=y; j<y+size; j++){
                if(value != arr[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void partition(int x, int y, int size){

        if(check(x,y,size)){
            sb.append(arr[x][y]);
            return; //해당 들어간 값들이 check에 만족하면 여기서 종료
        }

        int newsize = size/2;

        sb.append('(');

        partition(x,y,newsize);//1구역
        partition(x,y+newsize,newsize); //2구역
        partition(x+newsize, y, newsize);//3구역
        partition(x+newsize,y+newsize,newsize);//4구역

        sb.append(')');	// 해당 레벨이 끝나면 닫는괄호도 닫아준다.
    }















}