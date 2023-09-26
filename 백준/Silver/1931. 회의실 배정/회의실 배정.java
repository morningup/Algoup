import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
 
public class Main {
 //문제 분류 :'활동 선택 문제 (그리디 알고리즘 사용)
    // 서로 겹치지 않는 활동에 대해 종료시간이 빠르면 더 많은 활동을 선택할 수 있는 시간이 많아진다는 것
    //종료시간을 기준으로 정렬 (정렬후 빨리끝나는것 서택)
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt(); //회의개수
		
		
		/*
		  time[][0] 은 시작시점을 의미 
		  time[][1] 은 종료시점을 의미 
		*/
		int[][] time = new int[N][2];
		
		
		for(int i = 0; i < N; i++) {
			time[i][0] = in.nextInt();	// 시작시간 
			time[i][1] = in.nextInt();	// 종료시간 
		}
		
		
		// 끝나는 시간을 기준으로 정렬하기 위해 compare 재정의 (객체를 정려래주는것)
        //Comparator는 두 매개변수 객체를 비교
        //(Comparator 와 Comparable)은 비교해주는것은 같지만 대상이다름 
        //여기선 Comparator사용
		Arrays.sort(time, new Comparator<int[]>() {
			//return은 음수,0,양수로 정렬가능하다.
            //음수: 첫 번째 객체(o1)가 작다는 것을 의미
            //0: 두 개의 객체가 같다는 것을 의미
            //양수: 첫 번째 객체(o1)가 크다는 것을 의미
			@Override
			public int compare(int[] o1, int[] o2) {
                //2차원배열일시 time[i][0] ,time[i][1]들의 값이
                //o1[1] 과 o1[0]에 들어가게 되는것
                //o2는 o1의 다음배열이 있는값을 의미
				
				// 종료시간이 같을 경우 시작시간이 빠른순으로 정렬해야한다.  
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0]; //양수가 나오면 o1이 더 큰것이므로 그대로 있고
                    //음수가 나오면 o1이 더 작은것이므로 앞으로 간다.
				}
				
				return o1[1] - o2[1]; //동일
			}
 
		});
		
		int count = 0;
		int prev_end_time = 0;
		
		for(int i = 0; i < N; i++) {
			
			// 직전 종료시간이 다음 회의 시작 시간보다 작거나 같다면 갱신 
			if(prev_end_time <= time[i][0]) {
				prev_end_time = time[i][1];
				count++;
			}
		}
		
		System.out.println(count);
	}
 
}