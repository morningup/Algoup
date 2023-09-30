import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //받는 숫자 n은 count와 연관이 된다 n이 1이면은 num이 666일때 그다음 2일때는 1666이므로 해당 내용에 맞게끔 코드를 구현한다.
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        int num = 666;
        int count = 1;

        while(count != N) {
            num++;
            if(String.valueOf(num).contains("666")) { //입력받은 int형 변수 num을 string타입으로 변환하여 contains함수 사용
                //contains:대상 문자열에 특정 문자열이 포함되어 있는지 확인하는 함수(대소문자 구분) return값은 true or false
                count++;
            }
        }
        System.out.println(num);
    }
}