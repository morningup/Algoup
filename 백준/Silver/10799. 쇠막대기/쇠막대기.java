import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

public class Main {//쇠막대기
    //구분방법:( 가 왔는데 바로 다음이 )면 그건 레이저이고 )가 아니면 그건 쇠막대기다

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        String[] sl = s.split("");
        Stack<String> st = new Stack<String>();
        int count = 0;
        for (int i = 0; i < sl.length; i++) {
            if(sl[i].equals("(")) {
                //레이저가 아니고, 여는 괄호
                //-> 그저 stack에 push 하면 되어 보인다!
                st.push(sl[i]);
            }
            else if (sl[i].equals(")")) {
                if (sl[i-1].equals("(")) {
                    //레이저의 닫는 괄호
                    // -> 이 곳에 위치할 때마다 count 변수에 스택의 크기를 더해준다.
                    st.pop();
                    count+=st.size();
                } else {
                    //레이저가 아니고, 닫는 괄호
                    //-> stack에 pop하고 count 변수의 크기를 1 늘린다.
                    st.pop();
                    count++;
                }
            }
        }
        System.out.print(count);

    }

}