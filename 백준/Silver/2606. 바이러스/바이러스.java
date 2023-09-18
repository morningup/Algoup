import java.util.Scanner;

public class Main {
    static int map[][]; //2차원 배열 생성
    static boolean visit[]; //bfs dfs시 방문했던걸 체크하기 위한 체크 배열 생성
    static int n, m, v;//n은 컴퓨터 수 , m은 연결된 수 , v는 탐색시작할 정점
    static int count = 0; //몇대가 걸리는지 확인

    public static int dfs(int i) {
        visit[i] = true; //dfs에 들어온 순간부터 바이러스가 걸렸으므로 visited는 true로 설정

        for(int j=1; j<=n; j++) {
            if(map[i][j] == 1 && visit[j] == false) { //연결이 되었으면서 아직 감염이 되지않았다면
                count ++; //확인 되었으므로 감염된 컴퓨터의 숫자를 1추가
                dfs(j); //재귀적으로 j번째에서 다시 연결된것을 찾음 
                // (반복되어 j가 들어온것을 visited로 true로 설정후 1번째는 이미 감염이 되었으므로)
                //for문 반복하며 돌면서 감염이 안된것들을 찾음 마지막에 n번째 왔을때 까지 진행하게되면 더이상 진행할게 없으므로 for문을
                //빠져 나와 감염된 컴퓨터의 수인 count를 반환한다.
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();	// 컴퓨터 수(정점)
        m = scan.nextInt();	// 연결된 컴퓨터 쌍의 수(간선)
        v = 1;	// 탐색 시장할 정점의 번호(문제 내에서 1번부터 감염이라고 하였음)
        map = new int[n+1][n+1];	// 각 정점간 탐색 경로를 저장할 배열 (1번 부터 시작이므로 0공간 냅두고 1부터 채움)
        visit = new boolean[n+1];	// 정점의 탐색 여부 체크(1번 컴퓨터 이므로 0번째 공간 냅두고 1부터 채움)

        for(int i=0; i<m; i++) {//연결된 컴퓨터 쌍 입력받기
            int a = scan.nextInt();
            int b = scan.nextInt();
            map[a][b] = map[b][a]= 1; //양방향이므로 각 방향에 1추가(여기서 1의 의미는 연결이 되었다는 의미)
        }

        System.out.println(dfs(1));
        scan.close();
    }
}