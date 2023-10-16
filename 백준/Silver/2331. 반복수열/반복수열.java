import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
 //D = {57, 74, 65, 61, 37, 58, 89, 145, 42, 20, 4, 16, 37, 58,...}으로 주어진다
    // {37, 58, 89, 145, 42, 20, 4, 16} 반복된다.
    // 반복되지 않는 수열 {57, 74, 65, 61}의 개수인 4개가 정답
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
 
        List<Integer> list = new ArrayList<>();
        // 리스트는 배열의 한계 때문에 만들어진 자료형입니다. 배열을 사용하기 위해서는 크기를 정해야 한다. 
        //그런데 프로그래밍 중에 동적으로 생성해야 하는 경우가 많기 때문에 크기를 미리 정할 수 없다. 
        //이때 List 는 메모리가 허용하는 한 계속 해서 추가 할 수 있도록 만든 자료형 클래스 
        list.add(A);//a를 입력받음
 
        while (true) {
            int temp = list.get(list.size() - 1); //값을꺼냄 , 
 
            int result = 0;
            // 어떤 수의 각 자리에 대해 P제곱만큼하여 각 자리를 더한 값을 구함.
            while (temp != 0) {
                //pow거듭제곱
                result += (int) Math.pow(temp % 10, (double) P); //각 자리수를 p만큼 제곱한 수를 더함
                temp /= 10; //이후 사용한 제곱수의 다음자리수 계산을 위해 계산한 이전자리수 몫으로 삭제
            }
 
            // result가 이미 list에 포함되어있다면,
            // 그 result가 가리키는 인덱스를 반환.
            if (list.contains(result)) { //contains 리스트안에 해당 result값이 존재하는지 확인
                int ans = list.indexOf(result); // 해당 result위치에 존재하는 index위치 반환
                bw.write(ans + "\n"); //result는 연속을 의미하는 변수이므로
                //해당 result의 변수가 list에 있는 부분은 처음 시작부분을 의미한다.
                //그리고 리스트는 0부터 시작하기때문에 해당부분의 번호는 즉 연속되지않는 부분들의 개수가 된다.
                break;
            }
 
            list.add(result);
        }
 
        bw.flush();
        bw.close();
        br.close();
    }
 
}
