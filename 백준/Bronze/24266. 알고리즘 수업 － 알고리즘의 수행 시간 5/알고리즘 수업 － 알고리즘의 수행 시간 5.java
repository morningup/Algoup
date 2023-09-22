import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		Long n= Long.parseLong(br.readLine());
		System.out.println(n*n*n);
		System.out.println('3'); //for문 3번 O(n^3)만큼의 시간복잡도 가짐
	}
}