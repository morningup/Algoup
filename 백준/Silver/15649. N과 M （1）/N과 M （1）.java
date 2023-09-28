import java.util.Scanner;
 
public class Main {
     //백트래킹 방식 사용:모든 경우의 수를 보되 가능성이 있는 경우의 수 들만 봄
    //dfs도 백트래킹의 일종
	public static int[] arr; //수열 출력공간(순서 존재)
	public static boolean[] visit; //dfs시 사용할 체크 배열
 
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
 
		int N = in.nextInt();
		int M = in.nextInt();
 
		arr = new int[M];
		visit = new boolean[N];
		dfs(N, M, 0);//재귀 진행
 
	}
 
	public static void dfs(int N, int M, int depth) {
		if (depth == M) { //depth즉 배열의 길이 m과 depth의 값이 동일하다면 (이미 배열은 m의 개수를 만족함)
            //그러므로 빠져나와 주는 역할
            for (int val : arr) {
				System.out.print(val + " ");
			}
			System.out.println();
			return; //예를 들어 n이 4이고 m이 2라면 첫번째 시작시 재귀문을 돌면서 {1,2}을 출력하고
            // return시 다음계층으로 내려감
		}
 
		for (int i = 0; i < N; i++) { //재귀적 호출로 인해 이 for문이 이중 m중 for문 구조가 됨
            //예를 들어 for문에서
            //즉 for문의 i가 번째의 각 배열 arr[0~m]의 경우의 수를 도맡는다. 
            //가장 위에 있는 for문이 arr[0]의 나올 경우의 수를 맡고 그 밑이 arr[1]의 경우의수
            //이런식으로 반복되며 진행된다.
			if (!visit[i]) {  
				visit[i] = true;
				arr[depth] = i + 1;
				dfs(N, M, depth + 1); //depth+1은 휘발성이기 때문에 depth변수에 저장은 안됨
                //그러나 depth++를 쓰면 depth의 값에 저장이 되므로 사용하면 안됨  
                //이 depth를 통해 해당 수열만큼만 조절할수있음 depth는 언제나 0으로 고정되있고
                //휘발성으로만 더해야함
                
  //여기서는 visit[i] = false; 이 코드가 있기 전이므로 모든 경우의수를 for문으로 돌며 visit배열의 판단가능
				visit[i] = false;//이후 방문한 해당 자식노드는 다시 false로 초기화함
                //0번째시에 0번째가 false로 초기화 되기 전에 위의 재귀적인것으로 0번째의 경우의 수를
                //다 뽑을것임 그리고 그 for문내에 있는 visit[i] =false문이 다시 초기화 해줄것이고 해당
                //true까지 다 뽑았다면 가장 위에 있는 for문의 i가 1증가하며 n을 향해 계속 반복해나감
			}
		}
	}
 
}