import java.io.*;
import java.util.*;
public class Main {//2007 구현문제
    //1월 1일은 월요일
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); //월
        int d = Integer.parseInt(st.nextToken()); //일
        String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
        //요일 저장
        int[] months = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        //월별 일수 저장
        int n = 0;
        for (int i = 0; i < m; i++) {
            n += months[i];
        }
        n += d-1;  //현재날 -1
        //-1을 하는 이유: ex) 1월 1일 , n = 0 + (1-1) = 0 , days [0 % 7] = days [0] = MON
        //1월 1일 부터 시작이므로 현재날의 -1을 하여주는것이다.
        //날짜는 값이 0부터시작을 하지않기때문에 기본적으로 해당날을 구하는 경우 -1을 포함하여야한다.
        System.out.print(days[n%7]);
    }
}