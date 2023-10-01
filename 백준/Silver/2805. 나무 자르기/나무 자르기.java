import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] tree = new int[N];
		
		int min = 0;
		int max = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			
			/*
			 * 나무들 중 최댓값을 구하기 위해 매 입력 때마다 max 변수와 비교하여
			 * 입력 받은 나무가 max보다 클 경우 max 값을 해당 나무의 높이로 갱신해준다. 
			 */
			if(max < tree[i]) {
				max = tree[i];
			}
		}
		
		// 이분 탐색 (upper bound)
        //이분탐색 중 upper bound의 가장 중요한 개념은 합의 값이 원하는 값(m)보다 작다면
        //max값을 줄이고(max의 값에 mid값을 넣으면서 줄임)
        //합의 값이 원하는 값보다 크다면
        //min값의 +1을 진행하여 하한값을 올려주는것이다.
        //이를 반복하다보면 어느순간 해당 min의 값이 mid의 값보다 커지는 순간이 발생하면서
        //while문을 빠져나오고 while의 조건의 따라 min-1등의 다양한 값이 나온다
        //이분 탐색의 경우 while 조건의 형식은 
        //https://jun-codinghistory.tistory.com/154해당 링크를 보며 매일 복습하자
		while(min < max) {
			
			int mid = (min + max) / 2;
			long sum = 0;
			for(int treeHeight : tree) {
				
				/*
				 *  tree의 잘린 길이 = tree의 높이 - 자르는 위치(mid)
				 *  tree의 잘린 길의의 합을 구한다.
				 *  이 때 자르는 위치가 주어진 나무의 높이보다 클 수 있기 때문에
				 *  0 이하인 경우(=음수)에는 합산을 하지 않고 양수만 합산하도록 해야한다.
				 */
				if(treeHeight - mid > 0) { 
					sum += (treeHeight - mid);
				}
			}
			
 
			/*
			 * 자른 나무 길의의 합이 M보다 작다는 것은
			 * 자르는 위치(높이)가 높다는 의미이므로 높이를 낮춰야 한다.
			 * 즉, 상한(max)를 줄여야 한다.
			 */
			if(sum < M) {
				max = mid;
			}
			
			/*
			 * 자른 나무 길이의 합이 M보다 크다는 것은
			 * 자르는 위치(높이)가 낮다는 의미이므로 높이를 높여야 한다.
			 * 또한, 같을 경우에는 최대한 적게 자르기 위해 자르는 높이를
			 * 높여야 하므로 하한(min)을 올려야 한다.
			 */
			else {
				min = mid + 1;
                //만약에 자르는 기준선의 최대의 값이 36m라면 이미 while문을 지나면서
                //해당 코드를 거쳐서 min이 sum과 같으므로 else문에 안착하여 min에 mid+1의 값인 37이
                //들어오게 된다. 
			}
		}
		
		System.out.println(min - 1); //그래서 여기에 min -1을 진행하는것임

		
	}
}
