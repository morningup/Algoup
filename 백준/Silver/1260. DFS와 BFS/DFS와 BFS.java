package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);   // 정점의 개수
        int M = Integer.parseInt(str[1]);   // 간선의 개수
        int V = Integer.parseInt(str[2]);   // 탐색을 시작할 정점의 번호

        arr = new int[N+1][N+1];
        // 정점 번호는 1번부터 N번까지이므로 편의상 배열의 크기를 +1해준다.
        /*
            arr배열의 의미: 정점들이 만나는지 표시를 해주기 위해
            arr  [N+1]      [N+1]
                해당 정점   그 정점과 만나는 다른 정점
        */

        for(int i = 0; i < M; i++) {
            String[] str2 = br.readLine().split(" ");

            int a = Integer.parseInt(str2[0]);
            int b = Integer.parseInt(str2[1]);

            arr[a][b] = 1;
            arr[b][a] = 1;
            // 입력으로 주어지는 간선은 양방향이므로 둘 다 체크해준다.
        }

        visited = new boolean[N + 1]; // 방문 여부
        dfs(V);

        System.out.println();

        visited = new boolean[N + 1]; // 여기서 다시 선언을 해주지 않으면 위에 dfs(V)함수에서 사용한 visited를 bfs(V)에서도 사용하게 되므로 초기화 시켜준다.
        bfs(V);
    }

    // dfs -> 재귀 방식을 사용함
    static void dfs(int V) {
        visited[V] = true; // V: 탐색을 시작할 정점의 번호 -> 해당 번호는 이미 방문한 정점이므로 방문했다는 표식을 남겨줌
        System.out.print(V + " ");

        if(V > arr.length - 1) {
            /*
                 arr.length는 정점의 갯수에서 +1 한 수이므로 'arr.length - 1 = 정점의 총 개수'
                 V(탐색을 시작할 정점 번호)가 정점의 총 갯수보다 클 수 없다!!
            */
           return;
        }
        
        for(int node = 1; node < arr.length; node++) { // j: 정점 번호 -> 정점의 1 ~ 끝 번호까지 반복문을 돌림
            // arr[V][node] == 1: 연결된 두 정점이다 / visited[j] == false: 아직 방문하지 않았다
            if(arr[V][node] == 1 && visited[node] == false) {
                dfs(node); // -> 다시 dfs(j) 함수를 호출해줌(재귀함수) -> 호출하면 이제 방문했다는 표식을 남길 수 있음
            }
        }
    }

    // bfs -> 큐(queue) 방식을 사용함
    static void bfs(int V) {
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(V); // 큐에 해당 정점 번호를 넣어줌
        visited[V] = true; // 방문했다는 표식을 남겨줌
        System.out.print(V + " ");

        while(!queue.isEmpty()) { // 큐가 비어있지 않으면
            int temp = queue.poll(); // 큐에 담겨있는 번호 중 가장 앞에 담겨져있는 번호
            for(int node = 1; node < arr.length; node++) {
                if(arr[temp][node] == 1 && visited[node] == false) { // 해당 노드와 연결된 다른 노드가 있고 그 다른 노드를 아직 방문하지 않았다면
                    queue.add(node); // 다른 노드를 queue에 넣고
                    visited[node] = true; // 방문했다는 표식을 남겨줌
                    System.out.print(node + " ");
                }
            }
        }
    }
}

//내가 각 코드에 단 주석
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int [][] arr; //2차원배열
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N =Integer.parseInt(str[0]);
        int M =Integer.parseInt(str[1]);
        int V =Integer.parseInt(str[2]);

        arr =new int[N+1][N+1]; //정점의 번호는 1부터 시작이므로 편의상 +1한거임
                    //해당정점 //그 정점과 만나는 다른정점
        for(int i=0; i<M; i++){
            String[] str2 = br.readLine().split(" ");

            int a =Integer.parseInt(str2[0]);
            int b = Integer.parseInt(str2[1]);

            arr[a][b] = 1;//입력으로 주는 간선이 양방향이므로 둘다 체크
            arr[b][a] = 1;// 1->2 == 2->1
        }
        visited = new boolean[N+1]; //DFS에서 사용할 visited 변수 초기화
        //N이 4라면[null,null,null,null,null] 0번째를 제외하고 나머지를 채운다 1번부터 시작므로
        dfs(V);

        System.out.println();

        visited = new boolean[N+1]; //BFS에서 사용할 visited 변수 초기황 아니면 예상한 값이 등장하지않음
        bfs(V);

    }
    static void dfs(int V){ //dfs는 스택 또는 재귀로(재귀를 사용하였기때문에 bfs의 while문을 안적어준것임)
        visited[V] = true; // 첫번째 예제 초기에는 1이v로 들어와있음 1부터 채워주고 시작
        //[null,true,null,null,null]
        System.out.print(V+" ");
        if(V > arr.length-1) return; //V의 번호가 arr의 길이보다 1작을때 즉 N이 4라면 길이또한 1줄어드므로 4이다 이것보다 큰경우는 존재하지않는다.
        //사실 없어도 됨

        for(int node = 1; node <arr.length; node++){
            if(arr[V][node] == 1 && visited[node] == false){
                dfs(node);
            }
        }


    }
    static void bfs(int V){//bfs는 큐로 해결
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(V);
        visited[V] = true;
        System.out.print(V+" "); //초기 1값을 넣어줌
        while(!queue.isEmpty()){
            int temp = queue.poll();
            for(int node = 1; node<arr.length; node++){
                if(arr[temp][node] == 1 && visited[node]==false){
                    queue.add(node);
                    visited[node] = true;
                    System.out.print(node + " "); //1값을 제외한 나머지 순서들을 넣어줌
                }
            }
        }
    }
}*/
