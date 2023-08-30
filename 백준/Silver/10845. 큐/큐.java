import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] que = new int[10001];
    //스택은 LIFO이기 때문에 size가 인덱스가 될 수 있었지만,
    //큐는 FIFO이기 때문에 저장된 값을 제외할때는 제일 앞의 값을 제외 시킨다.
    //하지만 주의할 건 그렇다고 0인덱스를 제외 시키면 안된다. 배열은 값을 제외시키는 개념이 없기 때문에
    //0인덱스 값을 제외 시켰는데 다음 값을 또 제외 시키려면 0인덱스는 실제론 지워진 것이 아니라 그렇게 출력만 했기때문에
    //0인덱스 다음인 1인덱스 값을 출력해야한다. 그걸 체크하기위한 first변수이다.
    static int first = 0;
    //last는 back이 입력 되었을때 마지막 인덱스에 저장된 값을 출력하기위한 변수이지만 스택문제의 size처럼 해도 된다.
    static int last = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());//명령어 개수 공간
        StringBuilder sb = new StringBuilder(); //명령어에 대한 결과를 출력하는 공간

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()); //토크나이저로 띄어쓰기한 부분을 가져옴
            String S = st.nextToken();

            switch(S) {
                case "push" : //정수값을 집어넣음
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop" ://
                    sb.append(pop()).append("\n");
                    break;
                case "size" :
                    sb.append(size()).append("\n");
                    break;
                case "empty" :
                    sb.append(empty()).append("\n");
                    break;
                case "front" :
                    sb.append(front()).append("\n");
                    break;
                case "back" :
                    sb.append(back()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void push(int X) { //값집어넣기 마지막부분에
        que[last] = X;
        last++;
    }

    public static int pop() { //앞에거 부터 빼야함
        if(last - first == 0) { //아무것도 존재하지않으면 -1 출력
            return -1;
        }else { //존재한다면 맨앞에있는것 제거(여기서는 제거가 없기때문에 ++로 처음부분을 넘어감)
            int P = que[first];
            first++;
            return P;
        }
    }

    public static int size() { //처음부분과 마지막부분의 차이가 곧 사이즈(우리는 마지막부분의 번호가 -1되어있기때문에)
        return last - first;
    }

    public static int empty() {//아무것도 존재하지않는다면 1출력 존재한다면 출력
        if(last - first == 0) {
            return 1;
        }else {
            return 0;
        }
    }

    public static int front() {
        if(last - first == 0) {//가장앞에부분 비어있다면 -1
            return -1;
        }else {
            int F = que[first];//존재한다면 first로 지정된 부분의 수 출력
            return F;
        }
    }

    public static int back() { //가장뒤에것
        if(last - first == 0) {
            return -1;
        }else {
            int B = que[last - 1]; //리스트는0부터 시작이기때문에 마지막 부분에 -1을해줌
            return B;
        }
    }

}