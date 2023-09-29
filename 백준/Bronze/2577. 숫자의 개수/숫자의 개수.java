import java.util.Scanner;
 
public class Main {
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
 
		int value = (in.nextInt() * in.nextInt() * in.nextInt()); //a*b*c 곱셈
		String str = Integer.toString(value);// 해당값을 Str로 설정
		in.close();// 스캐너 사용종료
		
		for (int i = 0; i < 10; i++) {//1부터 9까지 count출력
			int count = 0;
			for (int j = 0; j < str.length(); j++) { //str의 길이만큼 for문반복
				if ((str.charAt(j) - '0') == i) { //str의 1개의 문자(0~9)까지 해당자료가 있다면
					count++; //해당위치의 count++
				}
			}
			System.out.println(count);
		}
		
	}
}