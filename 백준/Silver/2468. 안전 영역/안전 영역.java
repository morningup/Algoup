import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

//물의 높이를 1부터 증가시켜서 최대 안전영역의 개수를 출력하라
class Main {


    private static boolean[][] checked;
    private static int[][] arr;
    private static int N;

    //방향
    static int[] dx = {1, 0 ,-1, 0}; //우 좌
    static int[] dy = {0, -1 ,0, 1}; //하 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        int maxheight = 0; //해당 배열내의 최고높이
        for(int i =0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(maxheight < arr[i][j]){
                    maxheight = arr[i][j];
                }
            }
        } //데이터 형 입력받음
        //비에 잠긴부분을 false로 변경하고

        int max=0;//높이

        //모든지역탐색
        for(int k=0; k<maxheight+1; k++){ //높이를 0부터 차근차근 올림
            checked = new boolean[N][N];
            int cnt =0;
            for(int i=0 ; i<N; i++ ){
                for(int j=0; j<N; j++){
                    if(!checked[i][j] && arr[i][j] > k){
                        cnt+=dfs(i,j,k); //해당 안전영역 탐색시작
                    }
                }
            }
            max = Math.max(max, cnt); //각 높이에 안전영역중 최대값 출력
        }
        System.out.println(max);
    }

    //k는 높이를 의미 :장마시 높아지는 높이
    static int dfs(int x , int y, int k) { //static 사용이유: 객체 생성안해도됨
        checked[x][y] = true;
        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx<0 || ny <0 || nx>N-1 || ny>N-1){ //규격왜를 벗어나면 그냥 지나침
                continue;
            }
            if(checked[nx][ny]) continue; //이미 방문한곳이라도 지나감
            if(arr[nx][ny] >k ){ //위의 제약조건을 다 통과하고 높이보다 배열안에 들은 값이 크다면
                dfs(nx,ny,k);
            }
        }
        return 1;
    }

    static int bfs(int x, int y, int height) { //bfs로 구현 (큐)
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        checked[x][y] = true;

        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int px = pos[0];
            int py = pos[1];

            for(int i=0; i<4; i++) {
                int nx = px +dx[i];
                int ny = py +dy[i];

                if(nx<0 || ny<0 || nx>N-1 || ny >N-1) continue;
                if(checked[nx][ny]) continue;
                if(arr[nx][ny]> height) { //만약에 이 조건에 해당하는 경우가 없는경우 모든 경우의 수를 탐색한것이므로 빠져나옴
                    checked[nx][ny] = true;
                    q.add(new int[] {nx,ny}); //현위치 다시 큐에 집어넣음
                }
            }
        }
        return 1;
    }




}