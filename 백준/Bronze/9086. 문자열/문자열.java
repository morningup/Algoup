import java.io.*;
import java.util.*;

public class Main { //문자열 첫글자 마지막글자 출력
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());//test개수
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            sb.append(s.charAt(0)); //첫번째
            sb.append(s.charAt(s.length()- 1) + "\n");//마지막번째
        }
        System.out.print(sb);
    }
}