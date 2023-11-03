import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다
//mxn에 익은 토마토는 1 안익은토마토는 0 빈칸은 -1 
//익는 범위는 해당 토마토의 상하좌우를 의미 (1루에 퍼져나가는 기간)
//최소일수 bfs(큐 이용)
public class Main {
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1}; //토마토가 익을때 범위이동을 시켜주는 좌표 
    static int n, m; //mxn 행열생성
    static int[][] map; // 행열이 들어갈 2차원 배열 생성
    static Queue<int[]> q = new LinkedList<>(); //bfs이기때문에 queue생성

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m]; //입력값 받아 mxn배열 생성


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                //각 좌표에 알맞는 값들 대입
                if (map[i][j] == 1) { //1 익은 토마토를 중심으로 bfs 진행
                    q.add(new int[]{i, j}); 
                }
            }
        }

        System.out.println(bfs()); //bfs 결과값 진행
    }

    private static int bfs() {
        while (!q.isEmpty()) { //q를 모두 비울때까지
            int[] t = q.poll(); // 가장 먼저들어온거 빼서
            int x = t[0]; //해당 위치의 x위치
            int y = t[1]; //y위치
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; //방향이동진행
                int ny = y + dy[i];
                //범위를 벗어나면 continue문을 이용해 이번 반복문을 지나침
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (map[nx][ny] == 0) { 
                    //만약 0이라면 즉 안익은 토마토가 있다면
                    map[nx][ny] = map[x][y] + 1; //해당 토마토를 익은토마토로
                    //변경후 +1을 진행 여기서 +1은 일수를 의미한다
                    q.add(new int[]{nx, ny}); //큐에 해당 익은토마토 좌표 대입
                }
            }
        }

        int max = Integer.MIN_VALUE; //모두 다 익는데 걸리는 최소일수를 찾기위함이므로 
        //처음은 정수형의 최소값으로 시작
        if (checkZero()) { 
            return -1; // 처음 입력부터 모두다 익었다면 -1반환
        } else { //만약 안익은게 존재한다면
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {//모든 배열의 반복을 통해
                    if (max < map[i][j]) { //max에 초기값인 minvallue보다 map[i][j]이 크다면
                        max = map[i][j];  //max에 값에 min value 대입
                        //각 해당일수중 가장 큰 해당일수가 모두 익는데에 걸리는 최소일수가 된다.
                    }
                }
            }

            return max - 1; //왜냐면 익은토마토는 기본값이 1이므로 해당 기본값을 제하고 
            //구하는것이 최소일수가 된다.
        }


    }

    private static boolean checkZero() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) //토마토가 모두다 익었다면 true 반환하는 함수
                    return true;
            }
        }
        return false;
    }
}