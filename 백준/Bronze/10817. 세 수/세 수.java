import java.util.Scanner;
public class Main {
 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
 
		int arr[] = {in.nextInt(), in.nextInt(), in.nextInt()};
		in.close();
        
/* 
가장 최후의 비교는 마지막 -1 번째 원소와 가장 마지막 원소이므로
i 는 배열 길이-1 까지만 반복해주면 된다.
*/
        
		for(int i=0; i<arr.length-1;i++) {
			for(int j =i+1; j<arr.length;j++) {
				if(arr[i]>arr[j]) {
					int temp = arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}	
			}
		}
		System.out.println(arr[1]); //오름차순 정렬후 0번째 1번째(2번째로 큰수) 2번째 밖에 존재하지않음
	}
}