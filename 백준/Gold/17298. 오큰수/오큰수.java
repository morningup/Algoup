
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //현재의 원소가 이전의 원소보다 작을때까지 push , 현재원소가 스택의 top에 있는 원소보다 클경우 stack의 원소를 pop한다.
        Stack<Integer> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];

        for(int i=0; i<n; i++){
            a[i] = sc.nextInt();
        } //틀생성


        /*
         * 스택이 비어있지 않으면서
         * 현재 원소가 스택의 맨 위 원소가 가리키는 원소보다 큰 경우
         * 해당 조건을 만족할 때 까지 stack의 원소를 pop하면서
         * 해당 인덱스의 값을 현재 원소로 바꿔준다.
         */
        for(int i=0; i<n; i++){
            while(!stack.isEmpty() && a[stack.peek()] <a[i]){ // 현재의 원소(i번째)가 스택의 top(peek)에 있는 원소보다 클경우
                a[stack.pop()] = a[i];
            }
            //현재의 원소 i번째 가 이전의 원소 peek 보다 작다면 push
            stack.push(i);
            /*만약 42785936 배열이 있다면
            * loop 0: 이전의 스택이 없으므로 0번 push
            * loop 1: n[stack.peek] 즉 4와 n[i]인 2와 비교 stack.peek이 더 크므로 push
            * loop 2: n[stack.peek] 2와 7을 비교하였을때 n[i]인 7이 더 크므로 이 경우 pop하여 1번째 위치에 값에 a[i]즉 2번째 위치의 값을 넣어줌
            *          스택이 빌때까지 반복문 이기 때문에 1이 나간뒤 peek인 0에도 동일한적용으로 a[i] 2번째위치의 값인 7을 넣어줌
            *          이후 a[2]일때는 또다시 스택이 비었으니 2를 스택에 대입
            * loop 3: 현재의 값인 8이 스택의 top인 7보다 크므로 pop후 2번위치에 a[i]의 값인 8을 대입 다시 스택내부에 아무것도 없기때문에
            *         a[i]즉 a[3]에서 3은 스택에 저장된다.
            * loop 4: stack의 top인 3에 저장된값은 8 현재의 a[4]는 5이므로 a[i]가 더 작으므로 스택에 push
            * loop 5: 9는 a[3]과a[4]보다 크므로 3과 4를 pop하여 해당위치에 9라는 값을 넣은뒤 스택이 비었으므로 5라는 값은 스택에 넣어짐
            * loop 6: a[6]인 3은 a[5]인 9보다 작으므로 스택에 쌓임
            * loop 7: a[7]인 6은 a[6]인 3보다 크므로 pop후 a[6]에 a[7]의 값인 6이 대입됨 이후 남아있는 a[5]보다는 작기
            *         때문에 pop하지 while문을 빠져나온뒤  stack에 push됨*/
        }

        /* 이미 스택으로 모든 loop를 돌았음으로 남아있는 값들은 오큰수가 없으므로 -1로 정의
         * 스택의 모든 원소를 pop하면서 해당 인덱스의 value를
         * -1로 초기화한다.
         */
        while(!stack.isEmpty()){
            a[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            sb.append(a[i]).append(" ");
        }

        System.out.print(sb);
    }
}