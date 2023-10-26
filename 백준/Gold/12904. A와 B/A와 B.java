import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //stringbuffer : 문자열을 추가하거나 변경할 때 주로 사용하는 자료형
    //append:뒤에 문자열 추가
    //insert:특정위치에 문자열 삽입
    //substring:특정위치의 문자 뽑아냄

    //입력받은 s를 t로 바꿀수 있으면 1 아니면 0 으로 한다. 연산은 아래에 2가지이다.
    //문자열의 뒤에 A를 추가한다.
    //문자열을 뒤집고 뒤에 B를 추가한다.

    //문제 풀이
    //위 방법을 거슬러올라가 t의 문장은 아래와 같은 방식으로 행하였을때
    //T의 끝이 A면 => A제거
    //T의 끝이 B면 => B제거 후 T뒤집기
    //s 본래의 문장이 등장하여하는지 비교한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer s = new StringBuffer(br.readLine()); //문장 s입력받음
        StringBuffer t = new StringBuffer(br.readLine()); //문장 t입력받음

        while(s.length() < t.length()){ //처음에 입력받을때 t의 문장이 s의 문장보다 무조건 길기때문에
            if(t.charAt(t.length()-1) =='A'){ //T의 마지막문장이 A라면 해당 A문자 삭제
                t.deleteCharAt(t.length()-1);
            }
            else if(t.charAt(t.length()-1) == 'B'){ //T의 마지막문장이 B라면 해당 B문자 삭제후 뒤집기
                t.deleteCharAt(t.length()-1);
                t.reverse();
            }
            //이렇게 연산을이어오다 S의 길이와 T의 길이가 같아지는 순간이 오면 WHILE문을 빠져나옴
        }

        if(s.toString().equals(t.toString())) {//"toString" 메서드는 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드
            System.out.println(1);
        }else {
            System.out.println(0);
        }



    }
}