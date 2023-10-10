import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
 * @author 은서파
 * @since 2021. 10. 4.
 *
 * @performance 16180	116
 * @category #시뮬레이션, #완탐(dfs)
 * @memo
 * 탐색의 회수가 지극히 제한적 --> 구지 방문체크할 필요가 없을듯. 오히려 더 부담.
 */



//최대 5번 이동 4*4*4*4*4 + 4*4*4*4 + 4*4*4 +4*4 + 4(총 경우의 수)
public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    static int N, MAX;
    static int [][] map;
    static int [][] deltas = {{-1, 0},{1, 0},{0, -1},{0, 1}}; //방향 우 좌 상 하

    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine());
        map = new int [N][N]; //구조 배열 받기
        for(int r=0; r<N; r++) {
            tokens = new StringTokenizer(input.readLine()); //읽어오기
            for(int c=0; c<N; c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
                MAX = Math.max(MAX, map[r][c]);
            }
        }// 입력 완료
        dfs(map, 0);
        System.out.println(MAX);
    }

    private static void dfs(int [][] map, int turn) { //파라미터 :주어진 배열 , 총5번 움직이는데 이를 해결하는 turn
        if(turn==5) {
            return;
        }

        for(int d=0; d<deltas.length; d++) { //사방탐색 (모든 방향으로 이동)
            int [][] moved = move(map, d); //이동함수 (배열 ,방향)
            dfs(moved, turn+1); //한번 이동시킨뒤 턴을 넘겨서 dfs진행
        }
    }

    /**
     * 주어진 배열 map을 d 방향으로 이동 시킨 결과를 이차원 배열 형태로 반환한다.
     * 이동 방향에 따라 먼저 점검해야 하는 부분이 달라진다.
     * @param map
     * @param d
     * @return
     */


    private static int [][] move(int [][] map, int d){
        int [][] newMap = new int [N][N]; //원본배열을 유지하기 위해 새로운 배열 생성
        /*
        int[][] newMap = new int[N][N];
        newMap[0][0] = 1;

        for (int i = 0; i < N; i++) {
            // ...
            // newMap 배열의 값들을 변경하는 로직
            // ...
        }

        return newMap; 이러한 구조인데 newMap은 move가 새로 될때마다 생성되는것이 아닌 return받은 newMap의 값들이 초기화되지않고 사용된다.
        결과: 만약 n이 3이라면
        {{1,0,0},{0,0,0},{0,0,0}}
        */

        //이동방향이 아래에서 위면이면 탐색방향은 위에서 아래로인 이유: 위쪽에 있는것 부터 변경이 발생을 하여야 순서에 맞게 가장 최하단에 있는것을 계산할수있음
        /*
        4 0 0 0
        4 0 0 0
        8 0 0 0
        8 0 0 0   이러한 배열이면 가장 상단에 있는 4가 8로 변경되야 하단의 두번째 4의 공백을 인덱스번호로 8로 채울수있고 하단의 8또한 동일하기때문에 idx의 번호-1을 통해 가장 상단에8 그다음 상단에 16이
                  올 수 있다.
        */

        /*                                                            nextIdx경우
        baseblock                                   원배열의 경우             0(N행 배열이기때문에 0번째에는 아무런 값을 집어넣지않음)  
        4                                               8                   1 (8이 채워짐)
        -1                                              16이 나온다           2 (16이 채워짐)
        8
        -1(-1일경우 합쳐져서 baseblock을 초기화해 준것)




        */

        if(d==0) { // {-1, 0} --> 위로 이동 --> 위에서 아래로 탐색(위에서 아래니까 진행이므로 for문의 r과 c의 배치가 현재의 구조가 됨) _원래였으면 r행 , c열 이므로 c가 우선 r이 나중이 와야하는데 문제상
            for(int c=0; c<N; c++) { //열은 0~n까지 증가
                int nextIdx = 0, baseBlock = -1; //0부터 시작이므로 nextIdx 0은 아무 값도 안들어오고 1부터 시작 [1~N][1~N]까지에만 배열이 차있음
                //기존에 있는 block의 값을 저장해 놓을 변수 baseblock(이전 블록의 값) 을 -1로 초기화
                //결합이 되어 새로운 값이 나오는 변수들을 저장할 nextindex(다음블록의 위치)를 초기화 (이 경우 위에서 내려가는 반복이 끝날때 마다 idx와 block초기화)
                for(int r=0; r<N; r++) { //행은 0~n까지 증가
                    // map[r][c]==0이면 할 일이 없다
                    if(map[r][c]==0) { //맵의 한 block의 값이 0이면 할일이없음
                        continue;
                    }
                    if(map[r][c]==baseBlock) { // 예를들어 baseblock이 4이고 map[r][c]도 4라면 2배인 8배로 nextIdx-1위치 즉 더 상위에 있는 4에 위치에 8의 값이 들어가면서 하위의 4는 사라짐
                        newMap[nextIdx-1][c] <<=1; //새로운 배열에 비트연산으로 2배로 만들어줌 이전위치의-1번째 위치에 위치시킴(idx를 증가시키지 않고 오히려 빼서 가장 상단에 위치해둔다)
                        baseBlock=-1; //-1로 초기화
                        MAX = Math.max(MAX, newMap[nextIdx-1][c]); //최대값인지 확인 맞으면 갱신
                    }else {
                        newMap[nextIdx++][c] =baseBlock = map[r][c]; //그렇지 않은경우(값이 다른 경우)
                        //여기서 nextIdx가 열에 들어오는 것은 위에서 아래로 탐색 (idx가 1씩증가하는것은 아래로 한칸씩 내려가는것을 의미하므로)
                        // newmap 배열에 작성된것의 다음번 아래위치와 baseblock값 에 map[r][c]를 넣는다.
                    }
                }
            }

        }else if(d==1) { // {1, 0} --> 아래로 이동 --> 아래에서 위로 탐색
            for(int c=0; c<N; c++) {
                int nextIdx = N-1, baseBlock = -1; //아래에서 위로 파악하므로 n-1부터임
                for(int r=N-1; r>=0; r--) {
                    // map[r][c]==0이면 할 일이 없다.. 다음으로~~
                    if(map[r][c]==0) {
                        continue;
                    }
                    if(map[r][c]==baseBlock) {
                        newMap[nextIdx+1][c]  <<=1; //반대방향
                        baseBlock=-1;
                        MAX = Math.max(MAX, newMap[nextIdx+1][c]);
                    }else {
                        newMap[nextIdx--][c] =baseBlock = map[r][c]; //반대방향
                    }
                }
            }
        }else if(d==2) { // {0, -1} --> 왼쪽으로 이동 --> 왼쪽에서 오른쪽으로 탐색
            for(int r=0; r<N; r++) {
                int nextIdx = 0, baseBlock = -1;
                for(int c=0; c<N; c++) {
                    // map[r][c]==0이면 할 일이 없다.. 다음으로~~
                    if(map[r][c]==0) {
                        continue;
                    }
                    if(map[r][c]==baseBlock) {
                        newMap[r][nextIdx-1]  <<=1;
                        baseBlock=-1;
                        MAX = Math.max(MAX, newMap[r][nextIdx-1]);
                    }else {
                        newMap[r][nextIdx++] =baseBlock = map[r][c];
                    }
                }
            }

        }else { // {0, 1} --> 오른쪽으로 이동 --> 오른쪽에서 왼쪽으로 탐색
            for(int r=0; r<N; r++) {
                int nextIdx =N-1, baseBlock = -1;
                for(int c=N-1; c>=0; c--) {
                    // map[r][c]==0이면 할 일이 없다.. 다음으로~~
                    if(map[r][c]==0) {
                        continue;
                    }
                    if(map[r][c]==baseBlock) {
                        newMap[r][nextIdx+1]  <<=1;
                        baseBlock=-1;
                        MAX = Math.max(MAX, newMap[r][nextIdx+1]);
                    }else {
                        newMap[r][nextIdx--] =baseBlock = map[r][c];
                    }
                }
            }
        }
        return newMap;
    }

    // REMOVE_START
    private static String src = "3\n"
            + "2 2 4\n"
            + "0 0 0\n"
            + "0 0 0";
    // REMOVE_END

}