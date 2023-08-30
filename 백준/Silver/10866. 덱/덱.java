import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque<Integer> deque = new ArrayDeque<>();
        int num =0;
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            switch (s) {
                case"push_front":
                    num = Integer.parseInt(st.nextToken());
                    deque.addFirst(num);
                    break;

                case"push_back":
                    num = Integer.parseInt(st.nextToken());
                    deque.addLast(num);
                    break;

                case"pop_front":
                    if(deque.isEmpty()){
                        sb.append("-1").append("\n");
                    }
                    else{
                        sb.append(deque.peekFirst()).append("\n");
                        deque.removeFirst();
                    }
                    break;

                case"pop_back":
                    if(deque.isEmpty()){
                        sb.append("-1").append("\n");
                    }
                    else{
                        sb.append(deque.peekLast()).append("\n");
                        deque.removeLast();
                    }
                    break;

                case"size":
                    sb.append(deque.size()).append("\n");
                    break;

                case"empty":
                    if(deque.isEmpty()){
                        sb.append("1").append("\n");
                    }
                    else{
                        sb.append("0").append("\n");
                    }
                    break;

                case"front":
                    if(deque.isEmpty()){
                        sb.append("-1").append("\n");
                    }
                    else{
                        sb.append(deque.peekFirst()).append("\n");
                    }
                    break;

                case"back":
                    if(deque.isEmpty()){
                        sb.append("-1").append("\n");
                    }
                    else{
                        sb.append(deque.peekLast()).append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
        br.close();


    }
}