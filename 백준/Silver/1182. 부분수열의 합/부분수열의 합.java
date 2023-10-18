import java.util.Scanner;

//https://eunsolsblog.tistory.com/entry/C-%EB%B0%B1%EC%A4%80-1182%EB%B2%88-%EB%B6%80%EB%B6%84%EC%88%98%EC%97%B4%EC%9D%98-%ED%95%A9
//위의 링크에 그림 참고
//
/* 계속 트리 형태로 진행을 하면서 받아들여진 수열에서 부분수열들의 합이 s와 동일한 경우의 수를 출력한다.
//ex) s가 0인 경우는 수열(-7,-3,-2,5,8)에서 부분수열 -2,-3,5의 경우의수 1개밖에 없기때문에 1을 출력하고 종료한다.
//위와 같은 트리구조들을 만들어 해당 순서에 상관없이 해당 부분수열들을 모두 찾아 경우의 수를 구한다.
*                        0 (맨처음 depth는 0일때 sum의 값또한 0)
*                     -7   0
*                  -10  -7    ...
                 -12 -10
               -7  -12
              1  -7 ...  */
class Main {
    static int[] num;
    private static int N; // 정수의 개수
    private static int S; // 정수의 합
    private static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }
        dfs(0, 0); //값입력받기
        if (S == 0) System.out.println(answer - 1);
            //S 0일때가 전체 합이 0 일때랑 겹치기 때문에 -1 을 해주어야 합니다.
            //위의 사진에 공집합이 0이라고 볼 수 있습니다
        else  System.out.println(answer);

    }

    private static void dfs(int depth, int sum) {
        if (depth == N) { //depth가 n인경우
            //예제의 경우 수열(-7,-3,-2,5,8)의 경우의 수는 -3,-2,5의 합인 부분수열이지만
            //이 경우 코드상에서는 0,-3,-2,5,0의 형식으로 배열형식으로 저장이되어 해당 depth는 N과 같아진다.
            if (sum == S) answer++; //
            return;
        }
        //tree를 생각해서 직접 그려보면 이해하기가 쉽습니다.
        dfs(depth + 1, sum + num[depth]);
        dfs(depth + 1, sum);
    }
}