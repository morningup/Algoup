import java.util.Scanner;


public class Main{
    static long[] array = new long[101]; //결과값의 범위가 int형범위를 넘어서므로 long형 부여
    //파도반 수열 : 피보나치의 경우 F(N) = F(N-1) + F(N-2)이지만 파도반의 경우 F(N) = F(N-2) +F(N-3)
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner in = new Scanner(System.in);
        int T = Integer.parseInt(in.nextLine());

        array[0] = 1;//값 설정
        array[1] = 1;
        array[2] = 1;

        for(int i = 0; i < T; i++) {
            int N = in.nextInt();

            sb.append(exp(N-1)).append("\n");//해당 테스트 케이스들의 값들을 sb에 저장후 한번에 출력을 위해 사용
            //배열을 편히사용하기위해 0부터 채워넣을려고 입력받은 n을 n-1하여 함수에 적용
        }
        System.out.println(sb);

    }

    static long exp (int a){
        if (array[a] != 0) {
            return array[a]; // 0 1 2와 같이 채워진것의 경우 해당 값을 사용한다.
        }else{
            return array[a] = exp(a-2) + exp(a-3); //피보나치와 문제유형이 동일하므로 재귀적 호출 사용
        }
    }
}