import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;
import java.util.*;

//물의 높이를 1부터 증가시켜서 최대 안전영역의 개수를 출력하라
class Main {

    //이분탐색은 배열이 정렬되어있어야 한다.
    //n개의 배열 1개 , m개의 배열 1개
    //이를 m개에 배열에서 비교하여 있으면 1을 출력 없으면 각자리수 번째에 맞게 0을 출력

    //방향
    static int N;
    static int M;
    static int [] A; //비교하는 대상만 배열형태로 정렬되어있음됨
    static int [] B; //들어오는 값은 배열에 없어도됨
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A); //이진탐색시 무조건적으로 정렬되어있어야함

        StringBuilder sb = new StringBuilder(); //한번에 출력하기위해 stringbuilder객체 생성

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        /*for(int j =0; j<M; j++){ 기준점이 되는 배열 A를 제외하고 비교하는 값인 B의 값들은 배열에 들어올 필요가 없음
            B[j] = Integer.parseInt(st.nextToken());
        }*/
        for(int j=0; j<M; j++){
            if(binarySearch(A , Integer.parseInt(st.nextToken())) >= 0){ //A배열내에 해당하는 값과 동일한 값이 있는경우
                sb.append(1).append("\n");
            }
            else{
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);

    }

    public static int binarySearch(int[] A , int key){ //정렬된 배열 A , 찾으려는값 key
        //up&down 이라 생각하면 편함
        int lo = 0;  //정렬시 가장 앞쪽에 있으면 low값
        int hi = A.length - 1; //맨뒤쪽에 있으니 high값

        while(lo <= hi){ //lo가 hi보다 커지는 경우 즉 최적의 값을 찾았다는 의미이거나 해당하는 값이 아예 존재하지않는것을의미
            int mid = (lo+hi)/2;

            if(key < A[mid]){
                hi = mid-1; // 현재 비교하는 값이 중간에 위치해있는값보다 크단느 의미이므로 high의 크기를 줄임(현재 비교했던 A[mid]보다 작은 A[mid-1]로)
            }

            else if(key > A[mid]){ //A[mid]의 값이 key보다 작은경우 더 크기를 키워주기 위해 lo에 중간값인 mid를 더함
                lo = mid+1;
            }

            else{
                return mid;
            }

        }

        //찾고자하는값이 없는경우 -1을 반환
        return -1;
    }








}