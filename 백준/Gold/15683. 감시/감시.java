import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int N, M;
	public static int[][] map;
	public static int[][] copyMap;
	public static int[] output;
	public static ArrayList<CCTV> cctvList;
	public static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 시계방향 순서 
	public static int[] dy = {0, 1, 0, -1};
	public static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		cctvList = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();

				if(map[i][j] != 0 &&  map[i][j] != 6) {
					cctvList.add(new CCTV(map[i][j], i, j));
				}
			}
		}

		output = new int[cctvList.size()]; // 순열을 담을 배열 
		permutation(0, cctvList.size());

		System.out.println(answer);
	}

	// DFS로 상하좌우 4방향 중에서 cctv의 총 개수, r만큼을 순서대로 뽑는 순열을 구함 
	public static void permutation(int depth, int r) {
		if(depth == r) {
			// Copy original 'map' array
			copyMap = new int[N][M];
			for(int i = 0; i < map.length; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
			}
						
			// cctv번호와 순열로 뽑혀진 방향에 맞는 상하좌우 방향 설정 
			for(int i = 0; i < cctvList.size(); i++) {
				direction(cctvList.get(i), output[i]);
			}
			
			// 사각 지대 구하기 
			getBlindSpot();

			return;
		}
		
		for(int i = 0; i < 4; i++) {
			output[depth] = i;
			permutation(depth+1, r);
		}
	}

	// 각 cctv 번호와 순열로 뽑혀진 방향에 맞게 감시 
	public static void direction(CCTV cctv, int d) {
		int cctvNum = cctv.num;

		if(cctvNum == 1) {
			if(d == 0) watch(cctv, 0); // 상 
			else if(d == 1) watch(cctv, 1); // 우 
			else if(d == 2) watch(cctv, 2); // 하  
			else if(d == 3) watch(cctv, 3); // 좌 
		} else if(cctvNum == 2) {
			if(d == 0 || d == 2) {
				watch(cctv, 0); watch(cctv, 2); // 상하 
			} else {
				watch(cctv, 1); watch(cctv, 3); // 좌우 
			}
		} else if(cctvNum == 3) {
			if(d == 0) {
				watch(cctv, 0); // 상우 
				watch(cctv, 1);
			} else if(d == 1) { 
				watch(cctv, 1); // 우하 
				watch(cctv, 2);
			} else if(d == 2) { 
				watch(cctv, 2); // 하좌 
				watch(cctv, 3);
			} else if(d == 3) { 
				watch(cctv, 0); // 좌상 
				watch(cctv, 3);
			}
		} else if(cctvNum == 4) {
			if(d == 0) {
				watch(cctv, 0); // 좌상우 
				watch(cctv, 1);
				watch(cctv, 3);
			} else if(d == 1) {
				watch(cctv, 0); // 상우하 
				watch(cctv, 1);
				watch(cctv, 2);
			} else if(d == 2) {
				watch(cctv, 1); // 좌하우 
				watch(cctv, 2);
				watch(cctv, 3);
			} else if(d == 3) {
				watch(cctv, 0); // 상좌하 
				watch(cctv, 2);
				watch(cctv, 3);
			}
		} else if(cctvNum == 5) { // 상우하좌
			watch(cctv, 0);
			watch(cctv, 1);
			watch(cctv, 2);
			watch(cctv, 3);
		}
	}
	
	// BFS로 방향에 맞게 감시 
	public static void watch(CCTV cctv, int d) {
		Queue<CCTV> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		queue.add(cctv);
		visited[cctv.x][cctv.y] = true;

		while(!queue.isEmpty()) {
			int nx = queue.peek().x + dx[d];
			int ny = queue.poll().y + dy[d];

			// 범위를 벗어나거나 벽을 만나면 끝 
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == 6) { 
				break;
			}

			if(copyMap[nx][ny] == 0) { 
				copyMap[nx][ny] = -1; // 빈칸이라면 감시할 수 있다는 의미로 -1 
				queue.add(new CCTV(cctv.num, nx, ny));
			} else { // 다른 cctv가 있거나 이미 감시된 칸이라면 
				queue.add(new CCTV(cctv.num, nx, ny)); // 그냥 통과 
			}
		}
	}
	
	// 사각 지대 구하기 
	public static void getBlindSpot() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		answer = Math.min(answer, cnt);
	}


}

