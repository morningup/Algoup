import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		Long n= Long.parseLong(br.readLine());

		System.out.println(n*(n-1)/2);
		System.out.println('2');
        /*i는 [1, n-1], j는 [i+1, n]번 돈다.
          n-1 + n-2 + ... + 1 번 돌게된다.
          차가 1인 등차수열만큼 돌게 되므로 n*(n-1)/2 만큼 돈다
          그래도 시간 복잡도는 똑같이 O(n^2)다. 차수는 2*/
	}
}	