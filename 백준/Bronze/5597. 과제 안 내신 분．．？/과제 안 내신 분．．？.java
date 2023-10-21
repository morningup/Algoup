import java.util.*;

public class Main { //단순구현 30명중 28명만 제출한것중 제출안한사람을 찾는 문제

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] student = new int[31];
		
		for(int i=1; i<29; i++) {
			int success = sc.nextInt();
			student[success] = 1;
		}
		for(int i=1; i<student.length; i++) {
			if(student[i]!=1)
				System.out.println(i);
		}
		
		
		sc.close();

	}

}