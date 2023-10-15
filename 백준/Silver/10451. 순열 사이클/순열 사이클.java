

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//from 1	 2	3	4	5	6	7	8(인덱스)
//to   3	 2	7	8	1	4	5	6(값)

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int ans = 0;
            int size = Integer.parseInt(in.readLine()); //입력받을사이즈
            int arr[] = new int[size+1]; //인덱스는 1부터 사용
            StringTokenizer st = new StringTokenizer(in.readLine()); 
            for (int i = 1; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken()); //arr배열에 값들 입력받음
            }
            boolean visit[] = new boolean[size+1]; //arr배열과 똑같은 사이즈의 visit 배열 생성 
            //boolean배열의 초기 값들은 false로 설정됨
            for (int i = 1; i < arr.length; i++) { //1부터 차례대로 진행
                if(!visit[i]) { //1회전시 visit[1]은 false가 저장되있을거싱ㅁ
                    visit[i] = true; //visit[1]을 true로 변경
                    int go = arr[i]; //arr[1]에는 3이 저장되어 있음 이 3을 go라는 변수에 저장
                    while(true) {
                        if(visit[go]) { //visit[go] 즉 visit[3]에는 false가 저장되어있으므로 불만족 밑으로 내려감
                            ans++;
                            break;
                        }
                        visit[go] = true; //방문했음을 알리는 visit[3]은 true로 변경
                        go = arr[go]; //arr[3]에는 7이 저장되어있음 다시 위로 올라가서 반복 그러다가 결국 나중에 3이 등장하면 종료후
                        //그다음 배열로 진행 만약 이전에 이미 사이클을 돌아 true인 경우 if문에 걸려서 다음 내용으로 넘어감
                    }
                }
            }
            System.out.println(ans);
        }
    }
}