import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
 *
 * @performance 반복문: 25328 236, 이분탐색: 25428 252
 * @category #반복문, #이분탐색
 */

//굴다리 길이 == 가로등 높이 제한
//굴다리 빛 비추기 h높이의 가로등은 높이만큼의 빛이 생긴다 . 이 빛이 모든 거리(x)에 다닿도록 하면서 높이가 최소인것을 찾아야함
    //처음부분 x-h>0 안된다. 처음부분의 x-h는 빛이 드리우는 거리인데 이 거리가 0보다 크다면 빛이 모든거리에 다닿지 않는다는 의미라 안된다.
    //중간부분  x1+h < x2-h 안된다. x1+h와 x2+h에 못미치는 것이므로 이렇게 되면 안된다.
    //끝부분 x+h<총거리 안된다. x+h가 총거리보다 작으면 안된다.
    //굴다리의 길이 즉 가로등의 높이와 가로등 개수는 반비례함 (이유:가로등의 높이가 거리 n이면 가로등의 개수는 1 , 높이가 1이면 가로등의 개수는 n개이므로)
    //반비례함을 이야기 하는 이유 표본제한이 크게 주어진것과 달리 결과적으로 그렇게 크지 않다.

public class Main {
    private static StringBuilder output = new StringBuilder();
    static int N, M; //굴다리길이,가로등 개수
    static int[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        input = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(input.readLine()); //거리
        M = Integer.parseInt(input.readLine()); //가로등 개수
        pos = new int[N]; //가로등 위치 담을수 있는 리스틑
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        for (int m = 0; m < M; m++) {
            pos[m] = Integer.parseInt(tokens.nextToken());//입력받음
        }
        //loop();
        binarySearch();
    }

    private static void binarySearch() {
        // 1. 이분 탐색 전제 조건: 요소들이 정렬된 상태여야 한다.!
        // 2. 기준 값들은?
        int l = 1, r = N, m = 0;//1분터시작하는 이유 높이는 최소 1 최대 N임
        int sol = Integer.MAX_VALUE; //최솟값을 찾아야 하므로 정수형 최대값으로 초기값설정
        boolean flag = false; //각 높이에서 성공과 실패를 나타낼 변수 flag 생성
        while (l <= r) { //이분탐색을 통해 l의 값이 r값보다 커지는 경우 빠져나와서 리턴해준다.
            //만약에 다 커버를 못한다면 while문아래에 l에 m+1을 넣어줌으로써 점점 최소값의 기준을 올려 중간값을 높여주고
            //만약에 다 커버를 한다면 while문아래에 r에 m-1을 넣어줌으로써 점점 최대값의 기준을 낮춘다.
            //예를 들어 총거리가 5 가로등의 개수가 2개 각 가로등의 위치가 2와 4에 위치한다면
            //코드를 돌때 초기값 l=1 r=5이다 여기서 m=3 이는 모든거리를 커버하므로
            //r = m-1 즉 2가된다 l=1이고 r=2에 m은 1이 된다 이는 모든 거리를 커버하지못함
            //l = m+1 1+1로 2가된다. r=2 이고 l=2는 while문을 돌수있는 경우 이때 m=2가되고 이는 거리를 커버할수있음
            //다시 r=m-1 을 진행시 r=2-1 즉 r은 1이 되버린다. 이는 l<=r조건에 위배되므로 while문을 멈추고 sol의 값인 2가 출력되면서 종료된다.

            m = (l + r) / 2; //1+n(총거리) /2 (이분탐색을 위해)
            flag = true; //매 반복시 마다 flag는 true로 처음에 설정 이후 if문들을 지나면서 안되는 변수부분들 제거
            // 맨 왼쪽 가로등
            if (pos[0] - m > 0) {
                flag = false; //이러면 실패(맨왼쪽)
            } else if (pos[M - 1] + m < N) {
                flag = false; //이러면 실패(끝부분)
            } else {
                for (int i = 1; i < M - 1; i++) { //범위가 이런것은 첫부분과 끝부분을 써먹었으므로 제외한 범위들을 테스트 한다
                    if (pos[i] + m < pos[i + 1] - m) { //이러면 실패(중간부분)
                        flag = false;
                        break;
                    }
                }
            } //위의 실패조건들을 다 통과한다면
            if (flag) {//true 모든 거리를 커버했다면 최소값인지 비교하여 현재의 값이 최소면 sol값을 m으로 갱신
                sol = Math.min(sol, m);
                r = m - 1;//이후 더 작은 값이 나올수이쓴지 확인하기위해 m-1 (최대값 r을 현재 높이 m에서 1낮춤) 최대치 증가
            } else {
                l = m + 1; //다 커버를 못한다는 의미이므로 높이를 높이기 위해 m+1 (최소값 l을 현재 높이 m에서 1높임) 최소치 증가
            }
        }
        System.out.println(sol);
    }

    private static void loop() { //시간복잡도를 o(n^2)으로 해도 성공하는 경우가 몇없기 때문에 폐기
        boolean flag = false;
        for (int h = 1; h <= N; h++) {
            flag = true;
            // 맨 왼쪽 가로등
            if (pos[0] - h > 0) {
                flag = false;
            } else if (pos[M - 1] + h < N) {
                flag = false;
            } else {
                for (int i = 1; i < M - 1; i++) {
                    if (pos[i] + h < pos[i + 1] - h) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                System.out.println(h);
                break;
            }
        }
    }

    // REMOVE_START
    private static String src = "5\n" +
            "2\n" +
            "2 4";
    // REMOVE_END
}