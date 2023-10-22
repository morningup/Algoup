import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int white = 0; //하얀색 개수
    static int blue = 0; //파란색 개수
    static int[][] board; //정사가형 규격이 들어갈 배열생성

    public static void main(String[] args) throws IOException {

        //색종이 문제 (분할정복)
        //분할정복시 2가지의 개념이필요
        //1. 재귀에 대한 이해 , 2. 탐색에 대한 이해


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

            }
        } //주어진 입력값들로 배열에 담기
        partition(0,0,N);
        System.out.println(white);
        System.out.println(blue);

    }

    public static boolean colorcheck(int row, int col, int size){ //색깔판별(사각형영역이 전부다 같은색깔인지)
        int color = board[row][col]; //첫번째 부터 검사
        for(int i=row; i<row+size; i++){
            for(int j=col; j<col+size; j++){
                if(board[i][j] !=  color){
                    return false;
                }
            }
        }
        return true;
    }

    public static  void partition(int row, int col , int size){
        if((colorcheck(row,col,size))) { //color사이즈가 트루면
            if(board[row][col] == 0){
                white ++;
            }
            else{
                blue++;
            }
            return; //빈 리턴을 넣어주는 이유: 더 이상 위의 조건들을 모두 지났다면 셀 수 있는 white와 blue는 없는것을 의미하므로
            //return 지점에서 중단하고 끝낸다.
        }
        int newsize = size/2;
        partition(row,col,newsize); //2사분면
        partition(row,col+newsize,newsize); //1사분면
        partition(row+newsize,col,newsize); //3사분면
        partition(row+newsize , col+newsize , newsize); //4사분면
        //이후 재귀를 계속 반복
    }

}