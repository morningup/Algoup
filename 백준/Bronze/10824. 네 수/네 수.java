//문자열로 바꾼뒤 int형을 변경하면 쉽게 풀릴것 
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();
        long first = Long.parseLong(a+b);
        String c = st.nextToken();
        String d = st.nextToken();
        long second = Long.parseLong(c+d);
        System.out.print(first + second);
    }
}