import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        int N = in.nextInt();
        int start = 0;

        while(N-- > 0){
            int value =in.nextInt();

            if(value > start){
                for(int i=start+1;  i<=value; i++){
                    stack.push(i);
                    sb.append('+').append('\n');// +를저장
                }
                start = value; //다음 push할때 오름차순 유지를 위해 변수 초기화
            }
            else if(stack.peek() !=value){ //peek은 Stack의 top에 있는 item을 삭제하지않고 해당 item을 반환
                System.out.println("NO");
                return;
            }

            stack.pop();
            sb.append('-').append('\n');
        }
        System.out.println(sb);
    }

}