import java.io.*;
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //공간 생성
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        String s = br.readLine();// 문자열 S에 입력받음
 
        String[] strArray = new String[s.length()]; //
        // 입력 string 크기만큼 배열 생성
        for (int i = 0; i < s.length(); i++) {
            strArray[i] = s.substring(i,s.length()); //tocharArray이용해도됨
            //여기서 최소 1개부터~ s.length개 만큼이 총 s.length()개 생성
            //즉 0,s.length면 문장전체가 들어가고
            //1,s.length면 0번 문자가 빠진 전체가들어가는것들이 반복됨
            //결국 문장의 길이만큼의 개수가 strarray에 넣어지게됨
        }
        // 하나씩 잘라서 넣고
        Arrays.sort(strArray); //그 배열들을 사전순으로 정렬
        // 알파벳순 정렬
        for(String str : strArray) {
            bw.write(str);
            bw.write('\n');//이후 출력
        }
        
        bw.flush();
 
    }
 
}