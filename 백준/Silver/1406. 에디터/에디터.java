import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine(); //초기문자들
        int M = Integer.parseInt(br.readLine()); //명령어 개수
        //_____(leftst)커서_____(rightst)커서가 양쪽으로 움직일때마다 각스택의 있는값들을 커서의 위치에 맞게끔 이동시키는것
        Stack<String> leftst = new Stack<String>(); //커서가 있다면 커서의 왼쪽위치에 있는것들을 담아두는 stack
        Stack<String> rightst = new Stack<String>(); //커서가 있다면 커서의 오른쪽위치에 있는것들을 담아두는 stack

        String[] arr = str.split("");//문장의 각 문자들 한개씩 리스트에 담기
        for(String s: arr){ //arr의 각 문자하나하나를 leftst에 넣음
            leftst.push(s);
        }

        for(int i=0; i<M; i++){//명령어 개수만큼 반복
            String comd = br.readLine(); //명령어 담기
            char c =comd.charAt(0); //0번째 위치의 명령어 종류를 파악

            switch (c){
                case'L': //왼쪽으로 커서옮기기
                    if(!leftst.isEmpty())//만약에 제일왼쪽에 커서가 위치해있지 않다면
                        rightst.push(leftst.pop()); //rightst에 leftst의 가장위에 것을 집어넣는다.
                    break;

                case'D':
                    if(!rightst.isEmpty())
                        leftst.push(rightst.pop()); //L과 반대
                    break;

                case'B':
                    if(!leftst.isEmpty()){
                        leftst.pop();//삭제하는것임으로 pop만 사용 push는 사용하지않음
                    }
                    break;

                case'P':                    // 0     1     2
                    char t = comd.charAt(2);//명령어 공백 추가할문장 중 추가할문장을 t에 담음
                    leftst.push(String.valueOf(t));
                    //String.valueOf() - 파라미터가 null이면 문자열 "null"을 만들어서 반환한다.
                    //toString() - 대상 값이 null이면 NPE를 발생시키고 Object에 담긴 값이 String이 아니여도 출력한다.
                    //문자한개만을 하면 아스키코드값이 출력될수있기때문에 string.valueof를 이용해 문자를 push한다.
                    break;

                default:
                    break;
            }
        }

        while(!leftst.isEmpty()){ //출력하기전 커서는 가장왼쪽으로 둔다. 즉 데이터들을 모두 오른쪽에 둔다.
            rightst.push(leftst.pop());
        }

        while(!rightst.isEmpty()){
            bw.write(rightst.pop());
        }
        bw.flush();
        bw.close();
    }

}