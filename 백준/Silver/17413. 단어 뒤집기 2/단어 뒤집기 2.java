import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //버퍼공간생성
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            Deque<Character> deque = new ArrayDeque<>(); //데크 변수 초기화
            String str = br.readLine() ; //문장 받기
            StringBuilder sb = new StringBuilder(); //문장정리를 위한 변수 초기화
            for(int i=0; i<str.length(); i++){ //str문장의 길이까지 for문 반복
                if(str.charAt(i) == '<'){ //스트링문자열을 char배열로 설정후 == <라면
                    int j=i;//i = j는 반복문에서 현재 처리 중인 인덱스(i)를 다음 작업할 인덱스(j)로 설정하는 역할
                    while(str.charAt(j) != '>'){ //닫힘꺽새가 나올때까지
                        sb.append(str.charAt(j)); //sb변수에 값들을 넣음
                        j++; //i와j의 필요성은 시작꺽새와 끝꺽새의 번호를 기억하기위함
                    }
                    sb.append(">"); //while문을 빠져나왔다는건 >가 나왔다는 의미이므로  >를 추가
                    i=j; //꺽새부분까지 이동을 해서 sb에 넣었음으로 j번째를 i변수에 초기화
                }
                else if(str.charAt(i) == ' '){ //공백발생시 해당부분 그대로 추가
                    sb.append(" ");
                }
                else{//꺽새부분이 아닌부분은 거꾸로 출력하기위해 deque이용
                    int j=i; //초기화된 j값(인덱스 위치값)
                    while(j<str.length() && str.charAt(j) != ' ' && str.charAt(j) != '<'){
                        //j < str.length() , str.charAt(j) != ' ', str.charAt(j) != '<'
                        //현재 인덱스(j)가 문자열 범위 내에 있고, 해당 위치의 문자가 공백 또는 태그 기호(<)가 아닐 때
                        deque.addLast(str.charAt(j));
                        j++;//계속하여 진행
                    }
                    int k=deque.size(); //반복하여 sb에 저장하기위해 size를 가져옴
                    for(int q=0; q<k; q++){
                        sb.append(deque.pollLast()); //횟수만큼 반복하여 sb에 저장
                    }
                    //i = j - 1;은 반복문에서 다음 작업할 인덱스(j)를 현재 인덱스(i)로 설정하는 역할
                    //현재 처리는하는 j는 언제나 1이 더해져서 다음 작업할 인덱스를 가르키기때문에 else문의 끝에는 현재의 인덱스를 넣어주는것이다.
                    i=j-1;// i = j - 1;은 다음 작업할 인덱스 값으로 설정하여 문자열 처리를 정확하게 이어나가기 위한 것
                }
            }
            System.out.println(sb.toString());

    }
}