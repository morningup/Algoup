import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		String S = s;
		String T = t;

		if(s.length() != t.length()) {
			int len = LCM(s.length(), t.length());

			while(S.length() != len) {
				S += s;
			}
			
			while(T.length() != len) {
				T += t;
			}
		}
		
		if(S.equals(T)) System.out.print(1);
		else System.out.print(0);

	}
	
	static int GCD(int a, int b) {
		while(b > 0) {
			int temp = a;
			a = b;
			b = temp%b;
		}
		return a;
	}
	
	static int LCM(int a, int b) {
		return (a*b)/GCD(a, b);
	}

}
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        String S = s;
        String T = t;

        if(s.length() != t.length()){
            int len = LCM(s.length() , t.length());
            while(S.length() != len){ //S의 길이가  len(최소공배수의 길이)와 같지 않다면 계속 해서 S에 더함
                S += s;
            }

            while(T.length() != len){
                T += t;
            }

            if(S.equals(T)) System.out.print(1); //이때 s와 t의 문자열들을 equals로 비교하여 같으면 1 아니면 0을 출력
            else System.out.print(0);
        }
    }

    static int LCM(int a ,int b){ //최소 공배수
        return (a*b)/GCD(a,b); //a와 b를 곱한뒤에 최대공약수로 나눈다. == 최소공배수
    }

    //유클리드 호제법
    //자연수 a, b(a > b)에 대해서 a를 b로 나눈 나머지가 r일 때, a와 b의 최대공약수는 b와 r의 최대공약수와 같다
    static int GCD(int a , int b){//최대 공약수
        while(b>0){ //만약 b가 0보다 크다면
            int temp = a; //temp에 a값을 집어넣고
            a=b; //a에 b의 값을넣고
            b=temp%b; //a와 b를 나눈 나머지를 다시 b에다가 집어넣는다.
        }
        return a;
    }
}*/