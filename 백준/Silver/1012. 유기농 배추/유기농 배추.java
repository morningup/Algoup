import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { //유기농 배추 BFS방식으로 이용

    static int M, N, K;// 가로 새로 배추가 심어진 위치의 개수
    static int[][] cabbage; //배추밭 규격
    static boolean[][] visit; //방문 배열(배추밭 규격)
    static int count; //배추 흰지렁이 개수
    static int[] dx = { 0, -1, 0, 1 }; //왼쪽 오른쪽
    static int[] dy = { 1, 0, -1, 0 }; //위 아래

    static void bfs(int x, int y) {
        Queue<int[]> qu = new LinkedList<int[]>(); //BFS를 구현하여 사용할 큐생성(이 큐에는 인트형 배열 자료가 들어감)
        qu.add(new int[] { x, y }); //큐에 배추가 있는 X와Y좌표 넣기

        while (!qu.isEmpty()) { //큐가 비어있지 않을때까지
            x = qu.peek()[0];//큐의 첫번째중 첫번째 값 참조 x축
            y = qu.peek()[1];//큐의 첫번째중 두번째 값 참조 y축
            visit[x][y] = true; //이후 해당 x와 y좌표에 있는 배열의 값을 true로 설정(현재 위치) 
            // 이것으로 초기 들어오는 visited배열의 좌표도 true 형태를 취할수있다.
            qu.poll(); //이후 큐에서 꺼냄
            for (int i = 0; i < 4; i++) {
                int cx = x + dx[i]; //상하좌우 이동
                int cy = y + dy[i];

                if (cx >= 0 && cy >= 0 && cx < M && cy < N) { //이동중 x와y의 값이 0보다 크고 규격M과N보다 작은경우
                    if (!visit[cx][cy] && cabbage[cx][cy] == 1) { //방문이 안되어있으면서 현재좌표에 배추가있으면
                        qu.add(new int[] { cx, cy }); //큐에다가 좌표대입후
                        visit[cx][cy] = true; //(다음에 이동한 위치) 상하좌우중 이동가능한것은 모두 집어넣음
                    }
                }

            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //BR생성

        int T = Integer.parseInt(br.readLine()); //테스트 케이스 개수 입력받기

        for (int c = 0; c < T; c++) {//테스트 케이스 만큼 반복
            StringTokenizer st = new StringTokenizer(br.readLine()," "); //공백에 따라 입력받음
            M = Integer.parseInt(st.nextToken());//가로
            N = Integer.parseInt(st.nextToken());//새로
            K = Integer.parseInt(st.nextToken());//배추심어진 위치
            cabbage = new int[M][N]; // 배추밭 규격
            visit = new boolean[M][N]; //배추밭 규격에서 동일한 방문 규격
            count=0; //흰지렁이 개수

            for (int i = 0; i < K; i++) { //배추심어진 개수 만큼 반복
                st = new StringTokenizer(br.readLine()," "); //심어진 위치의 X와 Y좌표에서 자르기
                int p1 = Integer.parseInt(st.nextToken()); //x좌표
                int p2 = Integer.parseInt(st.nextToken()); //y좌표
                cabbage[p1][p2] = 1; //해당 위치를 1로 설정

            }

            for (int i = 0; i < M; i++) {//가로 새로의 규격만큼 반복문 진행
                for (int j = 0; j < N; j++) {
                    if (cabbage[i][j] == 1 && !visit[i][j]) { //규격내에 1이 군데군데 섞여있기때문에 이를해결하기위해
                        bfs(i, j);//반복문으로 구성 이후 bfs로 군데군데에 심어진 여러개들을 파악
                        count++;
                    }
                }
            }
            System.out.println(count);
        }

    }
}