
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//완전탐색, 시뮬레이션
//모든 지역을 탐색하기 위해 dfs이용
public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;

    // CCTV 타입 별로 바라볼 수 있는 방향의 쌍들을 미리 정해두자.
    static int [][][] watchDirs = { //바라 볼 수 있는 방향
            {}, // 0번 타입
            {{0}, {1},{2},{3}}, // 1번 타입(상,하,좌,우) 4개
            {{0, 1},{2,3}}, // 2번타입 (상하, 좌우) 2개
            {{0, 2},{2, 1},{1, 3},{3, 0}},// 3번 타입 (상좌 , 좌하 , 하우 ,우상) 4개
            {{0, 1, 2},{0, 1, 3},{0, 2, 3},{1, 2, 3}}, //4번 타입(상하좌 , 상하우 , 상좌우 , 하좌우)
            {{0, 1, 2, 3}}// 5번 타입 (상하좌우) 1개

    };

    static int [][] deltas = {{-1, 0},{1, 0},{0, 1},{0, -1}};//4방향을 표현(우,하,좌,상)

    static int R, C, MIN;
    static int [][] map;

    // 관리할 CCTV 목록
    static List<CCTV> cctvs; //cctv 객체 생성

    // 초기 상태에서 관리해야 할 지점의 개수
    static int whiteArea = 0;

    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));

        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        map = new int [R][C]; //맵그리기
        cctvs  =new ArrayList<>();
        for(int r=0; r<R; r++) {
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<C; c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
                //CCTV 발견!!
                if(map[r][c] >0 && map[r][c]<6) { //cctv발견
                    cctvs.add(new CCTV(r, c, map[r][c])); //cctv 목록에 넣어줌
                }else if(map[r][c]==0) { //사각지대인경우
                    whiteArea++; //사각지대 개수를 담는 변수 1증가
                }
            }
        }
        // System.out.println("white area: "+whiteArea+", cctv : "+cctvs);
        // 입력 완료
        MIN = Integer.MAX_VALUE; //정수형 최대값을 기본적으로 담음
        dfs(0, 0); //모든 지점 탐색을 위해 저장된 cctv리스트의 0번부터
        System.out.println(MIN);
    }

    static void dfs(int cctvIdx, int clearSpot) { //cctvidx의 초기값은 cctv리스트의 0번째 부터 시작 ,clearSpot: 이전까지 scan으로 발견한 지점들
        //cctv를 한대가져온뒤 그 유형이 볼 수있는 방향 모두 탐색
        // 기저조건
        if(cctvIdx == cctvs.size()) {  //cctvidx가 cctv.size와 동일하다는 것은 리스트안에 담긴 모든 cctv유형을 탐색했다는 의미
            // 그럼 맨끝까지온 현 시점에서의 최소 사각
            MIN = Math.min(MIN, whiteArea - clearSpot); //기존에 저장된 최솟값 min과 전체 지역 개수-탐지 지역 개수를 비교하여 후자가 작다면 min을 갱신
            //min값 계산이 여기에 들어가야하는 이유: 모든 cctv유형의 각 다른 방향을 지난 뒤에 결과적인 min값을 정리할수있기때문
            //만약 2개의 cctv(1번유형 2개)가 존재한다면 상, 하 방향으로 했을때의 min값을 얻을려고 하기때문
            return ;
        }


        // CCTV를 한대 가져온다. --> 그녀석이 볼 수 있는 방향으로 회전시켜본다.!!
        CCTV cctv = cctvs.get(cctvIdx);
        for(int d=0; d<watchDirs[cctv.t].length; d++) { //방향의 유형수만큼 반복하여 각 유형일때 발생하는 모든 유형의 사각지대의 경우의수 탐지
            // 이때의 방향은?
            int [] dirs = watchDirs[cctv.t][d]; //  {0,1}과 같은 각 방향을 dirs리스트에 대입
            // dirs 방향으로 찾아보기. --> 맵 오염
            int scaned = scan(cctv, dirs, -1); //한쪽으로 쭉 스캔을 하게되면 발견되는 녀석들을 변수에 저장

            dfs(cctvIdx+1, clearSpot + scaned); //1번째 파라미터:cctv리스트에 담아둔 다음 유형으로 이동 , 2번째 파라미터:이전까지 발견한 clearspot에 이번의 발견한 scan을 더하여 다음것 진행

            // dirs 방향에 대해서 맵 원복..
            scan(cctv, dirs, 1);
        }
    }

    /**
     *
     * @param cctv - 사용할 CCTV
     * @param dirs - 그 CCTV가 보는 방향 정
     * @param flag - 오염(-1), 원복(+1) 오염시키는지 원복시키는 확인
     * @return 현재의 CCTV가 볼 수 있는 방향에서 처리할 수 있는 영역의 개수
     */
    static int scan(CCTV cctv, int [] dirs, int flag) {
        int cnt = 0;
        for(int d=0; d<dirs.length; d++) { //2번유형이라면 dirs.length는 2가된다(방향 2개를 보는 cctv이므로)
            for(int i=1; ; i++) { //각각의 방향으로 쭉 가봄
                int nr = cctv.r + deltas[dirs[d]][0]*i;//각 방향에 i를 곱하여 deltas[dirs[d]][0]가 1이라면 i가 1일때는 1을 더하고 2일때는 2를 더하는 방식으로 쭉진행
                int nc = cctv.c + deltas[dirs[d]][1]*i;//열또한 동일하게 진행

                if( !isIn(nr, nc) || map[nr][nc]==6) { //영역안에 없거나 또는 벽이면 종료
                    break;
                }
                // 다른 CCTV - 생략하고 다음꺼 체크하러 가기.
                if(map[nr][nc]>0) {
                    continue;
                }
                // 사각해소지역 증가.
                if(map[nr][nc]==0) {
                    cnt++;
                }
                // flag에 따라서 오염/원복.
                map[nr][nc] +=flag;
            }
        }
        return cnt;
    }

    static boolean isIn(int r, int c) { //영역안에 있는지 확인
        return 0<=r && r<R && 0<=c && c<C;
    }



    static class CCTV{ //cctv구조체 (cctv가 위치한 행,열,유형 )
        int r, c, t; // row, col, type

        public CCTV(int r, int c, int t) {
            super();
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public String toString() {
            return "CCTV [r=" + r + ", c=" + c + ", t=" + t + "]";
        }


    }
/*
    private static String src = "4 6\r\n"	+
            "0 0 0 0 0 0\r\n" +
            "0 0 0 0 0 0\r\n" +
            "0 0 1 0 6 0\r\n" +
            "0 0 0 0 0 0";
*/
}
