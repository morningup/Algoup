import java.util.Stack;
import java.util.Scanner;

public class Main {
    //문제 풀이 방식: 0을 입력받으면 최근에 입력받았던 수를 지운다. 

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Stack<Integer> stack = new Stack<Integer>();

        int K = in.nextInt(); //k줄의 각 행의 정수를 입력받음

        for(int i = 0; i < K; i++) {
            int number = in.nextInt();

            if(number == 0) {	// 0이라면 스택에 저장된 top 원소를 지운다.
                stack.pop();
            }
            else {
                /*
                 * push() 대신 add()로 대체해도 됨 (똑같이 상단에 원소를 추가하는 메소드다.)
                 * ex) stack.add(number);
                 */
                stack.push(number);
            }
        }
        int sum = 0;

        for(int o : stack) {
            sum += o;
        }

        System.out.println(sum);
    }

}