class CCTV {
	int num;
	int x;
	int y;

	CCTV(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

/*import java.util.Stack;
import java.util.Scanner;

public class Main {
    //cctv는 5가지 유형이존재
    //1:한쪽만 감시 , 2:양방향감시(단 각 방향은 서로의 반대) 3.2방향 감시 단 감시하는 방향이 직각을 이루어야함  4.3방향감시, , 4.4방향 다갑시가능

    //0빈칸 , 1~5cctv유형 , 6벽

    //감시할수있는 영역 #로 표기(벽 존재시 벽을 뚫고서는 못봄)

    //입력: 매트릭스 구조를 입력후 해당 구조를 넣는다.(벽과 cctv)
    //출력: 최소의 사각지대의 개수 출력
    //모든 경우의 수를 파악하여 각 경우의 수중 최소의 개수만을 선정해야하기 때문에 "완전탐색" 이용

    //저장된 배열구조중 0을 값으로 가진 배열이 최소개인것이 우리가 원하는 답

    //문제 핵심
    //문제 설명중 2번유형의 cctv가 두대 , 5번유형 1대인 예제를 보면서 생각하면
    //각 cctv가 어디를 보는지에 따라 유형이 두가지로 나뉜다 .
    //2번유형의 경우 가로 새로 2가지 경우 5번유형은 4방향 감시하므로 회전시켜도 경우의수는 동일하다.(cctv감시 순서는 영향을 안줌)
    //그러므로 2*2*1 = 4 총 4가지의 경우의 수를 확인한뒤 그중에 최소개의 개수를 확인후 출력한다.

    static int n ,m ;
    static int[][] map = new int[8][8]; //최대 배열의 크기는 8*8
    static int camcnt ;//카메라 정보저장(몇번째 카메라 인지 확인)
    static int[] camtype = new int[8]; //카메라는 최대 8개 까지저장 배치된 각카메라의 유형들 담는 배열
    static int[] camrow =new int[8]; //카메라의 행위치
    static int[] camcol = new int[8]; //카메라의 열위치

    static final int inf = 987654321;

    //이 배열은 상하좌우의 값이 저장된다
    static int[] dr  ={0,-1,0,1}; // 0=우 , 1=상 , 2=좌 ,3=하 이는 cctv를 90도 이동시킨 순서와 같다.
    static int[] dc = {1 ,0 ,-1 ,0};

    //카메라 타입별로 만들어야하는 경우의 수를dcam이라는 배열에 저장 (이 배열에 1차원배열이 5개만 있어도 되는 이유는 각도를 변환시켜서 구할수있기때문)
    static int[][] dcam = {
            {1,0,0,0,4}, //1번유형  회전시 총 4가지의 경우의수등장 마지막에 경우의수 입력받음
            {1,0,1,0,2}, //2번유형   회전시 총 2가지의 경우의수등장
            {1,1,0,0,4}, //3번유형 회전시 총 4가지의 경우의수등장
            {1,1,1,0,4}, //4번유형 회전시 총 4가지의 경우의수등장
            {1,1,1,1,1} //5번유형 회전시 총 1가지의 경우의수등장
    };

    static void watch(int r, int c, int dir ) { //행 ,열 ,방향을 파라미터로 받음
        int srow = r , scol =c;
        for(;;){ //무한 루프
            int nr= srow + dr[dir];  //새로 뻗어나가는 감시 위치 행
            int nc= scol + dc[dir]; //새로 뻗어나가는 감시 위치 열
            //즉 dir이 0이라는 것은 dr의 0번째 위치에서 가져오고 dc도 0번째 위치 에서 가져옴 즉 우 방향을 의미

            if (nr <0 || nr >n-1  || nc< 0 || nc >m-1) return;  //주어진 해당 영역을 벗어나면 종료

            if (map[nr][nc] == 6) return; //벽을 만나도 감시를 종료

            //위의 조건을 다 해당한다면 감시영역
            map[nc][nr] = 7; //감시영역이라는 뜻으로 7을 대입
            srow =nr; //갱신(for문이 돌때마다 한 방향으로 계속 뻣어나갈것임)
            scol =nc;



        }
    }

    static void copymap(int[][] dst, int[][]src){ //목적지 , 소스
        for(int i =0; i<n; i++){
            for(int j=0; j<m; j++){
                dst[i][j] = src[i][j];// 소스에 있는 값이 dst에 있는 값으로 대입됨
            }
        }
    }

    static int solve(int pos){
        //재귀호출 이용
        if (pos == camcnt){ //모든 카메라의 경우의 수 를 파악을 완료했다는 조건
            int sum =0; //그때의 사각지대를 세서 반환을 해줌
            for(int i=0; i<n; i++)
                for(int j=0; j<m; j++)
                    if(map[i][j] == 0) sum++; //배열을 순회하며 0의 개수를 세줌
            return sum;
        }

        //watch함수를 진행하면서 변해버린 값들을 방지하기 위해 백업변수
        int[][] backup = new int[8][8];

        int ret = inf; //사각지대의 최대값은 어차피 8*8구조 배열이기때문에 64를 넘지못함 그래서 최대값은 64보다는 커야함 (우리는 최소값을 찾는거임(사각지대))

        //카메라 타입별로 모든 배치의 경우의 수를 만들어낼것임
        int type = camtype[pos] - 1; //인덱스를 0부터 쓰기위해서 -1을 해줌(입력받는 카메라는 1~5번까지이므로)

        //방향 90도 회전 //첫번째 for문에서 0,1,2,3은 얼마나 90 180 270 360을 의미
        for(int i=0; i<dcam[type][4]; i++){//dcam[type][4]에 경우 인덱스(4,2,4,4,1)를 넣어두었음(값이 1씩 증가할때마다 cctv의 보는 방향이 90도씩 회전)
            copymap(backup,map);//백업

            for(int j=0;  j<4; i++){ //4방향(우,상,하,좌)을 그려보는 시도를 할 것임
                if(dcam[type][j] == 1){//dcam의 배열안에 있는 값이 1일 경우에 만 그려야함
                    watch(camrow[pos] ,camcol[pos] ,(i+j)%4);//watch함수를 이용해 보는 시각을 리턴 받아 올것임 파라미터(행, 열 ,위치)
                    //i+j의 의미 :// dcam내의 방향이 90도 회전 했을 경우를 계산해주는 효과 4보다 큰 경우가 발생할 수도 있음 이를 방지하기위해 %4 연산을 진행(다시 볼것)
                }
            }

            ret = Math.min(ret,solve(pos+1));//지금 for문까지 현재의 camtype[pos]에 해당하는 카메라의 방향을 지정해주었기때문에 다음카메라의 방향을 지정할수있도록 1을 더함
            // 그리고 이때 우리가 ret의 값이 현재 나온 값보다 크다면 최소값 갱신

            copymap(map,backup); //복원

        }
        return ret;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        camcnt = 0;
        for(int i=0; i<n; i++){//행
            for(int j=0; j<m; j++){//열
                map[i][j] = sc.nextInt(); //대입
                if(map[i][j] >=1 && map[i][j]<=5){//카메라인지 확인
                    camtype[camcnt] = map[i][j]; //카메라 정보 배열에 현재 배열의 존재하는 카메라 유형을 담음
                    camrow[camcnt] = i;
                    camcol[camcnt++] = j; //이후 camcnt에 값을 담았으니 다음배열로 넘어가기 위해 camcnt 1증가
                }
            }
        }

        System.out.println(solve(0)); //여기서 0은 0번카메라를 의미한다

    }

}*/