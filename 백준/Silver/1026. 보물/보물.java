import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class Main {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st; //기본 입출력 객체 생성
 
        int N = Integer.parseInt(br.readLine()); //n개의 배열길이 생성
        int[] A = new int[N]; //a배열생성
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken()); //입력받은 요소 배열에 차례대로입력
        }
        Arrays.sort(A); // A를 오름차순으로 정렬
 
        Integer[] B = new Integer[N]; //b배열생성
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(B, Comparator.reverseOrder()); // B를 내림차순으로 정렬
 
        int ans = 0;//결과값
        for (int i = 0; i < N; i++) { // A의 가장 작은 값과 B의 가장 큰 값을 곱해서 더해 나감.
            ans += A[i] * B[i];
        }
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
}
 