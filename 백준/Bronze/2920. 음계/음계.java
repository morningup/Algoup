import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] inputs = new int[8];
        for (int i = 0; i < inputs.length; i++) inputs[i] = sc.nextInt(); //배열만큼 음계입력받음
        sc.close();//스캐너 사용했으니 close

        String output = ""; //string 객체 생성
        for (int i = 0; i < inputs.length - 1; i++) {// 모든길이의 반복
            if (inputs[i] == inputs[i + 1] - 1) output = "ascending";
            //input의 i번째 배열의 값과 i+1번째 배열의 값에 -1한 값이 같다면 true이므로 asc단어 출력
            //(이유: 순서대로 증가하여 값들의 차이가 i와 i+1의 차이가 1씩 나야 하므로)
            else if (inputs[i] == inputs[i + 1] + 1) output = "descending";
            //이 경우   8 7 6 5 4 3 2 1이 되야하므로 i번째가 i+1번째보다 1이 큰경우 des단어 출력
            else {
                output = "mixed"; //이 경우가 아닐시 mixed 단어 출력
                break;
            }
        }
        System.out.println(output);
    }
}