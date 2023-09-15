import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
//bfs를 쓰는 이유: 최단경로를 구할때 목적지를 찾자마자 최단경로임이 보장되어 종료 가능함
//큐를 이용하므로 스택오버플로우에서 자유로울수 있음
//초기 큐에 시작좌표를 넣고 반복과정 시작
    //1.0,0에서 이동가능한 좌표의 모든 경로를 큐에 집어넣음
    //계속 반복하여 도착지에 도달하면 해당 경로의 count를 알 수 있다.
    //dfs는 자신의 depth를 알고있어야하기 때문에 이게 약간 복잡할수있음(재귀 or stack사용)
    //bfs는 해당 깊이에서 갈 수 있는 노드 탐색을 마친후 다음 깊이로 넘어가기때문에 목적지에 나오는 depth가 곧 찾고자 하는 depth이다.
    //시작: 상하좌우 네방향의 인접한 칸을 본다. 인접한 칸의 숫자가 1이면서 아직방문하지않았다면 큐에 삼입한다(반복). 종료지점(n,m)을 찾으면 종료한다.
    //센스: 모든부분이 상하좌우가 뚫려있지는 않기때문에 굳이 1로서 정의하는게 아닌 바로 1인부분을 거쳤다면 인접한 부분은 바로 큐에 집어넣고 2로 바꾼다.
    //즉 배열에 있는값에 거리를 대입한다.(센스가 있는 이유: 맵 내에 있는 값들은 쓰레기값이 없음(거리값을 저장하므로))
    //depth == 거리
    //1.상하좌우 설정하기(dx,dy)
    //2.2차원 행렬 a(행렬좌표 n,m) , visited배열
    //3.bfs 함수 구현 3-1:큐 자료구조에 시작노드 삼입 , 3-2:visited 배열에 현재 노드 방문 기록하기
    //반복(큐가 비어있을때 까지)
    //조건(유효한 좌표시) && 조건(이동 할 수 있는 칸이면서 방문하지 않은 노드) == 배열에 방문 기록하기, 배열에 depth를 현재노드의 depth+1하기 ,큐에 데이터 삽입하기(add연산)
    //
    //https://www.youtube.com/watch?v=2QVfsI55AVo 해당 영상을 참고
    static int[] dx = {0,1,0,-1}; //dx[0]와 dy[0]의 값으로(0,1)은 하 이라는 의미 이처럼 각 배열의 dx dy를 정의하면
    static int[] dy = {1,0,-1,0}; //하 우 상 좌
    static boolean [][] visited;
    static int[][] A;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken()); //입력값 받기
        A = new int[N][M]; //2차원 배열 생성
        visited = new boolean[N][M]; //A와 크기가 동일한 방문했는지에 대한 배열 생성
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            //이번에는 그냥 string값으로 받음 , 공백이없으므로 ex)01101010으로 들어올것임
            String line = st.nextToken();// 한줄 다 받음
            for(int j=0; j<M; j++){
                A[i][j] = Integer.parseInt(line.substring(j,j+1)); //이후 한줄 데이터를 하나씩 끊어서 데이터를 넣어줌
            }
        }
        BFS(0,0);
        System.out.println(A[N-1][M-1]); //N-1 과 M-1은 문제의 1,1이 0,0으로 수행되는 코드로 작성하였기 때문

    }

    private static void BFS(int i, int j){
        Queue<int[]> queue = new LinkedList<>(); //1차원 배열 즉 값이 2개들어가는 큐 생성
        queue.offer(new int[] {i,j});//큐에 입력좌표 넣어줌
        while(!queue.isEmpty()){//큐가 빌때까지 반복
            int now[] = queue.poll(); // 큐에 들어온 좌표를 뽑으면 그 좌표가 바로 현재 좌표
            visited[i][j] =true; //큐에 나갔다가 들어온것은 방문했다는 것으로 인지하여 true로 변경
            for(int k=0; k<4; k++){//상하좌우 탐색
                int x =now[0] + dx[k];
                int y =now[1] + dy[k]; //x와 y의 좌표를 덧셈과 뺄샘이 가능함(즉 상하좌우 표시가능)
                if(x>=0 && y>=0 && x<N && y <M){
                    if(A[x][y]!=0 && !visited[x][y]){//0이어서 갈수없거나 방문했던곳이 아니라면
                        visited[x][y] = true; //방문했다는 기록을 위해 true로 바꾸고
                        A[x][y]  =A[now[0]][now[1]]+1; //현 x와y좌표의 값을 현재 있는 곳의 값에 1을 더한다.
                        queue.add(new int[]{x,y}); //이후 큐에 넣는다.
                    }
                }
            }
        }
    }


}
