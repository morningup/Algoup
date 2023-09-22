import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		System.out.println(n);
		System.out.println('1');
        /*for문이 한번 돌면 for문에 적힌 변수(i,n 등등..) 만큼 시간 복잡도가 나온다
          O(n) 의 시간 복잡도를 가짐. 차수는 1*/
	}
}