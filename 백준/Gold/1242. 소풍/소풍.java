import java.io.*;

public class Main {

    public static void main(String[] args){
        Solve solve = new Solve();
        System.out.println(solve.run());
    }

}

class Solve{

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int input[] = new int[5];

    public Solve(){
        input();
    }

    public int run(){
        return getExit(input[0], input[2]); //N과 M값 제공받음
    }

    private void input(){
        try{
            String str[] = br.readLine().split(" "); //각 N K M 받아오기
            for(int i = 0; i < 3; i++) input[i] = Integer.parseInt(str[i]);
        } catch (IOException ioe){}
    }

    //동호가 외치는 번호가 변경되는 규칙 : 현재 제거되는 사람이 외치는 번호가 exitNumber라고 할때,
    //
    //다른 사람은 다음 게임에서, (현재 자신이 외치는 숫자(target) - exitNumber)만큼 줄어든 숫자 단 이 경우는 동호의 위치가 K보다 클때 가능하다.

    //만약 동호의 위치가 K보다 작은 경우
    //현재사람 수(numberofpeople)-exitNumber + target

    private int getExit(int numberOfPeople, int target){ //사람수 , 동호 번호
        int exitNumber = input[1] % numberOfPeople; //K번째 사람 % 사람수 (하는 이유 전체 사람수가 계속 줄어드므로)
        if(exitNumber == 0) exitNumber = numberOfPeople; //결국 exitnumber가 K의 크기와 동일할 경우 exitnumber의 값이 현재사람수의 번호와 같다
        if(exitNumber == target || numberOfPeople == 1) return 1; //exitnumber가 우리가 찾고자하는 동호번호인 target과 동일하거나
        //또는 현재인원수가 1명남았다면 return 1을 반환후 종료(이것은 재귀함수이기때문에 결국 +1을 더하고 종료한다는의미)
        if(target < exitNumber) {
            target = (numberOfPeople-exitNumber) + target;
        }
        else target = target - exitNumber;
        return 1 + getExit(numberOfPeople-1,target);
    }
}