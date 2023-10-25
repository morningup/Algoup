
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N;
    static int animal[];
    static int animal_cnt[];
    static boolean flag=false;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine()); //동물의 수 저장
        animal = new int[N]; //해당 배열에 동물의 수만큼 공간 생성
        animal_cnt = new int[41]; //각 동물 번호의 출현횟수
        st=new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            animal[i]=Integer.parseInt(st.nextToken()); //동물의 키순위를 순서대로 배열에 저장
            animal_cnt[animal[i]]++; //동물 번호의 출현횟수 위치에 ++로 해당 번째에 동물이 들어왔음을 저장
        }
        int sum = 0;//대답을 받은 동물의 수
        int result = 1; //가능한 조합의 수 초기값은 1
        boolean findOne = false; // 토끼나 고양이 중 한 종류의 동물이 이미 발견되었는지를 나타내는 변수 , 초기값은 false
        for(int i = 0 ; i < N ; i++) {
            //2가 나오는 경우 토끼와 여우가 동일한 번째수를 (토끼 2번째로 큼 , 여우 2번째로 큼) 가지는 경우 animal_cnt가 두번++ 가 됨
            if(animal_cnt[i] == 2) { //animal_cnt[i] 값이 2인 경우, 토끼(2번 대답)인 경우로 간주합니다.
                                     // findOne이 false인 경우에만 조합의 수를 2배로 곱해줍니다.
                if(!findOne) result *= 2;//이 와중에 findOne이 false이면 결과값에 2를 곱함
                else break; //만약에 true면은 빠져나옴
            }else if(animal_cnt[i] == 1) { //animal_cnt[i] 값이 1인 경우, 토끼나 고양이(1번 대답)인 경우로 간주합니다.
                                           // findOne 변수를 true로 설정합니다.
                findOne = true;
            }else { //animal_cnt[i] 값이 0인 경우, 다른 동물인 경우로 간주합니다. 반복문을 종료합니다.
                break;
            }
            sum+=animal_cnt[i]; // sum 변수를 업데이트합니다.
        }
        //findOne이 true인 경우, 토끼나 고양이 중 한 종류의 동물이 이미 발견되었음을 의미합니다. 이 경우, 가능한 조합의 수를 2배로 곱해줍니다.
        if(findOne) result *= 2; //만약에
        //sum과 N이 다른 경우, 대답을 받은 동물의 수와 전체 동물의 수가 일치하지 않는다는 의미입니다. 이 경우, 가능한 조합의 수를 0으로 설정합니다.
        if(sum != N) result = 0; //만약에 합이 N개와 맞지않다면 이거는 잘못된 키순서이므로 0을 출력
        System.out.println(result);
    }

}