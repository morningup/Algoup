import java.util.Scanner;
 
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //바구니 개수
        int m = sc.nextInt(); //바구니 역순 정렬 횟수
        
        int arr[] = new int[n + 1]; // 바구니 (1~n)
       
        for(int i=1; i<=n; i++) {//담기
            arr[i] = i;
        }
        
        for(int k=0; k<m; k++) {// 바꿀 바구니 선별
            int i = sc.nextInt();
            int j = sc.nextInt();
 
            for(int h=i; h<=j; h++) {//i 부터 j까지 역순으로 정렬을 m번 반복
                int nn = j--;//i ~ j 반복문을 돌리고 nn에 j를 넣고 -- 연산진행
                //이 연산은 후위 연산자 이기때문에 j의 값을 사용하고 나서 j를 1 감소
                //즉 nn 변수에는 현재의 j 값이 할당되고, 그 후에 j가 1 감소
                int tmp = arr[h];
                arr[h] = arr[nn];
                arr[nn] = tmp;
            }
        }
        
        for(int i=1; i<=n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
