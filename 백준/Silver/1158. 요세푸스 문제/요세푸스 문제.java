import java.util.*;

public class Main { //큐 2개를 이용해서 1쪽에 2개를 빼서 k번째 값을 빼고 두번째큐에 쌓인 2개의 값을 다시 첫번째큐 뒤로 놓고
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 숫자의 개수
        int k = sc.nextInt(); // k번째
        Queue<Integer> q = new LinkedList<>(); //큐를 이용

        for(int i=1; i<=n; i++){ //큐에 값을 순서대로 저장
            q.offer(i);
        }
        StringBuilder sb = new StringBuilder(); //출력할때 사용할 스트링빌더
        sb.append("<"); //초기 꺽새 추가

        while(q.size() != 1){
            for(int i = 0; i<k-1; i++){ //k-1번째 까지 값을 뺏다가 다시 뒤로 넣어주고
                q.offer(q.poll());
            }
            sb.append(q.poll()+", "); //k번째에는 sb변수에 값을 저장
        }
        sb.append(q.poll() +">"); //마지막 마루리 꺽새 sb에 저장
        System.out.print(sb.toString()+"\n"); //값출력
    }
}