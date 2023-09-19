import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

//애드혹 문제 :정형화된 공식이 존재하지않으며 내가 아이디어를 생각해야한다.
//문제 설명 : 처음에 (p,q)에서 출발한 개미는 1시간 후에는 (p+1,q+1)로 옮겨간다. 단, 이 속력으로 움직이다가 경계면에 부딪치면 같은 속력으로 반사되어 움직인다.
            //w*h공간에서 처음에 p,q에서 출발하는 개미의 t시간후의 x,y를 계산해서 출력해야한다.

//문제 해결 :문제는 시간제한이 0.15초이고 t의입력값이 2억이다. 시간초과를 조심해야함
// (p+1,q+1)로 옮겨간다. 단, 이 속력으로 움직이다가 경계면에 부딪치면 같은 속력으로 반사되어 움직인다. (반대방향으로 다시 나아가는 것을 의미)

public class Main {

    public static void main(String[] args) throws IOException{ //런타임오류시 IOException 이부분을 주의깊게 확인하자
        //반복문을 사용하면 시간초과가 난다.
        //long x = W - Math.abs(W - (P + T) % (W * 2));//Math.abs는 절대값을 구하는 함수(이유 반대방향 또한 결국 +좌표 이므로)
        //기본규격 == W
        //다음번에 나올 x좌표는 기본 규격 - (기본규격-(이전x좌표와 가는시간만큼의 합)%(기본규격*2))
        //기본규격*2를 하는 이유: P+T가 W보다 작다면 같으면 개미의 위치는 그대로 유지가된다. 그러나 W보다 크다면 W까지 갓다가 다시 뒤돌아와야한다.
        //*2를 하는 이유는 갔다 왔다 다시 본래의 자리로 돌아오기 때문(즉 무의미한 계산이므로 미리 나누어 나머지만 계산한다)
        //전체적인 식을 정리하면 기본규격 - 이동한 만큼의 차이
        //long y = H - Math.abs(H - (Q + T) % (H * 2));
        //이 또한 x와 동일하다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int t = Integer.parseInt(br.readLine());

        p = w - Math.abs(w - (t + p) % (w * 2));
        q = h - Math.abs(h - (t + q) % (h * 2));

        System.out.printf("%d %d", p, q);
    }
}