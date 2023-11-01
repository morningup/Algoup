import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력값 받기
        int t = Integer.parseInt(br.readLine());

        //소수 구하기 에라토스테네스의 체 소수는 false (소수만 false인 배열 생성)
        boolean[] num = new boolean[1000001]; //0~100만번까지 공간생성
        num[0] = num[1] = true; //0과 1은 어차피 안씀 소수는 2부터임

        for(int i=2; i*i<= 1000000; i++){ //i*i는 n의 소수는 n의 제곱근 보다 작다는 수식
            if(!num[i]){ //num이 false라면
                for(int j=i+i; j<=1000000; j+=i){ //i의 배수들은 제거 즉 2의배수 3의 배수들중 2와3을 제외한 나머지 배수들은 제거
                    //i+i이기때문에 2와3은 false로 유지됨
                    num[j] = true;
                }
            }
        }

        while(t-- > 0){ //t를 계속 1씩빼며 반복하면서 이때 t가 0보다 클때까지 반복(테스트 케이스 입력받기)
            int temp = Integer.parseInt(br.readLine()); //테스트 1개의 입력값
            int ans = 0; //그때 파티션의 개수
            for(int j=2; j<=temp/2; j++){ //예를들어 5라면 2+3과 3+2는 동일한 파티션으로 보므로 temp/2를 진행 (반반 구역을 나누고)
                //2 ~temp/2까지는 i가 temp/2~ temp까지는 j가 맡음(1은 소수가 아니니까 제외 2부터 시작)
                if(!num[j] && !num[temp-j]){ //두 배열이 false 즉 소수이면 골드바흐 파티션 완성 (이유: temp+j-j = temp이므로)
                    ans++;
                }
            }
            System.out.println(ans);
        }
    }




}