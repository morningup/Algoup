import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static long a,b ; //5억을 10억으로 만들 경우에는,
    // 두 가지 종류의 연산 중 마지막 자리에 1을 붙이는 연산을 진행할 경우에 Queue 에 50억+1 이 들어가야 하므로 overflow가 발생하게 된다!

    static int cnt ;//연산횟수

    static int bfs(){ //bfs 너비우선탐색(큐로 주로 구현)
        Queue<Long> q = new LinkedList<>();
        q.add(a);

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                long tmp = q.poll();
                if(tmp == b){
                    return cnt+1;
                }

                if(tmp*2 <= b){
                    q.add(tmp*2);
                }
                if(tmp*10+1 <= b){
                    q.add(tmp*10+1);
                }

            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
//정수 A를 B로 바꿈 여기서 취할수있는 가지수는 2가지 아래와 같다.
        //2를 곱한다.
        //1을 수의 가장 오른쪽에 추가한다.
        //BFS로 구현

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        System.out.print(bfs());
    }


}