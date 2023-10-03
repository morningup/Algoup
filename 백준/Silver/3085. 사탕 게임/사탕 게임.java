import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //브루트포스:조합가능한 모든 경우의수를 찾아서 해결
    //이 문제는 교환을 계속 하면서 먹고 내려오는 candy crush같은것이 아니라 한번 교환을 통해 가장 많이 먹을수있는 max의 값을 구하는것이다.
    //또한 한번 교환으로 나온 것들중 가장 긴 행 또는 열 중 한가지만 먹는것이 max값이다.
    public static char[][] board;	// 맵 크기 N*N
    public static int N;			// 맵 크기
    public static int max = 0;		// 먹을 수 있는 최대 사탕개수(static 변수 설정) : 각 경로를 지나면서 appcheck로 max값을 얻은뒤 그 경우에 다시 최대값의 경우를 더해야하므로

    /* 두 문자 교환하는 메소드 */
    public static void swap(char a, char b) {
        char temp = a;
        a = b;
        b = temp;
    }

    /* 가로로, 세로로 비교하면서 먹을 수 있는 사탕의 최대 갯수 찾기 */
    public static void arrCheck() { //인접한 칸의 swap이 발생한뒤 해당 변경된 배열의 모든 경우의 수 를 발견하여 그 중에 max인 경우의 수 를 찾는다.

        // → 가로로 체크
        for(int i=0; i<N; i++) {
            int count = 1; //count가 0이 아니라 1로 설정한이유 : 인접한 2개가 있을때 비로소 먹을수있으니 최소한 2개를 먹는다. 그래서 기본값을 1로설정
            for(int j=0; j<N-1; j++) {//

                // 이전 사탕과 동일한 경우 -> 계속 먹는다
                if(board[i][j] == board[i][j+1])
                    count ++;

                    // 이전과 다른 사탕인 경우 -> 새로 먹어야하므로 1로 초기화
                else
                    count = 1;

                // 사탕 최대 개수 저장
                max = Math.max(max, count);
            }
        }

        // ↓ 세로로 체크
        for(int i=0; i<N; i++) {
            int count = 1;
            for(int j=0; j<N-1; j++) {
                if(board[j][i] == board[j+1][i])
                    count ++;
                else
                    count = 1;
                max = Math.max(max, count);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();	// 보드의 크기
        board = new char[N][N];

        /* N x N 사탕 입력받기 */
        for(int i=0; i<N; i++) {
            String str = scan.next();
            for(int j=0; j<board[i].length; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        //원래 가로와 세로의 경우 최적의 max값은 가로와 세로가 번갈아 나올 수 있는데 여기서는 이중for문 두개로 가로만 이동하거나
        //세로만 이동하는 경우를 만든 이유는 appcheck에서 가로에서 한번움직인것의 다음 행동인 가로와 새로의 모든 경우의 수를 파악하여 주기때문에
        //main함수에서는 가로만 이동하는것과 새로만 이동하는것을 따로 구현하고 해당 내용에 appcheck만 들어가면 문제가 없다.

        /* 가로로 인접한 두 사탕 교환하고 최대 사탕 개수 찾고 원위치 */
        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) { //이중 for문을 통해 가로로 이동하며 마지막 열로 가면  그다음 행으로 넘어간다 
                //j의 n-1이 한계인 이유 마지막까지 왔을때 n-1과 n을 서로 swap하는 경우 이기 때문에 
                // 가로로 인접한 두 문자 교환
                //swap(board[i][j], board[i][j+1]);
                char temp = board[i][j];
                //swap을 함수사용하지않고 그냥 코드를 작성한 이유:값을 교환해야하는데 이 함수 방식으로는 배열의 값들이 변경되지않음
                board[i][j] = board[i][j+1];
                board[i][j+1] = temp;

                // →, ↓ 체크
                arrCheck(); //여기서 각 swap

                // 이전에 교환한 문자 복구
                //swap(board[i][j], board[i][j+1]);
                temp = board[i][j];
                board[i][j] = board[i][j+1];
                board[i][j+1] = temp;
            }
        }

        /* 세로로 인접한 두 사탕 교환하고 최대 사탕 개수 찾고 원위치 */
        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) {
                //swap(board[j][i], board[j+1][i]);

                char temp = board[j][i];
                board[j][i] = board[j+1][i];
                board[j+1][i] = temp;

                // →, ↓ 체크
                arrCheck();

                // 이전에 교환한 문자 복구
                //swap(board[j][i], board[j+1][i]);
                temp = board[j][i];
                board[j][i] = board[j+1][i];
                board[j+1][i] = temp;
            }
        }

        System.out.println(max);
        scan.close();
    }

}