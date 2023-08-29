import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
/*variable--; variable > 0; 이 두 가지를 합쳐놓은 것입니다.
0 보다 조건 값을 뺀 값이 클 경우, 즉 -- (증감 연산자)와 > (꺾쇠괄호)를 합쳐 놓은 형태인 거죠.
*/
        while(t-- > 0){
            Stack<Character> stack = new Stack<>();
            String str = br.readLine() + "\n"; //끝에다가 \n을 올리는이유는 마지막을 구분하기 위함 여기 마지막의 단어도 뒤바꾸어주어야되기때문
            //String.toCharArray() string문자열을 char배열로바꾼다.
            //for(A:B) B에서 차례로 꺼내 A에다가 넣겠다 (B에서 더이상꺼낼객체가 없을때까지)
            for(char ch : str.toCharArray()){
                if(ch == ' ' || ch =='\n'){
                    while(!stack.isEmpty()){ //isEmpty 길이가 0인지 체크후 맞으면 true반환
                        bw.write(stack.pop()); //단어를 뒤집어야 하기 때문에 pop으로 꺼내어 출력
                }
                bw.write(ch); //공백과 \n을 출력하기위한 코드
            }
                else{
                stack.push(ch); //스택안에다가 단어들을 집어넣음
                }
            }
            bw.flush();// 파일 입출력을 이용하면 언제나 close 또는 flush를 사용해야한다.
            //flush는 버퍼에 있는데이터들을 모두 강제로 출력하고 버퍼를 비워줌
            //close는 더이상 스트림을 이용하여 작성할수없음
        }


    }
}