import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

interface Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String[] arr = br.readLine().split(" ");
        int a1 = Integer.parseInt(arr[0]), a0 = Integer.parseInt(arr[1]);
        int c = Integer.parseInt(br.readLine());
        int n0 = Integer.parseInt(br.readLine());
        //a1,a0,c,n0를 입력받음 이는 문제에 나온 O(n)의 정의인
        //O(g(n)) = {f(n) | 모든 n ≥ n0에 대하여 f(n) ≤ c × g(n)인 양의 상수 c와 n0가 존재한다}
        //여기서 f(n) = a1n + a0 이므로
        //수식을 풀면 a1n +a0 <= cn 으로 구현 할 수 있다.

        int result = (a1 * n0 + a0 <= c * n0) && (c >= a1)?1:0;
        //정의를 만족할 경우 1 아닐경우 0을 출력
        //즉 O(n)을 만족하는지 판별하는것
        System.out.println(result);
    }
}