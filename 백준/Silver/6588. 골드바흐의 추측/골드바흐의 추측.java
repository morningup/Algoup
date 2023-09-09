import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {//골드바흐의 추측
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        boolean[] isPrime = new boolean[1000001]; //시간초과가 발생해서 로직을 바꿈 아예 생성
        isPrime[0] = true; // 소수를 false로 함
        isPrime[1] = true;
        // 처음엔 다 prime(false)으로 세팅
        // 배수는 prime이 아니므로 true
        for (int i = 2; i <= (int) Math.sqrt(1000000); i++) {
            for (int j = 2; i * j < 1000001; j++) {//후 소수 판별
                isPrime[i * j] = true; // 소수가 아님
            }
        }

        //이론
        //에라스토스테네스의 체
        //소수 대칭성 이해하기(반복시에 시간을 줄이기 위해 n/2를 하는 이유)
        //자연수는 1과 소수와 합성수로 이루어 진다.
        //
        //소수 : 1과 자기 자신 만을 약수로 갖는 1보다 큰 양의 정수
        //합성수 : 1과 자기 자신 이외의 약수를 가진 양의 정수
        //합성수를 제거하기위해 sqrt 즉 제곱근을 사용하는 이유는 수식을 보면 편하다.
        // X는 M x N의 형태로 나타날 수 있다.
        //그럼 여기서 M >= N 이라고 하자.
        //그럼 아래와 같은 식이 성립한다.
        //-> X = M x N
        //-> M x M >= M x N .... (N <= M)
        //-> M^2 >= X (M x N = X)
        //-> M >= sqrt(X)
        //즉 어떤 수 M의 최소값은 sqrt(X)이다.
        //그리고 N <= M 이므로 N의 최대값은 sqrt(X)이 된다.
        //어떤 수 X가 합성수라면 sqrt(x)보다 같거나 큰수 M과, sqrt(X) 보다 같거나 작은 수 N의 합성수로 이루어 진다.
        //그래서 N보다 같거나 작은수들로만 모듈러 연산을 해서 0이 나오면 합성수, 나오지 않으면 소수가 됨

        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            boolean ans = false;
            for (int i = 2; i <= n / 2; i++) { //
                if (!isPrime[i] && !isPrime[n - i]) {
                    System.out.println(n + " = " + i + " + " + (n - i));
                    ans = true;
                    break;
                }
            }
            if (ans == false) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }
}