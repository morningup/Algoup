import java.util.Arrays;
import java.util.Scanner;

public class Main { //아파트 단지 이름이 n이면 n개의 아파트를 보유하고있음
    private static int dx[] = {0,0,1,-1}; //좌,우 설정
    private static int dy[] = {1,-1,0,0}; //상,하 설정
    private static int[] aparts = new int[25*25]; //총 제한 격자 크기
    private static int n;//x축 y축 크기
    private static int apartNum = 0; //아파트 단지 번호의 수
    private static boolean[][] visited = new boolean[25][25]; //방문여부
    private static int[][] map = new int[25][25]; //지도

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); //n입력받기

        map = new int[n][n]; //map크기 n*n 생성
        visited = new boolean[n][n]; //방문또한 동일하게 생성

        //전체 사각형 입력 받기
        for(int i=0; i<n; i++){
            String input = sc.next();
            for(int j=0; j<n; j++){
                map[i][j] = input.charAt(j)-'0'; //-'0' 정수형으로 변환
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == 1 && !visited[i][j]){ //dfs 방식
                    apartNum++; //아파트의 단지개수 구하기(아파트의 개수가 n이면 n단지)
                    dfs(i,j);
                }
            }
        }

        Arrays.sort(aparts); //문제의 조건이 단지수의 아파트들을 정렬해서 출력하는것이었음
        System.out.println(apartNum); //단지개수 출력

        for(int i=0; i<aparts.length; i++){
            if(aparts[i] == 0){
            }else{
                System.out.println(aparts[i]);
            }
        }
    }

    private static void dfs(int x, int y) { //아파트 단지수내에 아파트 개수찾기
        visited[x][y] = true; //방문했음을 표시
        aparts[apartNum]++; //aparts라는 배열의 해당 아파트의 개수만큼을 n이라 한다면 n단지라는 이름이 붙여지므로
        //

        for(int i=0; i<4; i++){//상하좌우 이동
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >=0 && ny >=0 && nx < n && ny < n){ //x좌표 y좌표가 0보다크고 주어진 n규격 보다 x좌표와 y좌표가 작아야함
                if(map[nx][ny] == 1 && !visited[nx][ny]){ //이후 그 좌표가 1로 연결됨과 동시에 visited가 false인것
                    dfs(nx,ny); //다시 dfs반복
                }
            }
        }
    }
}

/*bfs방식 
public class ApartNumberingBfs {
    private static int dx[] = {0,0,1,-1};
    private static int dy[] = {1,-1,0,0};
    private static int[] aparts = new int[25*25];
    private static int n;
    private static int apartNum = 0; //아파트 단지 번호의 수
    private static boolean[][] visited = new boolean[25][25]; //방문여부
    private static int[][] map = new int[25][25]; //지도

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n][n];
        visited = new boolean[n][n];

        //전체 사각형 입력 받기
        for(int i=0; i<n; i++){
            String input = sc.next();
            for(int j=0; j<n; j++){
                map[i][j] = input.charAt(j)-'0';
             }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    apartNum++;
                    bfs(i,j);
                }
            }
        }

        Arrays.sort(aparts);
        System.out.println(apartNum);

        for(int i=0; i<aparts.length; i++){
            if(aparts[i] == 0){
            }else{
                System.out.println(aparts[i]);
            }
        }

}

    private static void bfs(int x, int y) {
        //2차원 LinkedList를 가진 큐 선언
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x,y});
        visited[x][y] = true;
        aparts[apartNum]++;

        while(!que.isEmpty()){

            //x, y 값을 받아오기 위한 방법
            int curX = que.peek()[0];
            int curY = que.peek()[1];
            que.poll();

            for(int i=0; i<4; i++){
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                    if(map[nx][ny] == 1 && !visited[nx][ny]){
                        que.add(new int[]{nx,ny});
                        visited[nx][ny] = true;
                        aparts[apartNum]++;
                    }
                }
            }
        }
    }
}*/