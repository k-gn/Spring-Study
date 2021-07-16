package graph;

import java.util.*;

public class DijkstraTest {

    public static void main(String[] args) {

        int N = 5;
        List<Edge>[] path = new LinkedList[N];
        for(int i = 0; i < N; i++) {
            path[i] = new LinkedList<>();
        }
        path[0].add(new Edge(2, 1));
        path[0].add(new Edge(1, 4));
        path[1].add(new Edge(3, 1));
        path[2].add(new Edge(1, 2));
        path[4].add(new Edge(0, 2));
        path[4].add(new Edge(2, 3));
        path[4].add(new Edge(3, 7));
        new DijkstraTest().최단경로(4, path);
    }
    public int INF = Integer.MAX_VALUE;

    public void 최단경로(int start, List<Edge>[] path){
        int N = path.length;
        boolean[] visited = new boolean[N];
        int[] prev = new int[N];
        int[] dist = new int[N];
        Arrays.fill(prev,-1);
        Arrays.fill(dist,INF);

        dist[start] = 0;

        Queue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start,dist[start]));

        for(int i = 0; i < N; i++) {
            Edge minVertex = queue.poll();

            if(visited[minVertex.vertex]) continue;
            visited[minVertex.vertex] = true;

            for(Edge adj : path[minVertex.vertex] ){
                int newDist = dist[minVertex.vertex] + adj.weight;
                int current = dist[adj.vertex];
                if(!visited[adj.vertex] && newDist < current){
                    dist[adj.vertex] = newDist;
                    prev[adj.vertex] = minVertex.vertex;
                    queue.add(new Edge(adj.vertex,newDist));
                }
            }
        }

        System.out.println(Arrays.toString(dist));
        for(int i = 0; i < N; i++) {
            int k = i;
            List<Integer> order = new ArrayList<>();
            while(k != -1){
                order.add(k);
                k = prev[k];
            }
            Collections.reverse(order);
            for(int v : order ){
                System.out.print(v+" -> ");
            }
            System.out.println();
        }

    }
    private static class Edge implements Comparable<Edge>{
        int vertex;
        int weight;

        public Edge(int vertex,int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }
}