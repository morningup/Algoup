import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author itsmeyjc
 * @caution #dijkstra,(다익스트라)
 * 두 점간 최단 거리 문제 (최소비용으로 이동하고 싶다.)
 * 정점의 개수가 50000개로 매우 큼 - 그래프 구성시 인접 행렬은 25억개. ㅜㅜ - 인접 리스트 필요
 */

//대입할수 있는 자료구조 : 인접행렬 , 인접리스트
    //인접행렬: n*m행렬 (자료의 최대 50000) 총 25억개의 메모리 소모비용이 큼 (래프의 연결 관계를 이차원 배열 _각 행 렬에 방향과 노드 번호 개시)
    //인접리스트: 인접리스트를 사용하는게 자료의 소모가 적다. (그래프의 연결 관계를 vector의 배열(vector<int> adj[])로 나타내는 방식)
    //인접리스트 장점: 실제로 연결된 노드들에 대한 정보만 저장하기 때문에, 모든 벡터들의 원소의 개수의 합이 간선의 개수와 같다는 점이 있습니다. 즉, 간선의 개수에 비례하는 메모리만 차지
    //인접 행렬 장점: 인접 행렬의 장점은 구현이 쉬움

public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static int N; // N (1 <= N <= 50,000) 개의 헛간 - 정점(인접 행렬로 그래프 구성시 너무 큼: 5만*5만 = 25억 그러므로 인접 리스트 사용)
    static int M; // M (1 <= M <= 50,000) 개의 양방향 길 - 무향 그래프(간선)

    static List<Edge>[] graph; //인접 리스트 생성

    static int INF = 987654321;
    // 구해야 할 정답
    static int Min;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));

        // 입력 및 그래프 구성
        StringTokenizer tokens = new StringTokenizer(input.readLine(), " ");

        N = Integer.parseInt(tokens.nextToken()) + 1;//헛간 1번 부터 시작한다. (풀기 쉽도록 1번부터 시작하게 함)
        M = Integer.parseInt(tokens.nextToken()); //간선

        // 인접 리스트 형태로 그래프 구성
        graph = new List[N];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            int from = Integer.parseInt(tokens.nextToken()); //시작위치
            int to = Integer.parseInt(tokens.nextToken()); //향하는곳
            int cow = Integer.parseInt(tokens.nextToken());//(여물 비용)

            //인접리스트에 추가 (양방향 그래프 (양방향이므로 시작과 향하는곳에 둘다 넣어줘야함))
            graph[from].add(new Edge(to, cow, 0)); //향하는 그래프 노드에 연결되는 array들의 데이터형은 edge형이다.
            graph[to].add(new Edge(from, cow, 0));//처음이므로 cumcost는 아직 누적이 발생하지 않았기때문에 0이다.
        }
        /*        for (int[] row : graph) {
            System.out.println(Arrays.toString(row));
        }*/
        // 1--> 6으로 가는 최소 비용(특정 점 간의 최소 비용: dijkstra)
        Min = Integer.MAX_VALUE; //최소비용이전에 정수형 최대값으로 일단 초기화
        dijkstra(); //다익스트라 진행
        System.out.println(Min == Integer.MAX_VALUE ? 0 : Min); //MIN값이 MAXVLUE이거는 초기화가 안되었으므로 이동할수없다의 의미인 0을 변경이 되었다면 변경된 MIN값을 출력
    }

    //다익스트라 알고리즘 진행

    /*PriorityQueue란 우선순위 큐로써 일반적인 큐의 구조 FIFO(First In First Out)를 가지면서,
    데이터가 들어온 순서대로 데이터가 나가는 것이 아닌 우선순위를 먼저 결정하고 그 우선순위가 높은 데이터가 먼저 나가는 자료구조이다.*/
    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(); //우선순위 큐로 구현(모든 edge들 관리)
        // 일단 무한대로 출발지에서의 누적 비용을 초기화해서 *정점들을 관리할 배열*
        Edge[] dist = new Edge[N]; //출발지에서 최소 거리를 기록 (엣지 객체들을 담아둘것이기 때문에 엣지 배열 생성)
        for (int i = 1; i < dist.length; i++) { //n번 반복
            Edge v = new Edge(i, 0, INF); //엣지번호 , 엣지비용(관심x) ,누적비용(초기값 무한대로 설정)
            //비용을 0으로 설정하는이유 : graph변수에 담아져있는걸 가져오면 됨

            if (i == 1) {
                v.cumCost = 0; // 출발점(누적비용 0)
                pq.offer(v); //우선순위 큐에 넣고 시작
            }
            dist[i] = v;//방금우리가 만들 매열에 v객체를 넣어줌
        }

        while (!pq.isEmpty()) { //pq가 비어있을때 까지 반복진행
            Edge front = pq.poll(); //우선순위가 가장 높은것 끄집어냄

            // 자식 탐색
            List<Edge> list = graph[front.no]; //현 위치 번호 꺼냄(리스트 연결리스트)
            for (int i = 0; i < list.size(); i++) { //이후 연결되있는것들 가져옴
                Edge next = list.get(i); //get으로 list 데이터 가져옴 (연결되어있는 to 또는 from들이 나올것임)
                // 기존 누적 비용이 front를 거쳐서 온 비용보다 크다면 갱신 필요
                if (dist[next.no].cumCost > front.cumCost + next.cost) { // 기존 누적 비용 =현재까지의 노드의 누적비용 + 연결리스트연결된 노드들의 비용
                    dist[next.no].cumCost = front.cumCost + next.cost; //갱신 (Edge v = new Edge(i, 0, INF) 누적비용이 최대값 즉 무한대로 설정되어있으므로 진행시 초기화 가능)
                    // 다음 탐색 후보로 등록
                    pq.offer(dist[next.no]);
                }
            }
        }
        // System.out.println("정점: " + vertexes[N - 1]);
        Min = dist[N - 1].cumCost; //dist의 n-1번째의 누적비용을 min값으로 삼음 (n개의 배열을 생성하면 0~n-1까지 됨 우리 코드의 경우 1~n+1까지 이므로 -1을 해주는게 맞음)
    }

    //우선순위 queue를 이용해서 edge를 관리할것임 => 누적비용을 이용해서 가장 가깝게 연결할수있는 edge관리
    //출발점 ->해당 간선의 누적비용 , 간선번호가 구해져야함
    static class Edge implements Comparable<Edge> { //edge구조체 생성
        int no; //연결되어있는것들의 번호
        // 해당 간선의 비용으로 graph에서 사용
        int cost;
        // 출발점에서 부터의 누적 비용으로 dist에서 사용
        int cumCost;

        public Edge(int no, int cost, int cumCost) {
            this.no = no;
            this.cost = cost;
            this.cumCost = cumCost;
        }

        //참고 :https://jinee0717.tistory.com/36
        /*정렬하고자 하는 객체의 클래스에 Comparable Interface를 상속받아 compareTo()를 구현해주었다
          Integer.compare(a, b)는 현재 객체와 비교하고자 하는 객체를 오름차순으로 정렬해주는 함수*/
        /*//
        오름차순 정렬
        public int compareTo(Student compareStudent) {
            return this.id - compareStudent.id;
        }

        // 내림차순
        public int compareTo(Student compareStudent) {
            return compareStudent.id - id;
        }*/


        public int compareTo(Edge o) { //누적비용 서로 비교 (우선순위 비교)
            return Integer.compare(this.cumCost, o.cumCost); //누적비용 비교 (최소비용이므로 오름차순)
            //
        }
    }

    private static String src = "6 8\r\n" +
            "4 5 3\r\n" +
            "2 4 0\r\n" +
            "4 1 4\r\n" +
            "2 1 1\r\n" +
            "5 6 1\r\n" +
            "3 6 2\r\n" +
            "3 2 6\r\n" +
            "3 4 4";
}