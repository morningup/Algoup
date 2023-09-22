import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		Long n= Long.parseLong(br.readLine());

	
		System.out.println(n*(n-1)*(n-2)/6); //6을 나누면 횟수가 알맞음
        //6을 나누는 이유 :
        //ex) n이 7이라면 i의 따른 수행횟수는 15, 10, 6, 3, 1 감소
        //-5, -4, -3, -2으로 줄어든다라는 규칙성은 찾았지만, 식으로 바꿀 수 없었음
        //펌) 1 부터 n까지의 숫자중 3가지(i, j, k에서 하나씩 뽑는거라 볼 수 있음)를 뽑아 중복 없이, 
        //크기 순으로 작성하는 경우의 수가 수행 횟수이다. -이는 고등학교때 조합이라고 볼수있음 (순서 상관x)  
        //nCr =  n!/(n-r)!*r!  => n*n-1....(n-r)*(n-r-1)...*1 /(n-r)*(n-r-1)...*1 * r*(r-1)..*1
        //여기서 우리는 무작위로 3개를 뽀는 즉 nC3이므로 r이 3임을 알수있음 
        //다시 계산을 하면 (n*(n-1)*(n-2))/6이 됨을 알 수 있음
        
		System.out.println('3'); //최고차항은3
	}
}	